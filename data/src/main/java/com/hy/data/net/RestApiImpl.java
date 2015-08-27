package com.hy.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.hy.data.entity.AlarmPageEntity;
import com.hy.data.entity.EquipmentPageEntity;
import com.hy.data.entity.MapEntity;
import com.hy.data.entity.UserEntity;
import com.hy.data.entity.mapper.MapEntityJsonMapper;
import com.hy.data.entity.mapper.PageEntityJsonMapper;
import com.hy.data.entity.mapper.UserEntityJsonMapper;
import com.hy.data.exception.NetworkConnectionException;
import com.hy.data.utile.SystemRestClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.rey.material.widget.SnackBar;

import org.apache.http.Header;

import java.io.UnsupportedEncodingException;
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

    public RestApiImpl(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context;
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
                                         RequestParams params = new RequestParams();
                                         params.put("uername", loginAccount);
                                         params.put("uerpwd", loginPwd);

                                         SystemRestClient.get("/login", params, new AsyncHttpResponseHandler() {
                                             @Override
                                             public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                                 Log.i("msg", "登陆成功");
                                                 String responseUserEntities = null;
                                                 try {
                                                     responseUserEntities = new String(responseBody, "UTF-8");
//                             responseUserEntities = StringTransformation.transform(responseUserEntities);
                                                     Log.e("result", responseUserEntities);
                                                     if (responseUserEntities.equals("false")) {
                                                         subscriber.onError(new NetworkConnectionException("用户名或密码错误"));
                                                     } else {
                                                         subscriber.onNext(userEntityJsonMapper.transformUserEntity(
                                                                 responseUserEntities));
                                                         subscriber.onCompleted();
                                                     }
                                                 } catch (UnsupportedEncodingException e) {
                                                     e.printStackTrace();
                                                 }
                                             }

                                             @Override
                                             public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                                 Log.e("getUserEntitiesFromApi", "onFailure");
                                                 new SnackBar(context).text("连接失败,请稍后再试....").show();
                                                 subscriber.onError(new NetworkConnectionException("链接失败"));
                                             }
                                         });
                                     }
                                 }
        );
    }

    /**
     * 获取设备列表
     *
     * @param userId
     * @param choiceType
     * @return
     */
    @Override
    public Observable<EquipmentPageEntity> equipmentEntity(int userId, int choiceType, int pageNumber) {
        return Observable.create(new Observable.OnSubscribe<EquipmentPageEntity>() {
            @Override
            public void call(Subscriber<? super EquipmentPageEntity> subscriber) {

                RequestParams params = new RequestParams();
                params.put("userId", userId);
                params.put("curProject", choiceType);
                params.put("pageNum", pageNumber);

                SystemRestClient.post("/getEquipmentList", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String responseEquipmentEntities = null;
                        try {
                            responseEquipmentEntities = new String(responseBody, "UTF-8");
                            Log.i("EquipmentPageEntity",responseEquipmentEntities);
                            subscriber.onNext(pageEntityJsonMapper.transformEquipmentPageEntity(responseEquipmentEntities));
                            subscriber.onCompleted();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.e("getUserEntitiesFromApi", "onFailure");
                        new SnackBar(context).text("连接失败,请稍后再试....").show();
                        subscriber.onError(new NetworkConnectionException("链接失败"));
                    }
                });
            }
        });
    }

    /**
     * 查看用户的所有报警
     *
     * @param userId         唯一标示用户
     * @param queryAlarmType 查看的报警类型
     * @param status         查看的报警类型的状态(历史,或者新报警)
     * @param pageNumber     第几页的数据
     * @return 返回AlarmPage的对象
     */
    @Override
    public Observable<AlarmPageEntity> alarmEntity(int userId, String queryAlarmType, int status, int pageNumber) {
        return Observable.create(new Observable.OnSubscribe<AlarmPageEntity>() {
            @Override
            public void call(Subscriber<? super AlarmPageEntity> subscriber) {
                RequestParams params = new RequestParams();
                params.put("userId", userId);
                params.put("queryAlarmType", queryAlarmType);
                params.put("status", status);
                params.put("pageNum", pageNumber);

                SystemRestClient.post("/getAlarmInfrom", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            String responseAlarmEntities = new String(responseBody, "UTF-8");
                            Log.e("alarmPageEntity-getAll", responseAlarmEntities);
                            subscriber.onNext(pageEntityJsonMapper.transformAlarmPageEntity(responseAlarmEntities));
                            subscriber.onCompleted();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.e("getUserEntitiesFromApi", "onFailure");
                        new SnackBar(context).text("连接失败,请稍后再试....").show();
                        subscriber.onError(new NetworkConnectionException("链接失败"));
                    }
                });
            }
        });
    }

    /**
     * 根据 Equipmentname查看报警
     *
     * @param userId         唯一标示用户
     * @param equipmentName  设备名(唯一标示)
     * @param queryAlarmType 查看的报警类型
     * @param status         查看的报警类型的状态(历史,或者新报警)
     * @param pageNumber     第几页的数据
     * @return 返回AlarmPage的对象
     */
    @Override
    public Observable<AlarmPageEntity> alarmEntity(int userId, String equipmentName, String queryAlarmType, int status, int pageNumber) {
        return Observable.create(new Observable.OnSubscribe<AlarmPageEntity>() {
            @Override
            public void call(Subscriber<? super AlarmPageEntity> subscriber) {
                RequestParams params = new RequestParams();
                params.put("userId", userId);
                params.put("queryAlarmType", queryAlarmType);
                params.put("status", status);
                params.put("deviceId", equipmentName);
                params.put("pageNum", pageNumber);

                SystemRestClient.post("/getAlarmInfrom", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            String responseAlarmEntities = new String(responseBody, "UTF-8");
                            Log.e("alarmPageEntity-getSome", responseAlarmEntities);
                            subscriber.onNext(pageEntityJsonMapper.transformAlarmPageEntity(responseAlarmEntities));
                            subscriber.onCompleted();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.e("getUserEntitiesFromApi", "onFailure");
                        new SnackBar(context).text("连接失败,请稍后再试....").show();
                        subscriber.onError(new NetworkConnectionException("链接失败"));
                    }
                });
            }
        });
    }

    @Override
    public Observable<List<MapEntity>> mapEntity(int userId, int choiceType) {
        return Observable.create(new Observable.OnSubscribe<List<MapEntity>>() {
            @Override
            public void call(Subscriber<? super List<MapEntity>> subscriber) {
                String queryType = null;
                switch (choiceType) {
                    case 0://山火
                        queryType = "fire";
                        break;
                    case 1://外破
                        queryType = "break";
                        break;
                    case 2://普通视频
                        queryType = "video";
                        break;
                }

                RequestParams params = new RequestParams();
                params.put("userId", userId);
                params.put("curProject", queryType);

                SystemRestClient.post("/queryPoleLocation", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            String responseMapEntities = new String(responseBody, "UTF-8");
                            Log.e("alarmPageEntity-getSome", responseMapEntities);
                            subscriber.onNext(mapEntityJsonMapper.transformMapEntity(responseMapEntities));
                            subscriber.onCompleted();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.e("getUserEntitiesFromApi", "onFailure");
                        new SnackBar(context).text("连接失败,请稍后再试....").show();
                        subscriber.onError(new NetworkConnectionException("链接失败"));
                    }
                });
            }
        });
    }

    @Override
    public Observable<String> videoUrl(String fileName) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                RequestParams params = new RequestParams();
                params.put("fileName", fileName);
                SystemRestClient.post("/", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            String responseVideoUrl = new String(responseBody, "UTF-8");
                            Log.e("videoUrl", "url--?>" + responseVideoUrl);
                            subscriber.onNext(responseVideoUrl);
                            subscriber.onCompleted();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.e("getUserEntitiesFromApi", "onFailure");
                        new SnackBar(context).text("连接失败,请稍后再试....").show();
                        subscriber.onError(new NetworkConnectionException("链接失败"));
                    }
                });
            }
        });
    }

    @Override
    public Observable<String> videoUrl(String dvrType, int dvrId, int channelID, int streamType) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                RequestParams params = new RequestParams();
                SystemRestClient.post("/startRealPlay", dvrType, dvrId, channelID, streamType, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            String responseVideoUrl = new String(responseBody, "UTF-8");
                            Log.e("videoUrl", "url--?>" + responseVideoUrl);
                            subscriber.onNext(responseVideoUrl);
                            subscriber.onCompleted();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.e("getUserEntitiesFromApi", "onFailure");
                        new SnackBar(context).text("连接失败,请稍后再试....").show();
                        subscriber.onError(new NetworkConnectionException("链接失败"));
                    }
                });
            }
        });
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