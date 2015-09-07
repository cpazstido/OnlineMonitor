package com.hy.onlinemonitor.mapper;

import com.example.bean.DomainEquipment;
import com.hy.onlinemonitor.bean.Equipment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EquipmentDataMapper {
    public EquipmentDataMapper() {}

    public Equipment transform(DomainEquipment domainEquipment) {
        if (null == domainEquipment) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        Equipment equipment = new Equipment();

        return equipment;
    }

    public List<Equipment> transform(Collection<DomainEquipment> domainEquipments){
        List<Equipment> equipments = new ArrayList<>();
        Equipment equipment;
        for (DomainEquipment domainEquipment : domainEquipments) {
            equipment = transform(domainEquipment);
            if (equipment != null) {
                equipments.add(equipment);
            }
        }

        return equipments;
    }

}
