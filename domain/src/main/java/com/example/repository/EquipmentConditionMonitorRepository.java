package com.example.repository;

import com.example.bean.DomainEquipment;

import java.util.List;
import java.util.TreeMap;

import rx.Observable;

public interface EquipmentConditionMonitorRepository {

    Observable<List<DomainEquipment>> getEquipmentList();

    Observable<TreeMap<String,Float>> queryConditionMonitorData();

}
