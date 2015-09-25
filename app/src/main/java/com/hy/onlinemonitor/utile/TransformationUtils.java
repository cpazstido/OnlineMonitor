package com.hy.onlinemonitor.utile;

import com.google.gson.Gson;
import com.hy.onlinemonitor.bean.VideoUrl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class TransformationUtils {
    public static List<String> getListFromString(String str, String splitToWhat) {
        List<String> list = Arrays.asList(str.split(splitToWhat));
        return list;
    }

    public static Integer[] getIntegerFromInt(int[] nums) {
        Integer[] in = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            in[i] = Integer.valueOf(nums[i]);
        }
        return in;
    }

    public static void removeDuplicateWithOrder(List list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            Object element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        list.clear();
        list.addAll(newList);
        System.out.println(" remove duplicate " + list);
    }


    public static String getRealVideoUrl(String url) {
        Gson gson = new Gson();
        VideoUrl videoUrl = gson.fromJson(url, VideoUrl.class);
        return videoUrl.getRtspURL();
    }

}
