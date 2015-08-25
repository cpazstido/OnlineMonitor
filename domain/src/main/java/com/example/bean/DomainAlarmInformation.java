package com.example.bean;

/**
 * Created by 24363 on 2015/8/18.
 */
public class DomainAlarmInformation {
    private int alarmSn;
    private int deviceSn;
    private String poleName;
    private String deviceId;
    private String dvrID;
    private String alarmInformation;
    private String visibleLightImage;
    private String infraredImage;
    private String breakImage;
    private String isBlowingEquipment;
    private String videoFileName;
    private String CollectionTime;

    public DomainAlarmInformation() {
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

    public String getBreakImage() {
        return breakImage;
    }

    public void setBreakImage(String breakImage) {
        this.breakImage = breakImage;
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
        return CollectionTime;
    }

    public void setCollectionTime(String collectionTime) {
        CollectionTime = collectionTime;
    }
}
