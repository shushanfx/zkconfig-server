package com.shushanfx.zkconfig.server.zookeeper;

import com.shushanfx.zkconfig.server.util.JSONUtils;
import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.zookeeper.data.Stat;
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
    private String connectionPath = null;

    private ZkClient client = null;
    private String username = null;
    private String password = null;
    private String scheme = "digest";

    @PostConstruct
    public void init() {
        log.info(String.format("Init zookeeper server %s, timeout -> %d, path -> %s", servers, connectTimeout, path));
        client = new ZkClient(servers, connectTimeout);
        client.setZkSerializer(new ZNodeSerializer());
        if (username != null) {
            log.info(String.format("Auth zookeeper with %s:%s", username, password));
            client.addAuthInfo(scheme, (username + ":" + password).getBytes());
        }
        if (!client.exists(path)) {
            client.createPersistent(this.path, true);
        }
        if (!client.exists(connectionPath)) {
            client.createPersistent(this.connectionPath, true);
        }
    }

    public List<ZNode> getList(String name) {
        List<ZNode> list = new ArrayList<>();
        List<String> children = client.getChildren(this.path);
        String filter = StringUtils.isEmpty(name) ? null : name;

        if (children != null && children.size() > 0) {
            for (String item : children) {
                if (filter != null && !item.contains(filter)) {
                    continue;
                }
                ZNode node = getZNode(item, false);
                if (node != null) {
                    list.add(node);
                }
            }
        }
        if (list.size() > 0) {
            list.sort((a, b) -> {
                if (a.getModifiedTime() != null && b.getModifiedTime() != null) {
                    return (int) (b.getModifiedTime().getTime() - a.getModifiedTime().getTime());
                } else if (b.getModifiedTime() != null) {
                    return 1;
                } else if (a.getModifiedTime() != null) {
                    return -1;
                }
                return sortAlphabet(a.getName(), b.getName());
            });
        }

        return list;
    }

    public List<ZConnection> getConnectionList(String path, String ip) {
        List<ZConnection> connections = new ArrayList<>();
        if (connectionPath != null && client.exists(connectionPath)) {
            List<String> items = client.getChildren(connectionPath);
            for (String item : items) {
                String value = client.readData(join(connectionPath, item));
                if (value != null) {
                    ZConnection connection = JSONUtils.toObject(value, ZConnection.class);
                    if (connection != null) {
                        connections.add(connection);
                    }
                }
            }
        }

        return connections;
    }

    public int sortAlphabet(String str1, String str2) {
        int maxValue = Math.max(str1.length(), str2.length());
        for (int i = 0; i < maxValue; i++) {
            int ret = sortAlphabet(str1, str2, i);
            if (ret != 0) {
                return ret;
            }
        }
        return 0;
    }

    private static int sortAlphabet(String str1, String str2, int pos) {
        if (str1.length() > pos && str2.length() > pos) {
            return str2.charAt(pos) - str1.charAt(pos) > 0 ? 1 : -1;
        } else if (str2.length() > pos) {
            return 1;
        } else if (str1.length() > pos) {
            return -1;
        }
        return 0;
    }

    public static String join(String path, String... other) {
        Assert.notNull(path, "path cannot be null!");

        if (other != null && other.length > 0) {
            StringBuilder sb = new StringBuilder(path);
            for (int i = 0, size = other.length; i < size; i++) {
                sb.append("/").append(other[i]);
            }
            return sb.toString();
        }

        return path;
    }

    private ZNode getZNode(String name, boolean checkExist) {
        Assert.notNull(name, "name can not be null!");
        ZNode node = null;
        String newPath = join(path, name);
        if (checkExist && !client.exists(newPath)) {
            return node;
        }

        node = new ZNode();
        node.setName(name);

        Stat stat = new Stat();
        client.readData(newPath, stat);
        node.setSize(stat.getDataLength());
        node.setModifiedTime(new Date(stat.getMtime()));
        node.setCreatedTime(new Date(stat.getCtime()));

        List<String> list = client.getChildren(newPath);
        if (list != null && list.size() > 0) {
            for (String item : list) {
                if ("description".equalsIgnoreCase(item)) {
                    Object value = client.readData(join(newPath, item));
                    if (value == null) {
                        continue;
                    }
                    node.setDescription(value.toString());
                } else if ("creator".equalsIgnoreCase(item)) {
                    Object value = client.readData(join(newPath, item));
                    if (value == null) {
                        continue;
                    }
                    node.setCreator(value.toString());
                } else if ("modifier".equalsIgnoreCase(item)) {
                    Object value = client.readData(join(newPath, item));
                    if (value == null) {
                        continue;
                    }
                    node.setModifier(value.toString());
                } else if ("type".equalsIgnoreCase(item)) {
                    Object value = client.readData(join(newPath, item));
                    if (value == null) {
                        continue;
                    }
                    node.setType(value.toString());
                }
            }
        }
        return node;
    }

    public ZNode getContent(String name) {
        Assert.notNull(name, "name can not be null!");
        String newPath = join(this.path, name);
        ZNode node = null;
        if (client.exists(newPath)) {
            String content = client.readData(newPath);
            node = new ZNode();
            node.setName(name);
            node.setContent(content);
            String type = client.readData(join(newPath, "type"), true);
            if (!StringUtils.isEmpty(type)) {
                node.setType(type);
            }
            return node;
        }
        return node;
    }

    public ZNode getInfo(String name) {
        return getZNode(name, true);
    }

    public boolean saveInfo(String name, String description,
                            String type,
                            String username) {
        String newPath = join(this.path, name);
        if (!client.exists(newPath)) {
            client.createPersistent(newPath);
            client.createPersistent(join(newPath, "type"), type);
            client.createPersistent(join(newPath, "description"), description);
            client.createPersistent(join(newPath, "creator"), username);
            client.createPersistent(join(newPath, "modifier"), username);
        } else {
            saveOrUpdate(join(newPath, "description"), description);
            saveOrUpdate(join(newPath, "type"), type);
            saveOrUpdate(join(newPath, "modifier"), username);
        }

        return true;
    }

    public boolean saveContent(String name, String content, String username) {
        String newPath = join(this.path, name);
        if (client.exists(newPath)) {
            client.writeData(newPath, content);
            saveOrUpdate(join(newPath, "modifier"), username);
        }
        return true;
    }

    public boolean delete(String name) {
        String newPath = join(this.path, name);
        if (client.exists(newPath)) {
            client.deleteRecursive(newPath);
        }
        return true;
    }

    private void saveOrUpdate(String path, Object value) {
        Assert.notNull(path, "path can not be null.");

        if (client.exists(path)) {
            client.writeData(path, value);
        } else {
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

    public String getConnectionPath() {
        return connectionPath;
    }

    public void setConnectionPath(String connectionPath) {
        this.connectionPath = connectionPath;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }
}