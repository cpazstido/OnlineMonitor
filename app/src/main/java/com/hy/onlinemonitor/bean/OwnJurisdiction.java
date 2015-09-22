package com.hy.onlinemonitor.bean;

import java.util.ArrayList;
import java.util.List;

public class OwnJurisdiction {
    private static List<Integer> jurisdictionList = new ArrayList<>();

    public static List<Integer> getJurisdictionList(){
        return jurisdictionList;
    }

    public static void setJurisdictionList(List<Integer> ownJurisdictionList){
        jurisdictionList = ownJurisdictionList;
    }

}
