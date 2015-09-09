package com.hy.data.repository;

import android.content.Context;

import com.example.bean.DomainLine;
import com.example.bean.DomainAdministratorPage;
import com.example.bean.DomainCompany;
import com.example.bean.DomainRole;
import com.example.repository.SMAdministratorRepository;
import com.hy.data.entity.mapper.LineEntityDataMapper;
import com.hy.data.entity.mapper.LineEntityJsonMapper;
import com.hy.data.entity.mapper.CompanyEntityDataMapper;
import com.hy.data.entity.mapper.CompanyEntityJsonMapper;
import com.hy.data.entity.mapper.ListOfIntegerJsonMapper;
import com.hy.data.entity.mapper.PageEntityDataMapper;
import com.hy.data.entity.mapper.PageEntityJsonMapper;
import com.hy.data.entity.mapper.RoleEntityDataMapper;
import com.hy.data.entity.mapper.RoleEntityJsonMapper;
import com.hy.data.entity.mapper.StringJsonMapper;
import com.hy.data.net.RestApiImpl;

import java.util.List;

import rx.Observable;

public class AdministratorDataRepository implements SMAdministratorRepository {
    private final Context mContext;
    private int userId;
    private  int roleSn;
    private int companySn;
    private String loginName;
    private String realName;
    private String password;
    private String mobilePhone;
    private String isMessage;
    private int adminSn;
    private int sn;
    private List<Integer> snList;
    private int allPoleSelected;

    public AdministratorDataRepository(Context mContext, int userId) {
        this.mContext = mContext;
        this.userId = userId;
    }

    public AdministratorDataRepository(Context mContext, int userId,int sn) {
        this.sn = sn;
        this.mContext = mContext;
        this.userId = userId;
    }

    public AdministratorDataRepository(Context mContext, int roleSn, int companySn, String loginName, String realName, String password, String mobilePhone,String isMessage) {
        this.mContext = mContext;
        this.roleSn = roleSn;
        this.companySn = companySn;
        this.loginName = loginName;
        this.realName = realName;
        this.password = password;
        this.mobilePhone = mobilePhone;
        this.isMessage = isMessage;
    }

    public AdministratorDataRepository(Context mContext, int sn, int roleSn, int companySn, String loginName, String realName, String password, String phoneNumber, String isMessage) {
        this.mContext = mContext;
        this.roleSn = roleSn;
        this.companySn = companySn;
        this.loginName = loginName;
        this.realName = realName;
        this.password = password;
        this.mobilePhone = phoneNumber;
        this.isMessage = isMessage;
        this.sn = sn;

    }

    public AdministratorDataRepository(int sn, Context mContext) {
        this.sn = sn;
        this.mContext = mContext;
    }

    public AdministratorDataRepository(Context mContext, int userId, int adminSn,List<Integer> snList,int allPoleSelected) {
        this.mContext = mContext;
        this.userId = userId;
        this.adminSn = adminSn;
        this.snList = snList;
        this.allPoleSelected = allPoleSelected;
    }

    @Override
    public Observable<List<DomainCompany>> companyList() {

        RestApiImpl restApi = new RestApiImpl(mContext,new CompanyEntityJsonMapper());
        CompanyEntityDataMapper companyEntityDataMapper = new CompanyEntityDataMapper();
        return restApi.companyList(userId).map(companyEntityDataMapper::transform);

    }

    @Override
    public Observable<List<DomainRole>> roleList() {
        RestApiImpl restApi = new RestApiImpl(mContext,new RoleEntityJsonMapper());
        RoleEntityDataMapper roleEntityDataMapper = new RoleEntityDataMapper();
        return restApi.roleList(userId).map(roleEntityDataMapper::transform);
    }

    @Override
    public Observable<DomainAdministratorPage> administratorList() {
        RestApiImpl restApi = new RestApiImpl(mContext,new PageEntityJsonMapper());
        PageEntityDataMapper pageEntityDataMapper = new PageEntityDataMapper();
        return restApi.administratorEntity(userId).map(pageEntityDataMapper::transform);
    }

    @Override
    public Observable<DomainAdministratorPage> addAdministrator() {
        RestApiImpl restApi = new RestApiImpl(mContext,new PageEntityJsonMapper());
        PageEntityDataMapper pageEntityDataMapper = new PageEntityDataMapper();
        return restApi.addAdministrator(roleSn,companySn,loginName,realName,password,mobilePhone,isMessage).map(pageEntityDataMapper::transform);
    }

    @Override
    public Observable<DomainAdministratorPage> changeAdministrator() {
        RestApiImpl restApi = new RestApiImpl(mContext,new PageEntityJsonMapper());
        PageEntityDataMapper pageEntityDataMapper = new PageEntityDataMapper();
        return restApi.changeAdministrator(sn,roleSn, companySn, loginName, realName, password, mobilePhone, isMessage).map(pageEntityDataMapper::transform);

    }

    @Override
    public Observable<DomainAdministratorPage> deleteAdministrator() {
        RestApiImpl restApi = new RestApiImpl(mContext,new PageEntityJsonMapper());
        PageEntityDataMapper pageEntityDataMapper = new PageEntityDataMapper();
        return restApi.deleteAdministrator(sn).map(pageEntityDataMapper::transform);

    }

    @Override
    public Observable<List<DomainLine>> getAllTower() {
        RestApiImpl restApi = new RestApiImpl(mContext,new LineEntityJsonMapper());
        LineEntityDataMapper lineEntityDataMapper = new LineEntityDataMapper();
        return restApi.getAllTower(userId,sn).map(lineEntityDataMapper::transform);
    }

    @Override
    public Observable<List<Integer>> getOwnTower() {
        RestApiImpl restApi = new RestApiImpl(mContext,new ListOfIntegerJsonMapper());
        return restApi.getOwnTower(userId,sn);
    }

    @Override
    public Observable<String> changeManageTower() {
        RestApiImpl restApi = new RestApiImpl(mContext,new StringJsonMapper());
        return restApi.changeManageTower(userId,adminSn,snList,allPoleSelected);
    }

}
