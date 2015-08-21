package com.hy.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.google.gson.Gson;
import com.hy.data.entity.AlarmEntity;
import com.hy.data.entity.EquipmentEntity;
import com.hy.data.entity.MapEntity;
import com.hy.data.entity.UserEntity;
import com.hy.data.entity.mapper.AlarmEntityJsonMapper;
import com.hy.data.entity.mapper.EquipmentAlarmEntityJsonMapper;
import com.hy.data.entity.mapper.MapEntityJsonMapper;
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
    private MapEntityJsonMapper mapEntityJsonMapper;
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

    public RestApiImpl(Context context, MapEntityJsonMapper mapEntityJsonMapper) {
        if (context == null || mapEntityJsonMapper == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context;
        this.mapEntityJsonMapper = mapEntityJsonMapper;
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
    public Observable<List<AlarmEntity>> alarmEntity(String userId, String title) {
        return Observable.create(new Observable.OnSubscribe<List<AlarmEntity>>() {
            @Override
            public void call(Subscriber<? super List<AlarmEntity>> subscriber) {
                String responseAlarmEntities = null;
                int choiceType = -1;

                responseAlarmEntities = getAlarmEntitiesFromApi(userId, title);
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

    @Override
    public Observable<List<AlarmEntity>> alarmEntity(String userId, int equipmentSn) {
        return Observable.create(new Observable.OnSubscribe<List<AlarmEntity>>() {
            @Override
            public void call(Subscriber<? super List<AlarmEntity>> subscriber) {
                String responseAlarmEntities = null;
                responseAlarmEntities = getAlarmEntitiesFromApi(userId, equipmentSn);
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

    private String getAlarmEntitiesFromApi(String userName, int equipmentSn) {
        Gson gson = new Gson();
        List<AlarmEntity> alarmEntities = new ArrayList<>();
        alarmEntities.add(new AlarmEntity("分分分", "http://pic.yesky.com/imagelist/07/04/1837387_7424.jpg", "http://www.zgmaimai.cn/uploads/allimg/c120621/1340251A2213Z-2K263.jpg", "是", "紧急的报警", "已处理", 0));
        alarmEntities.add(new AlarmEntity("水水水", "http://pic.yesky.com/imagelist/07/04/1837387_7424.jpg", "http://www.zgmaimai.cn/uploads/allimg/c120621/1340251A2213Z-2K263.jpg", "是", "紧急的报警", "已处理", 1));
        alarmEntities.add(new AlarmEntity("哈哈哈", "http://pic.yesky.com/imagelist/07/04/1837387_7424.jpg", "http://www.zgmaimai.cn/uploads/allimg/c120621/1340251A2213Z-2K263.jpg", "是", "紧急的报警", "已处理", 2));
        alarmEntities.add(new AlarmEntity("多对多", "http://pic.yesky.com/imagelist/07/04/1837387_7424.jpg", "http://www.zgmaimai.cn/uploads/allimg/c120621/1340251A2213Z-2K263.jpg", "是", "紧急的报警", "已处理", 3));
        alarmEntities.add(new AlarmEntity("啊啊啊", "http://pic.yesky.com/imagelist/07/04/1837387_7424.jpg", "http://www.zgmaimai.cn/uploads/allimg/c120621/1340251A2213Z-2K263.jpg", "是", "紧急的报警", "已处理", 4));

        return gson.toJson(alarmEntities);
    }


    private String getAlarmEntitiesFromApi(String userName, String title) {
        Gson gson = new Gson();
        List<AlarmEntity> alarmEntities = new ArrayList<>();
        alarmEntities.add(new AlarmEntity("分分分", "http://pic.yesky.com/imagelist/07/04/1837387_7424.jpg", "http://www.zgmaimai.cn/uploads/allimg/c120621/1340251A2213Z-2K263.jpg", "是", "紧急的报警", "已处理", 0));
        alarmEntities.add(new AlarmEntity("水水水", "http://pic.yesky.com/imagelist/07/04/1837387_7424.jpg", "http://www.zgmaimai.cn/uploads/allimg/c120621/1340251A2213Z-2K263.jpg", "是", "紧急的报警", "已处理", 1));
        alarmEntities.add(new AlarmEntity("哈哈哈", "http://pic.yesky.com/imagelist/07/04/1837387_7424.jpg", "http://www.zgmaimai.cn/uploads/allimg/c120621/1340251A2213Z-2K263.jpg", "是", "紧急的报警", "已处理", 2));
        alarmEntities.add(new AlarmEntity("多对多", "http://pic.yesky.com/imagelist/07/04/1837387_7424.jpg", "http://www.zgmaimai.cn/uploads/allimg/c120621/1340251A2213Z-2K263.jpg", "是", "紧急的报警", "已处理", 3));
        alarmEntities.add(new AlarmEntity("啊啊啊", "http://pic.yesky.com/imagelist/07/04/1837387_7424.jpg", "http://www.zgmaimai.cn/uploads/allimg/c120621/1340251A2213Z-2K263.jpg", "是", "紧急的报警", "已处理", 4));

        return gson.toJson(alarmEntities);
    }


    @Override
    public Observable<List<MapEntity>> mapEntity(String userName,int choiceType) {
        return Observable.create(new Observable.OnSubscribe<List<MapEntity>>() {
            @Override
            public void call(Subscriber<? super List<MapEntity>> subscriber) {
                String responseMapEntities =null;

                responseMapEntities = getMapEntitiesFromApi(userName, choiceType);

                if (responseMapEntities != null) {
                    Log.e("aa",responseMapEntities);
                    subscriber.onNext(mapEntityJsonMapper.transformMapEntity(
                            responseMapEntities));
                    subscriber.onCompleted();
                } else {
                    subscriber.onError(new NetworkConnectionException());
                }
            }
        });
    }

    private String getMapEntitiesFromApi(String userName, int choiceType) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        List<MapEntity> mapEntities = new ArrayList<>();
        mapEntities.add(new MapEntity(116.400244,39.963175,  "盘梁山","1",2));
        mapEntities.add(new MapEntity(116.500244,39.933175,  "西道梁","3",7));
        mapEntities.add(new MapEntity(116.600244,39.943175,  "确诊果汁","41",6));
        return gson.toJson(mapEntities);
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