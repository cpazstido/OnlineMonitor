package com.hy.data.repository;

import android.content.Context;

import com.example.bean.DomainAlarmPage;
import com.example.repository.AlarmRepository;
import com.hy.data.entity.mapper.PageEntityDataMapper;
import com.hy.data.entity.mapper.PageEntityJsonMapper;
import com.hy.data.net.RestApiImpl;

import rx.Observable;

/**
 * Created by 24363 on 2015/8/18.
 */
public class AlarmDataRepository implements AlarmRepository{

    private Context mContext;
    private PageEntityDataMapper pageEntityDataMapper;
    private int userId;
    public AlarmDataRepository(Context mContext,int userId, String title) {
        this.userId = userId;
        this.mContext = mContext;
        this.pageEntityDataMapper = new PageEntityDataMapper();
    }

    public AlarmDataRepository(Context mContext,int userId, int equipmentSn) {
        this.userId = userId;
        this.mContext = mContext;
        this.pageEntityDataMapper = new PageEntityDataMapper();
    }

    @Override
    public Observable<DomainAlarmPage> alarmList(String title) {
        RestApiImpl restApi = new RestApiImpl(mContext,new PageEntityJsonMapper());
        return restApi.alarmEntity(userId, title).map(this.pageEntityDataMapper::transform);
    }

    @Override
    public Observable<DomainAlarmPage> alarmList(int equipmentSn) {
        RestApiImpl restApi = new RestApiImpl(mContext,new PageEntityJsonMapper());
        return restApi.alarmEntity(userId, equipmentSn).map(this.pageEntityDataMapper::transform);
    }
}
