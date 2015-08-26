package com.hy.onlinemonitor.bean;

import java.io.Serializable;

/**
 * Created by wsw on 2015/7/18.
 */
public class Map implements Serializable{
    private double Longitude;
    private double Latitude;
    private String EquipmentName;
    private String dvrType;
    private int dvrID;
    private String poleName;

    public Map() {
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

    public String getDvrType() {
        return dvrType;
    }

    public void setDvrType(String dvrType) {
        this.dvrType = dvrType;
    }

    public int getDvrID() {
        return dvrID;
    }

    public void setDvrID(int dvrID) {
        this.dvrID = dvrID;
    }

    public String getPoleName() {
        return poleName;
    }

    public void setPoleName(String poleName) {
        this.poleName = poleName;
    }
}
