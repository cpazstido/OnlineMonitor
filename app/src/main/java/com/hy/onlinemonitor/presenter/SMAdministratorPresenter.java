package com.hy.onlinemonitor.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.bean.DomainAdministratorPage;
import com.example.bean.DomainCompany;
import com.example.bean.DomainLine;
import com.example.bean.DomainRole;
import com.example.interactor.AdministratorUseCase;
import com.example.interactor.DefaultSubscriber;
import com.example.repository.SMAdministratorRepository;
import com.hy.data.repository.AdministratorDataRepository;
import com.hy.onlinemonitor.UIThread;
import com.hy.onlinemonitor.bean.AdministratorPage;
import com.hy.onlinemonitor.bean.Company;
import com.hy.onlinemonitor.bean.Line;
import com.hy.onlinemonitor.bean.Role;
import com.hy.onlinemonitor.mapper.CompanyDataMapper;
import com.hy.onlinemonitor.mapper.LineDataMapper;
import com.hy.onlinemonitor.mapper.PageDataMapper;
import com.hy.onlinemonitor.mapper.RoleDataMapper;
import com.hy.onlinemonitor.view.Activity.SystemManagement.AdministratorManageActivity;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;

public class SMAdministratorPresenter implements Presenter {
    private AdministratorManageActivity administratorManageActivity;
    private final Context mContext;
    private AdministratorUseCase administratorUseCase;
    private PageDataMapper pageDataMapper;
    private int adminSn;
    private int allPoleSelected;

    public void setAdministratorManageActivity(AdministratorManageActivity administratorManageActivity) {
        this.administratorManageActivity = administratorManageActivity;
    }

    public SMAdministratorPresenter(Context mContext) {
        this.mContext = mContext;
        this.pageDataMapper = new PageDataMapper();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {
        administratorUseCase.unsubscribe();
    }

    @Override
    public void destroy() {

    }

    @Override
    public void showViewLoading() {
        administratorManageActivity.showLoading();
    }

    @Override
    public void hideViewLoading() {
        administratorManageActivity.hideLoading();
    }

    /**
     * 加载公司列表,权限列表
     * 必须在创建时就加载,在点击添加等时才不会报错
     * @param userId 标示某一个用户
     */
    public void loadData(int userId) {
        showViewLoading();
        SMAdministratorRepository smAdministratorRepository = new AdministratorDataRepository(mContext, userId);
        this.administratorUseCase = new AdministratorUseCase(new UIThread(), AndroidSchedulers.mainThread(), smAdministratorRepository, 1);
        this.administratorUseCase.execute(new CompanyListSubscriber());
    }

    public void addAdministrator( int roleSn, int companySn, String loginName, String realName, String password, String mobilePhone,String isMessage) {
        showViewLoading();
        SMAdministratorRepository smAdministratorRepository = new AdministratorDataRepository(mContext, roleSn,companySn,loginName,realName,password,mobilePhone,isMessage);
        this.administratorUseCase = new AdministratorUseCase(new UIThread(), AndroidSchedulers.mainThread(), smAdministratorRepository, 4);
        this.administratorUseCase.execute(new AdministratorListSubscriber());
    }

    public void changeAdministrator(int sn ,int roleSn, int companySn, String loginName, String realName, String password, String phoneNumber, String isMessage) {
        showViewLoading();
        SMAdministratorRepository smAdministratorRepository = new AdministratorDataRepository(mContext, sn,roleSn,companySn,loginName,realName,password,phoneNumber,isMessage);
        this.administratorUseCase = new AdministratorUseCase(new UIThread(), AndroidSchedulers.mainThread(), smAdministratorRepository, 5);
        this.administratorUseCase.execute(new AdministratorListSubscriber());
    }

    public void deleteAdministrator(int sn) {
        showViewLoading();
        SMAdministratorRepository smAdministratorRepository = new AdministratorDataRepository(sn,mContext);
        this.administratorUseCase = new AdministratorUseCase(new UIThread(), AndroidSchedulers.mainThread(), smAdministratorRepository, 6);
        this.administratorUseCase.execute(new AdministratorListSubscriber());
    }

    public void loadAllTower(int userId,int sn) {
        showViewLoading();
        this.adminSn = sn;
        SMAdministratorRepository smAdministratorRepository = new AdministratorDataRepository(mContext, userId, sn);
        this.administratorUseCase = new AdministratorUseCase(new UIThread(), AndroidSchedulers.mainThread(), smAdministratorRepository, 7);
        this.administratorUseCase.execute(new AdminLineListSubscriber());
    }

    public void onlyLoadAllTower(int userId, int sn) {
        showViewLoading();
        this.adminSn =sn;
        SMAdministratorRepository smAdministratorRepository = new AdministratorDataRepository(mContext, userId, sn);
        this.administratorUseCase = new AdministratorUseCase(new UIThread(), AndroidSchedulers.mainThread(), smAdministratorRepository, 7);
        this.administratorUseCase.execute(new AdminTowerListOnlySubscriber());

    }

    private class AdminTowerListOnlySubscriber extends DefaultSubscriber<List<DomainLine>> {
        @Override
        public void onCompleted() {
            SMAdministratorPresenter.this.hideViewLoading();
            administratorManageActivity.LineDialogShow();
        }

        @Override
        public void onError(Throwable e) {
            SMAdministratorPresenter.this.hideViewLoading();
            Toast.makeText(mContext, "AdminLineListOnlySubscriber出现错误", Toast.LENGTH_SHORT).show();
            super.onError(e);
        }

        @Override
        public void onNext(List<DomainLine> domainLines) {
            List<Line> mList = LineDataMapper.transform(domainLines);
            administratorManageActivity.setLines(mList);
        }
    }

    private class AdminLineListSubscriber extends DefaultSubscriber<List<DomainLine>> {
        @Override
        public void onCompleted() {
            SMAdministratorPresenter.this.administratorUseCase.setType(8);
            SMAdministratorPresenter.this.administratorUseCase.execute(new ownTowerListSubscriber());
        }

        @Override
        public void onError(Throwable e) {
            SMAdministratorPresenter.this.hideViewLoading();
            Toast.makeText(mContext, "AdminLineListSubscriber出现错误", Toast.LENGTH_SHORT).show();
            super.onError(e);
        }

        @Override
        public void onNext(List<DomainLine> domainLines) {
            List<Line> mList = LineDataMapper.transform(domainLines);
            administratorManageActivity.setLines(mList);
        }
    }

    private class ownTowerListSubscriber extends DefaultSubscriber<List<Integer>> {

        @Override
        public void onCompleted() {
            hideViewLoading();
            administratorManageActivity.LineDialogShow();
        }

        @Override
        public void onError(Throwable e) {
            SMAdministratorPresenter.this.hideViewLoading();
            Toast.makeText(mContext, "出现错误", Toast.LENGTH_SHORT).show();
            super.onError(e);
        }

        @Override
        public void onNext(List<Integer> ownTowerList) {
            administratorManageActivity.setOwnTowerSn(ownTowerList);
        }
    }

    private class CompanyListSubscriber extends DefaultSubscriber<List<DomainCompany>> {

        @Override
        public void onCompleted() {
            SMAdministratorPresenter.this.administratorUseCase.setType(2);
            SMAdministratorPresenter.this.administratorUseCase.execute(new RoleListSubscriber());
        }

        @Override
        public void onError(Throwable e) {
            SMAdministratorPresenter.this.hideViewLoading();
            Toast.makeText(mContext, "出现错误", Toast.LENGTH_SHORT).show();
            super.onError(e);
        }

        @Override
        public void onNext(List<DomainCompany> companyList) {
            List<Company> mList = CompanyDataMapper.transform(companyList);
            administratorManageActivity.setCompanyNameList(mList);
        }
    }

    private class RoleListSubscriber extends DefaultSubscriber<List<DomainRole>> {

        @Override
        public void onCompleted() {
            administratorManageActivity.setList();
            SMAdministratorPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            SMAdministratorPresenter.this.hideViewLoading();
            Toast.makeText(mContext, "出现错误", Toast.LENGTH_SHORT).show();
            super.onError(e);
        }

        @Override
        public void onNext(List<DomainRole> domainRoles) {
            List<Role> mList = RoleDataMapper.transform(domainRoles);
            administratorManageActivity.setRoleNameList(mList);
        }
    }

    /**
     * 加载管理员列表
     * @param userId 标示某一个用户
     */
    public void  loadAdminData(int userId) {
        showViewLoading();
        SMAdministratorRepository smAdministratorRepository = new AdministratorDataRepository(mContext, userId);
        this.administratorUseCase = new AdministratorUseCase(new UIThread(), AndroidSchedulers.mainThread(), smAdministratorRepository, 3);
        this.administratorUseCase.execute(new AdministratorListSubscriber());
    }

    private class AdministratorListSubscriber extends DefaultSubscriber<DomainAdministratorPage> {

        @Override
        public void onCompleted() {
            SMAdministratorPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            SMAdministratorPresenter.this.hideViewLoading();
            Toast.makeText(mContext, "出现错误", Toast.LENGTH_SHORT).show();
            super.onError(e);
        }

        @Override
        public void onNext(DomainAdministratorPage domainAdministratorPage) {
            SMAdministratorPresenter.this.showAdministratorPage(domainAdministratorPage);
        }
    }

    public void changeManageTower(int userId,List<Integer> snList,int allPoleSelected) {
        showViewLoading();
        this.allPoleSelected = allPoleSelected;
        SMAdministratorRepository smAdministratorRepository = new AdministratorDataRepository(mContext, userId,adminSn ,snList,allPoleSelected);
        this.administratorUseCase = new AdministratorUseCase(new UIThread(), AndroidSchedulers.mainThread(), smAdministratorRepository, 9);
        this.administratorUseCase.execute(new ChangeTowerManageSubscriber());
    }

    private class ChangeTowerManageSubscriber extends DefaultSubscriber<String> {

        @Override
        public void onCompleted() {
            SMAdministratorPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            SMAdministratorPresenter.this.hideViewLoading();
            Toast.makeText(mContext, "出现错误", Toast.LENGTH_SHORT).show();
            super.onError(e);
        }

        @Override
        public void onNext(String res) {
            SMAdministratorPresenter.this.setAdmin(allPoleSelected);
//            ShowUtile.toastShow(mContext,res);
        }
    }

    private void setAdmin(int allPoleSelected) {
        this.administratorManageActivity.setAdmin(allPoleSelected);
    }

    private void showAdministratorPage(DomainAdministratorPage domainAdministratorPage) {
        AdministratorPage administratorPage = this.pageDataMapper.transform(domainAdministratorPage);
        this.administratorManageActivity.renderEquipmentList(administratorPage);
    }
}
