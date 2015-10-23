package com.example.repository;

import com.example.bean.DomainCompany;
import com.example.bean.DomainOnlineDeviceStatePage;

import java.util.List;

import rx.Observable;

public interface EquipmentStateMonitorRepository {
    Observable<List<DomainCompany>> getAllLine();

    Observable<DomainOnlineDeviceStatePage> loadOnlineDeviceState();
}
