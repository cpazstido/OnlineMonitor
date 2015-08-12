package com.hy.onlinemonitor.bean;

/**
 * Created by wsw on 2015/7/15.
 */
public class EquipmentAlarmInformation {
    private String equipmnetName;
    private String equipmnetState;
    private int SN;
    private int newFireAlarm;
    private int newBreakAlarm;
    private int newSensorAlarm;

    public EquipmentAlarmInformation(String equipmnetName, String equipmnetState, int newFireAlarm, int newBreakAlarm, int newSensorAlarm) {
        this.equipmnetName = equipmnetName;
        this.equipmnetState = equipmnetState;
        this.newFireAlarm = newFireAlarm;
        this.newBreakAlarm = newBreakAlarm;
        this.newSensorAlarm = newSensorAlarm;
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

    @Override
    public String toString() {
        return "EquipmnetAlarmInformation{" +
                "equipmnetName='" + equipmnetName + '\'' +
                ", equipmnetState='" + equipmnetState + '\'' +
                ", SN=" + SN +
                ", newFireAlarm=" + newFireAlarm +
                ", newBreakAlarm=" + newBreakAlarm +
                ", newSensorAlarm=" + newSensorAlarm +
                '}';
    }
}
