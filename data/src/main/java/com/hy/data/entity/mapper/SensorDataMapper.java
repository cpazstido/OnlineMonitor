package com.hy.data.entity.mapper;

import com.example.bean.DomainSensor;
import com.hy.data.entity.SensorEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 24363 on 2015/9/11.
 */
public class SensorDataMapper {
    public SensorDataMapper() {
    }

    public static DomainSensor transform (SensorEntity sensorEntity){
        DomainSensor domainSensor = null;

        if (sensorEntity != null) {
            domainSensor = new DomainSensor();
            domainSensor.setSn(sensorEntity.getSn());
            domainSensor.setCount(sensorEntity.getCount());
            domainSensor.setInfo(sensorEntity.getInfo());
            domainSensor.setName(sensorEntity.getName());
            domainSensor.setSensorInDeviceID(sensorEntity.getSensorInDeviceID());
        }

        return domainSensor;
    }

    public static List<DomainSensor> transform(Collection<SensorEntity> sensorEntities) {
        List<DomainSensor> domainSensors = new ArrayList<>();
        DomainSensor domainSensor;
        for (SensorEntity sensorEntity : sensorEntities) {
            domainSensor = transform(sensorEntity);
            if (domainSensor != null) {
                domainSensors.add(domainSensor);
            }
        }
        return domainSensors;
    }
}
