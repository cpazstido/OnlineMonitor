package com.hy.onlinemonitor.bean;

import java.util.List;

/**
 * Created by 24363 on 2015/9/2.
 */
public class AdminLine {
    private String name;
    private List<AdminTower> towers;

    public AdminLine() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AdminTower> getTowers() {
        return towers;
    }

    public void setTowers(List<AdminTower> towers) {
        this.towers = towers;
    }
}
