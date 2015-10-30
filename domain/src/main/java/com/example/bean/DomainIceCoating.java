package com.example.bean;

import java.io.Serializable;

public class DomainIceCoating implements Serializable{
    private int sn;
    private int deviceSn;
    private String collectDataTimeStr;//数据采集时间
    private double deflectionAngle;
    private double equalIceThickness;
    private String sensorName;
    private double tension;//综合悬挂载荷(N)
    private double tensionDifference;//不均衡张力差(N)
    private double windageYawAngle;

    public String getCollectDataTimeStr() {
        return collectDataTimeStr;
    }

    public void setCollectDataTimeStr(String collectDataTimeStr) {
        this.collectDataTimeStr = collectDataTimeStr;
    }

    public double getDeflectionAngle() {
        return deflectionAngle;
    }

    public void setDeflectionAngle(double deflectionAngle) {
        this.deflectionAngle = deflectionAngle;
    }

    public double getEqualIceThickness() {
        return equalIceThickness;
    }

    public void setEqualIceThickness(double equalIceThickness) {
        this.equalIceThickness = equalIceThickness;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public double getTension() {
        return tension;
    }

    public void setTension(double tension) {
        this.tension = tension;
    }

    public double getTensionDifference() {
        return tensionDifference;
    }

    public void setTensionDifference(double tensionDifference) {
        this.tensionDifference = tensionDifference;
    }

    public int getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(int deviceSn) {
        this.deviceSn = deviceSn;
    }

    public double getWindageYawAngle() {
        return windageYawAngle;
    }

    public void setWindageYawAngle(double windageYawAngle) {
        this.windageYawAngle = windageYawAngle;
    }
}
