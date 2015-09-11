package com.hy.data.entity;

/**
 * Created by 24363 on 2015/9/11.
 */
public class SensorEntity {
    private int sn;
    private String name; //传感器名称
    private String info; //传感器标识
    private int count;
    private String sensorInDeviceID;

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSensorInDeviceID() {
        return sensorInDeviceID;
    }

    public void setSensorInDeviceID(String sensorInDeviceID) {
        this.sensorInDeviceID = sensorInDeviceID;
    }
}
