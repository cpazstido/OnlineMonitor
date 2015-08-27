package com.hy.data.entity.mapper;

import com.example.bean.DomainEquipmentInformation;
import com.hy.data.entity.EquipmentEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 24363 on 2015/8/13.
 */
public class EquipmentAlarmEntityDataMapper {
    public EquipmentAlarmEntityDataMapper() {
    }

    public DomainEquipmentInformation transform(EquipmentEntity equipmentEntity) {
        DomainEquipmentInformation domainEquipmentInformation = null;

        if (equipmentEntity != null) {
            domainEquipmentInformation = new DomainEquipmentInformation();
            domainEquipmentInformation.setSN(equipmentEntity.getSN());
            domainEquipmentInformation.setEquipmnetName(equipmentEntity.getEquipmnetName());
            domainEquipmentInformation.setEquipmnetState(equipmentEntity.getEquipmnetState());
            domainEquipmentInformation.setNewBreakAlarm(equipmentEntity.getNewBreakAlarm());
            domainEquipmentInformation.setNewFireAlarm(equipmentEntity.getNewFireAlarm());
            domainEquipmentInformation.setNewSensorAlarm(equipmentEntity.getNewSensorAlarm());
            domainEquipmentInformation.setDvrType(equipmentEntity.getDvrType());
            domainEquipmentInformation.setDvrId(domainEquipmentInformation.getDvrId());
        }

        return domainEquipmentInformation;
    }

    public List<DomainEquipmentInformation> transform(Collection<EquipmentEntity> equipmentAlarmEntities) {
        List<DomainEquipmentInformation> equipmentAlarmInformationList = new ArrayList<>();
        DomainEquipmentInformation domainEquipmentInformation;
        for (EquipmentEntity equipmentEntity : equipmentAlarmEntities) {
            domainEquipmentInformation = transform(equipmentEntity);
            if (domainEquipmentInformation != null) {
                equipmentAlarmInformationList.add(domainEquipmentInformation);
            }
        }

        return equipmentAlarmInformationList;
    }
}
