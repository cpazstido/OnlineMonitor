package com.hy.onlinemonitor.mapper;


import com.example.bean.DomainIceCoating;
import com.hy.onlinemonitor.bean.IceCoatings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 24363 on 2015/10/29.
 */
public class IceCoatingDataMapper {
    
    public static IceCoatings transform(DomainIceCoating domainIceCoating) {
        IceCoatings iceCoatings = null;
        if (domainIceCoating != null) {
            iceCoatings = new IceCoatings();
            iceCoatings.setSn(domainIceCoating.getSn());
            iceCoatings.setSensorName(domainIceCoating.getSensorName());
            iceCoatings.setCollectDataTimeStr(domainIceCoating.getCollectDataTimeStr());
            iceCoatings.setDeviceSn(domainIceCoating.getDeviceSn());
            iceCoatings.setEqualIceThickness(domainIceCoating.getEqualIceThickness());
            iceCoatings.setWindageYawAngle(domainIceCoating.getWindageYawAngle());
            iceCoatings.setDeflectionAngle(domainIceCoating.getDeflectionAngle());
            iceCoatings.setTensionDifference(domainIceCoating.getTensionDifference());
            iceCoatings.setTension(domainIceCoating.getTension());
        }

        return iceCoatings;
    }

    public static List<IceCoatings> transform(Collection<DomainIceCoating> domainIceCoatings) {
        List<IceCoatings> iceCoatingses = new ArrayList<>();
        IceCoatings iceCoating;
        for (DomainIceCoating domainIceCoating : domainIceCoatings) {
            iceCoating = transform(domainIceCoating);
            if (iceCoating != null) {
                iceCoatingses.add(iceCoating);
            }
        }
        return iceCoatingses;
    }
}
