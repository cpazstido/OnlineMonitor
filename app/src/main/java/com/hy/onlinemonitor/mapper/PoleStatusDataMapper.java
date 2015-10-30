package com.hy.onlinemonitor.mapper;


import com.example.bean.DomainPoleStatus;
import com.hy.onlinemonitor.bean.PoleStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 24363 on 2015/10/29.
 */
public class PoleStatusDataMapper {
    
    public static PoleStatus transform(DomainPoleStatus poleStatusEntity) {
        PoleStatus domainPoleStatus = null;
        if (poleStatusEntity != null) {
            domainPoleStatus = new PoleStatus();
            domainPoleStatus.setSn(poleStatusEntity.getSn());
            domainPoleStatus.setSensorName(poleStatusEntity.getSensorName());
            domainPoleStatus.setCollectDataTimeStr(poleStatusEntity.getCollectDataTimeStr());
            domainPoleStatus.setDeviceSn(poleStatusEntity.getDeviceSn());
            domainPoleStatus.setAngleX(poleStatusEntity.getAngleX());
            domainPoleStatus.setAngleY(poleStatusEntity.getAngleY());
        }
        return domainPoleStatus;
    }

    public static List<PoleStatus> transform(Collection<DomainPoleStatus> domainPoleStatuses) {
        List<PoleStatus> poleStatuses = new ArrayList<>();
        PoleStatus poleStatus;
        for (DomainPoleStatus domainPoleStatus : domainPoleStatuses) {
            poleStatus = transform(domainPoleStatus);
            if (poleStatus != null) {
                poleStatuses.add(poleStatus);
            }
        }
        return poleStatuses;
    }
}
