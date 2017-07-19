package com.shushanfx.zkconfig.server.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ZK的配置信息
 * Created by dengjianxin on 2017/7/19.
 */
@Repository
@ConfigurationProperties(prefix = "zkconfig")
public class ZkConfiguration {
    private List<String> typeList = null;
    private Map<String, String> contentDefault = null;

    public ZkConfiguration() {
        typeList = new ArrayList<>();
        contentDefault = new HashMap<>();
    }

    public List<String> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<String> typeList) {
        this.typeList = typeList;
    }

    public Map<String, String> getContentDefault() {
        return contentDefault;
    }

    public void setContentDefault(Map<String, String> contentDefault) {
        this.contentDefault = contentDefault;
    }

    public String getDefaultContent(String type){
        if(contentDefault!=null){
            String content = contentDefault.get(type);
            if(content!=null){
                return content;
            }
        }
        return "";
    }
}
