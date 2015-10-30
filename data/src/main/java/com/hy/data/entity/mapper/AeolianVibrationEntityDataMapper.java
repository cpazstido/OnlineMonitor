package com.hy.data.entity.mapper;

import com.example.bean.DomainAeolianVibration;
import com.hy.data.entity.AeolianVibrationEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AeolianVibrationEntityDataMapper {

    public static DomainAeolianVibration transform(AeolianVibrationEntity aeolianVibrationEntity) {
        DomainAeolianVibration domainAeolianVibration = null;
        if (aeolianVibrationEntity != null) {
            domainAeolianVibration = new DomainAeolianVibration();
            domainAeolianVibration.setSn(aeolianVibrationEntity.getSn());
            domainAeolianVibration.setSensorName(aeolianVibrationEntity.getSensorName());
            domainAeolianVibration.setCollectDataTimeStr(aeolianVibrationEntity.getCollectDataTimeStr());
            domainAeolianVibration.setDeviceSn(aeolianVibrationEntity.getDeviceSn());
            domainAeolianVibration.setDynamicBendingStrain(aeolianVibrationEntity.getDynamicBendingStrain());
            domainAeolianVibration.setVibrationAmplitude(aeolianVibrationEntity.getVibrationAmplitude());
            domainAeolianVibration.setVibrationFrequencyround(aeolianVibrationEntity.getVibrationFrequencyround());
        }
        return domainAeolianVibration;
    }

    public static List<DomainAeolianVibration> transform(Collection<AeolianVibrationEntity> aeolianVibrationEntities) {
        List<DomainAeolianVibration> domainAeolianVibrations = new ArrayList<>();
        DomainAeolianVibration domainAeolianVibration;
        for (AeolianVibrationEntity aeolianVibrationEntity : aeolianVibrationEntities) {
            domainAeolianVibration = transform(aeolianVibrationEntity);
            if (domainAeolianVibration != null) {
                domainAeolianVibrations.add(domainAeolianVibration);
            }
        }
        return domainAeolianVibrations;
    }
}
