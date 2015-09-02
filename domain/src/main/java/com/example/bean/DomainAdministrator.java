package com.example.bean;

import java.util.List;

/**
 * Created by 24363 on 2015/8/31.
 */
public class DomainAdministrator {
    private String loginName;
    private String realName;
    private String phoneNumber;
    private String isReceiveMessages;
    private String password;
    private DomainRole domainRole;
    private int companySn;
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

    public DomainRole getDomainRole() {
        return domainRole;
    }

    public void setDomainRole(DomainRole domainRole) {
        this.domainRole = domainRole;
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
