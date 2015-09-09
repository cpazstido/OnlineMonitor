package com.hy.data.repository;

import android.content.Context;

import com.example.bean.DomainCompany;
import com.example.bean.DomainLinePage;
import com.example.repository.SMLineRepository;
import com.hy.data.entity.mapper.CompanyEntityDataMapper;
import com.hy.data.entity.mapper.CompanyEntityJsonMapper;
import com.hy.data.entity.mapper.PageEntityDataMapper;
import com.hy.data.entity.mapper.PageEntityJsonMapper;
import com.hy.data.net.RestApiImpl;

import java.util.List;

import rx.Observable;

/**
 * Created by 24363 on 2015/9/8.
 */
public class LineDataRepository implements SMLineRepository{
    private final Context mContext;
    private int userId;
    private int companySn;
    private String lineName;
    private String lineStart;
    private String lineFinish;
    private String lineTrend;
    private String voltageLevel;
    private int lineSn;

    public LineDataRepository(Context mContext, int userId, int companySn, String lineName, String lineStart, String lineFinish, String lineTrend, String voltageLevel) {
        this.mContext = mContext;
        this.userId = userId;
        this.companySn = companySn;
        this.lineName = lineName;
        this.lineStart = lineStart;
        this.lineFinish = lineFinish;
        this.lineTrend = lineTrend;
        this.voltageLevel = voltageLevel;
    }

    public LineDataRepository(Context mContext, int userId, int companySn, String lineName, String lineStart, String lineFinish, String lineTrend, String voltageLevel, int lineSn) {
        this.mContext = mContext;
        this.userId = userId;
        this.companySn = companySn;
        this.lineName = lineName;
        this.lineStart = lineStart;
        this.lineFinish = lineFinish;
        this.lineTrend = lineTrend;
        this.voltageLevel = voltageLevel;
        this.lineSn = lineSn;
    }

    public LineDataRepository(Context mContext, int userId) {
        this.mContext = mContext;
        this.userId = userId;
    }

    public LineDataRepository(Context mContext, int userId, int companySn) {
        this.mContext = mContext;
        this.userId = userId;
        this.companySn = companySn;
    }

    public LineDataRepository(Context mContext, int userId, int lineSn, String isDelete) {
        this.mContext = mContext;
        this.userId = userId;
        this.lineSn = lineSn;
    }

    @Override
    public Observable<List<DomainCompany>> getCompanyList() {
        RestApiImpl restApi = new RestApiImpl(mContext,new CompanyEntityJsonMapper());
        CompanyEntityDataMapper companyEntityDataMapper = new CompanyEntityDataMapper();
        return restApi.companyList(userId).map(companyEntityDataMapper::transform);
    }

    @Override
    public Observable<DomainLinePage> getLinePage() {
        RestApiImpl restApi = new RestApiImpl(mContext,new PageEntityJsonMapper());
        PageEntityDataMapper pageEntityDataMapper = new PageEntityDataMapper();
        return restApi.getLinePage(userId, companySn).map(pageEntityDataMapper::transform);
    }

    @Override
    public Observable<DomainLinePage> addLine() {
        RestApiImpl restApi = new RestApiImpl(mContext,new PageEntityJsonMapper());
        PageEntityDataMapper pageEntityDataMapper = new PageEntityDataMapper();
        return restApi.addLine(userId,companySn,lineName,lineStart,lineFinish,lineTrend,voltageLevel).map(pageEntityDataMapper::transform);
    }

    @Override
    public Observable<DomainLinePage> deleteLine() {
        RestApiImpl restApi = new RestApiImpl(mContext,new PageEntityJsonMapper());
        PageEntityDataMapper pageEntityDataMapper = new PageEntityDataMapper();
        return restApi.deleteLine(userId,lineSn).map(pageEntityDataMapper::transform);
    }

    @Override
    public Observable<DomainLinePage> changeLine() {
        RestApiImpl restApi = new RestApiImpl(mContext,new PageEntityJsonMapper());
        PageEntityDataMapper pageEntityDataMapper = new PageEntityDataMapper();
        return restApi.changeLine(userId,companySn,lineSn,lineName,lineStart,lineFinish,lineTrend,voltageLevel).map(pageEntityDataMapper::transform);
    }

    @Override
    public Observable<DomainLinePage> getAllLine() {
        RestApiImpl restApi = new RestApiImpl(mContext,new PageEntityJsonMapper());
        PageEntityDataMapper pageEntityDataMapper = new PageEntityDataMapper();
        return restApi.getAllLine(userId).map(pageEntityDataMapper::transform);
    }
}
