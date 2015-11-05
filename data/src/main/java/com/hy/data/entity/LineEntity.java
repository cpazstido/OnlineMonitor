package com.hy.data.entity;

import java.util.List;

/**
 * Created by 24363 on 2015/9/2.
 */
public class LineEntity {
    private String name;
    private int sn;
    private List<PoleEntity> poleSet;

    private String circuitOrigin;
    private String circuitTerminal;
    private String lineAlignment;
    private String voltage;
    public LineEntity() {
    }

    public String getCircuitOrigin() {
        return circuitOrigin;
    }

    public void setCircuitOrigin(String circuitOrigin) {
        this.circuitOrigin = circuitOrigin;
    }

    public String getCircuitTerminal() {
        return circuitTerminal;
    }

    public void setCircuitTerminal(String circuitTerminal) {
        this.circuitTerminal = circuitTerminal;
    }

    public String getLineAlignment() {
        return lineAlignment;
    }

    public void setLineAlignment(String lineAlignment) {
        this.lineAlignment = lineAlignment;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
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
