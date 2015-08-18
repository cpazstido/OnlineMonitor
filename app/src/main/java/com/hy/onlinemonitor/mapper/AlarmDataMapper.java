package com.hy.onlinemonitor.mapper;

import com.example.bean.DomainAlarmInformation;
import com.hy.onlinemonitor.bean.AlarmInformation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 24363 on 2015/8/18.
 */
public class AlarmDataMapper {
    public AlarmDataMapper() {}

    public AlarmInformation transform(DomainAlarmInformation domainAlarmInformation) {
        if (null == domainAlarmInformation) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        AlarmInformation alarmInformation = new AlarmInformation();

        alarmInformation.setVisibleLightImage(domainAlarmInformation.getVisibleLightImage());
        alarmInformation.setTypeAlarm(domainAlarmInformation.getTypeAlarm());
        alarmInformation.setIsHandle(domainAlarmInformation.getIsHandle());
        alarmInformation.setIsBlowingEquipment(domainAlarmInformation.getIsBlowingEquipment());
        alarmInformation.setAlarmInformation(domainAlarmInformation.getAlarmInformation());
        alarmInformation.setAlarmName(domainAlarmInformation.getAlarmName());
        alarmInformation.setInfraredImage(domainAlarmInformation.getInfraredImage());

        return alarmInformation;
    }

    public List<AlarmInformation> transform(Collection<DomainAlarmInformation> domainAlarmInformations){

        List<AlarmInformation> alarmInformations = new ArrayList<>();
        AlarmInformation alarmInformation;
        for (DomainAlarmInformation domainAlarmInformation : domainAlarmInformations) {
            alarmInformation = transform(domainAlarmInformation);
            if (alarmInformation != null) {
                alarmInformations.add(alarmInformation);
            }
        }

        return alarmInformations;
    }
}
