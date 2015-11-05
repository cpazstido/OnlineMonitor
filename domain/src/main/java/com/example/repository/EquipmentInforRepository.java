package com.example.repository;

import com.example.bean.DomainEquipmentInforPage;
import com.example.bean.DomainLine;

import java.util.List;

import rx.Observable;

public interface EquipmentInforRepository {

    Observable<DomainEquipmentInforPage> equipmentList();

    Observable<DomainEquipmentInforPage> searchByLineSn();

    Observable<DomainEquipmentInforPage> searchByName();

    Observable<List<DomainLine>> getAllTower();

}
