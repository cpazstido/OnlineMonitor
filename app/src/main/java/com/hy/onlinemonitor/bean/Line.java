package com.hy.onlinemonitor.bean;

import java.util.List;

/**
 * Created by 24363 on 2015/9/2.
 */
public class Line {
    private String lineName;
    private int lineSn;
    private String lineStart;
    private String lineFinish;
    private String lineTrend;
    private String voltageLevel;
    private List<Pole> poleList;

    public Line() {
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public List<Pole> getPoleList() {
        return poleList;
    }

    public void setPoleList(List<Pole> poleList) {
        this.poleList = poleList;
    }

    public int getLineSn() {
        return lineSn;
    }

    public void setLineSn(int lineSn) {
        this.lineSn = lineSn;
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
}
