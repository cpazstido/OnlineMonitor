package com.hy.data.entity.mapper;

import com.example.bean.DomainOnlineDeviceState;
import com.hy.data.entity.OnlineDeviceStateEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OnlineDeviceStateEntityDataMapper {

    public static DomainOnlineDeviceState transform(OnlineDeviceStateEntity onlineDeviceStateEntity) {
        DomainOnlineDeviceState domainOnlineDeviceState = null;
        if (onlineDeviceStateEntity != null) {
            domainOnlineDeviceState = new DomainOnlineDeviceState();
            domainOnlineDeviceState.setSn(onlineDeviceStateEntity.getSn());
            domainOnlineDeviceState.setPoleName(onlineDeviceStateEntity.getPoleName());
            domainOnlineDeviceState.setDeviceSn(onlineDeviceStateEntity.getDeviceSn());
            domainOnlineDeviceState.setDeviceId(onlineDeviceStateEntity.getDeviceId());
            domainOnlineDeviceState.setIp(onlineDeviceStateEntity.getIp());
            domainOnlineDeviceState.setPort(onlineDeviceStateEntity.getPort());
            domainOnlineDeviceState.setBat_Capacity(onlineDeviceStateEntity.getBat_Capacity());
            domainOnlineDeviceState.setWork_Current(onlineDeviceStateEntity.getWork_Current());
            domainOnlineDeviceState.setBat_Consume_total(onlineDeviceStateEntity.getBat_Consume_total());
            domainOnlineDeviceState.setBat_Consume_Camer(onlineDeviceStateEntity.getBat_Consume_Camer());
            domainOnlineDeviceState.setBat_Consume_Sensor(onlineDeviceStateEntity.getBat_Consume_Sensor());
            domainOnlineDeviceState.setCharge_Capacity(onlineDeviceStateEntity.getCharge_Capacity());
            domainOnlineDeviceState.setTerminal_work_time(onlineDeviceStateEntity.getTerminal_work_time());
            domainOnlineDeviceState.setCamer_work_time(onlineDeviceStateEntity.getCamer_work_time());
            domainOnlineDeviceState.setTotal_Working_time(onlineDeviceStateEntity.getTotal_Working_time());
            domainOnlineDeviceState.setCamer_power_status(onlineDeviceStateEntity.getCamer_power_status());
            domainOnlineDeviceState.setPower_3G_status(onlineDeviceStateEntity.getPower_3G_status());
            domainOnlineDeviceState.setCharge_Currrent_1(onlineDeviceStateEntity.getCharge_Currrent_1());
            domainOnlineDeviceState.setCharge_Currrent_2(onlineDeviceStateEntity.getCharge_Currrent_2());
            domainOnlineDeviceState.setCharge_Currrent_3(onlineDeviceStateEntity.getCharge_Currrent_3());
            domainOnlineDeviceState.setCharge_Currrent_4(onlineDeviceStateEntity.getCharge_Currrent_4());
            domainOnlineDeviceState.setBat5_ChargeCurrrent(onlineDeviceStateEntity.getBat5_ChargeCurrrent());
            domainOnlineDeviceState.setCharge_Currrent_1_2(onlineDeviceStateEntity.getCharge_Currrent_1_2());
            domainOnlineDeviceState.setCharge_Currrent_2_2(onlineDeviceStateEntity.getCharge_Currrent_2_2());
            domainOnlineDeviceState.setCharge_Currrent_3_2(onlineDeviceStateEntity.getCharge_Currrent_3_2());
            domainOnlineDeviceState.setCharge_Currrent_4_2(onlineDeviceStateEntity.getCharge_Currrent_4_2());
            domainOnlineDeviceState.setBat5_ChargeCurrrent2(onlineDeviceStateEntity.getBat5_ChargeCurrrent2());
            domainOnlineDeviceState.setBat_GetChargeCurrrent(onlineDeviceStateEntity.getBat_GetChargeCurrrent());
            domainOnlineDeviceState.setBat_Dump_Energy(onlineDeviceStateEntity.getBat_Dump_Energy());
            domainOnlineDeviceState.setBat1_Voltage(onlineDeviceStateEntity.getBat1_Voltage());
            domainOnlineDeviceState.setBat2_Voltage(onlineDeviceStateEntity.getBat2_Voltage());
            domainOnlineDeviceState.setBat3_Voltage(onlineDeviceStateEntity.getBat3_Voltage());
            domainOnlineDeviceState.setBat4_Voltage(onlineDeviceStateEntity.getBat4_Voltage());
            domainOnlineDeviceState.setBat5_Voltage(onlineDeviceStateEntity.getBat5_Voltage());
            domainOnlineDeviceState.setBat1_RemainCapacity(onlineDeviceStateEntity.getBat1_RemainCapacity());
            domainOnlineDeviceState.setBat2_RemainCapacity(onlineDeviceStateEntity.getBat2_RemainCapacity());
            domainOnlineDeviceState.setBat3_RemainCapacity(onlineDeviceStateEntity.getBat3_RemainCapacity());
            domainOnlineDeviceState.setBat4_RemainCapacity(onlineDeviceStateEntity.getBat4_RemainCapacity());
            domainOnlineDeviceState.setBat5_RemainCapacity(onlineDeviceStateEntity.getBat5_RemainCapacity());
            domainOnlineDeviceState.setBat_GetChargeRemainCapacity(onlineDeviceStateEntity.getBat_GetChargeRemainCapacity());
            domainOnlineDeviceState.setBat1_ChargeCapacity(onlineDeviceStateEntity.getBat1_ChargeCapacity());
            domainOnlineDeviceState.setBat2_ChargeCapacity(onlineDeviceStateEntity.getBat2_ChargeCapacity());
            domainOnlineDeviceState.setBat3_ChargeCapacity(onlineDeviceStateEntity.getBat3_ChargeCapacity());
            domainOnlineDeviceState.setBat4_ChargeCapacity(onlineDeviceStateEntity.getBat4_ChargeCapacity());
            domainOnlineDeviceState.setBat1_UsedCapacity(onlineDeviceStateEntity.getBat1_UsedCapacity());
            domainOnlineDeviceState.setBat2_UsedCapacity(onlineDeviceStateEntity.getBat2_UsedCapacity());
            domainOnlineDeviceState.setBat3_UsedCapacity(onlineDeviceStateEntity.getBat3_UsedCapacity());
            domainOnlineDeviceState.setBat4_UsedCapacity(onlineDeviceStateEntity.getBat4_UsedCapacity());
            domainOnlineDeviceState.setOperation_Temperature(onlineDeviceStateEntity.getOperation_Temperature());
            domainOnlineDeviceState.setSignal_Intensity(onlineDeviceStateEntity.getSignal_Intensity());
            domainOnlineDeviceState.setBat1_OutputCurrent(onlineDeviceStateEntity.getBat1_OutputCurrent());
            domainOnlineDeviceState.setBat2_OutputCurrent(onlineDeviceStateEntity.getBat2_OutputCurrent());
            domainOnlineDeviceState.setBat3_OutputCurrent(onlineDeviceStateEntity.getBat3_OutputCurrent());
            domainOnlineDeviceState.setBat4_OutputCurrent(onlineDeviceStateEntity.getBat4_OutputCurrent());
            domainOnlineDeviceState.setBat5_OutputCurrent(onlineDeviceStateEntity.getBat5_OutputCurrent());
            domainOnlineDeviceState.setDVR_power_status(onlineDeviceStateEntity.getDVR_power_status());
            domainOnlineDeviceState.setDVR_VisibleCamera(onlineDeviceStateEntity.getDVR_VisibleCamera());
            domainOnlineDeviceState.setDVR_Router3G(onlineDeviceStateEntity.getDVR_Router3G());
            domainOnlineDeviceState.setDVR_InfraredCamera(onlineDeviceStateEntity.getDVR_InfraredCamera());
            domainOnlineDeviceState.setDVR_A9(onlineDeviceStateEntity.getDVR_A9());
            domainOnlineDeviceState.setDVR_PTZ(onlineDeviceStateEntity.getDVR_PTZ());
            domainOnlineDeviceState.setBat_GetChargeVoltage(onlineDeviceStateEntity.getBat_GetChargeVoltage());
            domainOnlineDeviceState.setBat_GetChargeOutputCurrent(onlineDeviceStateEntity.getBat_GetChargeOutputCurrent());
            domainOnlineDeviceState.setBat1_SolarPanelVoltage(onlineDeviceStateEntity.getBat1_SolarPanelVoltage());
            domainOnlineDeviceState.setBat2_SolarPanelVoltage(onlineDeviceStateEntity.getBat2_SolarPanelVoltage());
            domainOnlineDeviceState.setBat3_SolarPanelVoltage(onlineDeviceStateEntity.getBat3_SolarPanelVoltage());
            domainOnlineDeviceState.setBat4_SolarPanelVoltage(onlineDeviceStateEntity.getBat4_SolarPanelVoltage());
            domainOnlineDeviceState.setBat5_SolarPanelVoltage(onlineDeviceStateEntity.getBat5_SolarPanelVoltage());
            domainOnlineDeviceState.setBat1_SolarPanelVoltage2(onlineDeviceStateEntity.getBat1_SolarPanelVoltage2());
            domainOnlineDeviceState.setBat2_SolarPanelVoltage2(onlineDeviceStateEntity.getBat2_SolarPanelVoltage2());
            domainOnlineDeviceState.setBat3_SolarPanelVoltage2(onlineDeviceStateEntity.getBat3_SolarPanelVoltage2());
            domainOnlineDeviceState.setBat4_SolarPanelVoltage2(onlineDeviceStateEntity.getBat4_SolarPanelVoltage2());
            domainOnlineDeviceState.setBat5_SolarPanelVoltage2(onlineDeviceStateEntity.getBat5_SolarPanelVoltage2());
            domainOnlineDeviceState.setDVR_k60_SoftwareVer(onlineDeviceStateEntity.getDVR_k60_SoftwareVer());
            domainOnlineDeviceState.setDVR_3G_Uart(onlineDeviceStateEntity.getDVR_3G_Uart());
            domainOnlineDeviceState.setBat1_ChargeStaus(onlineDeviceStateEntity.getBat1_ChargeStaus());
            domainOnlineDeviceState.setBat2_ChargeStaus(onlineDeviceStateEntity.getBat2_ChargeStaus());
            domainOnlineDeviceState.setBat3_ChargeStaus(onlineDeviceStateEntity.getBat3_ChargeStaus());
            domainOnlineDeviceState.setBat4_ChargeStaus(onlineDeviceStateEntity.getBat4_ChargeStaus());
            domainOnlineDeviceState.setBat5_ChargeStaus(onlineDeviceStateEntity.getBat5_ChargeStaus());
            domainOnlineDeviceState.setBat1_ChargeStaus2(onlineDeviceStateEntity.getBat1_ChargeStaus2());
            domainOnlineDeviceState.setBat2_ChargeStaus2(onlineDeviceStateEntity.getBat2_ChargeStaus2());
            domainOnlineDeviceState.setBat3_ChargeStaus2(onlineDeviceStateEntity.getBat3_ChargeStaus2());
            domainOnlineDeviceState.setBat4_ChargeStaus2(onlineDeviceStateEntity.getBat4_ChargeStaus2());
            domainOnlineDeviceState.setBat5_ChargeStaus2(onlineDeviceStateEntity.getBat5_ChargeStaus2());
            domainOnlineDeviceState.setK60_SoftwareVer(onlineDeviceStateEntity.getK60_SoftwareVer());
            domainOnlineDeviceState.setK60_HardWareVer(onlineDeviceStateEntity.getK60_HardWareVer());
            domainOnlineDeviceState.setBat_GetChargeWork(onlineDeviceStateEntity.getBat_GetChargeWork());
            domainOnlineDeviceState.setBat_GetChargeCollectCurrent(onlineDeviceStateEntity.getBat_GetChargeCollectCurrent());
            domainOnlineDeviceState.setDVR_Signal_Intensity(onlineDeviceStateEntity.getDVR_Signal_Intensity());
            domainOnlineDeviceState.setKEY_Negotiation_status(onlineDeviceStateEntity.getKEY_Negotiation_status());
            domainOnlineDeviceState.setFailed_send_weather_num(onlineDeviceStateEntity.getFailed_send_weather_num());
            domainOnlineDeviceState.setFailed_send_tower_slop_num(onlineDeviceStateEntity.getFailed_send_tower_slop_num());
            domainOnlineDeviceState.setFailed_send_angle_num(onlineDeviceStateEntity.getFailed_send_angle_num());
            domainOnlineDeviceState.setSend_flow(onlineDeviceStateEntity.getSend_flow());
            domainOnlineDeviceState.setReceive_flow(onlineDeviceStateEntity.getReceive_flow());
            domainOnlineDeviceState.setA9_Voltage(onlineDeviceStateEntity.getA9_Voltage());
            domainOnlineDeviceState.setA9_Temperature(onlineDeviceStateEntity.getA9_Temperature());

        }
        return domainOnlineDeviceState;
    }

    public static List<DomainOnlineDeviceState> transform(Collection<OnlineDeviceStateEntity> onlineDeviceStateEntities) {
        List<DomainOnlineDeviceState> domainOnlineDeviceStates = new ArrayList<>();
        DomainOnlineDeviceState domainOnlineDeviceState;
        for (OnlineDeviceStateEntity onlineDeviceStateEntity : onlineDeviceStateEntities) {
            domainOnlineDeviceState = transform(onlineDeviceStateEntity);
            if (domainOnlineDeviceState != null) {
                domainOnlineDeviceStates.add(domainOnlineDeviceState);
            }
        }
        return domainOnlineDeviceStates;
    }
}
