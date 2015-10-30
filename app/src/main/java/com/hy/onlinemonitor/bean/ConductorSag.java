package com.hy.onlinemonitor.bean;

import java.io.Serializable;

/**
 * Created by 24363 on 2015/10/28.
 */
public class ConductorSag  implements Serializable {

    private int sn;
    private int deviceSn;
    private String sensorName;//传感器编号
    private String collectDataTimeStr;//采集时间
    private double sag;//导线弧垂(m)
    private double angleabs;//线夹出口处导线切线与水平线夹角(°)
    private String testmeasure;//测量方法

    public String getCollectDataTimeStr() {
        return collectDataTimeStr;
    }

    public void setCollectDataTimeStr(String collectDataTimeStr) {
        this.collectDataTimeStr = collectDataTimeStr;
    }

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

    public double getSag() {
        return sag;
    }

    public void setSag(double sag) {
        this.sag = sag;
    }

    public double getAngleabs() {
        return angleabs;
    }

    public void setAngleabs(double angleabs) {
        this.angleabs = angleabs;
    }

    public String getTestmeasure() {
        return testmeasure;
    }

    public void setTestmeasure(String testmeasure) {
        this.testmeasure = testmeasure;
    }
}
