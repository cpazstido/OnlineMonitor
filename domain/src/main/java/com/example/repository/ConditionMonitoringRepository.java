package com.example.repository;

import com.example.bean.DomainConditionMonitoringPage;
import com.example.bean.DomainEquipmentInforPage;
import com.example.bean.DomainSensorType;

import java.util.List;

import rx.Observable;

/**
 * Created by 24363 on 2015/10/29.
 */
public interface ConditionMonitoringRepository {

    Observable<DomainConditionMonitoringPage> getAeolianVibration();

    Observable<DomainConditionMonitoringPage> getIceCoating();

    Observable<DomainConditionMonitoringPage> getConductorSag();

    Observable<DomainConditionMonitoringPage> getConductorSwingWithWind();

    Observable<DomainConditionMonitoringPage> getPoleStatus();

    Observable<DomainConditionMonitoringPage> getMicroclimate();

    Observable<List<DomainSensorType>> getConditionMonitoringType();

    Observable<DomainEquipmentInforPage> getEquipmentList();

}
