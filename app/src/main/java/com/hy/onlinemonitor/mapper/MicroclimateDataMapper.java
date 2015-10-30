package com.hy.onlinemonitor.mapper;


import com.example.bean.DomainMicroclimate;
import com.hy.onlinemonitor.bean.Microclimate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 24363 on 2015/10/29.
 */
public class MicroclimateDataMapper {
    
    public static Microclimate transform(DomainMicroclimate domainMicroclimate) {
        Microclimate microclimate = null;
        if (domainMicroclimate != null) {
            microclimate = new Microclimate();
            microclimate.setSn(domainMicroclimate.getSn());
            microclimate.setSensorName(domainMicroclimate.getSensorName());
            microclimate.setCollectDataTimeStr(domainMicroclimate.getCollectDataTimeStr());
            microclimate.setDeviceSn(domainMicroclimate.getDeviceSn());
            microclimate.setAirPressure(domainMicroclimate.getAirPressure());
            microclimate.setAirTemperature(domainMicroclimate.getAirTemperature());
            microclimate.setAverageWindDirection10Min(domainMicroclimate.getAverageWindDirection10Min());
            microclimate.setAverageWindSpeed10Min(domainMicroclimate.getAverageWindSpeed10Min());
            microclimate.setExtremeWindSpeed(domainMicroclimate.getExtremeWindSpeed());
            microclimate.setMaxWindSpeed(domainMicroclimate.getMaxWindSpeed());
            microclimate.setPrecipitation(domainMicroclimate.getPrecipitation());
            microclimate.setPrecipitationIntensity(domainMicroclimate.getPrecipitationIntensity());
            microclimate.setRadiationIntensity(domainMicroclimate.getRadiationIntensity());
            microclimate.setStandardWindSpeed(domainMicroclimate.getStandardWindSpeed());
            microclimate.setHumidity(domainMicroclimate.getHumidity());
        }
        return microclimate;
    }

    public static List<Microclimate> transform(Collection<DomainMicroclimate> domainMicroclimates) {
        List<Microclimate> microclimates = new ArrayList<>();
        Microclimate microclimate;
        for (DomainMicroclimate domainMicroclimate : domainMicroclimates) {
            microclimate = transform(domainMicroclimate);
            if (microclimate != null) {
                microclimates.add(microclimate);
            }
        }
        return microclimates;
    }
}
