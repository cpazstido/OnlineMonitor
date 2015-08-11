package com.example.bean;

/**
 * Created by wsw on 2015/8/8.
 */
public class DomainUser {

    private int selectionType;
    private String userName;
    private int id;
    private String OwnedEquipment;
    public DomainUser() {
        //empty
    }

    public String getOwnedEquipment() {
        return OwnedEquipment;
    }

    public void setOwnedEquipment(String ownedEquipment) {
        OwnedEquipment = ownedEquipment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSelectionType(int selectionType) {
        this.selectionType = selectionType;
    }



    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getSelectionType() {
        return selectionType;
    }


    public String getUserName() {
        return userName;
    }
}
