package com.hy.data.entity.mapper;

import com.example.bean.DomainAlarmInformation;
import com.hy.data.entity.AlarmEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AlarmEntityDataMapper {
    public AlarmEntityDataMapper() {
    }

    public DomainAlarmInformation transform(AlarmEntity alarmEntity) {
        DomainAlarmInformation domainAlarmInformation = null;

        if (alarmEntity != null) {
            domainAlarmInformation = new DomainAlarmInformation();
            domainAlarmInformation.setAlarmSn(alarmEntity.getAlarmSn());
            domainAlarmInformation.setDeviceSn(alarmEntity.getDeviceSn());
            domainAlarmInformation.setPoleName(alarmEntity.getPoleName());
            domainAlarmInformation.setDeviceId(alarmEntity.getDeviceId());
            domainAlarmInformation.setDvrID(alarmEntity.getDvrID());
            domainAlarmInformation.setAlarmInformation(alarmEntity.getAlarmInformation());
            domainAlarmInformation.setVisibleLightImage(alarmEntity.getVisibleLightImage());
            domainAlarmInformation.setInfraredImage(alarmEntity.getInfraredImage());
            domainAlarmInformation.setBreakImage(alarmEntity.getBrokenImage());
            domainAlarmInformation.setIsBlowingEquipment(alarmEntity.getIsBlowingEquipment());
            domainAlarmInformation.setVideoFileName(alarmEntity.getVideoFileName());
            domainAlarmInformation.setCollectionTime(alarmEntity.getCollectionTime());
            domainAlarmInformation.setLineName(alarmEntity.getCircuitName());
        }

        return domainAlarmInformation;
    }

    public List<DomainAlarmInformation> transform(Collection<AlarmEntity> alarmEntities) {
        List<DomainAlarmInformation> domainAlarmInformations = new ArrayList<>();
        DomainAlarmInformation domainAlarmInformation;
        for (AlarmEntity alarmEntity : alarmEntities) {
            domainAlarmInformation = transform(alarmEntity);
            if (domainAlarmInformation != null) {
                domainAlarmInformations.add(domainAlarmInformation);
            }
        }
        return domainAlarmInformations;
    }

}
