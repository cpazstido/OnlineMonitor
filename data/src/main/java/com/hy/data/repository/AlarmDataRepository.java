package com.hy.data.repository;

import android.content.Context;

import com.example.bean.DomainAlarmInformation;
import com.example.repository.AlarmRepository;
import com.hy.data.entity.mapper.AlarmEntityDataMapper;
import com.hy.data.entity.mapper.AlarmEntityJsonMapper;
import com.hy.data.net.RestApiImpl;

import java.util.List;

import rx.Observable;

/**
 * Created by 24363 on 2015/8/18.
 */
public class AlarmDataRepository implements AlarmRepository{

    private Context mContext;
    private AlarmEntityDataMapper alarmEntityDataMapper;
    private String userName;
    private String title;
    public AlarmDataRepository(Context mContext,String userName, String title) {
        this.userName= userName;
        this.title = title;
        this.mContext = mContext;
        this.alarmEntityDataMapper = new AlarmEntityDataMapper();
    }

    @Override
    public Observable<List<DomainAlarmInformation>> alarmList() {
        RestApiImpl restApi = new RestApiImpl(mContext,new AlarmEntityJsonMapper());
        return restApi.alarmEntity(userName, title).map(this.alarmEntityDataMapper::transform);

    }
}
