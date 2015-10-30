package com.hy.onlinemonitor.mapper;


import com.example.bean.DomainConductorSwingWithWind;
import com.hy.onlinemonitor.bean.ConductorSwingWithWind;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 24363 on 2015/10/29.
 */
public class ConductorSwingWithWindDataMapper {
    
    public static ConductorSwingWithWind transform(DomainConductorSwingWithWind domainConductorSwingWithWind) {
        ConductorSwingWithWind conductorSwingWithWind = null;
        if (domainConductorSwingWithWind != null) {
            conductorSwingWithWind = new ConductorSwingWithWind();
            conductorSwingWithWind.setSn(domainConductorSwingWithWind.getSn());
            conductorSwingWithWind.setSensorName(domainConductorSwingWithWind.getSensorName());
            conductorSwingWithWind.setCollectDataTimeStr(domainConductorSwingWithWind.getCollectDataTimeStr());
            conductorSwingWithWind.setDeviceSn(domainConductorSwingWithWind.getDeviceSn());
            conductorSwingWithWind.setDeflectionAngle(domainConductorSwingWithWind.getDeflectionAngle());
            conductorSwingWithWind.setLeastClearance(domainConductorSwingWithWind.getLeastClearance());
            conductorSwingWithWind.setWindageYawAngle(domainConductorSwingWithWind.getWindageYawAngle());
        }

        return conductorSwingWithWind;
    }

    public static List<ConductorSwingWithWind> transform(Collection<DomainConductorSwingWithWind> domainConductorSwingWithWinds) {
        List<ConductorSwingWithWind> conductorSwingWithWinds = new ArrayList<>();
        ConductorSwingWithWind conductorSwingWithWind;
        for (DomainConductorSwingWithWind domainConductorSwingWithWind : domainConductorSwingWithWinds) {
            conductorSwingWithWind = transform(domainConductorSwingWithWind);
            if (conductorSwingWithWind != null) {
                conductorSwingWithWinds.add(conductorSwingWithWind);
            }
        }
        return conductorSwingWithWinds;
    }
}
