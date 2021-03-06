package com.hy.data.entity.mapper;

import com.example.bean.DomainPole;
import com.hy.data.entity.PoleEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 24363 on 2015/9/2.
 */
public class PoleEntityDataMapper {
    public PoleEntityDataMapper() {
    }

    public static DomainPole transform(PoleEntity poleEntity) {
        DomainPole domainPole = null;

        if (poleEntity != null) {
            domainPole = new DomainPole();
            domainPole.setPoleSn(poleEntity.getSn());
            domainPole.setPoleName(poleEntity.getName());
            if(poleEntity.getEquipmentList()!=null){
                EquipmentEntityDataMapper equipmentEntityDataMapper = new EquipmentEntityDataMapper();
                domainPole.setEquipmentList(equipmentEntityDataMapper.transform(poleEntity.getEquipmentList()));
            }
            if(poleEntity.getLongitude()!= null){
                domainPole.setAltitude(poleEntity.getAltitude());
                domainPole.setLongitude(poleEntity.getLongitude());
                domainPole.setLatitude(poleEntity.getLatitude());
            }
        }
        return domainPole;
    }

    public static List<DomainPole> transform(Collection<PoleEntity> adminTowerEntities) {
        List<DomainPole> domainPoles = new ArrayList<>();
        DomainPole domainPole;
        for (PoleEntity poleEntity : adminTowerEntities) {
            domainPole = transform(poleEntity);
            if (domainPole != null) {
                domainPoles.add(domainPole);
            }
        }
        return domainPoles;
    }
}
