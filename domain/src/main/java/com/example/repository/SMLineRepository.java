package com.example.repository;

import com.example.bean.DomainCompany;
import com.example.bean.DomainLinePage;

import java.util.List;

import rx.Observable;

/**
 * Created by 24363 on 2015/9/8.
 */
public interface SMLineRepository {
    Observable<List<DomainCompany>> getCompanyList();

    Observable<DomainLinePage>  getLinePage();

    Observable<DomainLinePage>  addLine();

    Observable<DomainLinePage>  deleteLine();

    Observable<DomainLinePage>  changeLine();

    Observable<DomainLinePage> getAllLine();
}
