package com.example.bean;

import java.util.List;

public class DomainRole {
    private int sn;
    private String roleName;
    private List<Integer> snList;
    private List<DomainEquipment> equipmentList;

    public DomainRole() {
    }

    public List<Integer> getSnList() {
        return snList;
    }

    public void setSnList(List<Integer> snList) {
        this.snList = snList;
    }

    public List<DomainEquipment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<DomainEquipment> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
