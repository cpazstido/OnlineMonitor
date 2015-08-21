package com.hy.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.bean.DomainAlarmPage;
import com.google.gson.Gson;
import com.hy.data.entity.AlarmEntity;
import com.hy.data.entity.AlarmPageEntity;
import com.hy.data.entity.EquipmentEntity;
import com.hy.data.entity.EquipmentPageEntity;
import com.hy.data.entity.MapEntity;
import com.hy.data.entity.UserEntity;
import com.hy.data.entity.mapper.MapEntityJsonMapper;
import com.hy.data.entity.mapper.PageEntityJsonMapper;
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
    private MapEntityJsonMapper mapEntityJsonMapper;
    private PageEntityJsonMapper pageEntityJsonMapper;
    private int getType; // 判断是获得哪个数据的page-->设备列表,报警列表

    /**
     * Constructor of the class
     *
     * @param context              {@link Context}.
     * @param userEntityJsonMapper {@link UserEntityJsonMapper}.
     */

    public RestApiImpl(Context context, UserEntityJsonMapper userEntityJsonMapper) {
        if (context == null || userEntityJsonMapper == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.userEntityJsonMapper = userEntityJsonMapper;
    }

    public RestApiImpl(Context context, PageEntityJsonMapper pageEntityJsonMapper) {
        if (context == null || pageEntityJsonMapper == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context;
        this.pageEntityJsonMapper = pageEntityJsonMapper;
    }

    public RestApiImpl(Context context, MapEntityJsonMapper mapEntityJsonMapper) {
        if (context == null || mapEntityJsonMapper == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context;
        this.mapEntityJsonMapper = mapEntityJsonMapper;
    }

    /**
     * 获取用户登录信息
     *
     * @param loginAccount 用户名
     * @param loginPwd     用户密码
     * @return UserEntity
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
     * 获取设备列表
     *
     * @param userName
     * @param choiceType
     * @return
     */
    @Override
    public Observable<EquipmentPageEntity> equipmentEntity(String userName, int choiceType) {
        return Observable.create(new Observable.OnSubscribe<EquipmentPageEntity>() {
            @Override
            public void call(Subscriber<? super EquipmentPageEntity> subscriber) {
                String responseEquipmentEntities = null;
                responseEquipmentEntities = getEquipmentEntitiesFromApi(userName, choiceType);
                if (responseEquipmentEntities != null) {
                    subscriber.onNext(pageEntityJsonMapper.transformEquipmentPageEntity(responseEquipmentEntities));
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
        EquipmentPageEntity equipmentPageEntity = new EquipmentPageEntity();
        List<EquipmentEntity> equipmentAlarmEntities = new ArrayList<>();
        equipmentAlarmEntities.add(new EquipmentEntity("美国超级电力", "摄像机开电", 2, 0, 1, 1));
        equipmentAlarmEntities.add(new EquipmentEntity("英国核电力", "摄像机正在开电", 3, 1, 0, 1));
        equipmentAlarmEntities.add(new EquipmentEntity("火星电力", "摄像机断电", 5, 1, 1, 0));
        equipmentAlarmEntities.add(new EquipmentEntity("什么么贵", "开电中", 2, 2, 0, 1));
        equipmentPageEntity.setList(equipmentAlarmEntities);
        equipmentPageEntity.setPageNum(0);
        equipmentPageEntity.setPageSize(10);
        equipmentPageEntity.setTotalPage(1);
        equipmentPageEntity.setRowCount(4);
        return gson.toJson(equipmentPageEntity);
    }

    @Override
    public Observable<AlarmPageEntity> alarmEntity(String userId, String title) {
        return Observable.create(new Observable.OnSubscribe<AlarmPageEntity>() {
            @Override
            public void call(Subscriber<? super AlarmPageEntity> subscriber) {
                String responseAlarmEntities = null;
                int choiceType = -1;
                responseAlarmEntities = getAlarmEntitiesFromApi(userId, title);
                if (responseAlarmEntities != null) {
                    subscriber.onNext(pageEntityJsonMapper.transformAlarmPageEntity(responseAlarmEntities));
                    subscriber.onCompleted();
                } else {
                    subscriber.onError(new NetworkConnectionException());
                }
            }
        });
    }

    @Override
    public Observable<DomainAlarmPage> alarmEntity(String userId, int equipmentSn) {
        return Observable.create(new Observable.OnSubscribe<DomainAlarmPage>() {
            @Override
            public void call(Subscriber<? super DomainAlarmPage> subscriber) {
                String responseAlarmEntities = null;
                responseAlarmEntities = getAlarmEntitiesFromApi(userId, equipmentSn);
                if (responseAlarmEntities != null) {
                    subscriber.onNext(pageEntityJsonMapper.transformAlarmPageEntity(responseAlarmEntities));
                    subscriber.onCompleted();
                } else {
                    subscriber.onError(new NetworkConnectionException());
                }
            }
        });
    }

    private String getAlarmEntitiesFromApi(String userName, int equipmentSn) {
        EquipmentPageEntity equipmentPageEntity = new EquipmentPageEntity();
        Gson gson = new Gson();
        List<AlarmEntity> alarmEntities = new ArrayList<>();
        alarmEntities.add(new AlarmEntity("分分分", "http://pic.yesky.com/imagelist/07/04/1837387_7424.jpg", "http://www.zgmaimai.cn/uploads/allimg/c120621/1340251A2213Z-2K263.jpg", "是", "紧急的报警", "已处理", 0));
        alarmEntities.add(new AlarmEntity("水水水", "http://pic.yesky.com/imagelist/07/04/1837387_7424.jpg", "http://www.zgmaimai.cn/uploads/allimg/c120621/1340251A2213Z-2K263.jpg", "是", "紧急的报警", "已处理", 1));
        alarmEntities.add(new AlarmEntity("哈哈哈", "http://pic.yesky.com/imagelist/07/04/1837387_7424.jpg", "http://www.zgmaimai.cn/uploads/allimg/c120621/1340251A2213Z-2K263.jpg", "是", "紧急的报警", "已处理", 2));
        alarmEntities.add(new AlarmEntity("多对多", "http://pic.yesky.com/imagelist/07/04/1837387_7424.jpg", "http://www.zgmaimai.cn/uploads/allimg/c120621/1340251A2213Z-2K263.jpg", "是", "紧急的报警", "已处理", 3));
        alarmEntities.add(new AlarmEntity("啊啊啊", "http://pic.yesky.com/imagelist/07/04/1837387_7424.jpg", "http://www.zgmaimai.cn/uploads/allimg/c120621/1340251A2213Z-2K263.jpg", "是", "紧急的报警", "已处理", 4));
        equipmentPageEntity.setList(alarmEntities);
        equipmentPageEntity.setPageNum(0);
        equipmentPageEntity.setPageSize(10);
        equipmentPageEntity.setTotalPage(1);
        equipmentPageEntity.setRowCount(5);
        return gson.toJson(equipmentPageEntity);
    }


    private String getAlarmEntitiesFromApi(String userName, String title) {
        EquipmentPageEntity equipmentPageEntity = new EquipmentPageEntity();
        Gson gson = new Gson();
        List<AlarmEntity> alarmEntities = new ArrayList<>();
        alarmEntities.add(new AlarmEntity("分分分", "http://pic.yesky.com/imagelist/07/04/1837387_7424.jpg", "http://www.zgmaimai.cn/uploads/allimg/c120621/1340251A2213Z-2K263.jpg", "是", "紧急的报警", "已处理", 0));
        alarmEntities.add(new AlarmEntity("水水水", "http://pic.yesky.com/imagelist/07/04/1837387_7424.jpg", "http://www.zgmaimai.cn/uploads/allimg/c120621/1340251A2213Z-2K263.jpg", "是", "紧急的报警", "已处理", 1));
        alarmEntities.add(new AlarmEntity("哈哈哈", "http://pic.yesky.com/imagelist/07/04/1837387_7424.jpg", "http://www.zgmaimai.cn/uploads/allimg/c120621/1340251A2213Z-2K263.jpg", "是", "紧急的报警", "已处理", 2));
        alarmEntities.add(new AlarmEntity("多对多", "http://pic.yesky.com/imagelist/07/04/1837387_7424.jpg", "http://www.zgmaimai.cn/uploads/allimg/c120621/1340251A2213Z-2K263.jpg", "是", "紧急的报警", "已处理", 3));
        alarmEntities.add(new AlarmEntity("啊啊啊", "http://pic.yesky.com/imagelist/07/04/1837387_7424.jpg", "http://www.zgmaimai.cn/uploads/allimg/c120621/1340251A2213Z-2K263.jpg", "是", "紧急的报警", "已处理", 4));
        equipmentPageEntity.setList(alarmEntities);
        equipmentPageEntity.setPageNum(0);
        equipmentPageEntity.setPageSize(10);
        equipmentPageEntity.setTotalPage(1);
        equipmentPageEntity.setRowCount(5);
        return gson.toJson(equipmentPageEntity);
    }


    @Override
    public Observable<List<MapEntity>> mapEntity(String userName, int choiceType) {
        return Observable.create(new Observable.OnSubscribe<List<MapEntity>>() {
            @Override
            public void call(Subscriber<? super List<MapEntity>> subscriber) {
                String responseMapEntities = null;
                responseMapEntities = getMapEntitiesFromApi(userName, choiceType);
                if (responseMapEntities != null) {
                    Log.e("aa", responseMapEntities);
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
        mapEntities.add(new MapEntity(116.400244, 39.963175, "盘梁山", "1", 2));
        mapEntities.add(new MapEntity(116.500244, 39.933175, "西道梁", "3", 7));
        mapEntities.add(new MapEntity(116.600244, 39.943175, "确诊果汁", "41", 6));
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