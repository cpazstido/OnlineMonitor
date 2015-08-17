package com.hy.data.entity.mapper;

import com.example.bean.DomainEquipmentAlarmInformation;
import com.hy.data.entity.EquipmentAlarmEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    public List<DomainEquipmentAlarmInformation> transform(Collection<EquipmentAlarmEntity> equipmentAlarmEntities) {
        List<DomainEquipmentAlarmInformation> equipmentAlarmInformationList = new ArrayList<>();
        DomainEquipmentAlarmInformation domainEquipmentAlarmInformation;
        for (EquipmentAlarmEntity equipmentAlarmEntity : equipmentAlarmEntities) {
            domainEquipmentAlarmInformation = transform(equipmentAlarmEntity);
            if (domainEquipmentAlarmInformation != null) {
                equipmentAlarmInformationList.add(domainEquipmentAlarmInformation);
            }
        }

        return equipmentAlarmInformationList;
    }
}
