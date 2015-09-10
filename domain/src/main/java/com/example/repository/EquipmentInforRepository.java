package com.example.repository;

import com.example.bean.DoaminEquipmentInforPage;

import rx.Observable;

public interface EquipmentInforRepository {

    Observable<DoaminEquipmentInforPage> equipmentList();
}
