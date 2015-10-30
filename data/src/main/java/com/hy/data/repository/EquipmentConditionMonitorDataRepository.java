package com.hy.data.repository;

import android.content.Context;

import com.example.bean.DomainEquipmentInforPage;
import com.example.repository.EquipmentConditionMonitorRepository;
import com.hy.data.entity.mapper.PageEntityDataMapper;
import com.hy.data.entity.mapper.PageEntityJsonMapper;
import com.hy.data.net.RestApiImpl;

import java.util.TreeMap;

import rx.Observable;

/**
 * Created by 24363 on 2015/10/13.
 */
public class EquipmentConditionMonitorDataRepository implements EquipmentConditionMonitorRepository {
    private final Context mContext;
    private int userId;
    private int selectedType;

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    private int pageNumber;
    private String fieldName;
    private String startTime;
    private String endTime;
    private String deviceSn;
    private String statisticByTime;
    private String deviceID;
    private String type;
    public EquipmentConditionMonitorDataRepository(Context mContext, int userId, String fieldName, String startTime, String endTime, String deviceSn, String statisticByTime, String deviceID) {
        this.mContext = mContext;
        this.userId = userId;
        this.fieldName = fieldName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.deviceSn = deviceSn;
        this.statisticByTime = statisticByTime;
        this.deviceID = deviceID;
    }

    public EquipmentConditionMonitorDataRepository(Context mContext, String type, int userId, String fieldName, String startTime, String endTime, String deviceSn, String statisticByTime) {
        this.mContext = mContext;
        this.userId = userId;
        this.fieldName = fieldName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.deviceSn = deviceSn;
        this.statisticByTime = statisticByTime;
        this.type = type;
    }

    public EquipmentConditionMonitorDataRepository(Context mContext, int userId, int selectedType, int pageNumber) {
        this.mContext = mContext;
        this.userId = userId;
        this.selectedType = selectedType;
        this.pageNumber = pageNumber;
    }

    @Override
    public Observable<DomainEquipmentInforPage> getEquipmentList() {
        RestApiImpl restApi = new RestApiImpl(mContext, new PageEntityJsonMapper());
        PageEntityDataMapper pageEntityDataMapper = new PageEntityDataMapper();
        return restApi.equipmentEntity(userId, selectedType, pageNumber).map(pageEntityDataMapper::transform);
    }

    @Override
    public Observable<TreeMap<String, Float>> queryConditionMonitorData() {
        RestApiImpl restApi = new RestApiImpl(mContext);
        return restApi.queryConditionMonitorData(userId, fieldName, startTime, endTime, deviceSn, statisticByTime, deviceID);
    }

    @Override
    public Observable<TreeMap<String, Float>> queryMonitoringStateData() {
        RestApiImpl restApi = new RestApiImpl(mContext);
        return restApi.queryMonitoringStateData(type, userId, fieldName, startTime, endTime, deviceSn, statisticByTime);

    }

}
