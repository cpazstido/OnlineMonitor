package com.example.repository;

import com.example.bean.DomainRolePage;

import rx.Observable;

/**
 * Created by 24363 on 2015/9/7.
 */
public interface SMJurisdictionRepository {

    Observable<DomainRolePage> getRolePage();

    Observable<DomainRolePage> addRole();

    Observable<DomainRolePage> changeRole();

    Observable<DomainRolePage> deleteRole();

    Observable<String> jurisdictionChange();

}
