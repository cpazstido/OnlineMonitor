package com.hy.onlinemonitor.bean;

import java.io.Serializable;

/**
 * Created by wsw on 2015/7/15.
 */
public class AlarmInformation implements Serializable {
    private String alarmName;
    private String visibleLightImage;
    private String infraredImage;
    private String isBlowingEquipment;
    private String alarmInformation;
    private String videoFileName;
    private String alarmSN;
    private String isHandle;
    private int typeAlarm;

    public AlarmInformation() {
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

    public String getVideoFileName() {
        return videoFileName;
    }

    public void setVideoFileName(String videoFileName) {
        this.videoFileName = videoFileName;
    }

    public String getAlarmSN() {
        return alarmSN;
    }

    public void setAlarmSN(String alarmSN) {
        this.alarmSN = alarmSN;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AlarmInformation{");
        sb.append("alarmName='").append(alarmName).append('\'');
        sb.append(", visibleLightImage='").append(visibleLightImage).append('\'');
        sb.append(", infraredImage='").append(infraredImage).append('\'');
        sb.append(", isBlowingEquipment='").append(isBlowingEquipment).append('\'');
        sb.append(", alarmInformation='").append(alarmInformation).append('\'');
        sb.append(", videoFileName='").append(videoFileName).append('\'');
        sb.append(", alarmSN='").append(alarmSN).append('\'');
        sb.append(", isHandle='").append(isHandle).append('\'');
        sb.append(", typeAlarm=").append(typeAlarm);
        sb.append('}');
        return sb.toString();
    }
}
