package com.hy.onlinemonitor.mapper;

import com.example.bean.DomainOnlineDeviceState;
import com.hy.onlinemonitor.bean.OnlineDeviceState;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 24363 on 2015/10/22.
 */
public class OnlineDeviceStateDataMapper {

    public OnlineDeviceStateDataMapper() {
    }

    public static OnlineDeviceState transform(DomainOnlineDeviceState domainOnlineDeviceState1) {
        OnlineDeviceState onlineDeviceState = null;
        if (domainOnlineDeviceState1 != null) {
            onlineDeviceState = new OnlineDeviceState();
            onlineDeviceState.setSn(domainOnlineDeviceState1.getSn());
            onlineDeviceState.setPoleName(domainOnlineDeviceState1.getPoleName());
            onlineDeviceState.setDeviceSn(domainOnlineDeviceState1.getDeviceSn());
            onlineDeviceState.setDeviceId(domainOnlineDeviceState1.getDeviceId());
            onlineDeviceState.setIp(domainOnlineDeviceState1.getIp());
            onlineDeviceState.setPort(domainOnlineDeviceState1.getPort());
            onlineDeviceState.setBat_Capacity(domainOnlineDeviceState1.getBat_Capacity());
            onlineDeviceState.setWork_Current(domainOnlineDeviceState1.getWork_Current());
            onlineDeviceState.setBat_Dump_Energy(domainOnlineDeviceState1.getBat_Dump_Energy());
            onlineDeviceState.setBat_Consume_total(domainOnlineDeviceState1.getBat_Consume_total());
            onlineDeviceState.setBat_Consume_Camer(domainOnlineDeviceState1.getBat_Consume_Camer());
            onlineDeviceState.setBat_Consume_Sensor(domainOnlineDeviceState1.getBat_Consume_Sensor());
            onlineDeviceState.setCharge_Capacity(domainOnlineDeviceState1.getCharge_Capacity());
            onlineDeviceState.setTerminal_work_time(domainOnlineDeviceState1.getTerminal_work_time());
            onlineDeviceState.setCamer_work_time(domainOnlineDeviceState1.getCamer_work_time());
            onlineDeviceState.setTotal_Working_time(domainOnlineDeviceState1.getTotal_Working_time());
            onlineDeviceState.setCamer_power_status(domainOnlineDeviceState1.getCamer_power_status());
            onlineDeviceState.setPower_3G_status(domainOnlineDeviceState1.getPower_3G_status());
            onlineDeviceState.setCharge_Currrent_1(domainOnlineDeviceState1.getCharge_Currrent_1());
            onlineDeviceState.setCharge_Currrent_2(domainOnlineDeviceState1.getCharge_Currrent_2());
            onlineDeviceState.setCharge_Currrent_3(domainOnlineDeviceState1.getCharge_Currrent_3());
            onlineDeviceState.setCharge_Currrent_4(domainOnlineDeviceState1.getCharge_Currrent_4());
            onlineDeviceState.setBat5_ChargeCurrrent(domainOnlineDeviceState1.getBat5_ChargeCurrrent());
            onlineDeviceState.setCharge_Currrent_1_2(domainOnlineDeviceState1.getCharge_Currrent_1_2());
            onlineDeviceState.setCharge_Currrent_2_2(domainOnlineDeviceState1.getCharge_Currrent_2_2());
            onlineDeviceState.setCharge_Currrent_3_2(domainOnlineDeviceState1.getCharge_Currrent_3_2());
            onlineDeviceState.setCharge_Currrent_4_2(domainOnlineDeviceState1.getCharge_Currrent_4_2());
            onlineDeviceState.setBat5_ChargeCurrrent2(domainOnlineDeviceState1.getBat5_ChargeCurrrent2());
            onlineDeviceState.setBat_GetChargeCurrrent(domainOnlineDeviceState1.getBat_GetChargeCurrrent());
            onlineDeviceState.setBat1_Voltage(domainOnlineDeviceState1.getBat1_Voltage());
            onlineDeviceState.setBat2_Voltage(domainOnlineDeviceState1.getBat2_Voltage());
            onlineDeviceState.setBat3_Voltage(domainOnlineDeviceState1.getBat3_Voltage());
            onlineDeviceState.setBat4_Voltage(domainOnlineDeviceState1.getBat4_Voltage());
            onlineDeviceState.setBat5_Voltage(domainOnlineDeviceState1.getBat5_Voltage());
            onlineDeviceState.setBat1_RemainCapacity(domainOnlineDeviceState1.getBat1_RemainCapacity());
            onlineDeviceState.setBat2_RemainCapacity(domainOnlineDeviceState1.getBat2_RemainCapacity());
            onlineDeviceState.setBat3_RemainCapacity(domainOnlineDeviceState1.getBat3_RemainCapacity());
            onlineDeviceState.setBat4_RemainCapacity(domainOnlineDeviceState1.getBat4_RemainCapacity());
            onlineDeviceState.setBat5_RemainCapacity(domainOnlineDeviceState1.getBat5_RemainCapacity());
            onlineDeviceState.setBat_GetChargeRemainCapacity(domainOnlineDeviceState1.getBat_GetChargeRemainCapacity());
            onlineDeviceState.setBat1_ChargeCapacity(domainOnlineDeviceState1.getBat1_ChargeCapacity());
            onlineDeviceState.setBat2_ChargeCapacity(domainOnlineDeviceState1.getBat2_ChargeCapacity());
            onlineDeviceState.setBat3_ChargeCapacity(domainOnlineDeviceState1.getBat3_ChargeCapacity());
            onlineDeviceState.setBat4_ChargeCapacity(domainOnlineDeviceState1.getBat4_ChargeCapacity());
            onlineDeviceState.setBat1_UsedCapacity(domainOnlineDeviceState1.getBat1_UsedCapacity());
            onlineDeviceState.setBat2_UsedCapacity(domainOnlineDeviceState1.getBat2_UsedCapacity());
            onlineDeviceState.setBat3_UsedCapacity(domainOnlineDeviceState1.getBat3_UsedCapacity());
            onlineDeviceState.setBat4_UsedCapacity(domainOnlineDeviceState1.getBat4_UsedCapacity());
            onlineDeviceState.setOperation_Temperature(domainOnlineDeviceState1.getOperation_Temperature());
            onlineDeviceState.setSignal_Intensity(domainOnlineDeviceState1.getSignal_Intensity());
            onlineDeviceState.setBat1_OutputCurrent(domainOnlineDeviceState1.getBat1_OutputCurrent());
            onlineDeviceState.setBat2_OutputCurrent(domainOnlineDeviceState1.getBat2_OutputCurrent());
            onlineDeviceState.setBat3_OutputCurrent(domainOnlineDeviceState1.getBat3_OutputCurrent());
            onlineDeviceState.setBat4_OutputCurrent(domainOnlineDeviceState1.getBat4_OutputCurrent());
            onlineDeviceState.setBat5_OutputCurrent(domainOnlineDeviceState1.getBat5_OutputCurrent());
            onlineDeviceState.setDVR_power_status(domainOnlineDeviceState1.getDVR_power_status());
            onlineDeviceState.setDVR_VisibleCamera(domainOnlineDeviceState1.getDVR_VisibleCamera());
            onlineDeviceState.setDVR_Router3G(domainOnlineDeviceState1.getDVR_Router3G());
            onlineDeviceState.setDVR_InfraredCamera(domainOnlineDeviceState1.getDVR_InfraredCamera());
            onlineDeviceState.setDVR_A9(domainOnlineDeviceState1.getDVR_A9());
            onlineDeviceState.setDVR_PTZ(domainOnlineDeviceState1.getDVR_PTZ());
            onlineDeviceState.setBat_GetChargeVoltage(domainOnlineDeviceState1.getBat_GetChargeVoltage());
            onlineDeviceState.setBat_GetChargeOutputCurrent(domainOnlineDeviceState1.getBat_GetChargeOutputCurrent());
            onlineDeviceState.setBat1_SolarPanelVoltage(domainOnlineDeviceState1.getBat1_SolarPanelVoltage());
            onlineDeviceState.setBat2_SolarPanelVoltage(domainOnlineDeviceState1.getBat2_SolarPanelVoltage());
            onlineDeviceState.setBat3_SolarPanelVoltage(domainOnlineDeviceState1.getBat3_SolarPanelVoltage());
            onlineDeviceState.setBat4_SolarPanelVoltage(domainOnlineDeviceState1.getBat4_SolarPanelVoltage());
            onlineDeviceState.setBat5_SolarPanelVoltage(domainOnlineDeviceState1.getBat5_SolarPanelVoltage());
            onlineDeviceState.setBat1_SolarPanelVoltage2(domainOnlineDeviceState1.getBat1_SolarPanelVoltage2());
            onlineDeviceState.setBat2_SolarPanelVoltage2(domainOnlineDeviceState1.getBat2_SolarPanelVoltage2());
            onlineDeviceState.setBat3_SolarPanelVoltage2(domainOnlineDeviceState1.getBat3_SolarPanelVoltage2());
            onlineDeviceState.setBat4_SolarPanelVoltage2(domainOnlineDeviceState1.getBat4_SolarPanelVoltage2());
            onlineDeviceState.setBat5_SolarPanelVoltage2(domainOnlineDeviceState1.getBat5_SolarPanelVoltage2());
            onlineDeviceState.setDVR_k60_SoftwareVer(domainOnlineDeviceState1.getDVR_k60_SoftwareVer());
            onlineDeviceState.setDVR_3G_Uart(domainOnlineDeviceState1.getDVR_3G_Uart());
            onlineDeviceState.setBat1_ChargeStaus(domainOnlineDeviceState1.getBat1_ChargeStaus());
            onlineDeviceState.setBat2_ChargeStaus(domainOnlineDeviceState1.getBat2_ChargeStaus());
            onlineDeviceState.setBat3_ChargeStaus(domainOnlineDeviceState1.getBat3_ChargeStaus());
            onlineDeviceState.setBat4_ChargeStaus(domainOnlineDeviceState1.getBat4_ChargeStaus());
            onlineDeviceState.setBat5_ChargeStaus(domainOnlineDeviceState1.getBat5_ChargeStaus());
            onlineDeviceState.setBat1_ChargeStaus2(domainOnlineDeviceState1.getBat1_ChargeStaus2());
            onlineDeviceState.setBat2_ChargeStaus2(domainOnlineDeviceState1.getBat2_ChargeStaus2());
            onlineDeviceState.setBat3_ChargeStaus2(domainOnlineDeviceState1.getBat3_ChargeStaus2());
            onlineDeviceState.setBat4_ChargeStaus2(domainOnlineDeviceState1.getBat4_ChargeStaus2());
            onlineDeviceState.setBat5_ChargeStaus2(domainOnlineDeviceState1.getBat5_ChargeStaus2());
            onlineDeviceState.setK60_SoftwareVer(domainOnlineDeviceState1.getK60_SoftwareVer());
            onlineDeviceState.setK60_HardWareVer(domainOnlineDeviceState1.getK60_HardWareVer());
            onlineDeviceState.setBat_GetChargeWork(domainOnlineDeviceState1.getBat_GetChargeWork());
            onlineDeviceState.setBat_GetChargeCollectCurrent(domainOnlineDeviceState1.getBat_GetChargeCollectCurrent());
            onlineDeviceState.setDVR_Signal_Intensity(domainOnlineDeviceState1.getDVR_Signal_Intensity());
            onlineDeviceState.setKEY_Negotiation_status(domainOnlineDeviceState1.getKEY_Negotiation_status());
            onlineDeviceState.setFailed_send_weather_num(domainOnlineDeviceState1.getFailed_send_weather_num());
            onlineDeviceState.setFailed_send_tower_slop_num(domainOnlineDeviceState1.getFailed_send_tower_slop_num());
            onlineDeviceState.setFailed_send_angle_num(domainOnlineDeviceState1.getFailed_send_angle_num());
            onlineDeviceState.setSend_flow(domainOnlineDeviceState1.getSend_flow());
            onlineDeviceState.setReceive_flow(domainOnlineDeviceState1.getReceive_flow());
            onlineDeviceState.setA9_Voltage(domainOnlineDeviceState1.getA9_Voltage());
            onlineDeviceState.setA9_Temperature(domainOnlineDeviceState1.getA9_Temperature());

        }
        return onlineDeviceState;
    }

    public static List<OnlineDeviceState> transform(Collection<DomainOnlineDeviceState> domainOnlineDeviceStates) {
        List<OnlineDeviceState> onlineDeviceStates = new ArrayList<>();
        OnlineDeviceState onlineDeviceState;
        for (DomainOnlineDeviceState domainOnlineDeviceState : domainOnlineDeviceStates) {
            onlineDeviceState = transform(domainOnlineDeviceState);
            if (onlineDeviceState != null) {
                onlineDeviceStates.add(onlineDeviceState);
            }
        }
        return onlineDeviceStates;
    }
}
