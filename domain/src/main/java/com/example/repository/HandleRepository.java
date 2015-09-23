package com.example.repository;

import rx.Observable;

/**
 * Created by 24363 on 2015/9/23.
 */
public interface HandleRepository {

    Observable<String> handleAlarm();

}
