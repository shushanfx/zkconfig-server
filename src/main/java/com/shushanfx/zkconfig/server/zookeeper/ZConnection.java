package com.shushanfx.zkconfig.server.zookeeper;

/**
 * Created by shushanfx on 2017/6/13.
 */
public class ZConnection {
    private String id = null;
    private String path = null;
    private long connectedTime = 0L;
    private String ip = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getConnectedTime() {
        return connectedTime;
    }

    public void setConnectedTime(long connectTime) {
        this.connectedTime = connectTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
