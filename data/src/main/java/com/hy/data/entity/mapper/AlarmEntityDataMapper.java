package com.hy.data.entity.mapper;

import com.example.bean.DomainAlarmInformation;
import com.hy.data.entity.AlarmEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 24363 on 2015/8/18.
 */
public class AlarmEntityDataMapper {
    public AlarmEntityDataMapper() {
    }

    public DomainAlarmInformation transform (AlarmEntity alarmEntity){
        DomainAlarmInformation domainAlarmInformation = null;

        if (alarmEntity != null) {
            domainAlarmInformation = new DomainAlarmInformation();
            domainAlarmInformation.setAlarmInformation(alarmEntity.getAlarmInformation());
            domainAlarmInformation.setAlarmName(alarmEntity.getAlarmName());
            domainAlarmInformation.setInfraredImage(alarmEntity.getInfraredImage());
            domainAlarmInformation.setIsBlowingEquipment(alarmEntity.getIsBlowingEquipment());
            domainAlarmInformation.setIsHandle(alarmEntity.getIsHandle());
            domainAlarmInformation.setTypeAlarm(alarmEntity.getTypeAlarm());
            domainAlarmInformation.setVisibleLightImage(alarmEntity.getVisibleLightImage());
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
