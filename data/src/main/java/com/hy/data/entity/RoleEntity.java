package com.hy.data.entity;

import java.util.List;

public class RoleEntity {
    private int sn;
    private String roleName;
    private List<Integer> snList;

    public RoleEntity() {
    }

    public List<Integer> getSnList() {
        return snList;
    }

    public void setSnList(List<Integer> snList) {
        this.snList = snList;
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
