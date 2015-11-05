package com.hy.onlinemonitor.mapper;

import com.example.bean.DomainEquipmentInformation;
import com.hy.onlinemonitor.bean.EquipmentInformation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EquipmentInforDataMapper {
    public EquipmentInforDataMapper() {
    }

    public EquipmentInformation transform(DomainEquipmentInformation domainEquipmentInformation) {
        if (null == domainEquipmentInformation) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        EquipmentInformation equipmentInformation = new EquipmentInformation();

        equipmentInformation.setSn(domainEquipmentInformation.getSN());
        equipmentInformation.setNewSensorAlarm(domainEquipmentInformation.getNewSensorAlarm());
        equipmentInformation.setNewFireAlarm(domainEquipmentInformation.getNewFireAlarm());
        equipmentInformation.setNewBreakAlarm(domainEquipmentInformation.getNewBreakAlarm());
        equipmentInformation.setHistorySensorAlarm(domainEquipmentInformation.getHistorySensorAlarm());
        equipmentInformation.setHistoryFireAlarm(domainEquipmentInformation.getHistoryFireAlarm());
        equipmentInformation.setHistoryBreakAlarm(domainEquipmentInformation.getHistoryBreakAlarm());
        equipmentInformation.setEquipmnetState(domainEquipmentInformation.getEquipmnetState());
        equipmentInformation.setEquipmnetName(domainEquipmentInformation.getEquipmnetName());
        equipmentInformation.setLineName(domainEquipmentInformation.getLineName());
        equipmentInformation.setPoleName(domainEquipmentInformation.getPoleName());
        equipmentInformation.setDvrId(domainEquipmentInformation.getDvrId());
        equipmentInformation.setDvrType(domainEquipmentInformation.getDvrType());
        return equipmentInformation;
    }

    public List<EquipmentInformation> transform(Collection<DomainEquipmentInformation> domainEquipmentInformations) {
        List<EquipmentInformation> equipmentInformations = new ArrayList<>();
        EquipmentInformation equipmentInformation;
        if(domainEquipmentInformations!=null){
            for (DomainEquipmentInformation domainEquipmentInformation : domainEquipmentInformations) {
                equipmentInformation = transform(domainEquipmentInformation);
                if (equipmentInformation != null) {
                    equipmentInformations.add(equipmentInformation);
                }
            }
        }

        return equipmentInformations;
    }

}
