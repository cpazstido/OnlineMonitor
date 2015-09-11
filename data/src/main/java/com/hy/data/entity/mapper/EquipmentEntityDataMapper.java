package com.hy.data.entity.mapper;

import com.example.bean.DomainEquipment;
import com.hy.data.entity.EquipmentEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 24363 on 2015/9/7.
 */
public class EquipmentEntityDataMapper {
    public EquipmentEntityDataMapper() {
    }

    public static DomainEquipment transform(EquipmentEntity equipmentEntity) {
        DomainEquipment domainEquipment = null;
        if (equipmentEntity != null) {
            domainEquipment = new DomainEquipment();

            domainEquipment.setEquipment_ID(equipmentEntity.getEquipment_ID());
            domainEquipment.setDvrID(equipmentEntity.getDvrID());
            domainEquipment.setDeviceID(equipmentEntity.getDeviceID());
            domainEquipment.setCma_ID(equipmentEntity.getCma_ID());
            domainEquipment.setAngleRelativeToNorthPole(equipmentEntity.getAngleRelativeToNorthPole());
            domainEquipment.setDeviceType(equipmentEntity.getDeviceType());
            domainEquipment.setDvrType(equipmentEntity.getDvrType());
            domainEquipment.setSendMmsState(equipmentEntity.getSendMmsState());
            domainEquipment.setSensor_ID(equipmentEntity.getSensor_ID());
            domainEquipment.setSensorType(equipmentEntity.getSensorType());
            domainEquipment.setSn(equipmentEntity.getSn());
            if(equipmentEntity.getSensorInDeviceSet() !=null) {
                domainEquipment.setSensorInDeviceSet(SensorDataMapper.transform(equipmentEntity.getSensorInDeviceSet()));
            }
        }
        return domainEquipment;
    }


    public static List<DomainEquipment> transform(Collection<EquipmentEntity> equipmentEntities) {
        List<DomainEquipment> domainEquipments = new ArrayList<>();
        DomainEquipment domainEquipment;
        for (EquipmentEntity equipmentEntity : equipmentEntities) {
            domainEquipment = transform(equipmentEntity);
            if (domainEquipment != null) {
                domainEquipments.add(domainEquipment);
            }
        }
        return domainEquipments;
    }


}
