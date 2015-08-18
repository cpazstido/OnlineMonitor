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

    public EquipmentEntity(String equipmnetName, String equipmnetState, int SN, int newFireAlarm, int newBreakAlarm, int newSensorAlarm) {
        this.equipmnetName = equipmnetName;
        this.equipmnetState = equipmnetState;
        this.SN = SN;
        this.newFireAlarm = newFireAlarm;
        this.newBreakAlarm = newBreakAlarm;
        this.newSensorAlarm = newSensorAlarm;
    }

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
}
