package com.hy.data.repository;

import android.content.Context;

import com.example.bean.DomainCompany;
import com.example.repository.EquipmentStateMonitorRepository;
import com.hy.data.entity.mapper.CompanyEntityDataMapper;
import com.hy.data.entity.mapper.CompanyEntityJsonMapper;
import com.hy.data.net.RestApiImpl;

import java.util.List;

import rx.Observable;

/**
 * Created by 24363 on 2015/10/17.
 */
public class EquipmentStateMonitorDataRepository implements EquipmentStateMonitorRepository{
    private final Context mContext;
    private int userId;
    public EquipmentStateMonitorDataRepository(Context mContext, int userId) {
        this.mContext = mContext;
        this.userId = userId;
    }

    @Override
    public Observable<List<DomainCompany>> getAllLine() {
        RestApiImpl restApi = new RestApiImpl(mContext,new CompanyEntityJsonMapper());
        CompanyEntityDataMapper companyEntityDataMapper = new CompanyEntityDataMapper();
        return restApi.getAllLine(userId).map(companyEntityDataMapper::transform);
    }

    @Override
    public Observable loadDeviceInformation() {
        return null;
    }
}
