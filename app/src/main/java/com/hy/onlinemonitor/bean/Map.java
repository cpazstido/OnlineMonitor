package com.hy.onlinemonitor.bean;

/**
 * Created by wsw on 2015/7/18.
 */
public class Map {
    private double Longitude;
    private double Latitude;
    private String EquipmentName;
    private String EquipmentId;
    private int SN;

    public Map() {
    }

    public Map(double latitude, double longitude, String equipmentName, int sN) {
        Longitude = longitude;
        Latitude = latitude;
        EquipmentName = equipmentName;
        SN = sN;
    }

    public int getSN() {
        return SN;
    }

    public void setSN(int SN) {
        this.SN = SN;
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

    public String getEquipmentId() {
        return EquipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        EquipmentId = equipmentId;
    }
}
