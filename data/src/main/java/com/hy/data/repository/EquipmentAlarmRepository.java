package com.hy.data.repository;

import android.content.Context;

import com.example.bean.DomainEquipmentAlarmInformation;
import com.example.repository.EquipmentRepository;
import com.hy.data.entity.mapper.EquipmentAlarmEntityDataMapper;
import com.hy.data.entity.mapper.EquipmentAlarmEntityJsonMapper;
import com.hy.data.net.RestApiImpl;

import java.util.List;

import rx.Observable;

/**
 * Created by 24363 on 2015/8/13.
 */
public class EquipmentAlarmRepository implements EquipmentRepository{

    private Context mContext;
    private String userName;
    private int choiceType;
    private EquipmentAlarmEntityDataMapper equipmentAlarmEntityDataMapper;

    public EquipmentAlarmRepository(Context mContext, String userName, int choiceType) {
        this.mContext = mContext;
        this.userName = userName;
        this.choiceType = choiceType;
        this.equipmentAlarmEntityDataMapper = new EquipmentAlarmEntityDataMapper();
    }

    @Override
    public Observable<List<DomainEquipmentAlarmInformation>> equipmentAlarmList() {
        RestApiImpl restApi = new RestApiImpl(mContext,new EquipmentAlarmEntityJsonMapper());
        return restApi.equipmentAlarmEntity(userName,choiceType).map(this.equipmentAlarmEntityDataMapper::transform);
    }
}
