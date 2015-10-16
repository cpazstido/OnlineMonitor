package com.hy.data.utile;

import org.apache.http.cookie.Cookie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 24363 on 2015/10/12.
 */
public class CookiesUtils {
    private static List<Cookie> cookies;

    public static List<Cookie> getCookies() {
        return cookies != null ? cookies : new ArrayList<>();
    }

    public static void setCookies(List<Cookie> cookies) {
        CookiesUtils.cookies = cookies;
    }
}
