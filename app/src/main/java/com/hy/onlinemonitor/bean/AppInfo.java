package com.hy.onlinemonitor.bean;

import java.io.Serializable;

/**
 * Created by 24363 on 2015/9/29.
 */
public class AppInfo implements Serializable {
    private long size;
    private String serverVersion;

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getServerVersion() {
        return serverVersion;
    }

    public void setServerVersion(String serverVersion) {
        this.serverVersion = serverVersion;
    }
}
