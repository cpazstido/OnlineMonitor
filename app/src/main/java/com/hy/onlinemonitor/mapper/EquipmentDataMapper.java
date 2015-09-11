package com.hy.onlinemonitor.mapper;

import com.example.bean.DomainEquipment;
import com.hy.onlinemonitor.bean.Equipment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EquipmentDataMapper {
    public EquipmentDataMapper() {}

    public static Equipment transform(DomainEquipment domainEquipment) {
        if (null == domainEquipment) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        Equipment equipment = new Equipment();
        equipment.setSn(domainEquipment.getSn());
        equipment.setAngleRelativeToNorthPole(domainEquipment.getAngleRelativeToNorthPole());
        equipment.setCma_ID(domainEquipment.getCma_ID());
        equipment.setDeviceID(domainEquipment.getDeviceID());
        equipment.setDeviceType(domainEquipment.getDeviceType());
        equipment.setDvrID(domainEquipment.getDvrID());
        equipment.setDvrType(domainEquipment.getDvrType());
        equipment.setEquipment_ID(domainEquipment.getEquipment_ID());
        equipment.setSendMmsState(domainEquipment.getSendMmsState());
        equipment.setSensor_ID(domainEquipment.getSensor_ID());
        equipment.setSensorType(domainEquipment.getSensorType());
        if(domainEquipment.getSensorInDeviceSet() !=null){
            equipment.setSensorInDeviceSet(SensorDataMapper.transform(domainEquipment.getSensorInDeviceSet()));
        }
        return equipment;
    }

    public static List<Equipment> transform(Collection<DomainEquipment> domainEquipments){
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
