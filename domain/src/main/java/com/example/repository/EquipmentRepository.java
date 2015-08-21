package com.example.repository;

import com.example.bean.DoaminEquipmentPage;

import rx.Observable;

/**
 * Created by 24363 on 2015/8/13.
 */
public interface EquipmentRepository {

    Observable<DoaminEquipmentPage> equipmentList();
}
