package com.hy.onlinemonitor.bean;


import java.io.Serializable;

public class EquipmentInformation implements Serializable {
    private int sn;
    private String equipmnetState;
    private String equipmnetName;
    private String lineName;
    private String poleName;
    private int dvrId;
    private String dvrType;
    private int newFireAlarm;
    private int newBreakAlarm;
    private int newSensorAlarm;

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public String getEquipmnetState() {
        return equipmnetState;
    }

    public void setEquipmnetState(String equipmnetState) {
        this.equipmnetState = equipmnetState;
    }

    public String getEquipmnetName() {
        return equipmnetName;
    }

    public void setEquipmnetName(String equipmnetName) {
        this.equipmnetName = equipmnetName;
    }

    public int getDvrId() {
        return dvrId;
    }

    public void setDvrId(int dvrId) {
        this.dvrId = dvrId;
    }

    public String getDvrType() {
        return dvrType;
    }

    public void setDvrType(String dvrType) {
        this.dvrType = dvrType;
    }

    public int getNewFireAlarm() {
        return newFireAlarm;
    }

    public void setNewFireAlarm(int newFireAlarm) {
        this.newFireAlarm = newFireAlarm;
    }

    public int getNewBreakAlarm() {
        return newBreakAlarm;
    }

    public void setNewBreakAlarm(int newBreakAlarm) {
        this.newBreakAlarm = newBreakAlarm;
    }

    public int getNewSensorAlarm() {
        return newSensorAlarm;
    }

    public void setNewSensorAlarm(int newSensorAlarm) {
        this.newSensorAlarm = newSensorAlarm;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getPoleName() {
        return poleName;
    }

    public void setPoleName(String poleName) {
        this.poleName = poleName;
    }
}
