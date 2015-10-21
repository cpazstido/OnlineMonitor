package com.hy.data.repository;

import android.content.Context;

import com.example.bean.DomainAlarmPage;
import com.example.repository.AlarmRepository;
import com.hy.data.entity.mapper.PageEntityDataMapper;
import com.hy.data.entity.mapper.PageEntityJsonMapper;
import com.hy.data.net.RestApiImpl;

import rx.Observable;

public class AlarmDataRepository implements AlarmRepository {

    private Context mContext;
    private PageEntityDataMapper pageEntityDataMapper;
    private int userId;
    private int pageNumber;
    private String queryAlarmType;
    private String curProject;
    private int status;

    public AlarmDataRepository(Context mContext, int userId, String queryAlarmType, String curProject, int status, int pageNumber) {
        this.userId = userId;
        this.mContext = mContext;
        this.queryAlarmType = queryAlarmType;
        this.status = status;
        this.curProject = curProject;
        this.pageNumber = pageNumber;
        this.pageEntityDataMapper = new PageEntityDataMapper();
    }

    @Override
    public Observable<DomainAlarmPage> alarmList() {
        RestApiImpl restApi = new RestApiImpl(mContext, new PageEntityJsonMapper());
        return restApi.alarmEntity(userId, curProject,queryAlarmType, status, pageNumber).map(this.pageEntityDataMapper::transform);
    }

    @Override
    public Observable<DomainAlarmPage> alarmList(String equipmentName) {
        RestApiImpl restApi = new RestApiImpl(mContext, new PageEntityJsonMapper());
        return restApi.alarmEntity(userId, equipmentName, curProject,queryAlarmType, status, pageNumber).map(this.pageEntityDataMapper::transform);
    }
}
