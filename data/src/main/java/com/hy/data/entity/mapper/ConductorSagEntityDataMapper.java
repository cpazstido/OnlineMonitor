package com.hy.data.entity.mapper;

import com.example.bean.DomainConductorSag;
import com.hy.data.entity.ConductorSagEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ConductorSagEntityDataMapper {
    public static DomainConductorSag transform(ConductorSagEntity conductorSagEntity) {
        DomainConductorSag domainConductorSag = null;
        if (conductorSagEntity != null) {
            domainConductorSag = new DomainConductorSag();
            domainConductorSag.setSn(conductorSagEntity.getSn());
            domainConductorSag.setSensorName(conductorSagEntity.getSensorName());
            domainConductorSag.setCollectDataTimeStr(conductorSagEntity.getCollectDataTimeStr());
            domainConductorSag.setDeviceSn(conductorSagEntity.getDeviceSn());
            domainConductorSag.setAngleabs(conductorSagEntity.getAngleabs());
            domainConductorSag.setSag(conductorSagEntity.getSag());
            domainConductorSag.setTestmeasure(conductorSagEntity.getTestmeasure());
        }

        return domainConductorSag;
    }

    public static List<DomainConductorSag> transform(Collection<ConductorSagEntity> conductorSagEntities) {
        List<DomainConductorSag> domainConductorSags = new ArrayList<>();
        DomainConductorSag domainConductorSag;
        for (ConductorSagEntity conductorSagEntity : conductorSagEntities) {
            domainConductorSag = transform(conductorSagEntity);
            if (domainConductorSag != null) {
                domainConductorSags.add(domainConductorSag);
            }
        }
        return domainConductorSags;
    }
}
