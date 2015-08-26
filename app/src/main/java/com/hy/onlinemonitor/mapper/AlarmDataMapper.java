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

        alarmInformation.setAlarmSn(domainAlarmInformation.getAlarmSn());
        alarmInformation.setDeviceSn(domainAlarmInformation.getDeviceSn());
        alarmInformation.setPoleName(domainAlarmInformation.getPoleName());
        alarmInformation.setDeviceId(domainAlarmInformation.getDeviceId());
        alarmInformation.setDvrID(domainAlarmInformation.getDvrID());
        alarmInformation.setAlarmInformation(domainAlarmInformation.getAlarmInformation());
        alarmInformation.setVisibleLightImage(domainAlarmInformation.getVisibleLightImage());
        alarmInformation.setInfraredImage(domainAlarmInformation.getInfraredImage());
        alarmInformation.setBreakImage(domainAlarmInformation.getBreakImage());
        alarmInformation.setIsBlowingEquipment(domainAlarmInformation.getIsBlowingEquipment());
        alarmInformation.setVideoFileName(domainAlarmInformation.getVideoFileName());
        alarmInformation.setCollectionTime(domainAlarmInformation.getCollectionTime());
        alarmInformation.setLineName(domainAlarmInformation.getLineName());

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
