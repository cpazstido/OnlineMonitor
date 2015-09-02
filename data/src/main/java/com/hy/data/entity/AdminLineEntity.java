package com.hy.data.entity;

import java.util.List;

/**
 * Created by 24363 on 2015/9/2.
 */
public class AdminLineEntity {
    private String name;
    private List<AdminTowerEntity> poleSet;

    public AdminLineEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AdminTowerEntity> getPoleSet() {
        return poleSet;
    }

    public void setPoleSet(List<AdminTowerEntity> poleSet) {
        this.poleSet = poleSet;
    }
}
