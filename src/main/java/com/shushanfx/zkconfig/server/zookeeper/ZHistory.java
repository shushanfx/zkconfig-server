package com.shushanfx.zkconfig.server.zookeeper;

import com.shushanfx.zkconfig.server.util.SizeUtils;

/**
 * Created by dengjianxin on 2017/6/20.
 */
public class ZHistory {
    private String id = null;
    private String name = null;
    private String content = null;
    private long createdTime = 0L;
    private String username = null;
    private int size = 0;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSizeString(){
        return SizeUtils.getSizeString(this.size);
    }
}
