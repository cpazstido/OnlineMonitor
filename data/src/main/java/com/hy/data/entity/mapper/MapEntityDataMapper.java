package com.hy.data.entity.mapper;

import com.example.bean.DomainMap;
import com.hy.data.entity.MapEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 24363 on 2015/8/19.
 */
public class MapEntityDataMapper {
    public MapEntityDataMapper() {
    }

    public DomainMap transform (MapEntity mapEntity){
        DomainMap domainMap = null;

        if (mapEntity != null) {
            domainMap = new DomainMap();
            domainMap.setEquipmentName(mapEntity.getDeviceName());
            domainMap.setDvrType(mapEntity.getDvrType());
            domainMap.setLatitude(mapEntity.getLat());
            domainMap.setLongitude(mapEntity.getLng());
            domainMap.setDvrID(mapEntity.getDvrID());
            domainMap.setPoleName(mapEntity.getPoleName());
        }

        return domainMap;
    }

    public List<DomainMap> transform(Collection<MapEntity> mapEntities) {
        List<DomainMap> domainMaps = new ArrayList<>();
        DomainMap domainMap;
        for (MapEntity mapEntity : mapEntities) {
            domainMap = transform(mapEntity);
            if (domainMap != null) {
                domainMaps.add(domainMap);
            }
        }
        return domainMaps;
    }
}
