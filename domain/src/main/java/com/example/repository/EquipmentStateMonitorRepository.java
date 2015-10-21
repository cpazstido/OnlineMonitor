package com.example.repository;

import com.example.bean.DomainCompany;

import java.util.List;

import rx.Observable;

/**
 * Created by 24363 on 2015/10/17.
 */
public interface EquipmentStateMonitorRepository {
    Observable<List<DomainCompany>> getAllLine();

    Observable loadDeviceInformation();
}
