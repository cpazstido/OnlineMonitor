package com.example.repository;

import com.example.bean.DoaminEquipmentPage;

import rx.Observable;

public interface EquipmentRepository {

    Observable<DoaminEquipmentPage> equipmentList();
}
