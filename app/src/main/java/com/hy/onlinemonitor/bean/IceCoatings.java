package com.hy.onlinemonitor.bean;

import java.io.Serializable;

public class IceCoatings implements Serializable{
    private int sn;
    private int deviceSn;
    private String collectDataTimeStr;//数据采集时间
    private double deflectionAngle;//绝缘子串偏斜角(°)
    private double equalIceThickness;//等值覆冰厚度(mm)
    private String sensorName;//传感器编号
    private double tension;//综合悬挂载荷(N)
    private double tensionDifference;//不均衡张力差(N)
    private double windageYawAngle;//绝缘子串风偏角(°)

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
