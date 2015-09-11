package com.hy.onlinemonitor.bean;

/**
 * Created by 24363 on 2015/9/7.
 */
public class Sensor {
    private String SensorName;
    private int sensorNumber;
    private int type;

    public Sensor() {
    }

    public String getSensorName() {
        return SensorName;
    }

    public void setSensorName(String sensorName) {
        SensorName = sensorName;
    }

    public int getSensorNumber() {
        return sensorNumber;
    }

    public void setSensorNumber(int sensorNumber) {
        this.sensorNumber = sensorNumber;
    }
}
