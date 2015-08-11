package com.hy.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;
import com.hy.data.entity.UserEntity;
import com.hy.data.entity.mapper.UserEntityJsonMapper;
import com.hy.data.exception.NetworkConnectionException;

import java.net.MalformedURLException;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by wsw on 2015/8/10.
 */
public class LoginRestApi implements RestApi {

    private final Context context;
    private final UserEntityJsonMapper userEntityJsonMapper;

    /**
     * Constructor of the class
     *
     * @param context {@link Context}.
     * @param userEntityJsonMapper {@link UserEntityJsonMapper}.
     */
    public LoginRestApi(Context context, UserEntityJsonMapper userEntityJsonMapper) {
        if (context == null || userEntityJsonMapper == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.userEntityJsonMapper = userEntityJsonMapper;
    }

    @Override
    public Observable<UserEntity> userEntity(String loginAccount, String loginPwd) {
        return Observable.create(new Observable.OnSubscribe<UserEntity>() {
         @Override
         public void call(Subscriber<? super UserEntity> subscriber) {

             String responseUserEntities = null;
             responseUserEntities = getTestUserEntities(loginAccount,loginPwd);
//                     responseUserEntities = getUserEntitiesFromApi();
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

    private String getTestUserEntities(String loginAccount, String loginPwd) {
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

    private String getUserEntitiesFromApi() throws MalformedURLException {
//        return ApiConnection.createGET(RestApi.API_URL_GET_USER_LIST).requestSyncCall();
        return null;
    }

    /**
     * Checks if the device has any active internet connection.
     *
     * @return true device with internet connection, otherwise false.
     */
    private boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }

}