package com.hy.onlinemonitor.presenter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.bean.DomainEquipmentInforPage;
import com.example.interactor.DefaultSubscriber;
import com.example.interactor.EquipmentConditionMonitorUseCase;
import com.example.interactor.UseCase;
import com.hy.data.repository.EquipmentConditionMonitorDataRepository;
import com.hy.onlinemonitor.UIThread;
import com.hy.onlinemonitor.mapper.EquipmentInforDataMapper;
import com.hy.onlinemonitor.view.Activity.ConditionMonitor.EquipmentConditionMonitorActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by 24363 on 2015/10/13.
 */
public class EquipmentConditionMonitorPresenter implements Presenter {
    private static String TAG= "EquipmentConditionMonitorPresenter";
    private Context mContext;
    private UseCase equipmentConditionMonitorUseCase;
    private EquipmentConditionMonitorDataRepository equipmentConditionMonitorDataRepository;
    private EquipmentConditionMonitorActivity equipmentConditionMonitorActivity;
    private Collection equipmentLists=new ArrayList<>();
    private int userId;
    private int selectedType;

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

    public void getEquipmentList(int userId, int selectedType, int pageNumber) {
        this.showViewLoading();
        this.userId = userId;
        this.selectedType = selectedType;
        this.equipmentConditionMonitorDataRepository = new EquipmentConditionMonitorDataRepository(mContext, userId, selectedType, pageNumber);
        this.equipmentConditionMonitorUseCase = new EquipmentConditionMonitorUseCase(new UIThread(), AndroidSchedulers.mainThread(), equipmentConditionMonitorDataRepository, 1);
        this.equipmentConditionMonitorUseCase.execute(new EquipmentListSubscriber());
    }

    private class EquipmentListSubscriber extends DefaultSubscriber<DomainEquipmentInforPage> {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, e.getMessage());
            EquipmentConditionMonitorPresenter.this.hideViewLoading();
            equipmentConditionMonitorActivity.showError(e.getMessage());
            super.onError(e);
        }

        @Override
        public void onNext(DomainEquipmentInforPage domainEquipmentInforPage) {
            if(domainEquipmentInforPage.getPageNum() == 1)
                equipmentLists.clear();
            EquipmentInforDataMapper equipmentInforDataMapper = new EquipmentInforDataMapper();
            equipmentLists.addAll(equipmentInforDataMapper.transform(domainEquipmentInforPage.getList()));
            if (domainEquipmentInforPage.getPageNum() < domainEquipmentInforPage.getTotalPage()) {
                getEquipmentList(userId,selectedType,domainEquipmentInforPage.getPageNum()+1);
            } else {//已经加载到了最后一页
                setEquipmentList(equipmentLists);
                EquipmentConditionMonitorPresenter.this.equipmentConditionMonitorActivity.hideLoading();
            }
        }
    }

    public void queryConditionMonitorData(int userId, String fieldName, String startTime, String endTime, String deviceSn, String statisticByTime, String deviceID) {
        this.showViewLoading();
        this.equipmentConditionMonitorDataRepository = new EquipmentConditionMonitorDataRepository(mContext, userId, fieldName, startTime, endTime, deviceSn, statisticByTime, deviceID);
        this.equipmentConditionMonitorUseCase = new EquipmentConditionMonitorUseCase(new UIThread(), AndroidSchedulers.mainThread(), equipmentConditionMonitorDataRepository, 2);
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
            if (treeMap != null)
                equipmentConditionMonitorActivity.showChart(treeMap);
            else {
                EquipmentConditionMonitorPresenter.this.hideViewLoading();
                equipmentConditionMonitorActivity.showError("暂无数据");
            }
        }
    }


    private void setEquipmentList(Collection equipmentInformations) {
        equipmentConditionMonitorActivity.setEquipmentList(equipmentInformations);
    }

    public void setView(@Nullable EquipmentConditionMonitorActivity equipmentConditionMonitorActivity) {
        this.equipmentConditionMonitorActivity = equipmentConditionMonitorActivity;
    }
}
