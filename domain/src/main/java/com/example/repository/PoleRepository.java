package com.example.repository;

import com.example.bean.DomainLine;

import java.util.List;

import rx.Observable;

/**
 * Created by 24363 on 2015/9/9.
 */
public interface PoleRepository {

    Observable<List<DomainLine>> getAllTower();


}
