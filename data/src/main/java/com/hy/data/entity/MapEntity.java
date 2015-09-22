package com.hy.data.entity;

public class MapEntity {
    private double lng;
    private double lat;
    private String deviceName;
    private int dvrID;
    private String dvrType;
    private String poleName;
    private int equipmentSn;
    public MapEntity() {
    }

    public int getEquipmentSn() {
        return equipmentSn;
    }

    public void setEquipmentSn(int equipmentSn) {
        this.equipmentSn = equipmentSn;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
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
