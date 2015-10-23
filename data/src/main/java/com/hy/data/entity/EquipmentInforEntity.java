package com.hy.data.entity;

/**
 * Created by 24363 on 2015/8/13.
 */
public class EquipmentInforEntity {
    private String equipmnetName;
    private String equipmnetState;
    private String circuitName;
    private String poleName;
    private int sn;
    private int newFireAlarm;
    private int newBreakAlarm;
    private int newSensorAlarm;
    private int historyFireAlarm;
    private int historyBreakAlarm;
    private int historySensorAlarm;
    private int dvrID;
    private String dvrType;

    public EquipmentInforEntity() {
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

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
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

    public String getCircuitName() {
        return circuitName;
    }

    public void setCircuitName(String circuitName) {
        this.circuitName = circuitName;
    }

    public String getPoleName() {
        return poleName;
    }

    public void setPoleName(String poleName) {
        this.poleName = poleName;
    }

    public int getHistoryFireAlarm() {
        return historyFireAlarm;
    }

    public void setHistoryFireAlarm(int historyFireAlarm) {
        this.historyFireAlarm = historyFireAlarm;
    }

    public int getHistoryBreakAlarm() {
        return historyBreakAlarm;
    }

    public void setHistoryBreakAlarm(int historyBreakAlarm) {
        this.historyBreakAlarm = historyBreakAlarm;
    }

    public int getHistorySensorAlarm() {
        return historySensorAlarm;
    }

    public void setHistorySensorAlarm(int historySensorAlarm) {
        this.historySensorAlarm = historySensorAlarm;
    }
}
