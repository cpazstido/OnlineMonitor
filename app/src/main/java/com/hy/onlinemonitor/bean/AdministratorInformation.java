package com.hy.onlinemonitor.bean;

import java.util.List;

public class AdministratorInformation {
    private String loginName;
    private String realName;
    private String phoneNumber;
    private String isReceiveMessages;
    private String password;
    private Role role;
    private int companySn;
    private int sn;
    private List<String> ownTowerList;
    private List<String> allTowerList;

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getCompanySn() {
        return companySn;
    }

    public void setCompanySn(int companySn) {
        this.companySn = companySn;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIsReceiveMessages() {
        return isReceiveMessages;
    }

    public void setIsReceiveMessages(String isReceiveMessages) {
        this.isReceiveMessages = isReceiveMessages;
    }

    public List<String> getOwnTowerList() {
        return ownTowerList;
    }

    public void setOwnTowerList(List<String> ownTowerList) {
        this.ownTowerList = ownTowerList;
    }

    public List<String> getAllTowerList() {
        return allTowerList;
    }

    public void setAllTowerList(List<String> allTowerList) {
        this.allTowerList = allTowerList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
