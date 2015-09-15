package com.hy.onlinemonitor.mapper;

import com.example.bean.DomainSensorType;
import com.hy.onlinemonitor.bean.SensorType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SensoTypeDataMapper {
    public SensoTypeDataMapper() {}

    public static SensorType transform(DomainSensorType domainSensorType) {
        if (null == domainSensorType) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        SensorType sensorType = new SensorType();
        sensorType.setSn(domainSensorType.getSn());
        sensorType.setSensorName(domainSensorType.getSensorName());
        sensorType.setSensorMarke(domainSensorType.getSensorMarke());
        sensorType.setSensorID(domainSensorType.getSensorID());
        return sensorType;
    }

    public static List<SensorType> transform(Collection<DomainSensorType> domainSensorTypes){
        List<SensorType> sensorTypes = new ArrayList<>();
        SensorType sensorType;
        for (DomainSensorType domainSensorType : domainSensorTypes) {
            sensorType = transform(domainSensorType);
            if (sensorType != null) {
                sensorTypes.add(sensorType);
            }
        }
        return sensorTypes;
    }
}
