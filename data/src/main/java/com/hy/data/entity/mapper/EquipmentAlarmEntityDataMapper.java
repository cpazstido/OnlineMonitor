package com.hy.data.entity.mapper;

import com.example.bean.DomainEquipmentInformation;
import com.hy.data.entity.EquipmentInforEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 24363 on 2015/8/13.
 */
public class EquipmentAlarmEntityDataMapper {
    public EquipmentAlarmEntityDataMapper() {
    }

    public DomainEquipmentInformation transform(EquipmentInforEntity equipmentInforEntity) {
        DomainEquipmentInformation domainEquipmentInformation = null;

        if (equipmentInforEntity != null) {
            domainEquipmentInformation = new DomainEquipmentInformation();
            domainEquipmentInformation.setSN(equipmentInforEntity.getSn());
            domainEquipmentInformation.setEquipmnetName(equipmentInforEntity.getEquipmnetName());
            domainEquipmentInformation.setPoleName(equipmentInforEntity.getPoleName());
            domainEquipmentInformation.setLineName(equipmentInforEntity.getCircuitName());
            domainEquipmentInformation.setEquipmnetState(equipmentInforEntity.getEquipmnetState());
            domainEquipmentInformation.setNewBreakAlarm(equipmentInforEntity.getNewBreakAlarm());
            domainEquipmentInformation.setNewFireAlarm(equipmentInforEntity.getNewFireAlarm());
            domainEquipmentInformation.setNewSensorAlarm(equipmentInforEntity.getNewSensorAlarm());
            domainEquipmentInformation.setDvrType(equipmentInforEntity.getDvrType());
            domainEquipmentInformation.setDvrId(equipmentInforEntity.getDvrID());
        }

        return domainEquipmentInformation;
    }

    public List<DomainEquipmentInformation> transform(Collection<EquipmentInforEntity> equipmentAlarmEntities) {
        List<DomainEquipmentInformation> equipmentAlarmInformationList = new ArrayList<>();
        DomainEquipmentInformation domainEquipmentInformation;
        for (EquipmentInforEntity equipmentInforEntity : equipmentAlarmEntities) {
            domainEquipmentInformation = transform(equipmentInforEntity);
            if (domainEquipmentInformation != null) {
                equipmentAlarmInformationList.add(domainEquipmentInformation);
            }
        }

        return equipmentAlarmInformationList;
    }
}
