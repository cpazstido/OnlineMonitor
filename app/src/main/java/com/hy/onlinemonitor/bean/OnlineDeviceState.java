package com.hy.onlinemonitor.bean;

import java.io.Serializable;

/**
 * Created by 24363 on 2015/10/22.
 */
public class OnlineDeviceState implements Serializable {

    private int sn;
    private String poleName;
    public int deviceSn;
    public String deviceId;
    public String Ip; // IP地址
    public int Port; // 端口
    public float Bat_Capacity; // 工作电压 单位：V
    public float Bat_Dump_Energy; // 工作电池剩余电量：%
    public float Work_Current; // 工作电流 单位：A
    public float Bat_Consume_total; // 总电量消耗（每天） 单位：V.A.H
    public float Bat_Consume_Camer; // 摄像机电量消耗（每天） 单位：V.A.H
    public float Bat_Consume_Sensor; // 传感器电量消耗（每天） 单位：V.A.H

    public float Charge_Capacity; // 总充电电量  单位：V.A.H
    public long Terminal_work_time; // 终端设备连续工作时间 单位：S
    public long Camer_work_time; // 摄像机连续工作时间 单位：S
    public long Total_Working_time;//工作总时间  单位：S
    public byte Camer_power_status; // 摄像机开电状态 0：关机 1：正在启动 2：正常工作
    public byte Power_3G_status; // 3G开电状态 0：关机 1：正在启动 2：正常工作
    public float Charge_Currrent_1; // 1#充电电流  单位：A
    public float Charge_Currrent_2; // 2#充电电流  单位：A
    public float Charge_Currrent_3; // 3#充电电流  单位：A
    public float Charge_Currrent_4; // 4#充电电流  单位：A
    public float Bat5_ChargeCurrrent;//5#充电电流  单位：A
    public float Charge_Currrent_1_2; // 1#充电电流2 单位：A
    public float Charge_Currrent_2_2; // 2#充电电流2  单位：A
    public float Charge_Currrent_3_2; // 3#充电电流2  单位：A
    public float Charge_Currrent_4_2; // 4#充电电流2  单位：A
    public float Bat5_ChargeCurrrent2;//5#充电电流  单位：A
    public float Bat_GetChargeCurrrent; // 在线取电充电电流  单位：A
    public float Bat1_Voltage;//1#电池电压  单位：V
    public float Bat2_Voltage;//2#电池电压  单位：V
    public float Bat3_Voltage;//3#电池电压  单位：V
    public float Bat4_Voltage;//4#电池电压  单位：V
    public float Bat5_Voltage;//5#电池电压  单位：V
    public float Bat1_RemainCapacity;//1#电池剩余电量  单位：%
    public float Bat2_RemainCapacity;//2#电池剩余电量  单位：%
    public float Bat3_RemainCapacity;//3#电池剩余电量  单位：%
    public float Bat4_RemainCapacity;//4#电池剩余电量  单位：%
    public float Bat5_RemainCapacity;//#电池剩余电量  单位：%
    public float Bat_GetChargeRemainCapacity;//在线取电剩余电量
    public float Bat1_ChargeCapacity;//1#电池总充电电量  单位：V.A.H
    public float Bat2_ChargeCapacity;//2#电池总充电电量  单位：V.A.H
    public float Bat3_ChargeCapacity;//3#电池总充电电量  单位：V.A.H
    public float Bat4_ChargeCapacity;//4#电池总充电电量  单位：V.A.H
    public float Bat1_UsedCapacity;//1#电池总用电电量  单位：V.A.H
    public float Bat2_UsedCapacity;//2#电池总用电电量  单位：V.A.H
    public float Bat3_UsedCapacity;//3#电池总用电电量  单位：V.A.H
    public float Bat4_UsedCapacity;//4#电池总用电电量  单位：V.A.H


    public float Operation_Temperature;//工作温度  单位：c

    public byte Signal_Intensity;//DTU信号强度

    public float Bat1_OutputCurrent;//1#电池输出电流
    public float Bat2_OutputCurrent;//2#电池输出电流
    public float Bat3_OutputCurrent;//3#电池输出电流
    public float Bat4_OutputCurrent;//4#电池输出电流
    public float Bat5_OutputCurrent;//5#电池输出电流

    public byte DVR_power_status;//DVR开电状态
    public byte DVR_VisibleCamera;//DVR系统里可见光相机状态1：开机 	2：关机   3：故障
    public byte DVR_Router3G;//DVR系统里3G路由器状态
    public byte DVR_InfraredCamera;//DVR系统里红外相机状态
    public byte DVR_A9;//DVR系统里A9状态
    public byte DVR_PTZ;//DVR系统里云台状态

    private String totalWorkingtime;  //对long类型的 Total_Working_time进行转化
    private String terminalworktime;//对long型的 Terminal_work_time进行转化
    private String camerWorktime;//对long类型的 Camer_work_time进行转化

    public float Bat_GetChargeVoltage;//在线取电电池电压
    public float Bat_GetChargeOutputCurrent;//在线取电电池输出电流
    public float Bat1_SolarPanelVoltage;//电池1太阳能电池板开路电压
    public float Bat2_SolarPanelVoltage;//电池2太阳能电池板开路电压
    public float Bat3_SolarPanelVoltage;//电池3太阳能电池板开路电压
    public float Bat4_SolarPanelVoltage;//电池4太阳能电池板开路电压
    public float Bat5_SolarPanelVoltage;//电池5太阳能电池板开路电压
    public float Bat1_SolarPanelVoltage2;//电池1太阳能电池板开路电压2
    public float Bat2_SolarPanelVoltage2;//电池2太阳能电池板开路电压2
    public float Bat3_SolarPanelVoltage2;//电池3太阳能电池板开路电压2
    public float Bat4_SolarPanelVoltage2;//电池4太阳能电池板开路电压2
    public float Bat5_SolarPanelVoltage2;//电池5太阳能电池板开路电压2


    public byte DVR_k60_SoftwareVer;//DVR系统里k60程序版本号
    public byte DVR_3G_Uart;//DVR系统里3G串口占用情况 1:正常 3:故障
    public byte Bat1_ChargeStaus;//1#电池充电开关状态0:充电关闭1: 充电开启
    public byte Bat2_ChargeStaus;//2#电池充电开关状态0:充电关闭1: 充电开启
    public byte Bat3_ChargeStaus;//3#电池充电开关状态0:充电关闭1: 充电开启
    public byte Bat4_ChargeStaus;//4#电池充电开关状态0:充电关闭1: 充电开启
    public byte Bat5_ChargeStaus;//5#电池充电开关状态0:充电关闭1: 充电开启
    public byte Bat1_ChargeStaus2;//1#电池充电开关状态2，0:充电关闭1: 充电开启
    public byte Bat2_ChargeStaus2;//2#电池充电开关状态2，0:充电关闭1: 充电开启
    public byte Bat3_ChargeStaus2;//3#电池充电开关状态2，0:充电关闭1: 充电开启
    public byte Bat4_ChargeStaus2;//4#电池充电开关状态2，0:充电关闭1: 充电开启
    public byte Bat5_ChargeStaus2;//5#电池充电开关状态2，0:充电关闭1: 充电开启
    public String K60_SoftwareVer;//主机主板k60软件版本
    public String K60_HardWareVer;//主机主板硬件版本
    public float Bat_GetChargeWork;//在线取电功
    public float Bat_GetChargeCollectCurrent;//在线取电采集电流
    public byte DVR_Signal_Intensity;//DVR信号强度
    public byte KEY_Negotiation_status;//0:未发起协商1: 密钥协商成2.密钥协商失败 3.明文协商成功4明文协商失败
    public long Failed_send_weather_num;//未上传成功的气象数据条数
    public long Failed_send_tower_slop_num;//未上传成功的杆塔倾斜数据条数
    public long Failed_send_angle_num;//未上传成功的导线风偏数据条数
    public long Send_flow;//当月发送流量
    public long Receive_flow;//当月接收流量
    public float A9_Voltage;//A9工作电压
    public float A9_Temperature;//A9工作温度

    public OnlineDeviceState() {
    }

    public String format(long ss) {//将毫秒数换算成x天x时x分x秒x毫秒
        int mi = 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ss / dd;
        long hour = (ss - day * dd) / hh;
        long minute = (ss - day * dd - hour * hh) / mi;
        //	   long second = (ss - day * dd - hour * hh - minute * mi) / ss;

        String strDay = day < 10 ? "0" + day : "" + day;
        String strHour = hour < 10 ? "0" + hour : "" + hour;
        String strMinute = minute < 10 ? "0" + minute : "" + minute;
        //	   String strSecond = second < 10 ? "0" + second : "" + second;
        return strDay + "天" + strHour + "时" + strMinute + "分";
    }

    public float transformation(float a) {
        float b = (float) (Math.round(a * 100) / 100.00);//
        return b;
    }

    public String getTotalWorkingtime() {
        return totalWorkingtime = format(Total_Working_time);
    }

    public void setTotalWorkingtime(String totalWorkingtime) {
        this.totalWorkingtime = totalWorkingtime;
    }

    public String getTerminalworktime() {
        return terminalworktime = format(Terminal_work_time);
    }

    public void setTerminalworktime(String terminalworktime) {
        this.terminalworktime = terminalworktime;
    }

    public String getCamerWorktime() {
        return camerWorktime = format(Camer_work_time);
    }

    public void setCamerWorktime(String camerWorktime) {
        this.camerWorktime = camerWorktime;
    }

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
        return Bat5_RemainCapacity ;
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
        return Bat_Dump_Energy ;
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
        return Bat1_RemainCapacity ;
    }

    public void setBat1_RemainCapacity(float bat1_RemainCapacity) {
        Bat1_RemainCapacity = bat1_RemainCapacity;
    }

    public float getBat2_RemainCapacity() {
        return Bat2_RemainCapacity ;
    }

    public void setBat2_RemainCapacity(float bat2_RemainCapacity) {
        Bat2_RemainCapacity = bat2_RemainCapacity;
    }

    public float getBat3_RemainCapacity() {
        return Bat3_RemainCapacity ;
    }

    public void setBat3_RemainCapacity(float bat3_RemainCapacity) {
        Bat3_RemainCapacity = bat3_RemainCapacity;
    }

    public float getBat4_RemainCapacity() {
        return Bat4_RemainCapacity ;
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
        return Send_flow;
    }

    public void setSend_flow(long send_flow) {
        Send_flow = send_flow;
    }

    public long getReceive_flow() {
        return Receive_flow;
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
