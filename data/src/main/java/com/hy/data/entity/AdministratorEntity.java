package com.hy.data.entity;

import java.util.List;

public class AdministratorEntity {
    private String loginName;
    private String realName;
    private String mobilePhone;
    private String receiveMessage;
    private String password;
    private RoleEntity role;
    private int company_SN;
    private int sn;
    private int allPoleSeleceted;
    private List<String> ownTowerList;
    private List<String> allTowerList;

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public int getCompany_SN() {
        return company_SN;
    }

    public void setCompany_SN(int company_SN) {
        this.company_SN = company_SN;
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

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getReceiveMessage() {
        return receiveMessage;
    }

    public void setReceiveMessage(String receiveMessage) {
        this.receiveMessage = receiveMessage;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getAllPoleSeleceted() {
        return allPoleSeleceted;
    }

    public void setAllPoleSeleceted(int allPoleSeleceted) {
        this.allPoleSeleceted = allPoleSeleceted;
    }
}
