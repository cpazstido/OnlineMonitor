package com.hy.onlinemonitor.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.bean.DomainPrivilege;
import com.example.bean.DomainRolePage;
import com.example.interactor.DefaultSubscriber;
import com.example.interactor.JurisdictionUseCase;
import com.example.repository.SMJurisdictionRepository;
import com.hy.data.repository.JurisdictionDataRepository;
import com.hy.onlinemonitor.UIThread;
import com.hy.onlinemonitor.bean.Privilege;
import com.hy.onlinemonitor.bean.RolePage;
import com.hy.onlinemonitor.mapper.PageDataMapper;
import com.hy.onlinemonitor.mapper.PrivilegeDataMapper;
import com.hy.onlinemonitor.utile.ShowUtile;
import com.hy.onlinemonitor.view.Activity.SystemManagement.JurisdictionManageActivity;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by 24363 on 2015/9/7.
 */
public class SMJurisdictionPresenter implements Presenter {

    private final Context mContext;
    private PageDataMapper pageDataMapper;
    private JurisdictionUseCase jurisdictionUseCase;
    private JurisdictionManageActivity jurisdictionManageActivity;
    private PrivilegeDataMapper privilegeDataMapper;
    private int userId;

    public void setJurisdictionManageActivity(JurisdictionManageActivity jurisdictionManageActivity) {
        this.jurisdictionManageActivity = jurisdictionManageActivity;
    }

    public SMJurisdictionPresenter(Context mContext) {
        this.mContext = mContext;
        this.pageDataMapper = new PageDataMapper();
        this.privilegeDataMapper = new PrivilegeDataMapper();
    }

    /**
     * 加载role列表
     *
     * @param userId 唯一标示
     */
    public void loadRole(int userId) {
        showViewLoading();
        this.userId = userId;
        SMJurisdictionRepository smJurisdictionRepository = new JurisdictionDataRepository(mContext, userId);
        this.jurisdictionUseCase = new JurisdictionUseCase(new UIThread(), AndroidSchedulers.mainThread(), smJurisdictionRepository, 1);
        this.jurisdictionUseCase.execute(new RolePageSubscriber());
    }

    public void addRole(String roleName) {
        showViewLoading();
        SMJurisdictionRepository smJurisdictionRepository = new JurisdictionDataRepository(mContext, userId, roleName);
        this.jurisdictionUseCase = new JurisdictionUseCase(new UIThread(), AndroidSchedulers.mainThread(), smJurisdictionRepository, 2);
        this.jurisdictionUseCase.execute(new RolePageSubscriber());
    }

    public void deleteRole(int roleSn) {
        showViewLoading();
        SMJurisdictionRepository smJurisdictionRepository = new JurisdictionDataRepository(mContext, userId, roleSn);
        this.jurisdictionUseCase = new JurisdictionUseCase(new UIThread(), AndroidSchedulers.mainThread(), smJurisdictionRepository, 3);
        this.jurisdictionUseCase.execute(new RolePageSubscriber());
    }

    public void changeRole(int roleSn, String roleName) {
        showViewLoading();
        SMJurisdictionRepository smJurisdictionRepository = new JurisdictionDataRepository(mContext, roleSn, roleName);
        this.jurisdictionUseCase = new JurisdictionUseCase(new UIThread(), AndroidSchedulers.mainThread(), smJurisdictionRepository, 4);
        this.jurisdictionUseCase.execute(new RolePageSubscriber());
    }

    public void getOwnJurisdiction(int roleSn) {
        showViewLoading();
        SMJurisdictionRepository smJurisdictionRepository = new JurisdictionDataRepository(mContext, userId, roleSn);
        this.jurisdictionUseCase = new JurisdictionUseCase(new UIThread(), AndroidSchedulers.mainThread(), smJurisdictionRepository, 7);
        this.jurisdictionUseCase.execute(new OwnPrivilegeSubscriber());

    }

    private class RolePageSubscriber extends DefaultSubscriber<DomainRolePage> {

        @Override
        public void onCompleted() {
            SMJurisdictionPresenter.this.jurisdictionUseCase.setType(6);
            SMJurisdictionPresenter.this.jurisdictionUseCase.execute(new AllPrivilegeSubscriber());
        }

        @Override
        public void onError(Throwable e) {
            SMJurisdictionPresenter.this.hideViewLoading();
            Toast.makeText(mContext, "RolePageSubscriber+出现错误", Toast.LENGTH_SHORT).show();
            super.onError(e);
        }

        @Override
        public void onNext(DomainRolePage domainRolePage) {
            SMJurisdictionPresenter.this.showRolePageInView(domainRolePage);
        }
    }

    private class AllPrivilegeSubscriber extends DefaultSubscriber<List<DomainPrivilege>> {

        @Override
        public void onCompleted() {
            SMJurisdictionPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            SMJurisdictionPresenter.this.hideViewLoading();
            Toast.makeText(mContext, "AllPrivilegeSubscriber+出现错误", Toast.LENGTH_SHORT).show();
            super.onError(e);
        }

        @Override
        public void onNext(List<DomainPrivilege> domainPrivileges) {
            SMJurisdictionPresenter.this.setPrivileges(domainPrivileges);
        }
    }

    private class OwnPrivilegeSubscriber extends DefaultSubscriber<List<DomainPrivilege>> {

        @Override
        public void onCompleted() {
            SMJurisdictionPresenter.this.hideViewLoading();
            SMJurisdictionPresenter.this.jurisdictionManageActivity.changeJurisdictionDialogShow();
        }

        @Override
        public void onError(Throwable e) {
            SMJurisdictionPresenter.this.hideViewLoading();
            Toast.makeText(mContext, "AllPrivilegeSubscriber+出现错误", Toast.LENGTH_SHORT).show();
            super.onError(e);
        }

        @Override
        public void onNext(List<DomainPrivilege> domainPrivileges) {
            SMJurisdictionPresenter.this.setOwnPrivileges(domainPrivileges);
        }
    }

    private void setOwnPrivileges(List<DomainPrivilege> domainPrivileges) {
        List<Privilege> privileges = this.privilegeDataMapper.transform(domainPrivileges);
        this.jurisdictionManageActivity.setOwnPrivileges(privileges);
    }

    private void setPrivileges(List<DomainPrivilege> domainPrivileges) {
        List<Privilege> privileges = this.privilegeDataMapper.transform(domainPrivileges);
        this.jurisdictionManageActivity.setPrivileges(privileges);
    }

    private void showRolePageInView(DomainRolePage domainRolePage) {
        RolePage rolePage = this.pageDataMapper.transform(domainRolePage);
        this.jurisdictionManageActivity.renderRolePage(rolePage);
    }


    public void changeJurisdiction(int roleSn, List<Integer> snList) {
        showViewLoading();
        SMJurisdictionRepository smJurisdictionRepository = new JurisdictionDataRepository(mContext, roleSn, snList);
        this.jurisdictionUseCase = new JurisdictionUseCase(new UIThread(), AndroidSchedulers.mainThread(), smJurisdictionRepository, 5);
        this.jurisdictionUseCase.execute(new JurisdictionSubscriber());
    }

    private class JurisdictionSubscriber extends DefaultSubscriber<String> {

        @Override
        public void onCompleted() {
            SMJurisdictionPresenter.this.hideViewLoading();
            ShowUtile.toastShow(mContext,"修改完成");
        }

        @Override
        public void onError(Throwable e) {
            SMJurisdictionPresenter.this.hideViewLoading();
            Toast.makeText(mContext, "JurisdictionSubscriber+出现错误", Toast.LENGTH_SHORT).show();
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
        jurisdictionUseCase.unsubscribe();
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
