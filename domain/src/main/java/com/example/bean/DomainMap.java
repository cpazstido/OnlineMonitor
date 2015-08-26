package com.example.bean;

/**
 * Created by 24363 on 2015/8/19.
 */
public class DomainMap {
    private double Longitude;
    private double Latitude;
    private String EquipmentName;
    private int dvrID;
    private String dvrType;
    private String poleName;

    public DomainMap() {
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public String getEquipmentName() {
        return EquipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        EquipmentName = equipmentName;
    }

    public int getDvrID() {
        return dvrID;
    }

    public void setDvrID(int dvrID) {
        this.dvrID = dvrID;
    }

    public String getDvrType() {
        return dvrType;
    }

    public void setDvrType(String dvrType) {
        this.dvrType = dvrType;
    }

    public String getPoleName() {
        return poleName;
    }

    public void setPoleName(String poleName) {
        this.poleName = poleName;
    }
}
