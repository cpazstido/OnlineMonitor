package com.hy.data.repository;

import android.content.Context;

import com.example.repository.HandleRepository;
import com.hy.data.entity.mapper.StringJsonMapper;
import com.hy.data.net.RestApiImpl;

import rx.Observable;

/**
 * Created by 24363 on 2015/9/23.
 */
public class HandleDataRepository implements HandleRepository{
    private final Context mContext;
    private int alarmSn;
    private String queryAlarmType;
    public HandleDataRepository(Context mContext, int alarmSn, String queryAlarmType) {
        this.mContext = mContext;
        this.alarmSn = alarmSn;
        this.queryAlarmType = queryAlarmType;
    }

    @Override
    public Observable<String> handleAlarm() {
        RestApiImpl restApi = new RestApiImpl(mContext, new StringJsonMapper());
        return restApi.handleAlarm(alarmSn,queryAlarmType);
    }
}
