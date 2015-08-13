package com.example.repository;

import com.example.bean.DomainEquipmentAlarmInformation;

import java.util.List;

import rx.Observable;

/**
 * Created by 24363 on 2015/8/13.
 */
public interface EquipmentRepository {

    Observable<List<DomainEquipmentAlarmInformation>> equipmentAlarmList();
}
