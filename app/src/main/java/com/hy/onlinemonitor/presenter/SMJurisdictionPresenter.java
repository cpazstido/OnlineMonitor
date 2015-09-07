package com.hy.onlinemonitor.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.bean.DomainRolePage;
import com.example.interactor.DefaultSubscriber;
import com.example.interactor.JurisdictionUseCase;
import com.example.repository.SMJurisdictionRepository;
import com.hy.data.repository.JurisdictionDataRepository;
import com.hy.onlinemonitor.UIThread;
import com.hy.onlinemonitor.bean.RolePage;
import com.hy.onlinemonitor.mapper.PageDataMapper;
import com.hy.onlinemonitor.view.Activity.SystemManagement.JurisdictionManageActivity;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by 24363 on 2015/9/7.
 */
public class SMJurisdictionPresenter implements Presenter{

    private final Context mContext;
    private PageDataMapper pageDataMapper;
    private JurisdictionUseCase jurisdictionUseCase;
    private JurisdictionManageActivity jurisdictionManageActivity;

    public void setJurisdictionManageActivity(JurisdictionManageActivity jurisdictionManageActivity) {
        this.jurisdictionManageActivity = jurisdictionManageActivity;
    }

    public SMJurisdictionPresenter(Context mContext) {
        this.mContext = mContext;
        this.pageDataMapper = new PageDataMapper();
    }

    /**
     * 加载role列表
     * @param userId 唯一标示
     */
    public void loadRole(int userId){
        showViewLoading();
        SMJurisdictionRepository smJurisdictionRepository = new JurisdictionDataRepository(mContext, userId);
        this.jurisdictionUseCase = new JurisdictionUseCase(new UIThread(), AndroidSchedulers.mainThread(), smJurisdictionRepository, 1);
        this.jurisdictionUseCase.execute(new RolePageSubscriber());

    }
    public void addRole(int userId,String roleName){
        showViewLoading();
        SMJurisdictionRepository smJurisdictionRepository = new JurisdictionDataRepository(mContext, userId,roleName);
        this.jurisdictionUseCase = new JurisdictionUseCase(new UIThread(), AndroidSchedulers.mainThread(), smJurisdictionRepository, 2);
        this.jurisdictionUseCase.execute(new RolePageSubscriber());
    }

    public void deleteRole(int userId,int roleSn){
        showViewLoading();
        SMJurisdictionRepository smJurisdictionRepository = new JurisdictionDataRepository(mContext, userId,roleSn);
        this.jurisdictionUseCase = new JurisdictionUseCase(new UIThread(), AndroidSchedulers.mainThread(), smJurisdictionRepository, 3);
        this.jurisdictionUseCase.execute(new RolePageSubscriber());
    }
    public void changeRole(int roleSn,String roleName){
        showViewLoading();
        SMJurisdictionRepository smJurisdictionRepository = new JurisdictionDataRepository(mContext, roleSn,roleName);
        this.jurisdictionUseCase = new JurisdictionUseCase(new UIThread(), AndroidSchedulers.mainThread(), smJurisdictionRepository, 4);
        this.jurisdictionUseCase.execute(new RolePageSubscriber());
    }

    private class RolePageSubscriber extends DefaultSubscriber<DomainRolePage> {

        @Override
        public void onCompleted() {
            SMJurisdictionPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            SMJurisdictionPresenter.this.hideViewLoading();
            Toast.makeText(mContext, "出现错误", Toast.LENGTH_SHORT).show();
            super.onError(e);
        }

        @Override
        public void onNext(DomainRolePage domainRolePage) {
            SMJurisdictionPresenter.this.showRolePageInView(domainRolePage);
        }
    }

    private void showRolePageInView(DomainRolePage domainRolePage) {
        RolePage rolePage = this.pageDataMapper.transform(domainRolePage);
        this.jurisdictionManageActivity.renderRolePage(rolePage);
    }


    public void changeJurisdiction(int roleSn,List<Integer> snList ){
        showViewLoading();
        SMJurisdictionRepository smJurisdictionRepository = new JurisdictionDataRepository(mContext, roleSn,snList);
        this.jurisdictionUseCase = new JurisdictionUseCase(new UIThread(), AndroidSchedulers.mainThread(), smJurisdictionRepository, 5);
        this.jurisdictionUseCase.execute(new JurisdictionSubscriber());
    }

    private class JurisdictionSubscriber extends DefaultSubscriber<String> {

        @Override
        public void onCompleted() {
            SMJurisdictionPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            SMJurisdictionPresenter.this.hideViewLoading();
            Toast.makeText(mContext, "出现错误", Toast.LENGTH_SHORT).show();
            super.onError(e);
        }

        @Override
        public void onNext(String response) {

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
        this.jurisdictionManageActivity.showLoading();
    }

    @Override
    public void hideViewLoading() {
        this.jurisdictionManageActivity.hideLoading();
    }
}
