package com.hy.data.repository;

import android.content.Context;

import com.example.bean.DomainCompany;
import com.example.repository.SMCompanyRepository;
import com.hy.data.entity.mapper.CompanyEntityDataMapper;
import com.hy.data.entity.mapper.CompanyEntityJsonMapper;
import com.hy.data.net.RestApiImpl;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2015/9/15.
 */
public class CompanyDataRepository implements SMCompanyRepository {
    private final Context mContext;
    private int userId;
    private String companyName;
    private String companyAddress;
    private int sn;
    public CompanyDataRepository(Context mContext, int userId) {
        this.mContext = mContext;
        this.userId = userId;
    }

    public CompanyDataRepository(Context mContext, int userId, String companyName, String address, int sn) {
        this.mContext = mContext;
        this.userId = userId;
        this.companyName = companyName;
        this.companyAddress = address;
        this.sn = sn;
    }

    public CompanyDataRepository(Context mContext, int userId, int sn) {
        this.sn=sn;
        this.userId=userId;
        this.mContext=mContext;
    }

    @Override
    public Observable<List<DomainCompany>> getCompanyList() {
        RestApiImpl restApi = new RestApiImpl(mContext,new CompanyEntityJsonMapper());
        CompanyEntityDataMapper companyEntityDataMapper = new CompanyEntityDataMapper();
        return restApi.companyList(userId).map(companyEntityDataMapper::transform);
    }

    @Override
    public Observable<List<DomainCompany>> modifCompany() {
        RestApiImpl restApi = new RestApiImpl(mContext,new CompanyEntityJsonMapper());
        CompanyEntityDataMapper companyEntityDataMapper = new CompanyEntityDataMapper();
        return restApi.changeCompany(userId,sn,companyName,companyAddress).map(companyEntityDataMapper::transform);

    }

    @Override
    public Observable<List<DomainCompany>> deleteCompany() {
        RestApiImpl restApi = new RestApiImpl(mContext,new CompanyEntityJsonMapper());
        CompanyEntityDataMapper companyEntityDataMapper = new CompanyEntityDataMapper();
        return restApi.deleteCompany(userId, sn).map(companyEntityDataMapper::transform);
    }

    @Override
    public Observable<List<DomainCompany>> addCompany() {
        RestApiImpl restApi = new RestApiImpl(mContext,new CompanyEntityJsonMapper());
        CompanyEntityDataMapper companyEntityDataMapper = new CompanyEntityDataMapper();
        return restApi.addCompany(this.userId,this.sn,this.companyName,this.companyAddress).map(companyEntityDataMapper::transform);
    }

    @Override
    public Observable<List<DomainCompany>> getParentCompanyList() {
        RestApiImpl restApi = new RestApiImpl(mContext,new CompanyEntityJsonMapper());
        CompanyEntityDataMapper companyEntityDataMapper = new CompanyEntityDataMapper();
        return restApi.queryParetSelectCompany(userId).map(companyEntityDataMapper::transform);
    }
}
