package com.hy.onlinemonitor.mapper;

import com.example.bean.DomainEquipmentInformation;
import com.hy.onlinemonitor.bean.EquipmentInformation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 24363 on 2015/8/13.
 */
public class EquipmentDataMapper {
    public EquipmentDataMapper() {}

    public EquipmentInformation transform(DomainEquipmentInformation domainEquipmentInformation) {
        if (null == domainEquipmentInformation) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        EquipmentInformation equipmentInformation = new EquipmentInformation();

        equipmentInformation.setSN(domainEquipmentInformation.getSN());
        equipmentInformation.setNewSensorAlarm(domainEquipmentInformation.getNewSensorAlarm());
        equipmentInformation.setNewFireAlarm(domainEquipmentInformation.getNewFireAlarm());
        equipmentInformation.setNewBreakAlarm(domainEquipmentInformation.getNewBreakAlarm());
        equipmentInformation.setEquipmnetState(domainEquipmentInformation.getEquipmnetState());
        equipmentInformation.setEquipmnetName(domainEquipmentInformation.getEquipmnetName());

        return equipmentInformation;
    }

    public List<EquipmentInformation> transform(Collection<DomainEquipmentInformation> domainEquipmentInformations){
        List<EquipmentInformation> equipmentInformations = new ArrayList<>();
        EquipmentInformation equipmentInformation;
        for (DomainEquipmentInformation domainEquipmentInformation : domainEquipmentInformations) {
            equipmentInformation = transform(domainEquipmentInformation);
            if (equipmentInformation != null) {
                equipmentInformations.add(equipmentInformation);
            }
        }

        return equipmentInformations;
    }

}
