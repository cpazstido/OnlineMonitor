package com.hy.onlinemonitor.bean;

/**
 * Created by 24363 on 2015/10/17.
 */
public class DeviceInformation {
    private String deviceId;
    private String poleName;
    private String receiveTraffic;
    private String sendTraffic;
    private String softwareVersion;
    private String hardwareVersion;
    private String DVRVersion;
    private String signalIntensity;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getPoleName() {
        return poleName;
    }

    public void setPoleName(String poleName) {
        this.poleName = poleName;
    }

    public String getReceiveTraffic() {
        return receiveTraffic;
    }

    public void setReceiveTraffic(String receiveTraffic) {
        this.receiveTraffic = receiveTraffic;
    }

    public String getSendTraffic() {
        return sendTraffic;
    }

    public void setSendTraffic(String sendTraffic) {
        this.sendTraffic = sendTraffic;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public String getHardwareVersion() {
        return hardwareVersion;
    }

    public void setHardwareVersion(String hardwareVersion) {
        this.hardwareVersion = hardwareVersion;
    }

    public String getDVRVersion() {
        return DVRVersion;
    }

    public void setDVRVersion(String DVRVersion) {
        this.DVRVersion = DVRVersion;
    }

    public String getSignalIntensity() {
        return signalIntensity;
    }

    public void setSignalIntensity(String signalIntensity) {
        this.signalIntensity = signalIntensity;
    }
}
