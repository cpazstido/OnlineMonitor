package com.hy.data.entity;

import java.util.List;

/**
 * Created by 24363 on 2015/9/7.
 */
public class EquipmentEntity {
    private int sn;
    private String deviceID; //监测设备编码
    private String dvrID;
    private String deviceType;//设备类型
    private String dvrType;//用与接入服务器通信时表示设备类型，1为山火，2为外破，3为无人机，4为普通视频
    private int sensorType; //被监测设备类型
    private Double angleRelativeToNorthPole;//预置位0度相对于北极夹角
    private Integer sendMmsState; // 该设备是否发送彩信，0不发送，1发送
    private String cma_ID;
    private String sensor_ID;
    private String equipment_ID;
    private List<SensorEntity> sensorInDeviceSet;

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getDvrID() {
        return dvrID;
    }

    public void setDvrID(String dvrID) {
        this.dvrID = dvrID;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDvrType() {
        return dvrType;
    }

    public void setDvrType(String dvrType) {
        this.dvrType = dvrType;
    }

    public int getSensorType() {
        return sensorType;
    }

    public void setSensorType(int sensorType) {
        this.sensorType = sensorType;
    }

    public Double getAngleRelativeToNorthPole() {
        return angleRelativeToNorthPole;
    }

    public void setAngleRelativeToNorthPole(Double angleRelativeToNorthPole) {
        this.angleRelativeToNorthPole = angleRelativeToNorthPole;
    }

    public Integer getSendMmsState() {
        return sendMmsState;
    }

    public void setSendMmsState(Integer sendMmsState) {
        this.sendMmsState = sendMmsState;
    }

    public String getCma_ID() {
        return cma_ID;
    }

    public void setCma_ID(String cma_ID) {
        this.cma_ID = cma_ID;
    }

    public String getSensor_ID() {
        return sensor_ID;
    }

    public void setSensor_ID(String sensor_ID) {
        this.sensor_ID = sensor_ID;
    }

    public String getEquipment_ID() {
        return equipment_ID;
    }

    public void setEquipment_ID(String equipment_ID) {
        this.equipment_ID = equipment_ID;
    }

    public List<SensorEntity> getSensorInDeviceSet() {
        return sensorInDeviceSet;
    }

    public void setSensorInDeviceSet(List<SensorEntity> sensorInDeviceSet) {
        this.sensorInDeviceSet = sensorInDeviceSet;
    }
}
