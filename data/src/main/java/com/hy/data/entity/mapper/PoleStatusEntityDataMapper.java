package com.hy.data.entity.mapper;

import com.example.bean.DomainPoleStatus;
import com.hy.data.entity.PoleStatusEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 24363 on 2015/10/29.
 */
public class PoleStatusEntityDataMapper {

    public static DomainPoleStatus transform(PoleStatusEntity poleStatusEntity) {
        DomainPoleStatus domainPoleStatus = null;
        if (poleStatusEntity != null) {
            domainPoleStatus = new DomainPoleStatus();
            domainPoleStatus.setSn(poleStatusEntity.getSn());
            domainPoleStatus.setSensorName(poleStatusEntity.getSensorName());
            domainPoleStatus.setCollectDataTimeStr(poleStatusEntity.getCollectDataTimeStr());
            domainPoleStatus.setDeviceSn(poleStatusEntity.getDeviceSn());
            domainPoleStatus.setAngleX(poleStatusEntity.getAngleX());
            domainPoleStatus.setAngleY(poleStatusEntity.getAngleY());
        }
        return domainPoleStatus;
    }

    public static List<DomainPoleStatus> transform(Collection<PoleStatusEntity> poleStatusEntities) {
        List<DomainPoleStatus> domainPoleStatuses = new ArrayList<>();
        DomainPoleStatus domainPoleStatus;
        for (PoleStatusEntity poleStatusEntity : poleStatusEntities) {
            domainPoleStatus = transform(poleStatusEntity);
            if (domainPoleStatus != null) {
                domainPoleStatuses.add(domainPoleStatus);
            }
        }
        return domainPoleStatuses;
    }
}
