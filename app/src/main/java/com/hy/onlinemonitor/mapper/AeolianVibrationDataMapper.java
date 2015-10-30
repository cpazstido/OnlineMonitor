package com.hy.onlinemonitor.mapper;

import com.example.bean.DomainAeolianVibration;
import com.hy.onlinemonitor.bean.AeolianVibration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 24363 on 2015/10/29.
 */
public class AeolianVibrationDataMapper {
    
    public static AeolianVibration transform(DomainAeolianVibration domainAeolianVibration) {
        AeolianVibration aeolianVibration = null;
        if (domainAeolianVibration != null) {
            aeolianVibration = new AeolianVibration();
            aeolianVibration.setSn(domainAeolianVibration.getSn());
            aeolianVibration.setSensorName(domainAeolianVibration.getSensorName());
            aeolianVibration.setCollectDataTimeStr(domainAeolianVibration.getCollectDataTimeStr());
            aeolianVibration.setDeviceSn(domainAeolianVibration.getDeviceSn());
            aeolianVibration.setDynamicBendingStrain(domainAeolianVibration.getDynamicBendingStrain());
            aeolianVibration.setVibrationAmplitude(domainAeolianVibration.getVibrationAmplitude());
            aeolianVibration.setVibrationFrequencyround(domainAeolianVibration.getVibrationFrequencyround());
        }

        return aeolianVibration;
    }

    public static List<AeolianVibration> transform(Collection<DomainAeolianVibration> domainAeolianVibrations) {
        List<AeolianVibration> aeolianVibrations = new ArrayList<>();
        AeolianVibration aeolianVibration;
        for (DomainAeolianVibration domainAeolianVibration : domainAeolianVibrations) {
            aeolianVibration = transform(domainAeolianVibration);
            if (aeolianVibration != null) {
                aeolianVibrations.add(aeolianVibration);
            }
        }
        return aeolianVibrations;
    }
}
