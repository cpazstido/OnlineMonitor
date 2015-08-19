package com.example.repository;

import com.example.bean.DomainMap;

import java.util.List;

import rx.Observable;

/**
 * Created by 24363 on 2015/8/19.
 */
public interface MapRepository {

    Observable<List<DomainMap>> mapList();

}
