package com.hy.data.entity;

import java.util.List;

/**
 * Created by 24363 on 2015/9/2.
 */
public class LineEntity {
    private String name;
    private int lineSn;
    private List<PoleEntity> poleSet;

    public LineEntity() {
    }

    public int getLineSn() {
        return lineSn;
    }

    public void setLineSn(int lineSn) {
        this.lineSn = lineSn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PoleEntity> getPoleSet() {
        return poleSet;
    }

    public void setPoleSet(List<PoleEntity> poleSet) {
        this.poleSet = poleSet;
    }
}
