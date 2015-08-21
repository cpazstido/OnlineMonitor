package com.hy.onlinemonitor.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.bean.DomainAlarmInformation;
import com.example.interactor.AlarmUseCase;
import com.example.interactor.DefaultSubscriber;
import com.example.interactor.UseCase;
import com.hy.data.repository.AlarmDataRepository;
import com.hy.onlinemonitor.UIThread;
import com.hy.onlinemonitor.bean.AlarmInformation;
import com.hy.onlinemonitor.mapper.AlarmDataMapper;
import com.hy.onlinemonitor.view.AlarmListView;

import java.util.Collection;
import java.util.List;

/**
 * Created by 24363 on 2015/8/17.
 */
public class AlarmPresenter implements Presenter{

    private AlarmListView alarmView;
    private String userId;
    private Context mContext;
    private AlarmDataMapper alarmDataMapper;
    private UseCase getAlarmListUseCase;
    public AlarmPresenter(Context mContext) {
        this.mContext = mContext;
        this.alarmDataMapper = new AlarmDataMapper();
    }

    public void initialize(String title,String userId) {
        this.userId = userId;
        this.loadUserList(title);
    }
    
    public void initialize(String userId,int equipmentSn,int page) {
        this.userId = userId;
        this.loadUserList(equipmentSn);
    }
    
    private void loadUserList(int equipmentSn) {
        this.showViewLoading();
        this.getAlarmList(equipmentSn);
    }

    private void loadUserList(String title) {
        this.showViewLoading();
        this.getAlarmList(title);
    }
    @Override
    public void showViewLoading() {
        this.alarmView.showLoading();
    }
    @Override
    public void hideViewLoading(){
        this.alarmView.hideLoading();
    }

    private void getAlarmList(String title) {
        AlarmDataRepository alarmDataRepository = new AlarmDataRepository(mContext, userId,title);
        this.getAlarmListUseCase = new AlarmUseCase(new UIThread(),alarmDataRepository,title);
        this.getAlarmListUseCase.execute(new AlarmListSubscriber());
    }

    private void getAlarmList(int equipmentSn) {
        AlarmDataRepository alarmDataRepository = new AlarmDataRepository(mContext, userId,equipmentSn);
        this.getAlarmListUseCase = new AlarmUseCase(new UIThread(),alarmDataRepository,equipmentSn);
        this.getAlarmListUseCase.execute(new AlarmListSubscriber());
    }

    private class AlarmListSubscriber extends DefaultSubscriber<List<DomainAlarmInformation>> {

        @Override
        public void onCompleted() {
            AlarmPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(List<DomainAlarmInformation> domainAlarmInformations) {
            AlarmPresenter.this.showAlarmCollectionInView(domainAlarmInformations);
        }
    }

    private void showAlarmCollectionInView(List<DomainAlarmInformation> domainAlarmInformations) {
        final Collection<AlarmInformation> alarmInformations =
                this.alarmDataMapper.transform(domainAlarmInformations);
        this.alarmView.renderAlarmList(alarmInformations);
    }

    public void setView(@NonNull AlarmListView view){
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
