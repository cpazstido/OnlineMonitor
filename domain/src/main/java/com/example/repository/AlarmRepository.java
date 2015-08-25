package com.example.repository;

import com.example.bean.DomainAlarmPage;

import rx.Observable;

/**
 * Created by 24363 on 2015/8/18.
 */
public interface AlarmRepository {

    Observable<DomainAlarmPage> alarmList(String equipmentName);
    
    Observable<DomainAlarmPage> alarmList();

}
