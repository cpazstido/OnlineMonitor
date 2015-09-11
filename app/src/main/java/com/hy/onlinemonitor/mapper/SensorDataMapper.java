package com.hy.onlinemonitor.mapper;

import com.example.bean.DomainSensor;
import com.hy.onlinemonitor.bean.Sensor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 24363 on 2015/9/11.
 */
public class SensorDataMapper {
    public SensorDataMapper() {}

    public static Sensor transform(DomainSensor domainSensor) {
        if (null == domainSensor) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        Sensor sensor = new Sensor();
        sensor.setSn(domainSensor.getSn());
        sensor.setSensorInDeviceID(domainSensor.getSensorInDeviceID());
        sensor.setName(domainSensor.getName());
        sensor.setInfo(domainSensor.getInfo());
        sensor.setCount(domainSensor.getCount());
        return sensor;
    }

    public static List<Sensor> transform(Collection<DomainSensor> domainRoles){
        List<Sensor> sensors = new ArrayList<>();
        Sensor sensor;
        for (DomainSensor domainSensor : domainRoles) {
            sensor = transform(domainSensor);
            if (sensor != null) {
                sensors.add(sensor);
            }
        }
        return sensors;
    }
}
