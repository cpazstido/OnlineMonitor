package com.hy.onlinemonitor.mapper;

import com.example.bean.DomainMap;
import com.hy.onlinemonitor.bean.Map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 24363 on 2015/8/19.
 */
public class MapDataMapper {
    public Map transform(DomainMap domainMap) {
        if (null == domainMap) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        Map map = new Map();
        map.setLongitude(domainMap.getLongitude());
        map.setLatitude(domainMap.getLatitude());
        map.setEquipmentName(domainMap.getEquipmentName());
        map.setDvrID(domainMap.getDvrID());
        map.setDvrType(domainMap.getDvrType());
        map.setPoleName(domainMap.getPoleName());
        return map;
    }

    public List<Map> transform(Collection<DomainMap> domainMaps){

        List<Map> maps = new ArrayList<>();
        Map map;
        for (DomainMap domainMap : domainMaps) {
            map = transform(domainMap);
            if (map != null) {
                maps.add(map);
            }
        }
        return maps;
    }
}
