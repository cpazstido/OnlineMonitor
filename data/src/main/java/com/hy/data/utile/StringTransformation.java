package com.hy.data.utile;

import android.util.Log;

/**
 * Created by 24363 on 2015/8/24.
 */
public class StringTransformation {
    public static String transform(String str){
        String res = str.replace("\\","");
        Log.e("res",res);
        Log.e("str",str);
        res=res.substring(1, res.length()-1);
        return res;
    }
}
