package com.hy.onlinemonitor.presenter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.example.bean.DomainEquipmentAlarmInformation;
import com.example.interactor.DefaultSubscriber;
import com.example.interactor.EquipmentAlarmUseCase;
import com.example.interactor.UseCase;
import com.hy.data.repository.EquipmentAlarmRepository;
import com.hy.onlinemonitor.UIThread;
import com.hy.onlinemonitor.bean.EquipmentAlarmInformation;
import com.hy.onlinemonitor.mapper.EquipmentDataMapper;
import com.hy.onlinemonitor.view.Activity.Function.EquipmentListActivity;

import java.util.Collection;
import java.util.List;

/**
 * Created by 24363 on 2015/8/13.
 */
public class EquipmentListPresenter implements Presenter
{
    private final UseCase getEquipmentListUseCase;
    private EquipmentListActivity equipmentListActivity;
    private EquipmentDataMapper equipmentDataMapper;

    public EquipmentListPresenter(Context mContext,String userName,int choiceType) {
        EquipmentAlarmRepository equipmentAlarmRepository = new EquipmentAlarmRepository(mContext,userName,choiceType);
        this.getEquipmentListUseCase = new EquipmentAlarmUseCase(new UIThread(),equipmentAlarmRepository);
        this.equipmentDataMapper = new EquipmentDataMapper();
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.getEquipmentListUseCase.unsubscribe();
    }

    private void showViewLoading() {
        this.equipmentListActivity.showLoading();
    }

    private void hideViewLoading() {
        this.equipmentListActivity.hideLoading();
    }

    public void initialize() {
        this.loadEquipmentList();
    }

    private void loadEquipmentList() {
        this.showViewLoading();
        this.getEquipmentList();
    }

    private void getEquipmentList() {
        this.getEquipmentListUseCase.execute(new EquipmentListSubscriber());
    }

    private class EquipmentListSubscriber extends DefaultSubscriber<List<DomainEquipmentAlarmInformation>> {
        @Override
        public void onCompleted() {
            EquipmentListPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(List<DomainEquipmentAlarmInformation> domainEquipmentAlarmInformations) {
            EquipmentListPresenter.this.showEquipmentAlarmCollectionInView(domainEquipmentAlarmInformations);
        }
    }

    private void showEquipmentAlarmCollectionInView(List<DomainEquipmentAlarmInformation> domainEquipmentAlarmInformations) {
        final Collection<EquipmentAlarmInformation> equipmentAlarmInformations =
                this.equipmentDataMapper.transform(domainEquipmentAlarmInformations);
        this.equipmentListActivity.renderEquipmentList(equipmentAlarmInformations);
    }

    public void setView(@Nullable EquipmentListActivity equipmentListActivity) {
        this.equipmentListActivity = equipmentListActivity;
    }

}
