package com.hy.onlinemonitor.presenter;

import android.content.Context;

import com.example.bean.DomainCompany;
import com.example.bean.DomainOnlineDeviceStatePage;
import com.example.interactor.DefaultSubscriber;
import com.example.interactor.EquipmentStateMonitorUseCase;
import com.example.interactor.UseCase;
import com.hy.data.repository.EquipmentStateMonitorDataRepository;
import com.hy.onlinemonitor.UIThread;
import com.hy.onlinemonitor.bean.Company;
import com.hy.onlinemonitor.mapper.CompanyDataMapper;
import com.hy.onlinemonitor.mapper.PageDataMapper;
import com.hy.onlinemonitor.view.Activity.ConditionMonitor.EquipmentStateMonitorActivity;
import com.hy.onlinemonitor.view.Fragment.EquipmentStateMonitorFragment;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by 24363 on 2015/10/17.
 */
public class EquipmentStateMonitorPresenter implements Presenter {
    private Context mContext;
    private UseCase equipmentConditionMonitorUseCase;
    private EquipmentStateMonitorDataRepository equipmentStateMonitorDataRepository;
    private EquipmentStateMonitorActivity equipmentStateMonitorActivity;
    private EquipmentStateMonitorFragment equipmentStateMonitorFragment;

    public EquipmentStateMonitorPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void setView(EquipmentStateMonitorActivity equipmentStateMonitorActivity) {
        this.equipmentStateMonitorActivity = equipmentStateMonitorActivity;
    }

    public void setEquipmentStateMonitorFragment(EquipmentStateMonitorFragment equipmentStateMonitorFragment) {
        this.equipmentStateMonitorFragment = equipmentStateMonitorFragment;
    }

    /**
     * 加载所有线路,以便用于切换线路
     *
     * @param userId 用户唯一标示
     */
    public void loadAllLine(int userId) {
        showViewLoading();
        equipmentStateMonitorDataRepository = new EquipmentStateMonitorDataRepository(mContext, userId);
        this.equipmentConditionMonitorUseCase = new EquipmentStateMonitorUseCase(new UIThread(), AndroidSchedulers.mainThread(), equipmentStateMonitorDataRepository, 1);
        this.equipmentConditionMonitorUseCase.execute(new LineListSubscriber());
    }

    private class LineListSubscriber extends DefaultSubscriber<List<DomainCompany>> {
        @Override
        public void onCompleted() {
            EquipmentStateMonitorPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            EquipmentStateMonitorPresenter.this.hideViewLoading();
            equipmentStateMonitorActivity.showError(e.getMessage());
            super.onError(e);
        }

        @Override
        public void onNext(List<DomainCompany> domainCompanies) {
            List<Company> mList = CompanyDataMapper.transform(domainCompanies);
            equipmentStateMonitorActivity.setCompanyList(mList);
        }
    }

    /**
     * 加载装置信息列表
     *
     * @param mContext Context
     * @param userId   userId
     * @param lineSn   线路sn
     * @param pageNum  分页
     */
    public void loadOnlineDeviceState(Context mContext, int userId, int lineSn, int pageNum) {
        showViewLoading();
        equipmentStateMonitorDataRepository = new EquipmentStateMonitorDataRepository(mContext, userId, lineSn, pageNum);
        this.equipmentConditionMonitorUseCase = new EquipmentStateMonitorUseCase(new UIThread(), AndroidSchedulers.mainThread(), equipmentStateMonitorDataRepository, 2);
        this.equipmentConditionMonitorUseCase.execute(new OnlineDeviceStateSubscriber());
    }

    private class OnlineDeviceStateSubscriber extends DefaultSubscriber<DomainOnlineDeviceStatePage> {
        @Override
        public void onCompleted() {
            EquipmentStateMonitorPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            EquipmentStateMonitorPresenter.this.hideViewLoading();
            equipmentStateMonitorActivity.showError(e.getMessage());
            super.onError(e);
        }

        @Override
        public void onNext(DomainOnlineDeviceStatePage domainOnlineDeviceStatePage) {
            PageDataMapper pageDataMapper = new PageDataMapper();
            equipmentStateMonitorFragment.renderOnlineDeviceStateList(pageDataMapper.transform(domainOnlineDeviceStatePage));
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
        equipmentConditionMonitorUseCase.unsubscribe();
    }

    @Override
    public void showViewLoading() {
        equipmentStateMonitorActivity.showLoading();
    }

    @Override
    public void hideViewLoading() {
        equipmentStateMonitorActivity.hideLoading();
    }
}
