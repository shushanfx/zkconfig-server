package com.shushanfx.zkconfig.server.zookeeper;

/**
 * Created by shushanfx on 2017/6/20.
 */
public enum ZConfigType {
    PROPERTIES("properties"),
    JSON("json"),
    YAML("yaml");

    private String name = null;
    ZConfigType(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
