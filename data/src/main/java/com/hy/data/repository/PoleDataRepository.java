package com.hy.data.repository;

import android.content.Context;

import com.example.bean.DomainCompany;
import com.example.bean.DomainPolePage;
import com.example.repository.SMPoleRepository;
import com.hy.data.entity.mapper.CompanyEntityDataMapper;
import com.hy.data.entity.mapper.LineEntityJsonMapper;
import com.hy.data.entity.mapper.PageEntityDataMapper;
import com.hy.data.entity.mapper.PageEntityJsonMapper;
import com.hy.data.net.RestApiImpl;

import java.util.List;

import rx.Observable;

/**
 * Created by 24363 on 2015/9/9.
 */
public class PoleDataRepository implements SMPoleRepository {
    private final Context mContext;
    private int userId;
    private int lineSn;
    private String poleName;
    private String longitude;
    private String latitude;
    private String altitude;
    private int poleSn;

    public PoleDataRepository(Context mContext, int userId) {
        this.mContext = mContext;
        this.userId = userId;
    }

    public PoleDataRepository(Context mContext, int userId, int lineSn) {
        this.mContext = mContext;
        this.userId = userId;
        this.lineSn = lineSn;
    }

    public PoleDataRepository(Context mContext) {
        this.mContext = mContext;
    }

    public PoleDataRepository(Context mContext,int userId, int lineSn, String poleName, String longitude, String latitude, String altitude, int poleSn) {
        this.mContext = mContext;
        this.userId = userId;
        this.lineSn = lineSn;
        this.poleName = poleName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.poleSn = poleSn;
    }

    public PoleDataRepository(Context mContext, int userId, int lineSn, String poleName, String longitude, String latitude, String altitude) {
        this.mContext = mContext;
        this.userId = userId;
        this.lineSn = lineSn;
        this.poleName = poleName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
    }

    @Override
    public Observable<List<DomainCompany>> getAllLine() {
        RestApiImpl restApi = new RestApiImpl(mContext,new LineEntityJsonMapper());
        CompanyEntityDataMapper companyEntityDataMapper = new CompanyEntityDataMapper();
        return restApi.getAllLine(userId).map(companyEntityDataMapper::transform);
    }

    @Override
    public Observable<DomainPolePage> getPolePage() {
        RestApiImpl restApi = new RestApiImpl(mContext,new PageEntityJsonMapper());
        PageEntityDataMapper pageEntityDataMapper = new PageEntityDataMapper();
        return restApi.getPolePage(userId, lineSn).map(pageEntityDataMapper::transform);
    }

    @Override
    public Observable<DomainPolePage> addPole() {
        RestApiImpl restApi = new RestApiImpl(mContext,new PageEntityJsonMapper());
        PageEntityDataMapper pageEntityDataMapper = new PageEntityDataMapper();
        return restApi.addPole(userId, lineSn,poleName,longitude,latitude,altitude).map(pageEntityDataMapper::transform);
    }

    @Override
    public Observable<DomainPolePage> deletePole() {
        RestApiImpl restApi = new RestApiImpl(mContext,new PageEntityJsonMapper());
        PageEntityDataMapper pageEntityDataMapper = new PageEntityDataMapper();
        return restApi.deletePole(userId, poleSn).map(pageEntityDataMapper::transform);
    }

    @Override
    public Observable<DomainPolePage> changePole() {
        RestApiImpl restApi = new RestApiImpl(mContext,new PageEntityJsonMapper());
        PageEntityDataMapper pageEntityDataMapper = new PageEntityDataMapper();
        return restApi.changePole(userId, poleSn,lineSn,poleName,longitude,latitude,altitude).map(pageEntityDataMapper::transform);
    }
}
