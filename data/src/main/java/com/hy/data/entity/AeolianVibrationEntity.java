package com.hy.data.entity;

import java.io.Serializable;

/**
 * Created by 24363 on 2015/10/28.
 */
public class AeolianVibrationEntity implements Serializable {
    private String collectDataTimeStr;//数据收集时间
    private int deviceSn;
    private double dynamicBendingStrain;//动弯应变
    private String sensorName;//传感器编号
    private int sn;
    private double vibrationAmplitude;//振动幅值(mm)
    private double vibrationFrequencyround;//振动频率(Hz)

    public String getCollectDataTimeStr() {
        return collectDataTimeStr;
    }

    public void setCollectDataTimeStr(String collectDataTimeStr) {
        this.collectDataTimeStr = collectDataTimeStr;
    }

    public int getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(int deviceSn) {
        this.deviceSn = deviceSn;
    }

    public double getDynamicBendingStrain() {
        return dynamicBendingStrain;
    }

    public void setDynamicBendingStrain(double dynamicBendingStrain) {
        this.dynamicBendingStrain = dynamicBendingStrain;
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

    public double getVibrationAmplitude() {
        return vibrationAmplitude;
    }

    public void setVibrationAmplitude(double vibrationAmplitude) {
        this.vibrationAmplitude = vibrationAmplitude;
    }

    public double getVibrationFrequencyround() {
        return vibrationFrequencyround;
    }

    public void setVibrationFrequencyround(double vibrationFrequencyround) {
        this.vibrationFrequencyround = vibrationFrequencyround;
    }
}
