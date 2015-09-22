package com.example.bean;

/**
 * Created by wsw on 2015/8/8.
 */
public class DomainUser {

    private String companyName;
    private int userId;
    private String OwnedEquipment;
    private int selectionType;
    private int id;
    private int roleSn; //角色sn,用于权限

    public DomainUser() {
        //empty
    }

    public int getRoleSn() {
        return roleSn;
    }

    public void setRoleSn(int roleSn) {
        this.roleSn = roleSn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwnedEquipment() {
        return OwnedEquipment;
    }

    public void setOwnedEquipment(String ownedEquipment) {
        OwnedEquipment = ownedEquipment;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getSelectionType() {
        return selectionType;
    }

    public void setSelectionType(int selectionType) {
        this.selectionType = selectionType;
    }
}
