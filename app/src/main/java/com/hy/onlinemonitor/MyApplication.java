package com.hy.onlinemonitor;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.google.gson.Gson;
import com.hy.data.utile.SystemRestClient;
import com.hy.onlinemonitor.bean.AppInfo;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.io.UnsupportedEncodingException;


/**
 * Created by 24363 on 2015/9/29.
 */
public class MyApplication extends Application {
    public static int localVersion = 0;// 本地安装版本
    public static int serverVersion = 0;// 服务器版本
    public static long appSize = 0;


    @Override
    public void onCreate() {
        try {
            PackageInfo packageInfo = getApplicationContext()
                    .getPackageManager().getPackageInfo(getPackageName(), 0);
            localVersion = packageInfo.versionCode;
            SystemRestClient.get("/checkUpdate", null, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    try {
                        String response = new String(responseBody, "UTF-8");
                        Log.d("MyApplication", "response:" + response);
                        if (response.contains("资源已经被移除或不存在")) {
                            Log.e("tag", "访问升级服务失败");
                            serverVersion = 0;
                            appSize = 0;
                        } else if (response.isEmpty()) {
                            Log.e("tag", "访问升级服务失败");
                            serverVersion = 0;
                            appSize = 0;
                        } else if ("null".equals(response)) {
                            Log.e("tag", "访问升级服务失败");
                            serverVersion = 0;
                            appSize = 0;
                        }else {
                            Gson gson = new Gson();
                            AppInfo appInfo = gson.fromJson(response, AppInfo.class);
                            serverVersion = appInfo.getServerVersion();
                            appSize = appInfo.getSize();
                            Log.e("appInfo", "\nlocalVersion->" + localVersion + "\nserverVersion->" + serverVersion + "\nappSize->" + appSize);
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Log.e("application", "error");
                    serverVersion = 0;
                    appSize = 0;
                }
            });
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        super.onCreate();
    }

}
