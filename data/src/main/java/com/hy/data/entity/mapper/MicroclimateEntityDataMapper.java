package com.hy.data.entity.mapper;

import com.example.bean.DomainMicroclimate;
import com.hy.data.entity.MicroclimateEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 24363 on 2015/10/29.
 */
public class MicroclimateEntityDataMapper {
    public static DomainMicroclimate transform(MicroclimateEntity microclimateEntity) {
        DomainMicroclimate domainMicroclimate = null;
        if (microclimateEntity != null) {
            domainMicroclimate = new DomainMicroclimate();
            domainMicroclimate.setSn(microclimateEntity.getSn());
            domainMicroclimate.setSensorName(microclimateEntity.getSensorName());
            domainMicroclimate.setCollectDataTimeStr(microclimateEntity.getCollectDataTimeStr());
            domainMicroclimate.setDeviceSn(microclimateEntity.getDeviceSn());
            domainMicroclimate.setAirPressure(microclimateEntity.getAirPressure());
            domainMicroclimate.setAirTemperature(microclimateEntity.getAirTemperature());
            domainMicroclimate.setAverageWindDirection10Min(microclimateEntity.getAverageWindDirection10Min());
            domainMicroclimate.setAverageWindSpeed10Min(microclimateEntity.getAverageWindSpeed10Min());
            domainMicroclimate.setExtremeWindSpeed(microclimateEntity.getExtremeWindSpeed());
            domainMicroclimate.setMaxWindSpeed(microclimateEntity.getMaxWindSpeed());
            domainMicroclimate.setPrecipitation(microclimateEntity.getPrecipitation());
            domainMicroclimate.setPrecipitationIntensity(microclimateEntity.getPrecipitationIntensity());
            domainMicroclimate.setRadiationIntensity(microclimateEntity.getRadiationIntensity());
            domainMicroclimate.setStandardWindSpeed(microclimateEntity.getStandardWindSpeed());
            domainMicroclimate.setHumidity(microclimateEntity.getHumidity());
        }
        return domainMicroclimate;
    }

    public static List<DomainMicroclimate> transform(Collection<MicroclimateEntity> microclimateEntities) {
        List<DomainMicroclimate> domainMicroclimates = new ArrayList<>();
        DomainMicroclimate domainMicroclimate;
        for (MicroclimateEntity microclimateEntity : microclimateEntities) {
            domainMicroclimate = transform(microclimateEntity);
            if (domainMicroclimate != null) {
                domainMicroclimates.add(domainMicroclimate);
            }
        }
        return domainMicroclimates;
    }
}
