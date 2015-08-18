package com.hy.data.entity;

/**
 * Created by 24363 on 2015/8/18.
 */
public class AlarmEntity {
    private String alarmName;
    private String visibleLightImage;
    private String infraredImage;
    private String isBlowingEquipment;
    private String alarmInformation;
    private String isHandle;
    private int typeAlarm;

    public AlarmEntity() {
    }

    public AlarmEntity(String alarmName, String visibleLightImage, String infraredImage, String isBlowingEquipment, String alarmInformation, String isHandle, int typeAlarm) {
        this.alarmName = alarmName;
        this.visibleLightImage = visibleLightImage;
        this.infraredImage = infraredImage;
        this.isBlowingEquipment = isBlowingEquipment;
        this.alarmInformation = alarmInformation;
        this.isHandle = isHandle;
        this.typeAlarm = typeAlarm;
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
