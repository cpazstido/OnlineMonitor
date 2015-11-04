package com.hy.data.repository;

import android.content.Context;

import com.example.bean.DomainConditionMonitoringPage;
import com.example.bean.DomainEquipmentInforPage;
import com.example.bean.DomainSensorType;
import com.example.repository.ConditionMonitoringRepository;
import com.hy.data.entity.mapper.PageEntityDataMapper;
import com.hy.data.entity.mapper.PageEntityJsonMapper;
import com.hy.data.entity.mapper.SensorTypeEntityDataMapper;
import com.hy.data.entity.mapper.SensorTypeEntityJsonMapper;
import com.hy.data.net.RestApiImpl;

import java.util.List;

import rx.Observable;

public class ConditionMonitoringDataRepository implements ConditionMonitoringRepository {
    private final Context mContext;
    private String deviceSn;
    private int pageNum;
    private PageEntityDataMapper pageEntityDataMapper;
    private int userId;
    private int selectedType;
    private String startDate;
    private String endDate;
    public ConditionMonitoringDataRepository(Context mContext, String deviceSn) {
        this.mContext = mContext;
        this.deviceSn = deviceSn;
    }

    public ConditionMonitoringDataRepository(Context mContext, int pageNum, int userId, int selectedType) {
        this.mContext = mContext;
        this.pageNum = pageNum;
        this.userId = userId;
        this.selectedType = selectedType;
    }

    public ConditionMonitoringDataRepository(Context mContext, String deviceSn,String startDate,String endDate, int pageNum) {
        this.mContext = mContext;
        this.deviceSn = deviceSn;
        this.pageNum = pageNum;
        this.startDate = startDate;
        this.endDate = endDate;
        pageEntityDataMapper = new PageEntityDataMapper();
    }

    @Override
    public Observable<DomainConditionMonitoringPage> getAeolianVibration() {
        RestApiImpl restApi = new RestApiImpl(mContext, new PageEntityJsonMapper());
        return restApi.getAeolianVibration(deviceSn,startDate,endDate,pageNum).map(pageEntityDataMapper::transform);
    }

    @Override
    public Observable<DomainConditionMonitoringPage> getIceCoating() {
        RestApiImpl restApi = new RestApiImpl(mContext, new PageEntityJsonMapper());
        return restApi.getIceCoating(deviceSn,startDate,endDate,pageNum).map(pageEntityDataMapper::transform);
    }

    @Override
    public Observable<DomainConditionMonitoringPage> getConductorSag() {
        RestApiImpl restApi = new RestApiImpl(mContext, new PageEntityJsonMapper());
        return restApi.getConductorSag(deviceSn,startDate,endDate,pageNum).map(pageEntityDataMapper::transform);
    }

    @Override
    public Observable<DomainConditionMonitoringPage> getConductorSwingWithWind() {
        RestApiImpl restApi = new RestApiImpl(mContext, new PageEntityJsonMapper());
        return restApi.getConductorSwingWithWind(deviceSn,startDate,endDate,pageNum).map(pageEntityDataMapper::transform);
    }

    @Override
    public Observable<DomainConditionMonitoringPage> getPoleStatus() {
        RestApiImpl restApi = new RestApiImpl(mContext, new PageEntityJsonMapper());
        return restApi.getPoleStatus(deviceSn,startDate,endDate,pageNum).map(pageEntityDataMapper::transform);
    }

    @Override
    public Observable<DomainConditionMonitoringPage> getMicroclimate() {
        RestApiImpl restApi = new RestApiImpl(mContext, new PageEntityJsonMapper());
        return restApi.getMicroclimate(deviceSn,startDate,endDate,pageNum).map(pageEntityDataMapper::transform);
    }

    @Override
    public Observable<List<DomainSensorType>> getConditionMonitoringType() {
        RestApiImpl restApi = new RestApiImpl(mContext, new SensorTypeEntityJsonMapper());
        return restApi.getConditionMonitoringType(deviceSn).map(SensorTypeEntityDataMapper::transform);
    }

    @Override
    public Observable<DomainEquipmentInforPage> getEquipmentList() {
        RestApiImpl restApi = new RestApiImpl(mContext, new PageEntityJsonMapper());
        PageEntityDataMapper pageEntityDataMapper = new PageEntityDataMapper();
        return restApi.equipmentEntity(userId, selectedType, pageNum).map(pageEntityDataMapper::transform);
    }
}
