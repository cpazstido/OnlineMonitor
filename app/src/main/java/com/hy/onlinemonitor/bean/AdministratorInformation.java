package com.hy.onlinemonitor.bean;

public class AdministratorInformation {
    private String loginName;
    private String realName;
    private String phoneNumber;
    private String isReceiveMessages;
    private String password;
    private Role role;
    private int companySn;
    private int sn;
    private int allPoleSeleceted;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAllPoleSeleceted() {
        return allPoleSeleceted;
    }

    public void setAllPoleSeleceted(int allPoleSeleceted) {
        this.allPoleSeleceted = allPoleSeleceted;
    }
}
