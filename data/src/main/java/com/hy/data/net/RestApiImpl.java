package com.hy.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.hy.data.entity.AdministratorPageEntity;
import com.hy.data.entity.AlarmPageEntity;
import com.hy.data.entity.CompanyEntity;
import com.hy.data.entity.EquipmentInforPageEntity;
import com.hy.data.entity.LineEntity;
import com.hy.data.entity.LinePageEntity;
import com.hy.data.entity.MapEntity;
import com.hy.data.entity.PolePageEntity;
import com.hy.data.entity.PrivilegeEntity;
import com.hy.data.entity.RoleEntity;
import com.hy.data.entity.RolePageEntity;
import com.hy.data.entity.UserEntity;
import com.hy.data.entity.mapper.CompanyEntityJsonMapper;
import com.hy.data.entity.mapper.LineEntityJsonMapper;
import com.hy.data.entity.mapper.ListOfIntegerJsonMapper;
import com.hy.data.entity.mapper.MapEntityJsonMapper;
import com.hy.data.entity.mapper.PageEntityJsonMapper;
import com.hy.data.entity.mapper.PrivilegeEntityJsonMapper;
import com.hy.data.entity.mapper.StringJsonMapper;
import com.hy.data.entity.mapper.UserEntityJsonMapper;
import com.hy.data.exception.NetworkConnectionException;
import com.hy.data.utile.SystemRestClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

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
    private StringJsonMapper stringJsonMapper;
    private CompanyEntityJsonMapper companyEntityJsonMapper;
    private LineEntityJsonMapper lineEntityJsonMapper;
    private ListOfIntegerJsonMapper listOfIntegerJsonMapper;
    private PrivilegeEntityJsonMapper privilegeEntityJsonMapper;

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

    public RestApiImpl(Context context, StringJsonMapper stringJsonMapper) {
        if (context == null || stringJsonMapper == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.stringJsonMapper = stringJsonMapper;
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

    public RestApiImpl(Context context, CompanyEntityJsonMapper companyEntityJsonMapper) {
        if (context == null || companyEntityJsonMapper == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context;
        this.companyEntityJsonMapper = companyEntityJsonMapper;
    }

    public RestApiImpl(Context context, LineEntityJsonMapper lineEntityJsonMapper) {
        if (context == null || lineEntityJsonMapper == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context;
        this.lineEntityJsonMapper = lineEntityJsonMapper;
    }

    public RestApiImpl(Context context, ListOfIntegerJsonMapper listOfIntegerJsonMapper) {
        if (context == null || listOfIntegerJsonMapper == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context;
        this.listOfIntegerJsonMapper = listOfIntegerJsonMapper;
    }

    public RestApiImpl(Context context, PrivilegeEntityJsonMapper privilegeEntityJsonMapper) {
        if (context == null || privilegeEntityJsonMapper == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context;
        this.privilegeEntityJsonMapper = privilegeEntityJsonMapper;
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
    public Observable<EquipmentInforPageEntity> equipmentEntity(int userId, int choiceType, int pageNumber) {
        return Observable.create(new Observable.OnSubscribe<EquipmentInforPageEntity>() {
            @Override
            public void call(Subscriber<? super EquipmentInforPageEntity> subscriber) {

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
                            subscriber.onNext(pageEntityJsonMapper.transformEquipmentPageEntity(responseEquipmentEntities));
                            subscriber.onCompleted();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.e("getUserEntitiesFromApi", "onFailure");
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
                            Log.e("videoUrl", "url  ?>" + responseVideoUrl);
                            subscriber.onNext(responseVideoUrl);
                            subscriber.onCompleted();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.e("getUserEntitiesFromApi", "onFailure");
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
                            Log.e("videoUrl", "url  ?>" + responseVideoUrl);
                            subscriber.onNext(responseVideoUrl);
                            subscriber.onCompleted();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.e("getUserEntitiesFromApi", "onFailure");
                        subscriber.onError(new NetworkConnectionException("链接失败"));
                    }
                });
            }
        });
    }

    @Override
    public Observable<List<CompanyEntity>> companyList(int userId) {
        return Observable.create(new Observable.OnSubscribe<List<CompanyEntity>>() {
            @Override
            public void call(Subscriber<? super List<CompanyEntity>> subscriber) {
                RequestParams params = new RequestParams();
                params.put("userId", userId);
                SystemRestClient.post("/getCompanyList", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            String responseCompanyEntities = new String(responseBody, "UTF-8");
                            Log.e("responseCompanyEntities", responseCompanyEntities);
                            subscriber.onNext(companyEntityJsonMapper.transformCompanyEntity(responseCompanyEntities));
                            subscriber.onCompleted();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.e("getUserEntitiesFromApi", "onFailure");
                        subscriber.onError(new NetworkConnectionException("链接失败"));
                    }
                });
            }
        });
    }

    @Override
    public Observable<List<RoleEntity>> roleList(int userId) {
        return Observable.create(new Observable.OnSubscribe<List<RoleEntity>>() {
            @Override
            public void call(Subscriber<? super List<RoleEntity>> subscriber) {
                RequestParams params = new RequestParams();
                params.put("userId", userId);
                SystemRestClient.post("/getRolePage", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            String responseRoleEntities = new String(responseBody, "UTF-8");
                            Log.e("responseRoleEntities", responseRoleEntities);
                            subscriber.onNext(pageEntityJsonMapper.transformRolePageEntity(responseRoleEntities).getList());
                            subscriber.onCompleted();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.e("getUserEntitiesFromApi", "onFailure");
                        subscriber.onError(new NetworkConnectionException("链接失败"));
                    }
                });
            }
        });
    }

    @Override
    public Observable<AdministratorPageEntity> administratorEntity(int userId) {

        return Observable.create(new Observable.OnSubscribe<AdministratorPageEntity>() {
            @Override
            public void call(Subscriber<? super AdministratorPageEntity> subscriber) {
                RequestParams params = new RequestParams();
                params.put("userId", userId);

                SystemRestClient.post("/getAdministratorList", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            String responseAdministratorEntities = new String(responseBody, "UTF-8");
                            Log.e("response", responseAdministratorEntities);
                            subscriber.onNext(pageEntityJsonMapper.transformAdministratorPageEntity(responseAdministratorEntities));
                            subscriber.onCompleted();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.e("onFailure", "AdministratorPageEntity");
                        subscriber.onError(new NetworkConnectionException("链接失败"));
                    }
                });
            }
        });
    }

    @Override
    public Observable<AdministratorPageEntity> addAdministrator(int roleSn, int companySn, String loginName, String realName, String password, String mobilePhone, String isMessage) {
        return Observable.create(new Observable.OnSubscribe<AdministratorPageEntity>() {
            @Override
            public void call(Subscriber<? super AdministratorPageEntity> subscriber) {
                RequestParams params = new RequestParams();
                params.put("sn", roleSn);
                params.put("loginName", loginName);
                params.put("password", password);
                params.put("realName", realName);
                params.put("mobilePhone", mobilePhone);
                params.put("company_SN", companySn);
                params.put("operator.receiveMessage", isMessage);

                SystemRestClient.post("/addAdministrator", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            String responseAdministratorEntities = new String(responseBody, "UTF-8");
                            Log.e("response", responseAdministratorEntities);
                            subscriber.onNext(pageEntityJsonMapper.transformAdministratorPageEntity(responseAdministratorEntities));
                            subscriber.onCompleted();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        try {
                            String aaa = new String(responseBody, "UTF-8");
                            Log.e("response", aaa);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        Log.e("onFailure", "addAdministrator");
                        subscriber.onError(new NetworkConnectionException("链接失败"));
                    }
                });
            }
        });
    }

    @Override
    public Observable<AdministratorPageEntity> changeAdministrator(int sn, int roleSn, int companySn, String loginName, String realName, String password, String mobilePhone, String isMessage) {
        return Observable.create(new Observable.OnSubscribe<AdministratorPageEntity>() {
            @Override
            public void call(Subscriber<? super AdministratorPageEntity> subscriber) {
                RequestParams params = new RequestParams();
                params.put("sn", sn);
                params.put("roleSN", roleSn);
                params.put("loginName", loginName);
                params.put("password", password);
                params.put("realName", realName);
                params.put("mobilePhone", mobilePhone);
                params.put("company_SN", companySn);
                params.put("operator.receiveMessage", isMessage);
                Log.e("changeAdministrator", "changeAdministrator");

                SystemRestClient.post("/changeAdministrator", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            String responseAdministratorEntities = new String(responseBody, "UTF-8");
                            Log.e("response", responseAdministratorEntities);
                            subscriber.onNext(pageEntityJsonMapper.transformAdministratorPageEntity(responseAdministratorEntities));
                            subscriber.onCompleted();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        try {
                            String aaa = new String(responseBody, "UTF-8");
                            Log.e("response", aaa);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        Log.e("onFailure", "addAdministrator");
                        subscriber.onError(new NetworkConnectionException("链接失败"));
                    }
                });
            }
        });
    }

    @Override
    public Observable<AdministratorPageEntity> deleteAdministrator(int sn) {
        return Observable.create(new Observable.OnSubscribe<AdministratorPageEntity>() {
            @Override
            public void call(Subscriber<? super AdministratorPageEntity> subscriber) {
                RequestParams params = new RequestParams();
                params.put("sn", sn);

                SystemRestClient.post("/deleteAdministrator", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            String responseAdministratorEntities = new String(responseBody, "UTF-8");
                            Log.e("response", responseAdministratorEntities);
                            subscriber.onNext(pageEntityJsonMapper.transformAdministratorPageEntity(responseAdministratorEntities));
                            subscriber.onCompleted();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        try {
                            String aaa = new String(responseBody, "UTF-8");
                            Log.e("response", aaa);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        Log.e("onFailure", "addAdministrator");
                        subscriber.onError(new NetworkConnectionException("链接失败"));
                    }
                });
            }
        });
    }

    @Override
    public Observable<List<LineEntity>> getAllTower(int userId, int sn) {
        return Observable.create(new Observable.OnSubscribe<List<LineEntity>>() {
            @Override
            public void call(Subscriber<? super List<LineEntity>> subscriber) {
                Log.e("tag", "getAllTower");
                RequestParams params = new RequestParams();
                params.put("userId", userId);
                params.put("operatorSN", sn);
                SystemRestClient.post("/getAllTower", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            String responseAdminLineEntities = new String(responseBody, "UTF-8");
                            Log.e("getAllTower",responseAdminLineEntities);
                            subscriber.onNext(lineEntityJsonMapper.transformAdminLineEntity(responseAdminLineEntities));
                            subscriber.onCompleted();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.e("onFailure", "addAdministrator");
                        subscriber.onError(new NetworkConnectionException("链接失败"));
                    }
                });
            }
        });
    }

    @Override
    public Observable<List<Integer>> getOwnTower(int userId, int sn) {
        return Observable.create(new Observable.OnSubscribe<List<Integer>>() {
            @Override
            public void call(Subscriber<? super List<Integer>> subscriber) {
                Log.e("tag", "getOwnTower");
                RequestParams params = new RequestParams();
                params.put("userId", userId);
                params.put("operatorSN", sn);

                SystemRestClient.post("/getPoleSelect", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            String responseOwnTowerEntities = new String(responseBody, "UTF-8");
                            subscriber.onNext(listOfIntegerJsonMapper.transformIntegerEntity(responseOwnTowerEntities));
                            subscriber.onCompleted();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        try {
                            String aaa = new String(responseBody, "UTF-8");
                            Log.e("response", aaa);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        Log.e("onFailure", "addAdministrator");
                        subscriber.onError(new NetworkConnectionException("链接失败"));
                    }
                });
            }
        });
    }

    @Override
    public Observable<String> changeManageTower(int userId, int adminSn, List<Integer> snList, int allPoleSelected) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Log.e("tag", "changeManageTower");

                String newString = null;
                if (snList != null) {
                    String str = "";
                    for (int sn : snList) {
                        str += sn + ",";
                    }
                    newString = str.substring(0, str.length() - 1);
                }

                RequestParams params = new RequestParams();
                params.put("userId", userId);
                params.put("operatorSN", adminSn);
                params.put("allPoleSelected", allPoleSelected);
                params.put("poleSnList", newString);

                Log.e("a", "--");
                SystemRestClient.post("/selectOne", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            String responseString = new String(responseBody, "UTF-8");
                            Log.e("changeManageTower", responseString);
                            subscriber.onNext(stringJsonMapper.transformString(responseString));
                            subscriber.onCompleted();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.e("onFailure", "selectOne");
                        subscriber.onError(new NetworkConnectionException("链接失败"));
                    }
                });
            }
        });
    }

    @Override
    public Observable<RolePageEntity> getRolePage(int userId) {
        return Observable.create(new Observable.OnSubscribe<RolePageEntity>() {
            @Override
            public void call(Subscriber<? super RolePageEntity> subscriber) {
                RequestParams params = new RequestParams();
                params.put("userId", userId);

                SystemRestClient.post("/getRolePage", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            String responseEntities = new String(responseBody, "UTF-8");
                            Log.e("getRolePage", responseEntities);

                            subscriber.onNext(pageEntityJsonMapper.transformRolePageEntity(responseEntities));
                            subscriber.onCompleted();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        subscriber.onError(new NetworkConnectionException("链接失败"));
                    }
                });
            }
        });
    }

    @Override
    public Observable<RolePageEntity> addRole(int userId, String name) {
        return Observable.create(new Observable.OnSubscribe<RolePageEntity>() {
            @Override
            public void call(Subscriber<? super RolePageEntity> subscriber) {
                RequestParams params = new RequestParams();
                params.put("userId", userId);
                params.put("role.roleName", name);

                SystemRestClient.post("/addRole", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            String responseEntities = new String(responseBody, "UTF-8");
                            Log.e("response", responseEntities);
                            subscriber.onNext(pageEntityJsonMapper.transformRolePageEntity(responseEntities));
                            subscriber.onCompleted();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        subscriber.onError(new NetworkConnectionException("链接失败"));
                    }
                });
            }
        });
    }

    @Override
    public Observable<RolePageEntity> changeRole(int userId, int roleSn, String name) {
        return Observable.create(new Observable.OnSubscribe<RolePageEntity>() {
            @Override
            public void call(Subscriber<? super RolePageEntity> subscriber) {
                RequestParams params = new RequestParams();
                params.put("role.sn", roleSn);
                params.put("roleName", name);
                SystemRestClient.post("/changeRole", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            String responseEntities = new String(responseBody, "UTF-8");
                            Log.e("response", responseEntities);
                            subscriber.onNext(pageEntityJsonMapper.transformRolePageEntity(responseEntities));
                            subscriber.onCompleted();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        subscriber.onError(new NetworkConnectionException("链接失败"));
                    }
                });
            }
        });
    }

    @Override
    public Observable<RolePageEntity> deleteRole(int userId, int roleSn) {
        return Observable.create(new Observable.OnSubscribe<RolePageEntity>() {
            @Override
            public void call(Subscriber<? super RolePageEntity> subscriber) {
                RequestParams params = new RequestParams();
                params.put("userId", userId);
                params.put("role.sn", roleSn);

                SystemRestClient.post("/deleteRole", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            String responseEntities = new String(responseBody, "UTF-8");
                            Log.e("response", responseEntities);
                            subscriber.onNext(pageEntityJsonMapper.transformRolePageEntity(responseEntities));
                            subscriber.onCompleted();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        subscriber.onError(new NetworkConnectionException("链接失败"));
                    }
                });
            }
        });
    }

    @Override
    public Observable<List<PrivilegeEntity>> getOwnPrivilege(int userId, int roleSn) {
        return Observable.create(new Observable.OnSubscribe<List<PrivilegeEntity>>() {
            @Override
            public void call(Subscriber<? super List<PrivilegeEntity>> subscriber) {
                RequestParams params = new RequestParams();
                params.put("userId", userId);
                params.put("roleSN", roleSn);

                SystemRestClient.post("/getOwnPrivilege", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            String responseEntities = new String(responseBody, "UTF-8");
                            Log.e("response", responseEntities);
                            subscriber.onNext(privilegeEntityJsonMapper.transformPrivilegeEntity(responseEntities));
                            subscriber.onCompleted();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        subscriber.onError(new NetworkConnectionException("链接失败"));
                    }
                });
            }
        });
    }

    @Override
    public Observable<List<PrivilegeEntity>> getAllPrivilege(int userId) {
        return Observable.create(new Observable.OnSubscribe<List<PrivilegeEntity>>() {
            @Override
            public void call(Subscriber<? super List<PrivilegeEntity>> subscriber) {
                RequestParams params = new RequestParams();
                params.put("userId", userId);

                SystemRestClient.post("/privilege_showAll", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            String responseEntities = new String(responseBody, "UTF-8");
                            Log.e("response", responseEntities);
                            subscriber.onNext(privilegeEntityJsonMapper.transformPrivilegeEntity(responseEntities));
                            subscriber.onCompleted();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        subscriber.onError(new NetworkConnectionException("链接失败"));
                    }
                });
            }
        });
    }

    @Override
    public Observable<String> jurisdictionChange(int userId, int roleSn, List<Integer> jurisdictionSN) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String newString;
                if (jurisdictionSN.size() != 0) {
                    String str = "";
                    for (int sn : jurisdictionSN) {
                        str += sn + ",";
                    }
                    newString = str.substring(0, str.length() - 1);
                } else {
                    newString = "";
                }

                RequestParams params = new RequestParams();
                params.put("userId", userId);
                params.put("roleSN", roleSn);
                params.put("privileges", newString);

                SystemRestClient.post("/changeJurisdiction", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            String responseEntities = new String(responseBody, "UTF-8");
                            Log.e("response", responseEntities);
                            subscriber.onNext(responseEntities);
                            subscriber.onCompleted();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        subscriber.onError(new NetworkConnectionException("链接失败"));
                    }
                });
            }
        });
    }

    @Override
    public Observable<LinePageEntity> getLinePage(int userId, int companySn) {
        return Observable.create(new Observable.OnSubscribe<LinePageEntity>() {
            @Override
            public void call(Subscriber<? super LinePageEntity> subscriber) {
                RequestParams params = new RequestParams();
                params.put("userId", userId);
                params.put("companySn", companySn);

                SystemRestClient.post("/getLinePage", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            String responseEntities = new String(responseBody, "UTF-8");
                            Log.e("response", responseEntities);
                            subscriber.onNext(pageEntityJsonMapper.transformLinePageEntity(responseEntities));
                            subscriber.onCompleted();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        subscriber.onError(new NetworkConnectionException("链接失败"));
                    }
                });
            }
        });
    }

    @Override
    public Observable<LinePageEntity> getAllLinePage(int userId) {
        return Observable.create(new Observable.OnSubscribe<LinePageEntity>() {
            @Override
            public void call(Subscriber<? super LinePageEntity> subscriber) {
                RequestParams params = new RequestParams();
                params.put("userId", userId);

                SystemRestClient.post("/getLinePage", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            String responseEntities = new String(responseBody, "UTF-8");
                            Log.e("response", responseEntities);
                            subscriber.onNext(pageEntityJsonMapper.transformLinePageEntity(responseEntities));
                            subscriber.onCompleted();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        subscriber.onError(new NetworkConnectionException("链接失败"));
                    }
                });
            }
        });
    }

    @Override
    public Observable<LinePageEntity> addLine(int userId, int companySn,String lineName, String lineStart, String lineFinish, String lineTrend, String voltageLevel) {
        return Observable.create(new Observable.OnSubscribe<LinePageEntity>() {
            @Override
            public void call(Subscriber<? super LinePageEntity> subscriber) {
                RequestParams params = new RequestParams();
                params.put("userId", userId);
                params.put("companySn", companySn);
                params.put("circuitName", lineName);
                params.put("circuitOrigin", lineStart);
                params.put("circuitTerminal", lineFinish);
                params.put("lineAlignment", lineTrend);
                params.put("circuitVoltage", voltageLevel);

                SystemRestClient.post("/addLine", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            String responseEntities = new String(responseBody, "UTF-8");
                            Log.e("response", responseEntities);
                            subscriber.onNext(pageEntityJsonMapper.transformLinePageEntity(responseEntities));
                            subscriber.onCompleted();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        subscriber.onError(new NetworkConnectionException("链接失败"));
                    }
                });
            }
        });
    }

    @Override
    public Observable<LinePageEntity> deleteLine(int userId, int lineSn) {
        return Observable.create(new Observable.OnSubscribe<LinePageEntity>() {
            @Override
            public void call(Subscriber<? super LinePageEntity> subscriber) {
                RequestParams params = new RequestParams();
                params.put("userId", userId);
                params.put("circuitSn", lineSn);

                SystemRestClient.post("/deleteLine", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            String responseEntities = new String(responseBody, "UTF-8");
                            Log.e("response", responseEntities);
                            subscriber.onNext(pageEntityJsonMapper.transformLinePageEntity(responseEntities));
                            subscriber.onCompleted();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        subscriber.onError(new NetworkConnectionException("链接失败"));
                    }
                });
            }
        });
    }

    @Override
    public Observable<LinePageEntity> changeLine(int userId,int companySn, int lineSn, String lineName, String lineStart, String lineFinish, String lineTrend, String voltageLevel) {
        return Observable.create(new Observable.OnSubscribe<LinePageEntity>() {
            @Override
            public void call(Subscriber<? super LinePageEntity> subscriber) {
                RequestParams params = new RequestParams();
                params.put("circuitSn", lineSn);
                params.put("userId", userId);
                params.put("companySn", companySn);
                params.put("circuitName", lineName);
                params.put("circuitOrigin", lineStart);
                params.put("circuitTerminal", lineFinish);
                params.put("lineAlignment", lineTrend);
                params.put("circuitVoltage", voltageLevel);
                SystemRestClient.post("/changeLine", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            String responseEntities = new String(responseBody, "UTF-8");
                            Log.e("response", responseEntities);
                            subscriber.onNext(pageEntityJsonMapper.transformLinePageEntity(responseEntities));
                            subscriber.onCompleted();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        subscriber.onError(new NetworkConnectionException("链接失败"));
                    }
                });
            }
        });
    }

    @Override
    public Observable<PolePageEntity> getPolePage(int userId, int lineSn) {
        return Observable.create(new Observable.OnSubscribe<PolePageEntity>() {
            @Override
            public void call(Subscriber<? super PolePageEntity> subscriber) {
                RequestParams params = new RequestParams();
                params.put("userId", userId);
                params.put("circuitSn", lineSn);

                SystemRestClient.post("/getPoleData", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            String responseEntities = new String(responseBody, "UTF-8");
                            Log.e("response", responseEntities);
                            subscriber.onNext(pageEntityJsonMapper.transformPolePageEntity(responseEntities));
                            subscriber.onCompleted();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        subscriber.onError(new NetworkConnectionException("链接失败"));
                    }
                });
            }
        });
    }

    @Override
    public Observable<PolePageEntity> addPole(int userId, int lineSn, String poleName, String longitude, String latitude, String altitude) {
        return Observable.create(new Observable.OnSubscribe<PolePageEntity>() {
            @Override
            public void call(Subscriber<? super PolePageEntity> subscriber) {
                RequestParams params = new RequestParams();
                params.put("userId", userId);
                params.put("circuitSn", lineSn);
                params.put("towerName", poleName);
                params.put("longitude", longitude);
                params.put("latitude", latitude);
                params.put("altitude", altitude);

                SystemRestClient.post("/insertPoleData", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            String responseEntities = new String(responseBody, "UTF-8");
                            Log.e("response", responseEntities);
                            subscriber.onNext(pageEntityJsonMapper.transformPolePageEntity(responseEntities));
                            subscriber.onCompleted();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        subscriber.onError(new NetworkConnectionException("链接失败"));
                    }
                });
            }
        });
    }

    @Override
    public Observable<PolePageEntity> deletePole(int userId, int poleSn) {
        return Observable.create(new Observable.OnSubscribe<PolePageEntity>() {
            @Override
            public void call(Subscriber<? super PolePageEntity> subscriber) {
                RequestParams params = new RequestParams();
                params.put("userId", userId);
                params.put("poleSn", poleSn);

                SystemRestClient.post("/deletePoleData", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            String responseEntities = new String(responseBody, "UTF-8");
                            Log.e("response", responseEntities);
                            subscriber.onNext(pageEntityJsonMapper.transformPolePageEntity(responseEntities));
                            subscriber.onCompleted();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        subscriber.onError(new NetworkConnectionException("链接失败"));
                    }
                });
            }
        });
    }

    @Override
    public Observable<PolePageEntity> changePole(int userId, int poleSn, String poleName, String longitude, String latitude, String altitude) {
        return Observable.create(new Observable.OnSubscribe<PolePageEntity>() {
            @Override
            public void call(Subscriber<? super PolePageEntity> subscriber) {
                RequestParams params = new RequestParams();
                params.put("userId", userId);
                params.put("towerName", poleName);
                params.put("longitude", longitude);
                params.put("latitude", latitude);
                params.put("altitude", altitude);
                params.put("poleSn", poleSn);

                SystemRestClient.post("/updatePoleData", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            String responseEntities = new String(responseBody, "UTF-8");
                            Log.e("response", responseEntities);
                            subscriber.onNext(pageEntityJsonMapper.transformPolePageEntity(responseEntities));
                            subscriber.onCompleted();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        subscriber.onError(new NetworkConnectionException("链接失败"));
                    }
                });
            }
        });
    }

    @Override
    public Observable<List<CompanyEntity>> getAllLine(int userId) {
        return Observable.create(new Observable.OnSubscribe<List<CompanyEntity>>() {
            @Override
            public void call(Subscriber<? super List<CompanyEntity>> subscriber) {
                RequestParams params = new RequestParams();
                params.put("userId", userId);
                SystemRestClient.post("/queryCompanyAndCircut", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            String responseEntities = new String(responseBody, "UTF-8");
                            Log.e("response", responseEntities);
                            subscriber.onNext(companyEntityJsonMapper.transformCompanyEntity(responseEntities));
                            subscriber.onCompleted();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
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