package com.hy.onlinemonitor.bean;

/**
 * Created by 24363 on 2015/9/14.
 */
public class SensorType {
    private int sn;
    private String sensorName;
    private String sensorMarke;
    private String sensorID;

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getSensorMarke() {
        return sensorMarke;
    }

    public void setSensorMarke(String sensorMarke) {
        this.sensorMarke = sensorMarke;
    }

    public String getSensorID() {
        return sensorID;
    }

    public void setSensorID(String sensorID) {
        this.sensorID = sensorID;
    }
}
