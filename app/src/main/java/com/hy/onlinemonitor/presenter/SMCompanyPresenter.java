package com.hy.onlinemonitor.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.bean.DomainCompany;
import com.example.interactor.CompanyUseCase;
import com.example.interactor.DefaultSubscriber;
import com.hy.data.repository.CompanyDataRepository;
import com.hy.onlinemonitor.UIThread;
import com.hy.onlinemonitor.bean.Company;
import com.hy.onlinemonitor.mapper.CompanyDataMapper;
import com.hy.onlinemonitor.view.Activity.SystemManagement.CompanyManageActivity;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Administrator on 2015/9/15.
 */
public class SMCompanyPresenter implements Presenter{
    private CompanyUseCase companyUseCase;
    private final Context mContext;
    private CompanyManageActivity companyManageActivity;
    private int userId;
    private CompanyDataRepository companyDataRepository;

    public SMCompanyPresenter(Context mContext) {
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
        companyUseCase.unsubscribe();
    }

    @Override
    public void showViewLoading() {
        companyManageActivity.showLoading();
    }

    @Override
    public void hideViewLoading() {
        companyManageActivity.hideLoading();
    }

    public void setCompanyManageActivity(CompanyManageActivity companyManageActivity) {
        this.companyManageActivity =companyManageActivity;
    }

    public void addCompany(String name, String address, int sn) {
        companyDataRepository = new CompanyDataRepository(mContext, userId,name,address,sn);
        this.companyUseCase = new CompanyUseCase(new UIThread(), AndroidSchedulers.mainThread(), companyDataRepository, 5);
        this.companyUseCase.execute(new CompanyListSubscriber());
    }

    public void loadAllCompany(int userId) {
        this.userId = userId;
        companyDataRepository = new CompanyDataRepository(mContext, userId);
        this.companyUseCase = new CompanyUseCase(new UIThread(), AndroidSchedulers.mainThread(), companyDataRepository, 1);
        this.companyUseCase.execute(new CompanyListSubscriber());
    }

    public void modifCompany(String companyName, String address, int sn) {
        showViewLoading();
        companyDataRepository = new CompanyDataRepository(mContext, userId,companyName,address,sn);
        this.companyUseCase = new CompanyUseCase(new UIThread(), AndroidSchedulers.mainThread(), companyDataRepository, 2);
        this.companyUseCase.execute(new CompanyListSubscriber());
    }
    public void deleteCompany( int sn) {
        showViewLoading();
        companyDataRepository = new CompanyDataRepository(mContext, userId,sn);
        this.companyUseCase = new CompanyUseCase(new UIThread(), AndroidSchedulers.mainThread(), companyDataRepository, 3);
        this.companyUseCase.execute(new CompanyListSubscriber());
    }

    public void loadParentCompany(int userId) {
        showViewLoading();
        this.userId = userId;
        companyDataRepository = new CompanyDataRepository(mContext, userId);
        this.companyUseCase = new CompanyUseCase(new UIThread(), AndroidSchedulers.mainThread(), companyDataRepository, 4);
        this.companyUseCase.execute(new ParentCompanyListSubscriber());
    }

    private class ParentCompanyListSubscriber extends DefaultSubscriber<List<DomainCompany>> {

        @Override
        public void onCompleted() {
            SMCompanyPresenter.this.loadAllCompany(userId);
        }

        @Override
        public void onError(Throwable e) {
            SMCompanyPresenter.this.hideViewLoading();
            Toast.makeText(mContext, "出现错误", Toast.LENGTH_SHORT).show();
            super.onError(e);
        }

        @Override
        public void onNext(List<DomainCompany> companyList) {
            List<Company> mList = CompanyDataMapper.transform(companyList);
            companyManageActivity.setParentCompanyList(mList);
        }
    }

    private class CompanyListSubscriber extends DefaultSubscriber<List<DomainCompany>> {

        @Override
        public void onCompleted() {
            SMCompanyPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            SMCompanyPresenter.this.hideViewLoading();
            Toast.makeText(mContext, "出现错误", Toast.LENGTH_SHORT).show();
            super.onError(e);
        }

        @Override
        public void onNext(List<DomainCompany> companyList) {
            List<Company> mList = CompanyDataMapper.transform(companyList);
            companyManageActivity.setCompanyList(mList);
        }
    }
}
