package com.hy.onlinemonitor.bean;

import java.util.List;

public class Company {
    private int sn;
    private String companyName;
    private List<Line> lineList;
    private String companyAddress;
    private String CompanySuperior;


    public Company() {
    }
    public List<Line> getLineList() {
        return lineList;
    }

    public void setLineList(List<Line> lineList) {
        this.lineList = lineList;
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanySuperior() {
        return CompanySuperior;
    }

    public void setCompanySuperior(String companySuperior) {
        CompanySuperior = companySuperior;
    }
}
