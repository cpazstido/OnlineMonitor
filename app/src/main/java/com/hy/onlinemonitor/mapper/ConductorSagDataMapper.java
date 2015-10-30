package com.hy.onlinemonitor.mapper;

import com.example.bean.DomainConductorSag;
import com.hy.onlinemonitor.bean.ConductorSag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 24363 on 2015/10/29.
 */
public class ConductorSagDataMapper {
    
    public static ConductorSag transform(DomainConductorSag domainConductorSag) {
        ConductorSag conductorSag = null;
        if (domainConductorSag != null) {
            conductorSag = new ConductorSag();
            conductorSag.setSn(domainConductorSag.getSn());
            conductorSag.setSensorName(domainConductorSag.getSensorName());
            conductorSag.setCollectDataTimeStr(domainConductorSag.getCollectDataTimeStr());
            conductorSag.setDeviceSn(domainConductorSag.getDeviceSn());
            conductorSag.setAngleabs(domainConductorSag.getAngleabs());
            conductorSag.setSag(domainConductorSag.getSag());
            conductorSag.setTestmeasure(domainConductorSag.getTestmeasure());
        }

        return conductorSag;
    }

    public static List<ConductorSag> transform(Collection<DomainConductorSag> domainConductorSags) {
        List<ConductorSag> conductorSags = new ArrayList<>();
        ConductorSag conductorSag;
        for (DomainConductorSag conductorSagEntity : domainConductorSags) {
            conductorSag = transform(conductorSagEntity);
            if (conductorSag != null) {
                conductorSags.add(conductorSag);
            }
        }
        return conductorSags;
    }
}
