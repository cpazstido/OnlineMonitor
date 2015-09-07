package com.hy.onlinemonitor.bean;

import java.util.List;

/**
 * Created by 24363 on 2015/9/7.
 */
public class Equipment {
    int equipmentSn;
    String identifier;
    String deviceDvrID;
    String deviceType;
    String devicePreset;
    String deviceCmaid;
    String deviceSensorid;
    String deviceEeqmenid;
    String isSendAlarmInfromation;
    List<Sensor> sensorList;

    public Equipment() {
    }

    public int getEquipmentSn() {
        return equipmentSn;
    }

    public void setEquipmentSn(int equipmentSn) {
        this.equipmentSn = equipmentSn;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getDeviceDvrID() {
        return deviceDvrID;
    }

    public void setDeviceDvrID(String deviceDvrID) {
        this.deviceDvrID = deviceDvrID;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDevicePreset() {
        return devicePreset;
    }

    public void setDevicePreset(String devicePreset) {
        this.devicePreset = devicePreset;
    }

    public String getDeviceCmaid() {
        return deviceCmaid;
    }

    public void setDeviceCmaid(String deviceCmaid) {
        this.deviceCmaid = deviceCmaid;
    }

    public String getDeviceSensorid() {
        return deviceSensorid;
    }

    public void setDeviceSensorid(String deviceSensorid) {
        this.deviceSensorid = deviceSensorid;
    }

    public String getDeviceEeqmenid() {
        return deviceEeqmenid;
    }

    public void setDeviceEeqmenid(String deviceEeqmenid) {
        this.deviceEeqmenid = deviceEeqmenid;
    }

    public String getIsSendAlarmInfromation() {
        return isSendAlarmInfromation;
    }

    public void setIsSendAlarmInfromation(String isSendAlarmInfromation) {
        this.isSendAlarmInfromation = isSendAlarmInfromation;
    }

    public List<Sensor> getSensorList() {
        return sensorList;
    }

    public void setSensorList(List<Sensor> sensorList) {
        this.sensorList = sensorList;
    }
}
