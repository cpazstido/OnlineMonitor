package com.hy.onlinemonitor.presenter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.bean.DomainEquipment;
import com.example.interactor.DefaultSubscriber;
import com.example.interactor.EquipmentConditionMonitorUseCase;
import com.example.interactor.UseCase;
import com.hy.data.repository.EquipmentConditionMonitorDataRepository;
import com.hy.onlinemonitor.UIThread;
import com.hy.onlinemonitor.mapper.EquipmentDataMapper;
import com.hy.onlinemonitor.view.Activity.ConditionMonitor.EquipmentConditionMonitorActivity;

import java.util.List;
import java.util.TreeMap;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by 24363 on 2015/10/13.
 */
public class EquipmentConditionMonitorPresenter implements Presenter {
    private Context mContext;
    private UseCase equipmentConditionMonitorUseCase;
    private EquipmentConditionMonitorActivity equipmentConditionMonitorActivity;
    public EquipmentConditionMonitorPresenter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        equipmentConditionMonitorUseCase.unsubscribe();
    }

    @Override
    public void showViewLoading() {
        equipmentConditionMonitorActivity.showLoading();
    }

    @Override
    public void hideViewLoading() {
        equipmentConditionMonitorActivity.hideLoading();
    }

    public void getEquipmentList(int userId){
        this.showViewLoading();
        EquipmentConditionMonitorDataRepository equipmentConditionMonitorDataRepository = new EquipmentConditionMonitorDataRepository(mContext, userId);
        this.equipmentConditionMonitorUseCase = new EquipmentConditionMonitorUseCase(new UIThread(), AndroidSchedulers.mainThread(), equipmentConditionMonitorDataRepository,1);
        this.equipmentConditionMonitorUseCase.execute(new EquipmentListSubscriber());
    }
    private class EquipmentListSubscriber extends DefaultSubscriber<List<DomainEquipment>> {
        @Override
        public void onCompleted() {
            EquipmentConditionMonitorPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            Log.e("aaa", "error");
            EquipmentConditionMonitorPresenter.this.hideViewLoading();
            equipmentConditionMonitorActivity.showError(e.getMessage());
            super.onError(e);
        }

        @Override
        public void onNext(List<DomainEquipment> domainEquipmentList) {
            EquipmentConditionMonitorPresenter.this.setEquipmentList(domainEquipmentList);
        }
    }

    public void queryConditionMonitorData(int userId, String fieldName, String startTime, String endTime, String deviceSn, String statisticByTime, String deviceID){
        this.showViewLoading();
        EquipmentConditionMonitorDataRepository equipmentConditionMonitorDataRepository = new EquipmentConditionMonitorDataRepository(mContext, userId,fieldName,startTime,endTime,deviceSn,statisticByTime,deviceID);
        this.equipmentConditionMonitorUseCase = new EquipmentConditionMonitorUseCase(new UIThread(), AndroidSchedulers.mainThread(), equipmentConditionMonitorDataRepository,2);
        this.equipmentConditionMonitorUseCase.execute(new ConditionMonitorDataSubscriber());

    }
    private class ConditionMonitorDataSubscriber extends DefaultSubscriber<TreeMap<Float, Float>> {
        @Override
        public void onCompleted() {
            EquipmentConditionMonitorPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            EquipmentConditionMonitorPresenter.this.hideViewLoading();
            equipmentConditionMonitorActivity.showError(e.getMessage());
            super.onError(e);
        }

        @Override
        public void onNext(TreeMap<Float, Float> treeMap) {
            if(treeMap!=null)
                equipmentConditionMonitorActivity.showChart(treeMap);
            else{
                EquipmentConditionMonitorPresenter.this.hideViewLoading();
                equipmentConditionMonitorActivity.showError("暂无数据");
            }
        }
    }


    private void setEquipmentList(List<DomainEquipment> domainEquipmentList) {
        equipmentConditionMonitorActivity.setEquipmentList(EquipmentDataMapper.transform(domainEquipmentList));
    }

    public void setView(@Nullable EquipmentConditionMonitorActivity equipmentConditionMonitorActivity) {
        this.equipmentConditionMonitorActivity = equipmentConditionMonitorActivity;
    }
}
