package com.hy.data.entity.mapper;

import com.example.bean.DomainIceCoating;
import com.hy.data.entity.IceCoatingEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 24363 on 2015/10/29.
 */
public class IceCoatingEntityDataMapper {
    
    public static DomainIceCoating transform(IceCoatingEntity iceCoatingEntity) {
        DomainIceCoating domainIceCoating = null;
        if (iceCoatingEntity != null) {
            domainIceCoating = new DomainIceCoating();
            domainIceCoating.setSn(iceCoatingEntity.getSn());
            domainIceCoating.setSensorName(iceCoatingEntity.getSensorName());
            domainIceCoating.setCollectDataTimeStr(iceCoatingEntity.getCollectDataTimeStr());
            domainIceCoating.setDeviceSn(iceCoatingEntity.getDeviceSn());
            domainIceCoating.setEqualIceThickness(iceCoatingEntity.getEqualIceThickness());
            domainIceCoating.setWindageYawAngle(iceCoatingEntity.getWindageYawAngle());
            domainIceCoating.setDeflectionAngle(iceCoatingEntity.getDeflectionAngle());
            domainIceCoating.setTensionDifference(iceCoatingEntity.getTensionDifference());
            domainIceCoating.setTension(iceCoatingEntity.getTension());
        }

        return domainIceCoating;
    }

    public static List<DomainIceCoating> transform(Collection<IceCoatingEntity> iceCoatingEntities) {
        List<DomainIceCoating> domainIceCoatings = new ArrayList<>();
        DomainIceCoating domainIceCoating;
        for (IceCoatingEntity iceCoatingEntity : iceCoatingEntities) {
            domainIceCoating = transform(iceCoatingEntity);
            if (domainIceCoating != null) {
                domainIceCoatings.add(domainIceCoating);
            }
        }
        return domainIceCoatings;
    }
}
