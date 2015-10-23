package com.example.bean;

/**
 * Created by 24363 on 2015/10/22.
 */
public class DomainOnlineDeviceState {
    private int sn;
    private String poleName;
    public int deviceSn;
    public String deviceId;
    public String Ip;
    public int Port;
    public float Bat_Capacity;
    public float Bat_Dump_Energy;
    public float Work_Current;
    public float Bat_Consume_total;
    public float Bat_Consume_Camer;
    public float Bat_Consume_Sensor;

    public float Charge_Capacity;
    public long Terminal_work_time;
    public long Camer_work_time;
    public long Total_Working_time;
    public byte Camer_power_status;
    public byte Power_3G_status;
    public float Charge_Currrent_1;
    public float Charge_Currrent_2;
    public float Charge_Currrent_3;
    public float Charge_Currrent_4;
    public float Bat5_ChargeCurrrent;
    public float Charge_Currrent_1_2;
    public float Charge_Currrent_2_2;
    public float Charge_Currrent_3_2;
    public float Charge_Currrent_4_2;
    public float Bat5_ChargeCurrrent2;
    public float Bat_GetChargeCurrrent;
    public float Bat1_Voltage;
    public float Bat2_Voltage;
    public float Bat3_Voltage;
    public float Bat4_Voltage;
    public float Bat5_Voltage;
    public float Bat1_RemainCapacity;
    public float Bat2_RemainCapacity;
    public float Bat3_RemainCapacity;
    public float Bat4_RemainCapacity;
    public float Bat5_RemainCapacity;
    public float Bat_GetChargeRemainCapacity;
    public float Bat1_ChargeCapacity;
    public float Bat2_ChargeCapacity;
    public float Bat3_ChargeCapacity;
    public float Bat4_ChargeCapacity;
    public float Bat1_UsedCapacity;
    public float Bat2_UsedCapacity;
    public float Bat3_UsedCapacity;
    public float Bat4_UsedCapacity;


    public float Operation_Temperature;

    public byte Signal_Intensity;

    public float Bat1_OutputCurrent;
    public float Bat2_OutputCurrent;
    public float Bat3_OutputCurrent;
    public float Bat4_OutputCurrent;
    public float Bat5_OutputCurrent;

    public byte DVR_power_status;
    public byte DVR_VisibleCamera;
    public byte DVR_Router3G;
    public byte DVR_InfraredCamera;
    public byte DVR_A9;
    public byte DVR_PTZ;

    public float Bat_GetChargeVoltage;
    public float Bat_GetChargeOutputCurrent;
    public float Bat1_SolarPanelVoltage;
    public float Bat2_SolarPanelVoltage;
    public float Bat3_SolarPanelVoltage;
    public float Bat4_SolarPanelVoltage;
    public float Bat5_SolarPanelVoltage;
    public float Bat1_SolarPanelVoltage2;
    public float Bat2_SolarPanelVoltage2;
    public float Bat3_SolarPanelVoltage2;
    public float Bat4_SolarPanelVoltage2;
    public float Bat5_SolarPanelVoltage2;


    public byte DVR_k60_SoftwareVer;
    public byte DVR_3G_Uart;
    public byte Bat1_ChargeStaus;
    public byte Bat2_ChargeStaus;
    public byte Bat3_ChargeStaus;
    public byte Bat4_ChargeStaus;
    public byte Bat5_ChargeStaus;
    public byte Bat1_ChargeStaus2;
    public byte Bat2_ChargeStaus2;
    public byte Bat3_ChargeStaus2;
    public byte Bat4_ChargeStaus2;
    public byte Bat5_ChargeStaus2;
    public String K60_SoftwareVer;
    public String K60_HardWareVer;
    public float Bat_GetChargeWork;
    public float Bat_GetChargeCollectCurrent;
    public byte DVR_Signal_Intensity;
    public byte KEY_Negotiation_status;
    public long Failed_send_weather_num;
    public long Failed_send_tower_slop_num;
    public long Failed_send_angle_num;
    public long Send_flow;
    public long Receive_flow;
    public float A9_Voltage;
    public float A9_Temperature;

    public int getSn() {
        return sn;
    }

    public float getBat3_ChargeCapacity() {
        return Bat3_ChargeCapacity;
    }

    public void setBat3_ChargeCapacity(float bat3ChargeCapacity) {
        Bat3_ChargeCapacity = bat3ChargeCapacity;
    }

    public float getBat4_ChargeCapacity() {
        return Bat4_ChargeCapacity;
    }

    public void setBat4_ChargeCapacity(float bat4ChargeCapacity) {
        Bat4_ChargeCapacity = bat4ChargeCapacity;
    }

    public float getBat1_UsedCapacity() {
        return Bat1_UsedCapacity;
    }

    public void setBat1_UsedCapacity(float bat1UsedCapacity) {
        Bat1_UsedCapacity = bat1UsedCapacity;
    }

    public float getBat2_UsedCapacity() {
        return Bat2_UsedCapacity;
    }

    public void setBat2_UsedCapacity(float bat2UsedCapacity) {
        Bat2_UsedCapacity = bat2UsedCapacity;
    }

    public float getBat3_UsedCapacity() {
        return Bat3_UsedCapacity;
    }

    public void setBat3_UsedCapacity(float bat3UsedCapacity) {
        Bat3_UsedCapacity = bat3UsedCapacity;
    }

    public float getBat4_UsedCapacity() {
        return Bat4_UsedCapacity;
    }

    public void setBat4_UsedCapacity(float bat4UsedCapacity) {
        Bat4_UsedCapacity = bat4UsedCapacity;
    }

    public long getTotal_Working_time() {
        return Total_Working_time;
    }

    public void setTotal_Working_time(long totalWorkingTime) {
        Total_Working_time = totalWorkingTime;
    }

    public float getOperation_Temperature() {
        return Operation_Temperature;
    }

    public void setOperation_Temperature(float operationTemperature) {
        Operation_Temperature = operationTemperature;
    }


    public float getBat1_OutputCurrent() {
        return Bat1_OutputCurrent;
    }

    public void setBat1_OutputCurrent(float bat1OutputCurrent) {
        Bat1_OutputCurrent = bat1OutputCurrent;
    }

    public float getBat2_OutputCurrent() {
        return Bat2_OutputCurrent;
    }

    public void setBat2_OutputCurrent(float bat2OutputCurrent) {
        Bat2_OutputCurrent = bat2OutputCurrent;
    }

    public float getBat3_OutputCurrent() {
        return Bat3_OutputCurrent;
    }

    public void setBat3_OutputCurrent(float bat3OutputCurrent) {
        Bat3_OutputCurrent = bat3OutputCurrent;
    }

    public float getBat4_OutputCurrent() {
        return Bat4_OutputCurrent;
    }

    public void setBat4_OutputCurrent(float bat4OutputCurrent) {
        Bat4_OutputCurrent = bat4OutputCurrent;
    }

    public String getPoleName() {
        return poleName;
    }

    public void setPoleName(String poleName) {
        this.poleName = poleName;
    }

    public byte getSignal_Intensity() {
        return Signal_Intensity;
    }

    public void setSignal_Intensity(byte signalIntensity) {
        Signal_Intensity = signalIntensity;
    }

    public byte getDVR_power_status() {
        return DVR_power_status;
    }

    public void setDVR_power_status(byte dVRPowerStatus) {
        DVR_power_status = dVRPowerStatus;
    }

    public byte getDVR_VisibleCamera() {
        return DVR_VisibleCamera;
    }

    public void setDVR_VisibleCamera(byte dVRVisibleCamera) {
        DVR_VisibleCamera = dVRVisibleCamera;
    }

    public byte getDVR_Router3G() {
        return DVR_Router3G;
    }

    public void setDVR_Router3G(byte dVRRouter3G) {
        DVR_Router3G = dVRRouter3G;
    }

    public byte getDVR_InfraredCamera() {
        return DVR_InfraredCamera;
    }

    public void setDVR_InfraredCamera(byte dVRInfraredCamera) {
        DVR_InfraredCamera = dVRInfraredCamera;
    }

    public byte getDVR_A9() {
        return DVR_A9;
    }

    public void setDVR_A9(byte dVRA9) {
        DVR_A9 = dVRA9;
    }

    public byte getDVR_PTZ() {
        return DVR_PTZ;
    }

    public void setDVR_PTZ(byte dVRPTZ) {
        DVR_PTZ = dVRPTZ;
    }

    public float getBat_GetChargeVoltage() {
        return Bat_GetChargeVoltage;
    }

    public void setBat_GetChargeVoltage(float batGetChargeVoltage) {
        Bat_GetChargeVoltage = batGetChargeVoltage;
    }

    public float getBat_GetChargeOutputCurrent() {
        return Bat_GetChargeOutputCurrent;
    }

    public void setBat_GetChargeOutputCurrent(float batGetChargeOutputCurrent) {
        Bat_GetChargeOutputCurrent = batGetChargeOutputCurrent;
    }

    public float getBat1_SolarPanelVoltage() {
        return Bat1_SolarPanelVoltage;
    }

    public void setBat1_SolarPanelVoltage(float bat1SolarPanelVoltage) {
        Bat1_SolarPanelVoltage = bat1SolarPanelVoltage;
    }

    public float getBat2_SolarPanelVoltage() {
        return Bat2_SolarPanelVoltage;
    }

    public void setBat2_SolarPanelVoltage(float bat2SolarPanelVoltage) {
        Bat2_SolarPanelVoltage = bat2SolarPanelVoltage;
    }

    public float getBat3_SolarPanelVoltage() {
        return Bat3_SolarPanelVoltage;
    }

    public void setBat3_SolarPanelVoltage(float bat3SolarPanelVoltage) {
        Bat3_SolarPanelVoltage = bat3SolarPanelVoltage;
    }

    public float getBat4_SolarPanelVoltage() {
        return Bat4_SolarPanelVoltage;
    }

    public void setBat4_SolarPanelVoltage(float bat4SolarPanelVoltage) {
        Bat4_SolarPanelVoltage = bat4SolarPanelVoltage;
    }

    public float getBat5_SolarPanelVoltage() {
        return Bat5_SolarPanelVoltage;
    }

    public void setBat5_SolarPanelVoltage(float bat5SolarPanelVoltage) {
        Bat5_SolarPanelVoltage = bat5SolarPanelVoltage;
    }

    public float getBat5_Voltage() {
        return Bat5_Voltage;
    }

    public void setBat5_Voltage(float bat5Voltage) {
        Bat5_Voltage = bat5Voltage;
    }

    public float getBat5_OutputCurrent() {
        return Bat5_OutputCurrent;
    }

    public void setBat5_OutputCurrent(float bat5OutputCurrent) {
        Bat5_OutputCurrent = bat5OutputCurrent;
    }

    public float getBat5_ChargeCurrrent() {
        return Bat5_ChargeCurrrent;
    }

    public void setBat5_ChargeCurrrent(float bat5ChargeCurrrent) {
        Bat5_ChargeCurrrent = bat5ChargeCurrrent;
    }

    public float getBat5_RemainCapacity() {
        return Bat5_RemainCapacity * 100;
    }

    public void setBat5_RemainCapacity(float bat5RemainCapacity) {
        Bat5_RemainCapacity = bat5RemainCapacity;
    }

    public byte getDVR_k60_SoftwareVer() {
        return DVR_k60_SoftwareVer;
    }

    public void setDVR_k60_SoftwareVer(byte dVRK60SoftwareVer) {
        DVR_k60_SoftwareVer = dVRK60SoftwareVer;
    }

    public byte getDVR_3G_Uart() {
        return DVR_3G_Uart;
    }

    public void setDVR_3G_Uart(byte dVR_3GUart) {
        DVR_3G_Uart = dVR_3GUart;
    }

    public byte getBat1_ChargeStaus() {
        return Bat1_ChargeStaus;
    }

    public void setBat1_ChargeStaus(byte bat1ChargeStaus) {
        Bat1_ChargeStaus = bat1ChargeStaus;
    }

    public byte getBat2_ChargeStaus() {
        return Bat2_ChargeStaus;
    }

    public void setBat2_ChargeStaus(byte bat2ChargeStaus) {
        Bat2_ChargeStaus = bat2ChargeStaus;
    }

    public byte getBat3_ChargeStaus() {
        return Bat3_ChargeStaus;
    }

    public void setBat3_ChargeStaus(byte bat3ChargeStaus) {
        Bat3_ChargeStaus = bat3ChargeStaus;
    }

    public byte getBat4_ChargeStaus() {
        return Bat4_ChargeStaus;
    }

    public void setBat4_ChargeStaus(byte bat4ChargeStaus) {
        Bat4_ChargeStaus = bat4ChargeStaus;
    }

    public byte getBat5_ChargeStaus() {
        return Bat5_ChargeStaus;
    }

    public void setBat5_ChargeStaus(byte bat5ChargeStaus) {
        Bat5_ChargeStaus = bat5ChargeStaus;
    }

    public String getK60_SoftwareVer() {
        return K60_SoftwareVer;
    }

    public void setK60_SoftwareVer(String k60SoftwareVer) {
        K60_SoftwareVer = k60SoftwareVer;
    }

    public String getK60_HardWareVer() {
        return K60_HardWareVer;
    }

    public void setK60_HardWareVer(String k60HardWareVer) {
        K60_HardWareVer = k60HardWareVer;
    }

    public float getBat_GetChargeWork() {
        return (float) (Math.round(Bat_GetChargeWork * 100) / 100.00);
    }

    public void setBat_GetChargeWork(float batGetChargeWork) {
        Bat_GetChargeWork = batGetChargeWork;
    }

    public float getBat_GetChargeCurrrent() {
        return Bat_GetChargeCurrrent;
    }

    public void setBat_GetChargeCurrrent(float batGetChargeCurrrent) {
        Bat_GetChargeCurrrent = batGetChargeCurrrent;
    }

    public float getBat_GetChargeRemainCapacity() {
        return Bat_GetChargeRemainCapacity;
    }

    public void setBat_GetChargeRemainCapacity(float batGetChargeRemainCapacity) {
        Bat_GetChargeRemainCapacity = batGetChargeRemainCapacity;
    }

    public float getBat_GetChargeCollectCurrent() {
        return Bat_GetChargeCollectCurrent;
    }

    public void setBat_GetChargeCollectCurrent(float batGetChargeCollectCurrent) {
        Bat_GetChargeCollectCurrent = batGetChargeCollectCurrent;
    }

    public byte getDVR_Signal_Intensity() {
        return DVR_Signal_Intensity;
    }

    public void setDVR_Signal_Intensity(byte dVRSignalIntensity) {
        DVR_Signal_Intensity = dVRSignalIntensity;
    }

    public int getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(int deviceSn) {
        this.deviceSn = deviceSn;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getIp() {
        return Ip;
    }

    public void setIp(String ip) {
        Ip = ip;
    }

    public int getPort() {
        return Port;
    }

    public void setPort(int port) {
        Port = port;
    }

    public float getBat_Capacity() {
        return Bat_Capacity;
    }

    public void setBat_Capacity(float bat_Capacity) {
        Bat_Capacity = bat_Capacity;
    }

    public float getBat_Dump_Energy() {
        return Bat_Dump_Energy * 100;
    }

    public void setBat_Dump_Energy(float bat_Dump_Energy) {
        Bat_Dump_Energy = bat_Dump_Energy;
    }

    public float getWork_Current() {
        return Work_Current;
    }

    public void setWork_Current(float work_Current) {
        Work_Current = work_Current;
    }

    public float getBat_Consume_total() {
        return Bat_Consume_total;
    }

    public void setBat_Consume_total(float bat_Consume_total) {
        Bat_Consume_total = bat_Consume_total;
    }

    public float getBat_Consume_Camer() {
        return Bat_Consume_Camer;
    }

    public void setBat_Consume_Camer(float bat_Consume_Camer) {
        Bat_Consume_Camer = bat_Consume_Camer;
    }

    public float getBat_Consume_Sensor() {
        return Bat_Consume_Sensor;
    }

    public void setBat_Consume_Sensor(float bat_Consume_Sensor) {
        Bat_Consume_Sensor = bat_Consume_Sensor;
    }

    public float getCharge_Capacity() {
        return Charge_Capacity;
    }

    public void setCharge_Capacity(float charge_Capacity) {
        Charge_Capacity = charge_Capacity;
    }

    public long getTerminal_work_time() {
        return Terminal_work_time;
    }

    public void setTerminal_work_time(long terminal_work_time) {
        Terminal_work_time = terminal_work_time;
    }

    public long getCamer_work_time() {
        return Camer_work_time;
    }

    public void setCamer_work_time(long camer_work_time) {
        Camer_work_time = camer_work_time;
    }

    public byte getCamer_power_status() {
        return Camer_power_status;
    }

    public void setCamer_power_status(byte camer_power_status) {
        Camer_power_status = camer_power_status;
    }

    public byte getPower_3G_status() {
        return Power_3G_status;
    }

    public void setPower_3G_status(byte power_3g_status) {
        Power_3G_status = power_3g_status;
    }

    public float getCharge_Currrent_1() {
        return Charge_Currrent_1;
    }

    public void setCharge_Currrent_1(float charge_Currrent_1) {
        Charge_Currrent_1 = charge_Currrent_1;
    }

    public float getCharge_Currrent_2() {
        return Charge_Currrent_2;
    }

    public void setCharge_Currrent_2(float charge_Currrent_2) {
        Charge_Currrent_2 = charge_Currrent_2;
    }

    public float getCharge_Currrent_3() {
        return Charge_Currrent_3;
    }

    public void setCharge_Currrent_3(float charge_Currrent_3) {
        Charge_Currrent_3 = charge_Currrent_3;
    }

    public float getCharge_Currrent_4() {
        return Charge_Currrent_4;
    }

    public void setCharge_Currrent_4(float charge_Currrent_4) {
        Charge_Currrent_4 = charge_Currrent_4;
    }

    public float getBat1_Voltage() {
        return Bat1_Voltage;
    }

    public void setBat1_Voltage(float bat1_Voltage) {
        Bat1_Voltage = bat1_Voltage;
    }

    public float getBat2_Voltage() {
        return Bat2_Voltage;
    }

    public void setBat2_Voltage(float bat2_Voltage) {
        Bat2_Voltage = bat2_Voltage;
    }

    public float getBat3_Voltage() {
        return Bat3_Voltage;
    }

    public void setBat3_Voltage(float bat3_Voltage) {
        Bat3_Voltage = bat3_Voltage;
    }

    public float getBat4_Voltage() {
        return Bat4_Voltage;
    }

    public void setBat4_Voltage(float bat4_Voltage) {
        Bat4_Voltage = bat4_Voltage;
    }

    public float getBat1_RemainCapacity() {
        return Bat1_RemainCapacity * 100;
    }

    public void setBat1_RemainCapacity(float bat1_RemainCapacity) {
        Bat1_RemainCapacity = bat1_RemainCapacity;
    }

    public float getBat2_RemainCapacity() {
        return Bat2_RemainCapacity * 100;
    }

    public void setBat2_RemainCapacity(float bat2_RemainCapacity) {
        Bat2_RemainCapacity = bat2_RemainCapacity;
    }

    public float getBat3_RemainCapacity() {
        return Bat3_RemainCapacity * 100;
    }

    public void setBat3_RemainCapacity(float bat3_RemainCapacity) {
        Bat3_RemainCapacity = bat3_RemainCapacity;
    }

    public float getBat4_RemainCapacity() {
        return Bat4_RemainCapacity * 100;
    }

    public void setBat4_RemainCapacity(float bat4_RemainCapacity) {
        Bat4_RemainCapacity = bat4_RemainCapacity;
    }

    public float getBat1_ChargeCapacity() {
        return Bat1_ChargeCapacity;
    }

    public void setBat1_ChargeCapacity(float bat1_ChargeCapacity) {
        Bat1_ChargeCapacity = bat1_ChargeCapacity;
    }

    public float getBat2_ChargeCapacity() {
        return Bat2_ChargeCapacity;
    }

    public void setBat2_ChargeCapacity(float bat2_ChargeCapacity) {
        Bat2_ChargeCapacity = bat2_ChargeCapacity;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public byte getKEY_Negotiation_status() {
        return KEY_Negotiation_status;
    }

    public void setKEY_Negotiation_status(byte kEY_Negotiation_status) {
        KEY_Negotiation_status = kEY_Negotiation_status;
    }

    public long getFailed_send_weather_num() {
        return Failed_send_weather_num;
    }

    public void setFailed_send_weather_num(long failed_send_weather_num) {
        Failed_send_weather_num = failed_send_weather_num;
    }

    public long getFailed_send_tower_slop_num() {
        return Failed_send_tower_slop_num;
    }

    public void setFailed_send_tower_slop_num(long failed_send_tower_slop_num) {
        Failed_send_tower_slop_num = failed_send_tower_slop_num;
    }

    public long getFailed_send_angle_num() {
        return Failed_send_angle_num;
    }

    public void setFailed_send_angle_num(long failed_send_angle_num) {
        Failed_send_angle_num = failed_send_angle_num;
    }

    public long getSend_flow() {
        return Send_flow / (1024 * 1024);
    }

    public void setSend_flow(long send_flow) {
        Send_flow = send_flow;
    }

    public long getReceive_flow() {
        return Receive_flow / (1024 * 1024);
    }

    public void setReceive_flow(long receive_flow) {
        Receive_flow = receive_flow;
    }

    public float getCharge_Currrent_1_2() {
        return Charge_Currrent_1_2;
    }

    public void setCharge_Currrent_1_2(float charge_Currrent_1_2) {
        Charge_Currrent_1_2 = charge_Currrent_1_2;
    }

    public float getCharge_Currrent_2_2() {
        return Charge_Currrent_2_2;
    }

    public void setCharge_Currrent_2_2(float charge_Currrent_2_2) {
        Charge_Currrent_2_2 = charge_Currrent_2_2;
    }

    public float getCharge_Currrent_3_2() {
        return Charge_Currrent_3_2;
    }

    public void setCharge_Currrent_3_2(float charge_Currrent_3_2) {
        Charge_Currrent_3_2 = charge_Currrent_3_2;
    }

    public float getCharge_Currrent_4_2() {
        return Charge_Currrent_4_2;
    }

    public void setCharge_Currrent_4_2(float charge_Currrent_4_2) {
        Charge_Currrent_4_2 = charge_Currrent_4_2;
    }

    public float getBat1_SolarPanelVoltage2() {
        return Bat1_SolarPanelVoltage2;
    }

    public void setBat1_SolarPanelVoltage2(float bat1_SolarPanelVoltage2) {
        Bat1_SolarPanelVoltage2 = bat1_SolarPanelVoltage2;
    }

    public float getBat2_SolarPanelVoltage2() {
        return Bat2_SolarPanelVoltage2;
    }

    public void setBat2_SolarPanelVoltage2(float bat2_SolarPanelVoltage2) {
        Bat2_SolarPanelVoltage2 = bat2_SolarPanelVoltage2;
    }

    public float getBat3_SolarPanelVoltage2() {
        return Bat3_SolarPanelVoltage2;
    }

    public void setBat3_SolarPanelVoltage2(float bat3_SolarPanelVoltage2) {
        Bat3_SolarPanelVoltage2 = bat3_SolarPanelVoltage2;
    }

    public float getBat4_SolarPanelVoltage2() {
        return Bat4_SolarPanelVoltage2;
    }

    public void setBat4_SolarPanelVoltage2(float bat4_SolarPanelVoltage2) {
        Bat4_SolarPanelVoltage2 = bat4_SolarPanelVoltage2;
    }

    public float getBat5_SolarPanelVoltage2() {
        return Bat5_SolarPanelVoltage2;
    }

    public void setBat5_SolarPanelVoltage2(float bat5_SolarPanelVoltage2) {
        Bat5_SolarPanelVoltage2 = bat5_SolarPanelVoltage2;
    }

    public byte getBat1_ChargeStaus2() {
        return Bat1_ChargeStaus2;
    }

    public void setBat1_ChargeStaus2(byte bat1_ChargeStaus2) {
        Bat1_ChargeStaus2 = bat1_ChargeStaus2;
    }

    public byte getBat2_ChargeStaus2() {
        return Bat2_ChargeStaus2;
    }

    public void setBat2_ChargeStaus2(byte bat2_ChargeStaus2) {
        Bat2_ChargeStaus2 = bat2_ChargeStaus2;
    }

    public byte getBat3_ChargeStaus2() {
        return Bat3_ChargeStaus2;
    }

    public void setBat3_ChargeStaus2(byte bat3_ChargeStaus2) {
        Bat3_ChargeStaus2 = bat3_ChargeStaus2;
    }

    public byte getBat4_ChargeStaus2() {
        return Bat4_ChargeStaus2;
    }

    public void setBat4_ChargeStaus2(byte bat4_ChargeStaus2) {
        Bat4_ChargeStaus2 = bat4_ChargeStaus2;
    }

    public byte getBat5_ChargeStaus2() {
        return Bat5_ChargeStaus2;
    }

    public void setBat5_ChargeStaus2(byte bat5_ChargeStaus2) {
        Bat5_ChargeStaus2 = bat5_ChargeStaus2;
    }

    public float getBat5_ChargeCurrrent2() {
        return Bat5_ChargeCurrrent2;
    }

    public void setBat5_ChargeCurrrent2(float bat5_ChargeCurrrent2) {
        Bat5_ChargeCurrrent2 = bat5_ChargeCurrrent2;
    }

    public float getA9_Voltage() {
        return A9_Voltage;
    }

    public void setA9_Voltage(float a9_Voltage) {
        A9_Voltage = a9_Voltage;
    }

    public float getA9_Temperature() {
        return A9_Temperature;
    }

    public void setA9_Temperature(float a9_Temperature) {
        A9_Temperature = a9_Temperature;
    }

}
