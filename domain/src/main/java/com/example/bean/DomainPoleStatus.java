package com.example.bean;

/**
 * Created by 24363 on 2015/10/28.
 */
public class DomainPoleStatus {
    private int sn;
    private int deviceSn;
    private String sensorName;
    private String collectDataTimeStr;//采集时间
    private double angleX;
    private double angleY;

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public int getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(int deviceSn) {
        this.deviceSn = deviceSn;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getCollectDataTimeStr() {
        return collectDataTimeStr;
    }

    public void setCollectDataTimeStr(String collectDataTimeStr) {
        this.collectDataTimeStr = collectDataTimeStr;
    }

    public double getAngleX() {
        return angleX;
    }

    public void setAngleX(double angleX) {
        this.angleX = angleX;
    }

    public double getAngleY() {
        return angleY;
    }

    public void setAngleY(double angleY) {
        this.angleY = angleY;
    }
}
