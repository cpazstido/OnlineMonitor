package com.hy.data.utile;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by wsw on 2015/7/17.
 */
public class SystemRestClient {

        private static final String BASE_URL = "http://172.16.8.129:8080/eMonitorApp/android";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        Log.i("getAbsoluteUrl",BASE_URL + relativeUrl);
        return BASE_URL + relativeUrl;
    }

}
