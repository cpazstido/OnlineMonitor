package com.example.bean;

/**
 * Created by 24363 on 2015/8/18.
 */
public class DomainAlarmInformation {
    private String alarmName;
    private String visibleLightImage;
    private String infraredImage;
    private String isBlowingEquipment;
    private String alarmInformation;
    private String isHandle;
    private int typeAlarm;

    public DomainAlarmInformation() {
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
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

    public String getIsBlowingEquipment() {
        return isBlowingEquipment;
    }

    public void setIsBlowingEquipment(String isBlowingEquipment) {
        this.isBlowingEquipment = isBlowingEquipment;
    }

    public String getAlarmInformation() {
        return alarmInformation;
    }

    public void setAlarmInformation(String alarmInformation) {
        this.alarmInformation = alarmInformation;
    }

    public String getIsHandle() {
        return isHandle;
    }

    public void setIsHandle(String isHandle) {
        this.isHandle = isHandle;
    }

    public int getTypeAlarm() {
        return typeAlarm;
    }

    public void setTypeAlarm(int typeAlarm) {
        this.typeAlarm = typeAlarm;
    }
}
