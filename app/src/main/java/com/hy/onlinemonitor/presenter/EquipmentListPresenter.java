package com.hy.onlinemonitor.presenter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.bean.DomainEquipmentInforPage;
import com.example.bean.DomainLine;
import com.example.interactor.DefaultSubscriber;
import com.example.interactor.EquipmentInforUseCase;
import com.example.interactor.UseCase;
import com.example.repository.EquipmentInforRepository;
import com.hy.data.repository.EquipmentDataInforRepository;
import com.hy.onlinemonitor.UIThread;
import com.hy.onlinemonitor.bean.EquipmentInforPage;
import com.hy.onlinemonitor.bean.Line;
import com.hy.onlinemonitor.mapper.LineDataMapper;
import com.hy.onlinemonitor.mapper.PageDataMapper;
import com.hy.onlinemonitor.view.Activity.Function.EquipmentListViewActivity;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;

public class EquipmentListPresenter implements Presenter {
    private UseCase getEquipmentListUseCase;
    private EquipmentListViewActivity equipmentListActivity;
    private PageDataMapper pageDataMapper;
    private Context mContext;
    private int userId;
    private int selectedType;
    private int pageNumber;

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

    public void initialize(int userId, int selectedType, int pageNumber) {
        this.loadEquipmentList(userId, selectedType, pageNumber);
    }

    public void loadLineList(int userId, int selectedType, int pageNumber) {
        this.showViewLoading();
        EquipmentInforRepository equipmentDataRepository = new EquipmentDataInforRepository(mContext, userId);
        this.userId = userId;
        this.selectedType = selectedType;
        this.pageNumber = pageNumber;
        this.getEquipmentListUseCase = new EquipmentInforUseCase(new UIThread(), AndroidSchedulers.mainThread(), equipmentDataRepository, 2);
        this.getEquipmentListUseCase.execute(new TowerListSubscriber());
    }

    public void searchByLineSn(int lineSn) {
        this.showViewLoading();
        EquipmentInforRepository equipmentDataRepository = new EquipmentDataInforRepository(lineSn,mContext);
        this.getEquipmentListUseCase = new EquipmentInforUseCase(new UIThread(), AndroidSchedulers.mainThread(), equipmentDataRepository, 3);
        this.getEquipmentListUseCase.execute(new EquipmentListSubscriber());
    }

    public void searchByName(String searchName) {
        this.showViewLoading();
        EquipmentInforRepository equipmentDataRepository = new EquipmentDataInforRepository(mContext, searchName);
        this.getEquipmentListUseCase = new EquipmentInforUseCase(new UIThread(), AndroidSchedulers.mainThread(), equipmentDataRepository, 4);
        this.getEquipmentListUseCase.execute(new EquipmentListSubscriber());
    }

    private class TowerListSubscriber extends DefaultSubscriber<List<DomainLine>> {
        @Override
        public void onCompleted() {
            loadEquipmentList(userId, selectedType, pageNumber);
        }

        @Override
        public void onError(Throwable e) {
            EquipmentListPresenter.this.hideViewLoading();
            equipmentListActivity.showError(e.getMessage());
            super.onError(e);
        }

        @Override
        public void onNext(List<DomainLine> domainLines) {
            List<Line> mList = LineDataMapper.transform(domainLines);
            equipmentListActivity.setLines(mList);
        }
    }


    private void loadEquipmentList(int userId, int selectedType, int pageNumber) {
        this.showViewLoading();
        this.getEquipmentList(userId, selectedType, pageNumber);
    }

    private void getEquipmentList(int userId, int selectedType, int pageNumber) {
        EquipmentInforRepository equipmentDataRepository = new EquipmentDataInforRepository(mContext, userId, selectedType, pageNumber);
        this.getEquipmentListUseCase = new EquipmentInforUseCase(new UIThread(), AndroidSchedulers.mainThread(), equipmentDataRepository, 1);
        this.getEquipmentListUseCase.execute(new EquipmentListSubscriber());
    }

    private class EquipmentListSubscriber extends DefaultSubscriber<DomainEquipmentInforPage> {
        @Override
        public void onCompleted() {
            EquipmentListPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            Log.e("aaa", "error");
            EquipmentListPresenter.this.hideViewLoading();
            equipmentListActivity.showError(e.getMessage());
            super.onError(e);
        }

        @Override
        public void onNext(DomainEquipmentInforPage domainEquipmentInforPage) {
            EquipmentListPresenter.this.showEquipmentPage(domainEquipmentInforPage);
        }
    }

    private void showEquipmentPage(DomainEquipmentInforPage domainEquipmentInforPage) {
        EquipmentInforPage equipmentInforPage = this.pageDataMapper.transform(domainEquipmentInforPage);
        this.equipmentListActivity.renderEquipmentList(equipmentInforPage);
    }

    public void setView(@Nullable EquipmentListViewActivity equipmentListActivity) {
        this.equipmentListActivity = equipmentListActivity;
    }

}
