package com.example.repository;

import com.example.bean.DomainEquipmentInforPage;

import rx.Observable;

public interface EquipmentInforRepository {

    Observable<DomainEquipmentInforPage> equipmentList();
}
