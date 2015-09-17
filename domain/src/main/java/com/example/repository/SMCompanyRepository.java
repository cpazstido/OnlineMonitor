package com.example.repository;

import com.example.bean.DomainCompany;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2015/9/15.
 */
public interface SMCompanyRepository {
    Observable<List<DomainCompany>> getCompanyList();
    Observable<List<DomainCompany>> modifCompany();
    Observable<List<DomainCompany>> deleteCompany();
    Observable<List<DomainCompany>> addCompany();
    //得到上级公司列表
    Observable<List<DomainCompany>> getParentCompanyList();

}
