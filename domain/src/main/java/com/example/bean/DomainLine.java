package com.example.bean;

import java.util.List;

/**
 * Created by 24363 on 2015/9/2.
 */
public class DomainLine {
    private String name;
    private int lineSn;
    private List<DomainPole> towers;

    private String lineStart;
    private String lineFinish;
    private String lineTrend;
    private String voltageLevel;

    public DomainLine() {
    }

    public String getLineStart() {
        return lineStart;
    }

    public void setLineStart(String lineStart) {
        this.lineStart = lineStart;
    }

    public String getLineFinish() {
        return lineFinish;
    }

    public void setLineFinish(String lineFinish) {
        this.lineFinish = lineFinish;
    }

    public String getLineTrend() {
        return lineTrend;
    }

    public void setLineTrend(String lineTrend) {
        this.lineTrend = lineTrend;
    }

    public String getVoltageLevel() {
        return voltageLevel;
    }

    public void setVoltageLevel(String voltageLevel) {
        this.voltageLevel = voltageLevel;
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
