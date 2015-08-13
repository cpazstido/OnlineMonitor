package com.hy.data.entity.mapper;

import com.example.bean.DomainEquipmentAlarmInformation;
import com.hy.data.entity.EquipmentAlarmEntity;

/**
 * Created by 24363 on 2015/8/13.
 */
public class EquipmentAlarmEntityDataMapper {
    public EquipmentAlarmEntityDataMapper() {
    }

    public DomainEquipmentAlarmInformation transform(EquipmentAlarmEntity equipmentAlarmEntity) {
        DomainEquipmentAlarmInformation domainEquipmentAlarmInformation = null;

        if (equipmentAlarmEntity != null) {
            domainEquipmentAlarmInformation = new DomainEquipmentAlarmInformation();
            domainEquipmentAlarmInformation.setSN(equipmentAlarmEntity.getSN());
            domainEquipmentAlarmInformation.setEquipmnetName(equipmentAlarmEntity.getEquipmnetName());
            domainEquipmentAlarmInformation.setEquipmnetState(equipmentAlarmEntity.getEquipmnetState());
            domainEquipmentAlarmInformation.setNewBreakAlarm(equipmentAlarmEntity.getNewBreakAlarm());
            domainEquipmentAlarmInformation.setNewFireAlarm(equipmentAlarmEntity.getNewFireAlarm());
            domainEquipmentAlarmInformation.setNewSensorAlarm(equipmentAlarmEntity.getNewSensorAlarm());
        }

        return domainEquipmentAlarmInformation;
    }

}
