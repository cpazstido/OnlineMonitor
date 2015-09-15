package com.hy.onlinemonitor.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.bean.DomainSensorType;
import com.example.interactor.DefaultSubscriber;
import com.example.interactor.SensorUseCase;
import com.example.repository.SMSensorRepository;
import com.hy.data.repository.SensorDataRepository;
import com.hy.onlinemonitor.UIThread;
import com.hy.onlinemonitor.bean.SensorType;
import com.hy.onlinemonitor.mapper.SensoTypeDataMapper;
import com.hy.onlinemonitor.mapper.SensorDataMapper;
import com.hy.onlinemonitor.view.Activity.SystemManagement.SensorManageActivity;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by 24363 on 2015/9/14.
 */
public class SMSensorPresenter implements Presenter{

    private SensorManageActivity sensorManageActivity;
    private final Context mContext;
    private SensorUseCase sensorUseCase;
    private SensorDataMapper sensorDataMapper;
    private int userId;

    public SMSensorPresenter(Context mContext) {
        this.mContext = mContext;
        this.sensorDataMapper = new SensorDataMapper();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        sensorUseCase.unsubscribe();
    }

    @Override
    public void showViewLoading() {
        sensorManageActivity.showLoading();
    }

    @Override
    public void hideViewLoading() {
        sensorManageActivity.hideLoading();
    }

    public void setSensorManageActivity(SensorManageActivity sensorManageActivity) {
        this.sensorManageActivity = sensorManageActivity;
    }

    public void loadAllSensor(int userId,int equipmentSn) {
        showViewLoading();
        this.userId =userId;
        SMSensorRepository sensorDataRepository = new SensorDataRepository(equipmentSn,mContext,userId);
        this.sensorUseCase = new SensorUseCase(new UIThread(), AndroidSchedulers.mainThread(), sensorDataRepository, 1);
        this.sensorUseCase.execute(new AllSensorList());
    }

    private class AllSensorList extends DefaultSubscriber<List<DomainSensorType>> {
        @Override
        public void onCompleted() {
            hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            SMSensorPresenter.this.hideViewLoading();
            Toast.makeText(mContext, "AdminLineListOnlySubscriber出现错误", Toast.LENGTH_SHORT).show();
            super.onError(e);
        }

        @Override
        public void onNext(List<DomainSensorType> domainSensors) {
            List<SensorType> mList = SensoTypeDataMapper.transform(domainSensors);
            sensorManageActivity.setAllSensorList(mList);
        }
    }

    public void changeSensor(int equipmentSn,String sensorJson) {
        showViewLoading();
        SMSensorRepository sensorDataRepository = new SensorDataRepository(mContext,userId,equipmentSn,sensorJson);
        this.sensorUseCase = new SensorUseCase(new UIThread(), AndroidSchedulers.mainThread(), sensorDataRepository, 2);
        this.sensorUseCase.execute(new SensorList());
    }

    private class SensorList extends DefaultSubscriber<String> {
        @Override
        public void onCompleted() {
            hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            SMSensorPresenter.this.hideViewLoading();
            Toast.makeText(mContext, "AdminLineListOnlySubscriber出现错误", Toast.LENGTH_SHORT).show();
            super.onError(e);
        }

        @Override
        public void onNext(String result) {
            sensorManageActivity.isChange(result);
        }
    }

}
