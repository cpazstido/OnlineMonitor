package com.example.repository;

import com.example.bean.DomainEquipmentInformation;

import java.util.List;

import rx.Observable;

/**
 * Created by 24363 on 2015/8/13.
 */
public interface EquipmentRepository {

    Observable<List<DomainEquipmentInformation>> equipmentList();
}
