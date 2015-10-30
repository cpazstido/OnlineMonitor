package com.hy.onlinemonitor.presenter;

import android.content.Context;
import android.util.Log;

import com.example.bean.DomainConditionMonitoringPage;
import com.example.bean.DomainEquipmentInforPage;
import com.example.bean.DomainSensorType;
import com.example.interactor.ConditionMonitoringUseCase;
import com.example.interactor.DefaultSubscriber;
import com.example.interactor.UseCase;
import com.hy.data.repository.ConditionMonitoringDataRepository;
import com.hy.onlinemonitor.UIThread;
import com.hy.onlinemonitor.bean.SensorType;
import com.hy.onlinemonitor.mapper.EquipmentInforDataMapper;
import com.hy.onlinemonitor.mapper.PageDataMapper;
import com.hy.onlinemonitor.mapper.SensoTypeDataMapper;
import com.hy.onlinemonitor.view.Activity.ConditionMonitor.MonitoringStateAcitvity;
import com.hy.onlinemonitor.view.Fragment.MonitoringStateFragment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;

public class ConditionMonitoringPresenter implements Presenter {
    private static final String TAG = "Presenter";
    private Context mContext;
    private UseCase useCase;
    private ConditionMonitoringDataRepository conditionMonitoringDataRepository;
    private MonitoringStateFragment monitoringStateFragment;
    private Collection equipmentLists = new ArrayList<>();
    private MonitoringStateAcitvity monitoringStateAcitvity;
    private int userId;
    private int selectedType;
    private int transformType = -1;

    public void setMonitoringStateFragment(MonitoringStateFragment monitoringStateFragment) {
        this.monitoringStateFragment = monitoringStateFragment;
    }

    public ConditionMonitoringPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void setView(MonitoringStateAcitvity monitoringStateAcitvity) {
        this.monitoringStateAcitvity = monitoringStateAcitvity;
    }

    public void getEquipmentList(int userId, int selectedType, int pageNumber) {
        this.showViewLoading();
        this.userId = userId;
        this.selectedType = selectedType;
        this.conditionMonitoringDataRepository = new ConditionMonitoringDataRepository(mContext, pageNumber, userId, selectedType);
        this.useCase = new ConditionMonitoringUseCase(new UIThread(), AndroidSchedulers.mainThread(), conditionMonitoringDataRepository, 8);
        this.useCase.execute(new EquipmentListSubscriber());
    }

    private class EquipmentListSubscriber extends DefaultSubscriber<DomainEquipmentInforPage> {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, e.getMessage());
            ConditionMonitoringPresenter.this.hideViewLoading();
            super.onError(e);
        }

        @Override
        public void onNext(DomainEquipmentInforPage domainEquipmentInforPage) {
            if (domainEquipmentInforPage.getPageNum() == 1)
                equipmentLists.clear();
            EquipmentInforDataMapper equipmentInforDataMapper = new EquipmentInforDataMapper();
            equipmentLists.addAll(equipmentInforDataMapper.transform(domainEquipmentInforPage.getList()));
            if (domainEquipmentInforPage.getPageNum() < domainEquipmentInforPage.getTotalPage()) {
                getEquipmentList(userId, selectedType, domainEquipmentInforPage.getPageNum() + 1);
            } else {//已经加载到了最后一页
                setEquipmentList(equipmentLists);
                ConditionMonitoringPresenter.this.monitoringStateAcitvity.hideLoading();
            }
        }
    }

    private void setEquipmentList(Collection equipmentLists) {
        monitoringStateAcitvity.setEquipmentList(equipmentLists);
    }

    public void getAeolianVibration(String deviceSn, int pageNum) {
        this.showViewLoading();
        this.transformType = 1;
        this.conditionMonitoringDataRepository = new ConditionMonitoringDataRepository(mContext, deviceSn, pageNum);
        this.useCase = new ConditionMonitoringUseCase(new UIThread(), AndroidSchedulers.mainThread(), conditionMonitoringDataRepository, 2);
        this.useCase.execute(new ConditionMonitoringPageSubscriber());
    }

    public void getIceCoating(String deviceSn, int pageNum) {
        this.showViewLoading();
        this.transformType = 2;
        this.conditionMonitoringDataRepository = new ConditionMonitoringDataRepository(mContext, deviceSn, pageNum);
        this.useCase = new ConditionMonitoringUseCase(new UIThread(), AndroidSchedulers.mainThread(), conditionMonitoringDataRepository, 3);
        this.useCase.execute(new ConditionMonitoringPageSubscriber());

    }

    public void getConductorSag(String deviceSn, int pageNum) {
        this.showViewLoading();
        this.transformType = 3;
        this.conditionMonitoringDataRepository = new ConditionMonitoringDataRepository(mContext, deviceSn, pageNum);
        this.useCase = new ConditionMonitoringUseCase(new UIThread(), AndroidSchedulers.mainThread(), conditionMonitoringDataRepository, 4);
        this.useCase.execute(new ConditionMonitoringPageSubscriber());
    }

    public void getConductorSwingWithWind(String deviceSn, int pageNum) {
        this.showViewLoading();
        this.transformType = 4;
        this.conditionMonitoringDataRepository = new ConditionMonitoringDataRepository(mContext, deviceSn, pageNum);
        this.useCase = new ConditionMonitoringUseCase(new UIThread(), AndroidSchedulers.mainThread(), conditionMonitoringDataRepository, 5);
        this.useCase.execute(new ConditionMonitoringPageSubscriber());
    }

    public void getPoleStatus(String deviceSn, int pageNum) {
        this.showViewLoading();
        this.transformType = 5;
        this.conditionMonitoringDataRepository = new ConditionMonitoringDataRepository(mContext, deviceSn, pageNum);
        this.useCase = new ConditionMonitoringUseCase(new UIThread(), AndroidSchedulers.mainThread(), conditionMonitoringDataRepository, 6);
        this.useCase.execute(new ConditionMonitoringPageSubscriber());
    }

    public void getMicroclimate(String deviceSn, int pageNum) {
        this.showViewLoading();
        this.transformType = 6;
        this.conditionMonitoringDataRepository = new ConditionMonitoringDataRepository(mContext, deviceSn, pageNum);
        this.useCase = new ConditionMonitoringUseCase(new UIThread(), AndroidSchedulers.mainThread(), conditionMonitoringDataRepository, 7);
        this.useCase.execute(new ConditionMonitoringPageSubscriber());
    }


    private class ConditionMonitoringPageSubscriber extends DefaultSubscriber<DomainConditionMonitoringPage> {
        @Override
        public void onCompleted() {
            ConditionMonitoringPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, e.getMessage());
            ConditionMonitoringPresenter.this.hideViewLoading();
            //TODO 错误处理
            super.onError(e);
        }

        @Override
        public void onNext(DomainConditionMonitoringPage domainConditionMonitoringPage) {
            //TODO 交给Activity判断,并进行界面生成
            PageDataMapper pageDataMapper = new PageDataMapper();
            monitoringStateFragment.renderDataList(pageDataMapper.transform(domainConditionMonitoringPage,transformType));
        }
    }


    public void getConditionMonitoringType(String deviceSn) {
        this.showViewLoading();
        this.conditionMonitoringDataRepository = new ConditionMonitoringDataRepository(mContext, deviceSn);
        this.useCase = new ConditionMonitoringUseCase(new UIThread(), AndroidSchedulers.mainThread(), conditionMonitoringDataRepository, 1);
        this.useCase.execute(new ConditionMonitoringTypeSubscriber());
    }

    private class ConditionMonitoringTypeSubscriber extends DefaultSubscriber<List<DomainSensorType>> {
        @Override
        public void onCompleted() {
            ConditionMonitoringPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, e.getMessage());
            ConditionMonitoringPresenter.this.hideViewLoading();
            //TODO 错误处理
            super.onError(e);
        }

        @Override
        public void onNext(List<DomainSensorType> domainSensorTypes) {
            List<SensorType> mList = SensoTypeDataMapper.transform(domainSensorTypes);
            LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
            for (SensorType sensorType : mList) {
                linkedHashSet.add(sensorType.getSensorName());
            }
            Iterator<String> iterator = linkedHashSet.iterator();
            List<String> list = new ArrayList<>();
            while (iterator.hasNext()) {
                list.add(iterator.next());
            }
            monitoringStateAcitvity.pagerInit(list);
        }
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void showViewLoading() {
        monitoringStateAcitvity.showLoading();
    }

    @Override
    public void hideViewLoading() {
        monitoringStateAcitvity.hideLoading();
    }
}
