package com.shushanfx.zkconfig.server.zookeeper;

/**
 * Created by dengjianxin on 2017/6/20.
 */
public enum ZNodeType {
    PROPERTIES("properties"),
    JSON("json"),
    YAML("yaml");

    private String name = null;
    ZNodeType(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
