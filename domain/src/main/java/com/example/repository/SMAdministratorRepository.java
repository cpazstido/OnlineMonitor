package com.example.repository;

import com.example.bean.DomainLine;
import com.example.bean.DomainAdministratorPage;
import com.example.bean.DomainCompany;
import com.example.bean.DomainRole;

import java.util.List;

import rx.Observable;

/**
 * Created by 24363 on 2015/8/28.
 */
public interface SMAdministratorRepository {

    Observable<List<DomainCompany>> companyList();

    Observable<List<DomainRole>> roleList();

    Observable<DomainAdministratorPage> administratorList();

    Observable<DomainAdministratorPage> addAdministrator();

    Observable<DomainAdministratorPage> changeAdministrator();

    Observable<DomainAdministratorPage> deleteAdministrator();

    Observable<List<DomainLine>> getAllTower();

    Observable<List<Integer>> getOwnTower();

    Observable<String> changeManageTower();
}
