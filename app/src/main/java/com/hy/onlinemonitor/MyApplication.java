package com.hy.onlinemonitor;

import android.app.Application;


/**
 * Created by 24363 on 2015/9/29.
 */
public class MyApplication extends Application {
    public static void setLocalVersion(int localVersion) {
        MyApplication.localVersion = localVersion;
    }

    public static void setServerVersion(int serverVersion) {
        MyApplication.serverVersion = serverVersion;
    }

    public static void setAppSize(long appSize) {
        MyApplication.appSize = appSize;
    }
    public static int localVersion = 0;// 本地安装版本
    public static int serverVersion = 0;// 服务器版本
    public static long appSize = 0;
    public static int deviceListSortType =0;//设备列表排序方式,默认为0,按
    @Override
    public void onCreate() {
        super.onCreate();
    }

}
