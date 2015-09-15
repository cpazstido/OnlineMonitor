package com.hy.data.entity.mapper;

import com.example.bean.DomainSensorType;
import com.hy.data.entity.SensorTypeEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SensorTypeEntityDataMapper {
    public SensorTypeEntityDataMapper() {
    }

    public static DomainSensorType transform (SensorTypeEntity sensorTypeEntity){
        DomainSensorType domainSensorType = null;

        if (sensorTypeEntity != null) {
            domainSensorType = new DomainSensorType();
            domainSensorType.setSn(sensorTypeEntity.getSn());
            domainSensorType.setSensorID(sensorTypeEntity.getSensorID());
            domainSensorType.setSensorMarke(sensorTypeEntity.getSensorMarke());
            domainSensorType.setSensorName(sensorTypeEntity.getSensorName());
        }

        return domainSensorType;
    }

    public static List<DomainSensorType> transform(Collection<SensorTypeEntity> sensorTypeEntities) {
        List<DomainSensorType> domainSensorTypes = new ArrayList<>();
        DomainSensorType domainSensorType;
        for (SensorTypeEntity sensorTypeEntity : sensorTypeEntities) {
            domainSensorType = transform(sensorTypeEntity);
            if (domainSensorType != null) {
                domainSensorTypes.add(domainSensorType);
            }
        }
        return domainSensorTypes;
    }
}
