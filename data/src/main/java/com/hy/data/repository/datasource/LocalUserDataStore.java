package com.hy.data.repository.datasource;

import android.content.Context;

import com.hy.data.cache.UserCache;
import com.hy.data.entity.UserEntity;

import java.util.List;

import rx.Observable;


public class LocalUserDataStore implements UserDataStore{
    UserCache userCache;
    Context mContext;

    public LocalUserDataStore(Context mContext,UserCache userCache) {
        this.mContext = mContext;
        this.userCache = userCache;
    }

    @Override
    public Observable<UserEntity> userEntity(String loginAccount, String loginPwd) {
        return null;
    }

    @Override
    public Observable<List<String>> equipmentList() {
        return userCache.ownedEquipmentList();
    }

    @Override
    public Observable<String> upDataUser(int choiceType) {
        return userCache.upDataUser(choiceType);
    }

    @Override
    public Observable<UserEntity> getUserInfor() {
        return userCache.getUserInfor();
    }


}
