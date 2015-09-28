package com.hy.data.entity;

/**
 * Created by 24363 on 2015/8/18.
 */
public class AlarmEntity {
    private int alarmSn;
    private int deviceSn;
    private String poleName;
    private String deviceId;
    private String dvrID;
    private String dvrType;
    private String alarmInformation;
    private String visibleLightImage;
    private String infraredImage;
    private String brokenImage;
    private String isBlowingEquipment;
    private String videoFileName;
    private String collectionTime;
    private String circuitName;

    public AlarmEntity() {
    }

    public String getDvrType() {
        return dvrType;
    }

    public void setDvrType(String dvrType) {
        this.dvrType = dvrType;
    }

    public String getCircuitName() {
        return circuitName;
    }

    public void setCircuitName(String circuitName) {
        this.circuitName = circuitName;
    }

    public int getAlarmSn() {
        return alarmSn;
    }

    public void setAlarmSn(int alarmSn) {
        this.alarmSn = alarmSn;
    }

    public int getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(int deviceSn) {
        this.deviceSn = deviceSn;
    }

    public String getPoleName() {
        return poleName;
    }

    public void setPoleName(String poleName) {
        this.poleName = poleName;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDvrID() {
        return dvrID;
    }

    public void setDvrID(String dvrID) {
        this.dvrID = dvrID;
    }

    public String getAlarmInformation() {
        return alarmInformation;
    }

    public void setAlarmInformation(String alarmInformation) {
        this.alarmInformation = alarmInformation;
    }

    public String getVisibleLightImage() {
        return visibleLightImage;
    }

    public void setVisibleLightImage(String visibleLightImage) {
        this.visibleLightImage = visibleLightImage;
    }

    public String getInfraredImage() {
        return infraredImage;
    }

    public void setInfraredImage(String infraredImage) {
        this.infraredImage = infraredImage;
    }

    public String getBrokenImage() {
        return brokenImage;
    }

    public void setBrokenImage(String brokenImage) {
        this.brokenImage = brokenImage;
    }

    public String getIsBlowingEquipment() {
        return isBlowingEquipment;
    }

    public void setIsBlowingEquipment(String isBlowingEquipment) {
        this.isBlowingEquipment = isBlowingEquipment;
    }

    public String getVideoFileName() {
        return videoFileName;
    }

    public void setVideoFileName(String videoFileName) {
        this.videoFileName = videoFileName;
    }

    public String getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(String collectionTime) {
        this.collectionTime = collectionTime;
    }
}
