package com.hy.onlinemonitor;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.google.gson.Gson;
import com.hy.data.utile.SystemRestClient;
import com.hy.onlinemonitor.bean.AppInfo;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;

/**
 * Created by 24363 on 2015/9/29.
 */
public class MyApplication extends Application{
    public static int localVersion = 0;// 本地安装版本
    public static int serverVersion = 0;// 服务器版本
    public static long appSize = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            PackageInfo packageInfo = getApplicationContext()
                    .getPackageManager().getPackageInfo(getPackageName(), 0);
            localVersion = packageInfo.versionCode;

            SystemRestClient.get("", null, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    try {
                        String response = new String(responseBody, "UTF-8");
                        Gson gson = new Gson();
                        AppInfo appInfo = gson.fromJson(response,AppInfo.class);
                        serverVersion = appInfo.getServerVersion();
                        appSize = appInfo.getSize();
                        Log.e("appInfo", "localVersion->"+localVersion+"serverVersion->"+serverVersion+"appSize->"+appSize);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });


        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }
}
