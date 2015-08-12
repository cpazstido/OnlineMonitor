package com.hy.data.cache;

import com.hy.data.entity.UserEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by 24363 on 2015/8/11.
 */
public interface UserCache {

    Observable<UserEntity> getUserInfor();

    void put(UserEntity userEntity);

    Observable<String> upDataUser(int choiceType);

    Observable<List<String>> ownedEquipmentList();
}
