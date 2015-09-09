package com.hy.onlinemonitor.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.bean.DomainCompany;
import com.example.interactor.DefaultSubscriber;
import com.example.interactor.PoleUseCase;
import com.hy.data.repository.PoleDataRepository;
import com.hy.onlinemonitor.UIThread;
import com.hy.onlinemonitor.bean.Company;
import com.hy.onlinemonitor.mapper.CompanyDataMapper;
import com.hy.onlinemonitor.mapper.PageDataMapper;
import com.hy.onlinemonitor.view.Activity.SystemManagement.PoleManageActivity;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by 24363 on 2015/9/9.
 */
public class SMPolePresenter implements Presenter{
    private final Context mContext;

    public void setPoleManageActivity(PoleManageActivity poleManageActivity) {
        this.poleManageActivity = poleManageActivity;
    }
    private PoleManageActivity poleManageActivity;
    private PoleUseCase poleUseCase;
    private PageDataMapper pageDataMapper;
    private PoleDataRepository poleDataRepository;
    private int userId;

    public SMPolePresenter(Context mContext) {
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
        this.poleUseCase.unsubscribe();
    }

    @Override
    public void showViewLoading() {
        this.poleManageActivity.showLoading();
    }

    @Override
    public void hideViewLoading() {
        this.poleManageActivity.hideLoading();
    }

    public void loadAllLine(int userId) {
        showViewLoading();
        poleDataRepository = new PoleDataRepository(mContext, userId);
        this.poleUseCase = new PoleUseCase(new UIThread(), AndroidSchedulers.mainThread(), poleDataRepository, 1);
        this.poleUseCase.execute(new LineListSubscriber());
    }

    private class LineListSubscriber extends DefaultSubscriber<List<DomainCompany>> {
        @Override
        public void onCompleted() {
            SMPolePresenter.this.hideViewLoading();
            showViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            SMPolePresenter.this.hideViewLoading();
            Toast.makeText(mContext, "AdminLineListOnlySubscriber出现错误", Toast.LENGTH_SHORT).show();
            super.onError(e);
        }

        @Override
        public void onNext(List<DomainCompany> domainCompanies) {
            List<Company> mList = CompanyDataMapper.transform(domainCompanies);
            poleManageActivity.setCompanyList(mList);
        }
    }
}
