package com.example.bean;

import java.util.List;

public class DomainPole {
    private String poleName;
    private int poleSn;
    private String longitude;
    private String latitude;
    private String altitude;
    private List<DomainEquipment> equipmentList;

    public DomainPole() {
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

    public List<DomainEquipment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<DomainEquipment> equipmentList) {
        this.equipmentList = equipmentList;
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
}
