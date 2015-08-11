package com.hy.data.repository.datasource;

import android.content.Context;

import com.hy.data.cache.UserCache;
import com.hy.data.entity.UserEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by 24363 on 2015/8/11.
 */
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
    public void upDataUser(int choiceType) {
        userCache.upDataUser(choiceType);
    }
}
