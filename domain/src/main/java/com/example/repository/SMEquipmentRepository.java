package com.example.repository;

import com.example.bean.DomainEquipmentPage;
import com.example.bean.DomainLine;

import java.util.List;

import rx.Observable;

/**
 * Created by 24363 on 2015/9/10.
 */
public interface SMEquipmentRepository {

    Observable<List<DomainLine>> getAllTower();

    Observable<DomainEquipmentPage> getEquipmentPage();

    Observable<DomainEquipmentPage> addEquipment();

    Observable<DomainEquipmentPage> deleteEquipment();

    Observable<DomainEquipmentPage> changeEquipment();

    Observable<String> restartEquipment();

}
