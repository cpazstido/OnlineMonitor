package com.hy.data.entity.mapper;

import com.example.bean.DomainEquipment;
import com.hy.data.entity.EquipmentEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 24363 on 2015/9/7.
 */
public class EquipmentDataMapper {
    public EquipmentDataMapper() {
    }

    public DomainEquipment transform(EquipmentEntity equipmentEntity) {
        DomainEquipment domainEquipment = null;

        if (equipmentEntity != null) {
            domainEquipment = new DomainEquipment();
        }
        return domainEquipment;
    }

    public List<DomainEquipment> transform(Collection<EquipmentEntity> equipmentEntities) {
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
