package com.shushanfx.zkconfig.server.zookeeper;

import java.util.Date;

/**
 * Created by shushanfx on 17/六月/11.
 */
public class ZNode {
    private String name;
    private String content;
    private String description;
    private String type = "properties";
    private int size = 0;

    private String creator = null;
    private Date createdTime = null;
    private String modifier = null;
    private Date modifiedTime = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSizeString(){
        if(size <= 0){
            return "EMPTY";
        }
        else if(size < 1024){
            return String.format("%db", size);
        }
        else if(size < 1024 * 1024){
            return String.format("%.2fk", size / 1024.0);
        }
        else if(size < 1024 * 1024 * 1024){
            return String.format("%.2fM", size / 1024.0 / 1024.0);
        }
        else if(size < 1024 * 1024 * 1024 * 1024){
            return String.format("%.2fG", size / 1024.0 / 1024.0 / 1024.0);
        }
        return "TOO LARGE";
    }
}
