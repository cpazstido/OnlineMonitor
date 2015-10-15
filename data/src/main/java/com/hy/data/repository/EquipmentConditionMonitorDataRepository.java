package com.hy.data.repository;

import android.content.Context;

import com.example.bean.DomainEquipment;
import com.example.repository.EquipmentConditionMonitorRepository;
import com.hy.data.entity.mapper.EquipmentEntityDataMapper;
import com.hy.data.entity.mapper.PageEntityJsonMapper;
import com.hy.data.net.RestApiImpl;

import java.util.List;
import java.util.TreeMap;

import rx.Observable;

/**
 * Created by 24363 on 2015/10/13.
 */
public class EquipmentConditionMonitorDataRepository implements EquipmentConditionMonitorRepository{
    private final Context mContext;
    private int userId;
    private String fieldName;
    private String startTime;
    private String endTime;
    private String deviceSn;
    private String statisticByTime;
    private String deviceID;

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

    public EquipmentConditionMonitorDataRepository(Context mContext, int userId) {
        this.mContext = mContext;
        this.userId = userId;
    }

    @Override
    public Observable<List<DomainEquipment>> getEquipmentList() {
        RestApiImpl restApi = new RestApiImpl(mContext, new PageEntityJsonMapper());
        return restApi.getEquipmentList(userId).map(EquipmentEntityDataMapper::transform);
    }

    @Override
    public Observable<TreeMap<String, Float>> queryConditionMonitorData() {
        RestApiImpl restApi = new RestApiImpl(mContext);
        return restApi.queryConditionMonitorData(userId,fieldName,startTime,endTime,deviceSn,statisticByTime,deviceID);
    }

}
