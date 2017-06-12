package com.shushanfx.zkconfig.server.zookeeper;

import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by shushanfx on 17/六月/11.
 */
@Service
@ConfigurationProperties(prefix = "zookeeper")
public class ZNodeClient {
    private static final Log log = LogFactory.getLog(ZNodeClient.class);

    private int connectTimeout = 30000;
    private String servers = null;
    private String path = null;

    private ZkClient client = null;

    @PostConstruct
    public void init(){
        log.info(String.format("Init zookeeper server %s, timeout -> %d, path -> %s", servers, connectTimeout, path));

        client = new ZkClient(servers, connectTimeout);

        if(!client.exists(path)){
            client.createPersistent(this.path, true);
        }
    }

    public List<ZNode> getList(String name){
        List<ZNode> list = new ArrayList<>();
        List<String> children = client.getChildren(this.path);
        String filter = StringUtils.isEmpty(name) ? null : name;

        if(children!=null && children.size() > 0){
            for (String item :  children){
                if(filter!=null && !item.contains(filter)){
                    continue;
                }
                ZNode node = getZNode(item, false);
                if(node!=null){
                    list.add(node);
                }
            }
        }
        if(list.size() > 0){
            list.sort((a, b) -> {
                if(a.getModifiedTime()!=null && b.getModifiedTime()!=null){
                    return (int)(b.getModifiedTime().getTime() - a.getModifiedTime().getTime());
                }
                else if(b.getModifiedTime()!=null){
                    return 1;
                }
                else if(a.getModifiedTime() != null){
                    return -1;
                }
                return sortAlphabet(a.getName(), b.getName());
            });
        }

        return list;
    }

    public int sortAlphabet(String str1, String str2){
        int maxValue = Math.max(str1.length(), str2.length());
        for(int i =0; i< maxValue; i++){
            int ret = sortAlphabet(str1, str2, i);
            if(ret != 0){
                return ret;
            }
        }
        return 0;
    }

    private static int sortAlphabet(String str1, String str2, int pos){
        if(str1.length() > pos && str2.length() > pos){
            return str2.charAt(pos) - str1.charAt(pos) > 0 ? 1 : -1;
        }
        else if(str2.length() > pos){
            return 1;
        }
        else if(str1.length() > pos){
            return -1;
        }
        return 0;
    }

    public static String join(String path, String ... other){
        Assert.notNull(path, "path cannot be null!");

        if(other!=null && other.length > 0){
            StringBuilder sb = new StringBuilder(path);

            for(int i = 0, size = other.length; i < size; i++){
                sb.append("/").append(other[i]);
            }

            return sb.toString();
        }

        return path;
    }

    private ZNode getZNode(String name, boolean checkExist){
        Assert.notNull(name, "name can not be null!");
        ZNode node = null;
        String newPath = join(path, name);
        if(checkExist && !client.exists(newPath)){
            return node;
        }

        node = new ZNode();
        node.setName(name);

        List<String> list = client.getChildren(newPath);
        if(list!=null && list.size() > 0){
            for(String item : list){
                if("description".equalsIgnoreCase(item)){
                    Object value = client.readData(join(newPath, item));
                    if(value == null){
                        continue;
                    }
                    node.setDescription(value.toString());
                }
                else if("creator".equalsIgnoreCase(item)){
                    Object value = client.readData(join(newPath, item));
                    if(value == null){
                        continue;
                    }
                    node.setCreator(value.toString());
                }
                else if("createdTime".equalsIgnoreCase(item)){
                    Object value = client.readData(join(newPath, item));
                    if(value == null){
                        continue;
                    }
                    node.setCreatedTime(new Date((long)value));
                }
                else if("modifier".equalsIgnoreCase(item)){
                    Object value = client.readData(join(newPath, item));
                    if(value == null){
                        continue;
                    }
                    node.setModifier(value.toString());
                }
                else if("modifiedTime".equalsIgnoreCase(item)){
                    Object value = client.readData(join(newPath, item));
                    if(value == null){
                        continue;
                    }
                    node.setModifiedTime(new Date((long) value));
                }
            }
        }
        return node;
    }

    public ZNode getContent(String name){
        Assert.notNull(name, "name can not be null!");
        String newPath = join(this.path, name);
        ZNode node = null;
        if(client.exists(newPath)){
            String content = client.readData(newPath);
            node = new ZNode();
            node.setName(name);
            node.setContent(content);
            return node;
        }
        return node;
    }

    public ZNode getInfo(String name){
        return getZNode(name, true);
    }

    public boolean saveInfo(String name, String description, String username){
        String newPath = join(this.path,name);
        if(!client.exists(newPath)){
            client.createPersistent(newPath);
            client.createPersistent(join(newPath, "description"), description);
            client.createPersistent(join(newPath, "creator"), username);
            client.createPersistent(join(newPath, "createdTime"), System.currentTimeMillis());
            client.createPersistent(join(newPath, "modifier"), username);
            client.createPersistent(join(newPath, "modifiedTime"), System.currentTimeMillis());
        }
        else{
            saveOrUpdate(join(newPath, "description"), description);
            saveOrUpdate(join(newPath, "modifier"), username);
            saveOrUpdate(join(newPath, "modifiedTime"), System.currentTimeMillis());
        }

        return true;
    }

    public boolean saveContent(String name, String content, String username){
        String newPath = join(this.path, name);
        if(client.exists(newPath)){
            client.writeData(newPath, content);
            saveOrUpdate(join(newPath, "modifier"), username);
            saveOrUpdate(join(newPath, "modifiedTime"), System.currentTimeMillis());
        }
        return true;
    }

    public boolean delete(String name){
        String newPath = join(this.path, name);
        if(client.exists(newPath)){
            client.deleteRecursive(newPath);
        }
        return true;
    }

    private void saveOrUpdate(String path, Object value){
        Assert.notNull(path, "path can not be null.");

        if(client.exists(path)){
            client.writeData(path, value);
        }
        else{
            client.createPersistent(path, value);
        }
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public String getServers() {
        return servers;
    }

    public void setServers(String servers) {
        this.servers = servers;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
