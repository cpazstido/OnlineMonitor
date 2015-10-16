package com.example.repository;

import com.example.bean.DomainEquipmentInforPage;

import java.util.TreeMap;

import rx.Observable;

public interface EquipmentConditionMonitorRepository {

    Observable<DomainEquipmentInforPage> getEquipmentList();

    Observable<TreeMap<String,Float>> queryConditionMonitorData();

}
