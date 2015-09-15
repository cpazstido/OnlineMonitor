package com.hy.data.repository;

import android.content.Context;

import com.example.bean.DomainEquipmentInforPage;
import com.example.repository.EquipmentInforRepository;
import com.hy.data.entity.mapper.PageEntityDataMapper;
import com.hy.data.entity.mapper.PageEntityJsonMapper;
import com.hy.data.net.RestApiImpl;

import rx.Observable;

public class EquipmentDataInforRepository implements EquipmentInforRepository {

    private Context mContext;
    private int userId;
    private int selectedType;
    private PageEntityDataMapper pageEntityDataMapper;
    private int pageNumber;

    public EquipmentDataInforRepository(Context mContext, int userId, int selectedType, int pagaNubmer) {
        this.mContext = mContext;
        this.userId = userId;
        this.selectedType = selectedType;
        this.pageNumber = pagaNubmer;
        this.pageEntityDataMapper = new PageEntityDataMapper();
    }

    @Override
    public Observable<DomainEquipmentInforPage> equipmentList() {
        RestApiImpl restApi = new RestApiImpl(mContext,new PageEntityJsonMapper());
        return restApi.equipmentEntity(userId, selectedType,pageNumber).map(this.pageEntityDataMapper::transform);
    }

}
