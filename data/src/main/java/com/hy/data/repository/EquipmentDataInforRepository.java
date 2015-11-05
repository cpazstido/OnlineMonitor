package com.hy.data.repository;

import android.content.Context;

import com.example.bean.DomainEquipmentInforPage;
import com.example.bean.DomainLine;
import com.example.repository.EquipmentInforRepository;
import com.hy.data.entity.mapper.LineEntityDataMapper;
import com.hy.data.entity.mapper.LineEntityJsonMapper;
import com.hy.data.entity.mapper.PageEntityDataMapper;
import com.hy.data.entity.mapper.PageEntityJsonMapper;
import com.hy.data.net.RestApiImpl;

import java.util.List;

import rx.Observable;

public class EquipmentDataInforRepository implements EquipmentInforRepository {

    private Context mContext;
    private int userId;
    private int selectedType;
    private PageEntityDataMapper pageEntityDataMapper;
    private int pageNumber;
    private String searchName;
    private int lineSn;

    public EquipmentDataInforRepository(Context mContext, int userId, int selectedType, int pagaNubmer) {
        this.mContext = mContext;
        this.userId = userId;
        this.selectedType = selectedType;
        this.pageNumber = pagaNubmer;
        this.pageEntityDataMapper = new PageEntityDataMapper();
    }

    public EquipmentDataInforRepository(Context mContext, int userId) {
        this.mContext = mContext;
        this.userId = userId;
    }

    public EquipmentDataInforRepository(Context mContext, String searchName) {
        this.mContext = mContext;
        this.searchName = searchName;
        this.pageEntityDataMapper = new PageEntityDataMapper();
    }

    public EquipmentDataInforRepository(int lineSn, Context mContext) {
        this.mContext = mContext;
        this.lineSn = lineSn;
        this.pageEntityDataMapper = new PageEntityDataMapper();
    }

    @Override
    public Observable<DomainEquipmentInforPage> equipmentList() {
        RestApiImpl restApi = new RestApiImpl(mContext, new PageEntityJsonMapper());
        return restApi.equipmentEntity(userId, selectedType, pageNumber).map(this.pageEntityDataMapper::transform);
    }

    @Override
    public Observable<DomainEquipmentInforPage> searchByLineSn() {
        RestApiImpl restApi = new RestApiImpl(mContext, new PageEntityJsonMapper());
        return restApi.searchByLineSn(lineSn).map(this.pageEntityDataMapper::transform);

    }

    @Override
    public Observable<DomainEquipmentInforPage> searchByName() {
        RestApiImpl restApi = new RestApiImpl(mContext, new PageEntityJsonMapper());
        return restApi.searchByName(searchName).map(this.pageEntityDataMapper::transform);
    }

    @Override
    public Observable<List<DomainLine>> getAllTower() {
        RestApiImpl restApi = new RestApiImpl(mContext, new LineEntityJsonMapper());
        LineEntityDataMapper lineEntityDataMapper = new LineEntityDataMapper();
        return restApi.getAllTower(userId, -1).map(lineEntityDataMapper::transform);
    }

}
