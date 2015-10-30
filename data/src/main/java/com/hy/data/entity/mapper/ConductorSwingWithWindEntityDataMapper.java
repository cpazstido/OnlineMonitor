package com.hy.data.entity.mapper;

import com.example.bean.DomainConductorSwingWithWind;
import com.hy.data.entity.ConductorSwingWithWindEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 24363 on 2015/10/29.
 */
public class ConductorSwingWithWindEntityDataMapper {
    
    public static DomainConductorSwingWithWind transform(ConductorSwingWithWindEntity conductorSwingWithWindEntity) {
        DomainConductorSwingWithWind domainConductorSwingWithWind = null;
        if (conductorSwingWithWindEntity != null) {
            domainConductorSwingWithWind = new DomainConductorSwingWithWind();
            domainConductorSwingWithWind.setSn(conductorSwingWithWindEntity.getSn());
            domainConductorSwingWithWind.setSensorName(conductorSwingWithWindEntity.getSensorName());
            domainConductorSwingWithWind.setCollectDataTimeStr(conductorSwingWithWindEntity.getCollectDataTimeStr());
            domainConductorSwingWithWind.setDeviceSn(conductorSwingWithWindEntity.getDeviceSn());
            domainConductorSwingWithWind.setDeflectionAngle(conductorSwingWithWindEntity.getDeflectionAngle());
            domainConductorSwingWithWind.setLeastClearance(conductorSwingWithWindEntity.getLeastClearance());
            domainConductorSwingWithWind.setWindageYawAngle(conductorSwingWithWindEntity.getWindageYawAngle());
        }

        return domainConductorSwingWithWind;
    }

    public static List<DomainConductorSwingWithWind> transform(Collection<ConductorSwingWithWindEntity> conductorSwingWithWindEntities) {
        List<DomainConductorSwingWithWind> domainConductorSwingWithWinds = new ArrayList<>();
        DomainConductorSwingWithWind domainConductorSwingWithWind;
        for (ConductorSwingWithWindEntity conductorSwingWithWindEntity : conductorSwingWithWindEntities) {
            domainConductorSwingWithWind = transform(conductorSwingWithWindEntity);
            if (domainConductorSwingWithWind != null) {
                domainConductorSwingWithWinds.add(domainConductorSwingWithWind);
            }
        }
        return domainConductorSwingWithWinds;
    }
}
