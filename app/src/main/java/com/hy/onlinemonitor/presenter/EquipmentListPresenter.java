package com.hy.onlinemonitor.presenter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.bean.DoaminEquipmentPage;
import com.example.interactor.DefaultSubscriber;
import com.example.interactor.EquipmentUseCase;
import com.example.interactor.UseCase;
import com.example.repository.EquipmentRepository;
import com.hy.data.repository.EquipmentDataRepository;
import com.hy.onlinemonitor.UIThread;
import com.hy.onlinemonitor.bean.EquipmentPage;
import com.hy.onlinemonitor.mapper.PageDataMapper;
import com.hy.onlinemonitor.view.Activity.Function.EquipmentListViewActivity;

import rx.android.schedulers.AndroidSchedulers;

public class EquipmentListPresenter implements Presenter
{
    private UseCase getEquipmentListUseCase;
    private EquipmentListViewActivity equipmentListActivity;
    private PageDataMapper pageDataMapper;
    private Context mContext;
    public EquipmentListPresenter(Context mContext) {
        this.mContext = mContext;
        this.pageDataMapper = new PageDataMapper();
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
    @Override
    public void showViewLoading() {
        this.equipmentListActivity.showLoading();
    }
    @Override
    public void hideViewLoading() {
        this.equipmentListActivity.hideLoading();
    }

    public void initialize(int userId,int selectedType,int pageNumber) {
        this.loadEquipmentList(userId , selectedType ,pageNumber);
    }

    private void loadEquipmentList(int userId,int selectedType,int pageNumber) {
        this.showViewLoading();
        this.getEquipmentList(userId, selectedType, pageNumber);
    }

    private void getEquipmentList(int userId,int selectedType,int pageNumber) {
        EquipmentRepository equipmentDataRepository = new EquipmentDataRepository(mContext,userId,selectedType,pageNumber);
        this.getEquipmentListUseCase = new EquipmentUseCase(new UIThread(), AndroidSchedulers.mainThread(), equipmentDataRepository);
        this.getEquipmentListUseCase.execute(new EquipmentListSubscriber());
    }

    private class EquipmentListSubscriber extends DefaultSubscriber<DoaminEquipmentPage> {
        @Override
        public void onCompleted() {
            EquipmentListPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            Log.e("aaa","error");
            EquipmentListPresenter.this.hideViewLoading();
            super.onError(e);
        }

        @Override
        public void onNext(DoaminEquipmentPage doaminEquipmentPage) {
            EquipmentListPresenter.this.showEquipmentPage(doaminEquipmentPage);
        }
    }

    private void showEquipmentPage(DoaminEquipmentPage doaminEquipmentPage) {
        EquipmentPage equipmentPage = this.pageDataMapper.transform(doaminEquipmentPage);
        this.equipmentListActivity.renderEquipmentList(equipmentPage);
    }

    public void setView(@Nullable EquipmentListViewActivity equipmentListActivity) {
        this.equipmentListActivity = equipmentListActivity;
    }






}
