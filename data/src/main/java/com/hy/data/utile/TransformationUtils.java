package com.hy.data.utile;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;

public class TransformationUtils {
    public static List<String> getListFromString(String str, String splitToWhat) {
        return Arrays.asList(str.split(splitToWhat));
    }

    public static TreeMap<String, Float> getTreeMapFromJsonString(String str, String Type) {
        if (str != null && str != "" && str != "[]") {
            List<String> abscissaList = new ArrayList<>(); //时间,横坐标
            List<Float> ordinateList = new ArrayList<>(); //  值,纵坐标
            try {
                JSONArray jsonArray = new JSONArray(str);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONArray jsonArray1 = jsonArray.getJSONArray(i);
                    long f1 = (long) jsonArray1.get(0);//时间
                    Double f2 = Double.parseDouble(jsonArray1.get(1).toString());//获得的值
                    ordinateList.add(f2.floatValue());
                    Date date = new Date(f1);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                    String sdfDate = sdf.format(date);
                    String[] timeStr = sdfDate.split(" ");
//                String clock = timeStr[1];
//                String finalClock = clock.substring(0, 5);
//                String[] timeStr2 = finalClock.split(":");
//                String str2 = timeStr2[0]+"."+timeStr2[1];
//                float times = Float.valueOf(str2);
//                abscissaList.add(times);
                    switch (Type) {
                        case "allTime"://实时统计
                            abscissaList.add(timeStr[1]);
                            break;
                        case "day"://按日统计
                            abscissaList.add(timeStr[0]);
                            break;
                        case "month"://按月统计
                            String[] str2 = timeStr[0].split("-");
                            String str3 = str2[0] + "-" + str2[1];
                            abscissaList.add(str3);
                            break;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            TreeMap<String, Float> treeMap = new TreeMap<>();
            for (int i = 0; i < abscissaList.size(); i++) {
                treeMap.put(abscissaList.get(i), ordinateList.get(i));
            }
            return treeMap;
        } else {
            return null;
        }
    }

}
