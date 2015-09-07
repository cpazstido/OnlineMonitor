package com.hy.data.repository;

import android.content.Context;

import com.example.bean.DomainRolePage;
import com.example.repository.SMJurisdictionRepository;
import com.hy.data.entity.mapper.PageEntityDataMapper;
import com.hy.data.entity.mapper.PageEntityJsonMapper;
import com.hy.data.entity.mapper.StringJsonMapper;
import com.hy.data.net.RestApiImpl;

import java.util.List;

import rx.Observable;

/**
 * Created by 24363 on 2015/9/7.
 */
public class JurisdictionDataRepository implements SMJurisdictionRepository{
    private final Context mContext;
    private int userId;
    private int roleSn;
    private String name;
    private List<Integer> jurisdictionSn;
    public JurisdictionDataRepository(Context mContext,int userId) {
        this.mContext = mContext;
        this.userId = userId;
    }
    public JurisdictionDataRepository(Context mContext,int roleSn,String name) {
        this.mContext = mContext;
        this.roleSn = roleSn;
        this.name = name;
    }
    public JurisdictionDataRepository(Context mContext,int userId,int roleSn) {
        this.mContext = mContext;
        this.roleSn = roleSn;
        this.userId = userId;
    }
    public JurisdictionDataRepository(Context mContext,int roleSn,List<Integer> jurisdictionSn) {
        this.mContext = mContext;
        this.roleSn = roleSn;
        this.jurisdictionSn = jurisdictionSn;
    }

    @Override
    public Observable<DomainRolePage> getRolePage() {
        RestApiImpl restApi = new RestApiImpl(mContext,new PageEntityJsonMapper());
        PageEntityDataMapper pageEntityDataMapper = new PageEntityDataMapper();
        return restApi.getRolePage(userId).map(pageEntityDataMapper::transform);
    }

    @Override
    public Observable<DomainRolePage> addRole() {
        RestApiImpl restApi = new RestApiImpl(mContext,new PageEntityJsonMapper());
        PageEntityDataMapper pageEntityDataMapper = new PageEntityDataMapper();
        return restApi.addRole(userId,name).map(pageEntityDataMapper::transform);
    }

    @Override
    public Observable<DomainRolePage> changeRole() {
        RestApiImpl restApi = new RestApiImpl(mContext,new PageEntityJsonMapper());
        PageEntityDataMapper pageEntityDataMapper = new PageEntityDataMapper();
        return restApi.changeRole(userId, roleSn,name).map(pageEntityDataMapper::transform);
    }

    @Override
    public Observable<DomainRolePage> deleteRole() {
        RestApiImpl restApi = new RestApiImpl(mContext,new PageEntityJsonMapper());
        PageEntityDataMapper pageEntityDataMapper = new PageEntityDataMapper();
        return restApi.deleteRole(userId, roleSn).map(pageEntityDataMapper::transform);
    }

    @Override
    public Observable<String> jurisdictionChange() {
        RestApiImpl restApi = new RestApiImpl(mContext,new StringJsonMapper());
        return restApi.jurisdictionChange(userId, roleSn,jurisdictionSn);
    }
}
