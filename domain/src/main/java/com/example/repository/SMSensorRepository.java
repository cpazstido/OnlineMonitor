package com.example.repository;

import com.example.bean.DomainSensorType;

import java.util.List;

import rx.Observable;

/**
 * Created by 24363 on 2015/9/14.
 */
public interface SMSensorRepository {

    Observable<List<DomainSensorType>> getAllSensorType();

    Observable<String> changeSensor();
}
