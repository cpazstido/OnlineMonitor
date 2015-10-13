package com.hy.data.utile;

import com.loopj.android.http.AsyncHttpClient;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;

/**
 * Created by 24363 on 2015/10/12.
 */
public class FinalAsyncHttpClient {
    AsyncHttpClient client;

    public FinalAsyncHttpClient() {
        client = new AsyncHttpClient();
        client.setConnectTimeout(5);//5s超时
        if (CookiesUtils.getCookies() != null) {//每次请求都要带上cookie
            BasicCookieStore bcs = new BasicCookieStore();
            bcs.addCookies(CookiesUtils.getCookies().toArray(
                    new Cookie[CookiesUtils.getCookies().size()]));
            client.setCookieStore(bcs);
        }
    }

    public AsyncHttpClient getAsyncHttpClient(){
        return this.client;
    }
}
