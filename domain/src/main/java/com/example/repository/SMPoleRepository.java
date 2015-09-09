package com.example.repository;

import com.example.bean.DomainCompany;
import com.example.bean.DomainPolePage;

import java.util.List;

import rx.Observable;

/**
 * Created by 24363 on 2015/9/9.
 */
public interface SMPoleRepository {

    Observable<List<DomainCompany>> getAllLine();

    Observable<DomainPolePage> getPolePage();

    Observable<DomainPolePage> addPole();

    Observable<DomainPolePage> deletePole();

    Observable<DomainPolePage> changePole();


}
