package com.hy.data.repository;

import android.content.Context;

import com.example.bean.DomainSensorType;
import com.example.repository.SMSensorRepository;
import com.hy.data.entity.mapper.SensorTypeEntityDataMapper;
import com.hy.data.entity.mapper.SensorTypeEntityJsonMapper;
import com.hy.data.entity.mapper.StringJsonMapper;
import com.hy.data.net.RestApiImpl;

import java.util.List;

import rx.Observable;

/**
 * Created by 24363 on 2015/9/14.
 */
public class SensorDataRepository implements SMSensorRepository {
    private int userId;
    private final Context mContext;
    private int equipmentSn;
    private String sensorJson;

    public SensorDataRepository(Context mContext, int userId,int equipmentSn, String sensorJson) {
        this.mContext = mContext;
        this.sensorJson = sensorJson;
        this.equipmentSn = equipmentSn;
        this.userId = userId;
    }

    public SensorDataRepository(int equipmentSn, Context mContext, int userId) {
        this.equipmentSn = equipmentSn;
        this.mContext = mContext;
        this.userId = userId;
    }


    @Override
    public Observable<List<DomainSensorType>> getAllSensorType() {
        RestApiImpl restApi = new RestApiImpl(mContext, new SensorTypeEntityJsonMapper());
        return restApi.getAllSensor(userId).map(SensorTypeEntityDataMapper::transform);
    }

    @Override
    public Observable<String> changeSensor() {
        RestApiImpl restApi = new RestApiImpl(mContext, new StringJsonMapper());
        return restApi.changeSensor(userId, equipmentSn, sensorJson);
    }
}
