package com.hy.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;
import com.hy.data.entity.EquipmentAlarmEntity;
import com.hy.data.entity.UserEntity;
import com.hy.data.entity.mapper.EquipmentAlarmEntityJsonMapper;
import com.hy.data.entity.mapper.UserEntityJsonMapper;
import com.hy.data.exception.NetworkConnectionException;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * 用于取得网络数据
 */
public class RestApiImpl implements RestApi {

    private final Context context;
    private UserEntityJsonMapper userEntityJsonMapper;
    private EquipmentAlarmEntityJsonMapper equipmentAlarmEntityJsonMapper;
    /**
     * Constructor of the class
     *
     * @param context {@link Context}.
     * @param userEntityJsonMapper {@link UserEntityJsonMapper}.
     */
    public RestApiImpl(Context context, UserEntityJsonMapper userEntityJsonMapper) {
        if (context == null || userEntityJsonMapper == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.userEntityJsonMapper = userEntityJsonMapper;
    }

    public RestApiImpl(Context context, EquipmentAlarmEntityJsonMapper equipmentAlarmEntityJsonMapper) {
        if (context == null || equipmentAlarmEntityJsonMapper == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context;
        this.equipmentAlarmEntityJsonMapper = equipmentAlarmEntityJsonMapper;
    }

    /**
     * 获取用户登录信息
     * @param loginAccount  用户名
     * @param loginPwd  用户密码
     * @return  UserEntity
     */

    @Override
    public Observable<UserEntity> userEntity(String loginAccount, String loginPwd) {
        return Observable.create(new Observable.OnSubscribe<UserEntity>() {
         @Override
         public void call(Subscriber<? super UserEntity> subscriber) {

             String responseUserEntities = null;
             responseUserEntities = getTestUserEntities(loginAccount, loginPwd);
//           responseUserEntities = getUserEntitiesFromApi();
             if (responseUserEntities != null) {
                 subscriber.onNext(userEntityJsonMapper.transformUserEntity(
                         responseUserEntities));
                 subscriber.onCompleted();
             } else {
                 subscriber.onError(new NetworkConnectionException());
             }
         }
     }
        );
    }

    /**
     *
     * @param userName  用户名
     * @param choiceType 选择的查看类型
     * @return List<EquipmentAlarmEntity>
     */
    @Override
    public Observable<List<EquipmentAlarmEntity>> equipmentAlarmEntity(String userName, int choiceType) {
        return Observable.create(new Observable.OnSubscribe<List<EquipmentAlarmEntity>>() {
            @Override
            public void call(Subscriber<? super List<EquipmentAlarmEntity>> subscriber) {
                String responseEquipmentAlarmEntities = null;
                responseEquipmentAlarmEntities = getTestEquipmentAlarmEntities(userName, choiceType);
//                responseEquipmentAlarmEntities = getEquipmentAlarmEntitiesFromApi(UserName, choiceType);
                if (responseEquipmentAlarmEntities != null) {
                    subscriber.onNext(equipmentAlarmEntityJsonMapper.transformEquipmentAlarmEntity(
                            responseEquipmentAlarmEntities));
                    subscriber.onCompleted();
                } else {
                    subscriber.onError(new NetworkConnectionException());
                }
            }
        });
    }



    private String getUserEntitiesFromApi() {
        //        RequestParams params= new RequestParams();
//        params.put("loginAccount",loginAccount);
//        params.put("loginPwd",loginPwd);
//        SystemRestClient.post("/login", params, new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                        Log.i("msg", "登陆成功");
//                        String result = null;
//                        try {
//                            result = new String(responseBody, "UTF-8");
//                        } catch (UnsupportedEncodingException e) {
//                            e.printStackTrace();
//                        }
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                new SnackBar(context).text("连接失败,请稍后再试....").show();
//            }
//        });
        return null;
    }

    private String getTestUserEntities(String loginAccount, String loginPwd) {

        Gson gson = new Gson();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return gson.toJson(new UserEntity("山西电力", "山火,外破,普通视频,无人机"));
    }

    private String getTestEquipmentAlarmEntities(String userName, int choiceType) {
        Gson gson = new Gson();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<EquipmentAlarmEntity> equipmentAlarmEntities = new ArrayList<>();
        equipmentAlarmEntities.add(new EquipmentAlarmEntity("美国超级电力", "摄像机开电",2, 0, 1, 1));
        equipmentAlarmEntities.add(new EquipmentAlarmEntity("英国核电力", "摄像机正在开电",3, 1, 0, 1));
        equipmentAlarmEntities.add(new EquipmentAlarmEntity("火星电力", "摄像机断电",5,1, 1, 0));
        equipmentAlarmEntities.add(new EquipmentAlarmEntity("什么么贵","开电中",2,2,0,1));
        return gson.toJson(equipmentAlarmEntities);
    }

    private String getEquipmentAlarmEntitiesFromApi(String userName, int choiceType) {

        return null;
    }

    private boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }

}