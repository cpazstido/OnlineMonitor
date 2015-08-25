package com.hy.onlinemonitor.utile;

import java.util.Arrays;
import java.util.List;


public class TransformationUtils {
    public static List<String> getListFromString(String str,String splitToWhat){
        List<String> list = Arrays.asList(str.split(splitToWhat));
        return  list;
    }
    public static Integer[] getIntegerFromInt(int[] nums){
        Integer[] in = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            in[i] = Integer.valueOf(nums[i]);
        }
            return in;
    }
}
