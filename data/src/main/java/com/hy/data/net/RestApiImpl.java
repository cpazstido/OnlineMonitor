package com.hy.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;
import com.hy.data.entity.AlarmEntity;
import com.hy.data.entity.EquipmentEntity;
import com.hy.data.entity.UserEntity;
import com.hy.data.entity.mapper.AlarmEntityJsonMapper;
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
    private AlarmEntityJsonMapper alarmEntityJsonMapper;
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

    public RestApiImpl(Context context, AlarmEntityJsonMapper alarmEntityJsonMapper) {
        if (context == null || alarmEntityJsonMapper == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context;
        this.alarmEntityJsonMapper = alarmEntityJsonMapper;
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
           responseUserEntities = getUserEntitiesFromApi();
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
     */
    @Override
    public Observable<List<EquipmentEntity>> equipmentEntity(String userName, int choiceType) {
        return Observable.create(new Observable.OnSubscribe<List<EquipmentEntity>>() {
            @Override
            public void call(Subscriber<? super List<EquipmentEntity>> subscriber) {
                String responseEquipmentEntities = null;
                responseEquipmentEntities = getEquipmentEntitiesFromApi(userName, choiceType);
                if (responseEquipmentEntities != null) {
                    subscriber.onNext(equipmentAlarmEntityJsonMapper.transformEquipmentAlarmEntity(
                            responseEquipmentEntities));
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
        Gson gson = new Gson();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return gson.toJson(new UserEntity("山西电力", "山火,外破,普通视频,无人机"));
    }

    private String getEquipmentEntitiesFromApi(String userName, int choiceType) {
        Gson gson = new Gson();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<EquipmentEntity> equipmentAlarmEntities = new ArrayList<>();
        equipmentAlarmEntities.add(new EquipmentEntity("美国超级电力", "摄像机开电",2, 0, 1, 1));
        equipmentAlarmEntities.add(new EquipmentEntity("英国核电力", "摄像机正在开电",3, 1, 0, 1));
        equipmentAlarmEntities.add(new EquipmentEntity("火星电力", "摄像机断电",5,1, 1, 0));
        equipmentAlarmEntities.add(new EquipmentEntity("什么么贵","开电中",2,2,0,1));
        return gson.toJson(equipmentAlarmEntities);
    }

    @Override
    public Observable<List<AlarmEntity>> alarmEntity(String userName, String title) {
        return Observable.create(new Observable.OnSubscribe<List<AlarmEntity>>() {
            @Override
            public void call(Subscriber<? super List<AlarmEntity>> subscriber) {
                String responseAlarmEntities = null;
                int choiceType = -1;
                switch (title){
                    case "山火历史报警":
                        choiceType =0;
                        break;

                }

                responseAlarmEntities = getAlarmEntitiesFromApi(userName, choiceType);
                if (responseAlarmEntities != null) {
                    subscriber.onNext(alarmEntityJsonMapper.transformEquipmentAlarmEntity(
                            responseAlarmEntities));
                    subscriber.onCompleted();
                } else {
                    subscriber.onError(new NetworkConnectionException());
                }
            }
        });
    }

    private String getAlarmEntitiesFromApi(String userName, int choiceType) {
        Gson gson = new Gson();
        List<AlarmEntity> alarmEntities = new ArrayList<>();
        alarmEntities.add(new AlarmEntity("分分分", "http://pic.yesky.com/imagelist/07/04/1837387_7424.jpg", "http://www.zgmaimai.cn/uploads/allimg/c120621/1340251A2213Z-2K263.jpg", "是", "紧急的报警", "已处理", 0));
        alarmEntities.add(new AlarmEntity("水水水", "http://pic.yesky.com/imagelist/07/04/1837387_7424.jpg", "http://www.zgmaimai.cn/uploads/allimg/c120621/1340251A2213Z-2K263.jpg", "是", "紧急的报警", "已处理", 1));
        alarmEntities.add(new AlarmEntity("哈哈哈", "http://pic.yesky.com/imagelist/07/04/1837387_7424.jpg", "http://www.zgmaimai.cn/uploads/allimg/c120621/1340251A2213Z-2K263.jpg", "是", "紧急的报警", "已处理", 2));
        alarmEntities.add(new AlarmEntity("多对多", "http://pic.yesky.com/imagelist/07/04/1837387_7424.jpg", "http://www.zgmaimai.cn/uploads/allimg/c120621/1340251A2213Z-2K263.jpg", "是", "紧急的报警", "已处理", 3));
        alarmEntities.add(new AlarmEntity("啊啊啊", "http://pic.yesky.com/imagelist/07/04/1837387_7424.jpg", "http://www.zgmaimai.cn/uploads/allimg/c120621/1340251A2213Z-2K263.jpg", "是", "紧急的报警", "已处理", 4));

        return gson.toJson(alarmEntities);
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