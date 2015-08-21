package com.hy.data.repository;

import android.content.Context;

import com.example.bean.DoaminEquipmentPage;
import com.example.repository.EquipmentRepository;
import com.hy.data.net.RestApiImpl;

import rx.Observable;

/**
 * Created by 24363 on 2015/8/13.
 */
public class EquipmentDataRepository implements EquipmentRepository {

    private Context mContext;
    private String userName;
    private int selectedType;
    private PageEntityDataMapper pageEntityDataMapper;

    public EquipmentDataRepository(Context mContext, String userName, int selectedType) {
        this.mContext = mContext;
        this.userName = userName;
        this.selectedType = selectedType;
        this.pageEntityDataMapper = new PageEntityDataMapper();
    }

    @Override
    public Observable<DoaminEquipmentPage> equipmentList() {
        RestApiImpl restApi = new RestApiImpl(mContext,new PageEntityJsonMapper());
        return restApi.equipmentEntity(userName, selectedType).map(this.pageEntityDataMapper::transform);
    }
}
