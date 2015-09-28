package com.hy.onlinemonitor.utile;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 24363 on 2015/9/25.
 */
public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void finishAll(){
        for (Activity activity :activities){
            activity.finish();
        }
    }
}
