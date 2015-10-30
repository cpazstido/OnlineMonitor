package com.hy.onlinemonitor.bean;

/**
 * Created by 24363 on 2015/10/28.
 */
public class Microclimate {
    private int sn;
    private int deviceSn;
    private String sensorName;//传感器编号
    private String collectDataTimeStr;//采集时间
    private double averageWindSpeed10Min;//平均风速(m/s)
    private double averageWindDirection10Min;//风向
    private double maxWindSpeed;//最大风速(m/s)
    private double extremeWindSpeed;//极大风速(m/s)
    private double standardWindSpeed;//标准风速(m/s)
    private double airTemperature;//气温(℃)
    private double humidity;//空气湿度(%)
    private double airPressure;//气压(hPa)
    private double precipitation;//降雨量(mm)
    private double precipitationIntensity;//降水强度(mm/min)
    private double radiationIntensity;//辐射强度(W/㎡)

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

    public String getCollectDataTimeStr() {
        return collectDataTimeStr;
    }

    public void setCollectDataTimeStr(String collectDataTimeStr) {
        this.collectDataTimeStr = collectDataTimeStr;
    }

    public double getAverageWindSpeed10Min() {
        return averageWindSpeed10Min;
    }

    public void setAverageWindSpeed10Min(double averageWindSpeed10Min) {
        this.averageWindSpeed10Min = averageWindSpeed10Min;
    }

    public double getAverageWindDirection10Min() {
        return averageWindDirection10Min;
    }

    public void setAverageWindDirection10Min(double averageWindDirection10Min) {
        this.averageWindDirection10Min = averageWindDirection10Min;
    }

    public double getMaxWindSpeed() {
        return maxWindSpeed;
    }

    public void setMaxWindSpeed(double maxWindSpeed) {
        this.maxWindSpeed = maxWindSpeed;
    }

    public double getExtremeWindSpeed() {
        return extremeWindSpeed;
    }

    public void setExtremeWindSpeed(double extremeWindSpeed) {
        this.extremeWindSpeed = extremeWindSpeed;
    }

    public double getStandardWindSpeed() {
        return standardWindSpeed;
    }

    public void setStandardWindSpeed(double standardWindSpeed) {
        this.standardWindSpeed = standardWindSpeed;
    }

    public double getAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(double airTemperature) {
        this.airTemperature = airTemperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getAirPressure() {
        return airPressure;
    }

    public void setAirPressure(double airPressure) {
        this.airPressure = airPressure;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    public double getPrecipitationIntensity() {
        return precipitationIntensity;
    }

    public void setPrecipitationIntensity(double precipitationIntensity) {
        this.precipitationIntensity = precipitationIntensity;
    }

    public double getRadiationIntensity() {
        return radiationIntensity;
    }

    public void setRadiationIntensity(double radiationIntensity) {
        this.radiationIntensity = radiationIntensity;
    }
}
