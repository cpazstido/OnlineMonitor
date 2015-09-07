package com.example.bean;

import java.util.List;

/**
 * Created by 24363 on 2015/9/2.
 */
public class DomainLine {
    private String name;
    private int lineSn;
    private List<DomainPole> towers;

    public DomainLine() {
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

    public List<DomainPole> getTowers() {
        return towers;
    }

    public void setTowers(List<DomainPole> towers) {
        this.towers = towers;
    }
}
