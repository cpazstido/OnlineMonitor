package com.hy.onlinemonitor.mapper;

import com.example.bean.DomainEquipmentAlarmInformation;
import com.hy.onlinemonitor.bean.EquipmentAlarmInformation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 24363 on 2015/8/13.
 */
public class EquipmentDataMapper {
    public EquipmentDataMapper() {}

    public EquipmentAlarmInformation transform(DomainEquipmentAlarmInformation domainEquipmentAlarmInformation) {
        if (null == domainEquipmentAlarmInformation) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        EquipmentAlarmInformation equipmentAlarmInformation = new EquipmentAlarmInformation();

        equipmentAlarmInformation.setSN(domainEquipmentAlarmInformation.getSN());
        equipmentAlarmInformation.setNewSensorAlarm(domainEquipmentAlarmInformation.getNewSensorAlarm());
        equipmentAlarmInformation.setNewFireAlarm(domainEquipmentAlarmInformation.getNewFireAlarm());
        equipmentAlarmInformation.setNewBreakAlarm(domainEquipmentAlarmInformation.getNewBreakAlarm());
        equipmentAlarmInformation.setEquipmnetState(domainEquipmentAlarmInformation.getEquipmnetState());
        equipmentAlarmInformation.setEquipmnetName(domainEquipmentAlarmInformation.getEquipmnetName());

        return equipmentAlarmInformation;
    }

    public List<EquipmentAlarmInformation> transform(Collection<DomainEquipmentAlarmInformation> domainEquipmentAlarmInformations){

        List<EquipmentAlarmInformation> equipmentAlarmInformations = new ArrayList<>();
        EquipmentAlarmInformation equipmentAlarmInformation;
        for (DomainEquipmentAlarmInformation domainEquipmentAlarmInformation : domainEquipmentAlarmInformations) {
            equipmentAlarmInformation = transform(domainEquipmentAlarmInformation);
            if (equipmentAlarmInformation != null) {
                equipmentAlarmInformations.add(equipmentAlarmInformation);
            }
        }

        return equipmentAlarmInformations;
    }

}
