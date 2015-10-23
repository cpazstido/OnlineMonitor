package com.hy.data.repository;

import android.content.Context;

import com.example.bean.DomainCompany;
import com.example.bean.DomainOnlineDeviceStatePage;
import com.example.repository.EquipmentStateMonitorRepository;
import com.hy.data.entity.mapper.CompanyEntityDataMapper;
import com.hy.data.entity.mapper.CompanyEntityJsonMapper;
import com.hy.data.entity.mapper.PageEntityDataMapper;
import com.hy.data.entity.mapper.PageEntityJsonMapper;
import com.hy.data.net.RestApiImpl;

import java.util.List;

import rx.Observable;

public class EquipmentStateMonitorDataRepository implements EquipmentStateMonitorRepository {
    private final Context mContext;
    private int userId;
    private int lineSn;
    private int pageNum;
    private PageEntityDataMapper pageEntityDataMapper;

    public EquipmentStateMonitorDataRepository(Context mContext, int userId) {
        this.mContext = mContext;
        this.userId = userId;
    }

    public EquipmentStateMonitorDataRepository(Context mContext, int userId, int lineSn, int pageNum) {
        this.mContext = mContext;
        this.userId = userId;
        this.lineSn = lineSn;
        this.pageNum = pageNum;
        this.pageEntityDataMapper = new PageEntityDataMapper();
    }

    @Override
    public Observable<List<DomainCompany>> getAllLine() {
        RestApiImpl restApi = new RestApiImpl(mContext, new CompanyEntityJsonMapper());
        CompanyEntityDataMapper companyEntityDataMapper = new CompanyEntityDataMapper();
        return restApi.getAllLine(userId).map(companyEntityDataMapper::transform);
    }

    @Override
    public Observable<DomainOnlineDeviceStatePage> loadOnlineDeviceState() {
        RestApiImpl restApi = new RestApiImpl(mContext, new PageEntityJsonMapper());
        return restApi.loadOnlineDeviceState(userId, lineSn, pageNum).map(this.pageEntityDataMapper::transform);
    }
}
