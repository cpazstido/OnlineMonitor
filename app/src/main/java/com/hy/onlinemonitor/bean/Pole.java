package com.hy.onlinemonitor.bean;

import java.util.List;

/**
 * Created by 24363 on 2015/9/2.
 */
public class Pole {
    private int poleSn;
    private String poleName;
    private String longitude;
    private String latitude;
    private String altitude;
    private List<Equipment> equipmentList;
    public Pole() {
    }

    public String getPoleName() {
        return poleName;
    }

    public void setPoleName(String poleName) {
        this.poleName = poleName;
    }

    public int getPoleSn() {
        return poleSn;
    }

    public void setPoleSn(int poleSn) {
        this.poleSn = poleSn;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }
}
