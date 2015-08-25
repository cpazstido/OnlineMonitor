package com.hy.onlinemonitor.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.bean.DomainAlarmPage;
import com.example.interactor.AlarmUseCase;
import com.example.interactor.DefaultSubscriber;
import com.example.interactor.UseCase;
import com.hy.data.repository.AlarmDataRepository;
import com.hy.onlinemonitor.UIThread;
import com.hy.onlinemonitor.bean.AlarmInformation;
import com.hy.onlinemonitor.bean.AlarmPage;
import com.hy.onlinemonitor.mapper.PageDataMapper;
import com.hy.onlinemonitor.view.AlarmListView;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by 24363 on 2015/8/17.
 */
public class AlarmPresenter implements Presenter {

    private AlarmListView alarmView;
    private int userId;
    private Context mContext;
    private UseCase getAlarmListUseCase;
    private String queryAlarmType;
    private PageDataMapper pageDataMapper;
    private int status;

    public AlarmPresenter(Context mContext) {
        this.mContext = mContext;
        this.pageDataMapper = new PageDataMapper();
    }

    /**
     * 查看所有的报警
     * @param queryAlarmType 查看的报警类型
     * @param status 查看的报警类型的状态(历史,或者新报警)
     * @param userId 唯一标示用户
     */
    public void initialize( int userId ,String queryAlarmType, int status, int pageNumber) {
        this.userId = userId;
        this.status = status;
        this.queryAlarmType = queryAlarmType;
        this.loadUserList(pageNumber);
    }

    /**
     * 查看特定 equipmentName的报警信息
     * @param userId 唯一标示用户
     * @param equipmentName 设备名(唯一标示)
     * @param queryAlarmType 查看的报警类型
     * @param status 查看的报警类型的状态(历史,或者新报警)
     */
    public void initialize(int userId, String equipmentName, String queryAlarmType, int status, int pageNumber) {
        this.userId = userId;
        this.status = status;
        this.queryAlarmType = queryAlarmType;
        this.loadUserList(equipmentName,pageNumber);
    }

    private void loadUserList(int pageNumber) {
        this.showViewLoading();
        this.getAlarmList(pageNumber);
    }

    private void loadUserList(String equipmentName,int pageNumber) {
        this.showViewLoading();
        this.getAlarmList(equipmentName,pageNumber);
    }

    @Override
    public void showViewLoading() {
        this.alarmView.showLoading();
    }

    @Override
    public void hideViewLoading() {
        this.alarmView.hideLoading();
    }

    private void getAlarmList(int pageNumber) {   //获取所有的设备的报警
        AlarmDataRepository alarmDataRepository = new AlarmDataRepository(mContext, userId, queryAlarmType, status, pageNumber);
        this.getAlarmListUseCase = new AlarmUseCase(new UIThread(), AndroidSchedulers.mainThread(), alarmDataRepository);
        this.getAlarmListUseCase.execute(new AlarmListSubscriber());
    }

    private void getAlarmList(String equipmentName,int pageNumber) {   //获取名称为equipmentName的设备的报警
        AlarmDataRepository alarmDataRepository = new AlarmDataRepository(mContext, userId, queryAlarmType, status, pageNumber);
        this.getAlarmListUseCase = new AlarmUseCase(new UIThread(), AndroidSchedulers.mainThread(), alarmDataRepository, equipmentName);
        this.getAlarmListUseCase.execute(new AlarmListSubscriber());
    }

    private class AlarmListSubscriber extends DefaultSubscriber<DomainAlarmPage> {

        @Override
        public void onCompleted() {
            AlarmPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            AlarmPresenter.this.hideViewLoading();
            Toast.makeText(mContext,"出现错误",Toast.LENGTH_SHORT).show();
            super.onError(e);
        }

        @Override
        public void onNext(DomainAlarmPage domainAlarmPage) {
            AlarmPresenter.this.showAlarmCollectionInView(domainAlarmPage);
        }
    }

    private void showAlarmCollectionInView(DomainAlarmPage domainAlarmPage) {
        AlarmPage alarmPage = this.pageDataMapper.transform(domainAlarmPage);
        this.alarmView.renderAlarmList(alarmPage);
    }

    public void setView(@NonNull AlarmListView view) {
        this.alarmView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.getAlarmListUseCase.unsubscribe();
    }

    public void onAlarmClicked(AlarmInformation alarmInformation) {
        this.alarmView.viewAlarm(alarmInformation);
    }
}
