package com.hy.onlinemonitor.bean;

import java.io.Serializable;

/**
 * Created by wsw on 2015/7/15.
 */
public class EquipmentInformation implements Serializable{
    private int SN;
    private String equipmnetState;
    private String equipmnetName;
    private int dvrId;
    private String dvrType;
    private int newFireAlarm;
    private int newBreakAlarm;
    private int newSensorAlarm;

    public EquipmentInformation() {
    }

    public int getSN() {
        return SN;
    }

    public void setSN(int SN) {
        this.SN = SN;
    }

    public String getEquipmnetName() {
        return equipmnetName;
    }

    public void setEquipmnetName(String equipmnetName) {
        this.equipmnetName = equipmnetName;
    }

    public String getEquipmnetState() {
        return equipmnetState;
    }

    public void setEquipmnetState(String equipmnetState) {
        this.equipmnetState = equipmnetState;
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
}
