package com.hy.data.repository;

import android.content.Context;

import com.example.bean.DomainEquipmentInformation;
import com.example.repository.EquipmentRepository;
import com.hy.data.entity.mapper.EquipmentAlarmEntityDataMapper;
import com.hy.data.entity.mapper.EquipmentAlarmEntityJsonMapper;
import com.hy.data.net.RestApiImpl;

import java.util.List;

import rx.Observable;

/**
 * Created by 24363 on 2015/8/13.
 */
public class EquipmentDataRepository implements EquipmentRepository {

    private Context mContext;
    private String userName;
    private int selectedType;
    private EquipmentAlarmEntityDataMapper equipmentAlarmEntityDataMapper;

    public EquipmentDataRepository(Context mContext, String userName, int selectedType) {
        this.mContext = mContext;
        this.userName = userName;
        this.selectedType = selectedType;
        this.equipmentAlarmEntityDataMapper = new EquipmentAlarmEntityDataMapper();
    }

    @Override
    public Observable<List<DomainEquipmentInformation>> equipmentList() {
        RestApiImpl restApi = new RestApiImpl(mContext,new EquipmentAlarmEntityJsonMapper());
        return restApi.equipmentEntity(userName, selectedType).map(this.equipmentAlarmEntityDataMapper::transform);
    }
}
