package com.hy.data.utile;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.HttpEntity;

public class SystemRestClient {

    public static final String BASE_MONITOR_URL = "http://118.123.114.8:8080/eMonitorApp";
    public static final String BASE_PICTURE_URL = "http://118.123.114.8:8080/eMonitorApp/alarm/";
    private static final String BASE_URL = "http://118.123.114.8:8080/eMonitorApp/android";
    private static final String BASE_VIDEO_URL = "http://118.123.114.8:8080/eMonitorApp/accessServer";
    private static final String BASE_POWER_URL = "http://118.123.114.8:8080/eMonitorApp/frontendconfig";
    private static final String BASE_UPDATA_URL = "http://118.123.114.8:8080/eMonitorApp/appUpdate";

//    private static final String BASE_URL = "http://171.221.207.57:8080/eMonitorApp/android";
//    public static final String BASE_PICTURE_URL = "http://171.221.207.57:8080/eMonitorApp/alarm/";
//    private static final String BASE_VIDEO_URL = "http://171.221.207.57:8080/eMonitorApp/accessServer";
//    private static final String BASE_POWER_URL = "http://171.221.207.57:8080/eMonitorApp/frontendconfig";
//    private static final String BASE_UPDATA_URL = "http://171.221.207.57:8080/eMonitorApp/appUpdate";

//    public static final String BASE_MONITOR_URL = "http://172.16.8.236:8081/eMonitorApp";
//    private static final String BASE_URL = "http://172.16.8.236:8081/eMonitorApp/android";
//    public static final String BASE_PICTURE_URL = "http://172.16.8.236:8081/eMonitorApp/alarm/";
//    private static final String BASE_VIDEO_URL = "http://172.16.8.236:8081/eMonitorApp/accessServer";
//    private static final String BASE_POWER_URL = "http://172.16.8.236:8081/eMonitorApp/frontendconfig";

    private static FinalAsyncHttpClient finalAsyncHttpClient = new FinalAsyncHttpClient();
    private static AsyncHttpClient client = finalAsyncHttpClient.getAsyncHttpClient();

    static {
        client.setTimeout(15000);
    }

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getUpDataAbsoluteUrl(url), params, responseHandler);
    }

    public static void get(String url, AsyncHttpResponseHandler responseHandler) {
        client.get(getUpDataAbsoluteUrl(url), responseHandler);
    }

    private static String getUpDataAbsoluteUrl(String url) {
        Log.e("getUpDataAbsoluteUrl", BASE_UPDATA_URL + url);
        return BASE_UPDATA_URL + url;
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        Log.i("getAbsoluteUrl", BASE_URL + relativeUrl);
        return BASE_URL + relativeUrl;
    }

    public static void XmlPost(Context mContext, String url, HttpEntity httpEntity, String contentType, AsyncHttpResponseHandler responseHandler) {
        client.post(mContext, getXmlAbsoluteUrl(url), httpEntity, contentType, responseHandler);
    }

    private static String getXmlAbsoluteUrl(String relativeUrl) {
        Log.i("getAbsoluteUrl", BASE_VIDEO_URL + relativeUrl);
        return BASE_VIDEO_URL + relativeUrl;
    }

    public static void XmlControlPost(Context mContext, String url, int dvrID, int channelID, String dvrType, HttpEntity httpEntity, String contentType, AsyncHttpResponseHandler responseHandler) {
        client.post(mContext, getXmlControlAbsoluteUrl(url, dvrID, channelID, dvrType), httpEntity, contentType, responseHandler);
    }

    private static String getXmlControlAbsoluteUrl(String url, int dvrID, int channelID, String dvrType) {
        Log.i("XmlControlAbsoluteUrl", BASE_VIDEO_URL + url + "?DvrID=" + dvrID + "&ChannelID=" + channelID + "&DvrType=" + dvrType);
        return BASE_VIDEO_URL + url + "?DvrID=" + dvrID + "&ChannelID=" + channelID + "&DvrType=" + dvrType;
    }

    /**
     * 用于实时播放,以及停止的特殊请求post
     */
    public static void post(String url, String dvrType, int dvrId, int channelID, int streamType, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getXmlAbsoluteUrl(url, dvrType, dvrId, channelID, streamType), params, responseHandler);
    }

    private static String getXmlAbsoluteUrl(String relativeUrl, String dvrType, int dvrId, int channelID, int streamType) {
        Log.e("getAbsoluteUrl", BASE_VIDEO_URL + relativeUrl + "?DvrID=" + dvrId + "&StreamType=" + streamType + "&ChannelID=" + channelID + "&DvrType=" + dvrType);
        return BASE_VIDEO_URL + relativeUrl + "?DvrID=" + dvrId + "&StreamType=" + streamType + "&ChannelID=" + channelID + "&DvrType=" + dvrType;
    }

    public static void openPower(String url, String deviceId, int operationType, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url, deviceId, operationType), null, responseHandler);
    }

    public static void openFirePower(String url, int dvrId, int channelID, String dvrType, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url, dvrId, channelID, dvrType), null, responseHandler);
    }

    private static String getAbsoluteUrl(String url, int dvrId, int channelID, String dvrType) {
        Log.e("openCameraPower", BASE_VIDEO_URL + url + "?DvrID=" + dvrId + "&ChannelID=" + channelID + "&DvrType=" + dvrType);
        return BASE_VIDEO_URL + url + "?DvrID=" + dvrId + "&ChannelID=" + channelID + "&DvrType=" + dvrType;
    }

    private static String getAbsoluteUrl(String relativeUrl, String deviceId, int operationType) {
        Log.e("openPower", BASE_POWER_URL + relativeUrl + "?deviceID=" + deviceId + "&operationType=" + operationType);
        return BASE_POWER_URL + relativeUrl + "?deviceID=" + deviceId + "&operationType=" + operationType;
    }

    public static AsyncHttpClient getClient() {
        return client;
    }
}
