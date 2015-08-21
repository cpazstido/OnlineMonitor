package com.example.repository;

import com.example.bean.DomainAlarmInformation;

import java.util.List;

import rx.Observable;

/**
 * Created by 24363 on 2015/8/18.
 */
public interface AlarmRepository {

    Observable<List<DomainAlarmInformation>> alarmList(String title);
    
    Observable<List<DomainAlarmInformation>> alarmList(int equipmentSn);

}
