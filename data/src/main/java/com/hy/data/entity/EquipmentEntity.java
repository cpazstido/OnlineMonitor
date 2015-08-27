package com.hy.data.entity;

/**
 * Created by 24363 on 2015/8/13.
 */
public class EquipmentEntity {
    private String equipmnetName;
    private String equipmnetState;
    private int SN;
    private int newFireAlarm;
    private int newBreakAlarm;
    private int newSensorAlarm;
    private int dvrId;
    private String dvrType;

    public EquipmentEntity() {
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

    public int getSN() {
        return SN;
    }

    public void setSN(int SN) {
        this.SN = SN;
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
