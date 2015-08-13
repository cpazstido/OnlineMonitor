package com.hy.data.cache;

import android.content.Context;

import com.hy.data.entity.UserEntity;
import com.hy.data.exception.UserNotFoundException;
import com.hy.data.utile.TransformationUtils;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by 24363 on 2015/8/11.
 */
public class UserCacheImp implements UserCache{

    private Context mContext;
    public UserCacheImp(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public Observable<UserEntity> getUserInfor() {
        return Observable.create(new Observable.OnSubscribe<UserEntity>() {
            @Override
            public void call(Subscriber<? super UserEntity> subscriber) {
                DbUtils db = DbUtils.create(mContext);
                UserEntity userEntity =null;
                try {
                    List<UserEntity> users = db.findAll(Selector.from(UserEntity.class));
                    userEntity = users.get(0);

                } catch (DbException e) {
                    e.printStackTrace();
                }
                if(userEntity !=null) {

                    subscriber.onNext(userEntity);
                    subscriber.onCompleted();
                }else{
                    new Throwable("userEntity is NUll");
                }
            }
        });
    }

    @Override
    public void put(UserEntity userEntity) {
        DbUtils db = DbUtils.create(mContext);
        try {
            db.deleteAll(UserEntity.class);
            db.save(userEntity);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Observable<String> upDataUser(int choiceType) {

        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                DbUtils db = DbUtils.create(mContext);
                List<UserEntity> users = null;
                try {
                    users = db.findAll(Selector.from(UserEntity.class));
                    if (users != null) {
                        UserEntity  userEntity = users.get(0);
                        userEntity.setSelectionType(choiceType);
                        db.saveOrUpdate(userEntity);
                        users = db.findAll(Selector.from(UserEntity.class));
                    }
                } catch (DbException e) {
                    e.printStackTrace();
                }
                subscriber.onNext("success");
                subscriber.onCompleted();
            }
        });
    }

    @Override
    public Observable<List<String>> ownedEquipmentList() {
        return Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                List<String> ownedEquipmentList =null;
                try {
                    DbUtils db = DbUtils.create(mContext);
                    List<UserEntity> users = db.findAll(Selector.from(UserEntity.class));
                    UserEntity  userEntity = users.get(0);
                    String ownedEquipmentStr = userEntity.getOwnedEquipment();
                    ownedEquipmentList = TransformationUtils.getListFromString(ownedEquipmentStr, ",");
                } catch (DbException e) {
                    e.printStackTrace();
                }
                if(ownedEquipmentList != null){
                    subscriber.onNext(ownedEquipmentList);
                    subscriber.onCompleted();
                } else {
                    subscriber.onError(new UserNotFoundException());
                }
            }
        });
    }
}
