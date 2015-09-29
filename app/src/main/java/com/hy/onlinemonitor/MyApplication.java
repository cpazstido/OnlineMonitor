package com.hy.onlinemonitor;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by 24363 on 2015/9/29.
 */
public class MyApplication extends Application{
    public static int localVersion = 0;// 本地安装版本
    public static int serverVersion = 0;// 服务器版本
    public static String downloadDir = "hy";//安装目录

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            PackageInfo packageInfo = getApplicationContext()
                    .getPackageManager().getPackageInfo(getPackageName(), 0);
            localVersion = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }
}
