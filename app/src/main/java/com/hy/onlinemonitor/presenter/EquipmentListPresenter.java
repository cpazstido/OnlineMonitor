package com.hy.onlinemonitor.presenter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.example.bean.DoaminEquipmentPage;
import com.example.interactor.DefaultSubscriber;
import com.example.interactor.EquipmentUseCase;
import com.example.interactor.UseCase;
import com.hy.data.repository.EquipmentDataRepository;
import com.hy.onlinemonitor.UIThread;
import com.hy.onlinemonitor.bean.Pages;
import com.hy.onlinemonitor.mapper.PageDataMapper;
import com.hy.onlinemonitor.view.Activity.Function.EquipmentListViewActivity;

/**
 * Created by 24363 on 2015/8/13.
 */
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

    public void initialize(String userName,int selectedType) {
        this.loadEquipmentList(userName , selectedType);
    }

    private void loadEquipmentList(String userName,int selectedType) {
        this.showViewLoading();
        this.getEquipmentList(userName,selectedType);
    }

    private void getEquipmentList(String userName,int selectedType) {
        EquipmentDataRepository equipmentDataRepository = new EquipmentDataRepository(mContext,userName,selectedType);
        this.getEquipmentListUseCase = new EquipmentUseCase(new UIThread(), equipmentDataRepository);
        this.getEquipmentListUseCase.execute(new EquipmentListSubscriber());
    }

    private class EquipmentListSubscriber extends DefaultSubscriber<DoaminEquipmentPage> {
        @Override
        public void onCompleted() {
            EquipmentListPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(DoaminEquipmentPage doaminEquipmentPage) {
            EquipmentListPresenter.this.showEquipmentPage(doaminEquipmentPage);
        }
    }

    private void showEquipmentPage(DoaminEquipmentPage doaminEquipmentPage) {
        Pages pages = this.pageDataMapper.transform(doaminEquipmentPage);
        this.equipmentListActivity.renderEquipmentList(pages);
    }

    public void setView(@Nullable EquipmentListViewActivity equipmentListActivity) {
        this.equipmentListActivity = equipmentListActivity;
    }

}
