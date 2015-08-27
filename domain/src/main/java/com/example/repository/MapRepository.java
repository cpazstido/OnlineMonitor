package com.example.repository;

import com.example.bean.DomainMap;

import java.util.List;

import rx.Observable;

public interface MapRepository {

    Observable<List<DomainMap>> mapList();

}
