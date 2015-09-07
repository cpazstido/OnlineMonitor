package com.example.bean;

import java.util.List;

public class DomainCompany {
    private int sn;
    private String companyName;
    private List<DomainLine> lineList;

    public DomainCompany() {
    }

    public List<DomainLine> getLineList() {
        return lineList;
    }

    public void setLineList(List<DomainLine> lineList) {
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
}
