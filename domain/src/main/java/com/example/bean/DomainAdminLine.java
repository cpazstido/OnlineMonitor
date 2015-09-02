package com.example.bean;

import java.util.List;

/**
 * Created by 24363 on 2015/9/2.
 */
public class DomainAdminLine {
    private String name;
    private List<DomainAdminTower> towers;

    public DomainAdminLine() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DomainAdminTower> getTowers() {
        return towers;
    }

    public void setTowers(List<DomainAdminTower> towers) {
        this.towers = towers;
    }
}
