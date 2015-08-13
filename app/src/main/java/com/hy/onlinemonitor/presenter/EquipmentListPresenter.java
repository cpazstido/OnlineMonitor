package com.hy.onlinemonitor.presenter;

import com.example.interactor.DefaultSubscriber;
import com.example.interactor.UseCase;
import com.hy.onlinemonitor.bean.EquipmentAlarmInformation;
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

    public EquipmentListPresenter(UseCase getUserListUseCase) {
        this.getEquipmentListUseCase = getUserListUseCase;
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

    private class EquipmentListSubscriber extends DefaultSubscriber<List<EquipmentAlarmInformation>> {
        @Override
        public void onCompleted() {
            EquipmentListPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(List<EquipmentAlarmInformation> equipmentAlarmInformations) {
            EquipmentListPresenter.this.showEquipmentAlarmCollectionInView(equipmentAlarmInformations);
        }
    }

    private void showEquipmentAlarmCollectionInView(List<EquipmentAlarmInformation> equipmentAlarmInformations) {
        final Collection<EquipmentAlarmInformation> equipmentAlarmsCollection =
                this.equipmentAlarmDataMapper.transform(usersCollection);
        this.equipmentListActivity.renderUserList(userModelsC-ollection);
    }

    public void setView(EquipmentListActivity equipmentListActivity) {
        this.equipmentListActivity = equipmentListActivity;
    }


}
