package com.hy.onlinemonitor.bean;

import java.io.Serializable;

/**
 * Created by 24363 on 2015/10/28.
 */
public class ConductorSwingWithWind  implements Serializable {

    private int sn;
    private int deviceSn;
    private String sensorName;//传感器编号
    private String collectDataTimeStr;
    private double windageYawAngle;//风偏角(°)
    private double deflectionAngle;//偏斜角(°)
    private double leastClearance;//最小电气间隙(m)

    public String getCollectDataTimeStr() {
        return collectDataTimeStr;
    }

    public void setCollectDataTimeStr(String collectDataTimeStr) {
        this.collectDataTimeStr = collectDataTimeStr;
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public int getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(int deviceSn) {
        this.deviceSn = deviceSn;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public double getWindageYawAngle() {
        return windageYawAngle;
    }

    public void setWindageYawAngle(double windageYawAngle) {
        this.windageYawAngle = windageYawAngle;
    }

    public double getDeflectionAngle() {
        return deflectionAngle;
    }

    public void setDeflectionAngle(double deflectionAngle) {
        this.deflectionAngle = deflectionAngle;
    }

    public double getLeastClearance() {
        return leastClearance;
    }

    public void setLeastClearance(double leastClearance) {
        this.leastClearance = leastClearance;
    }
}
