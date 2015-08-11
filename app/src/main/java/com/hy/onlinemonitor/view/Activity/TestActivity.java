package com.hy.onlinemonitor.view.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hy.onlinemonitor.R;


/**
 * Created by Administrator on 2015/7/14.
 */
public class TestActivity extends AppCompatActivity {
    android.support.v7.app.AlertDialog ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
//        AlertDialog LoginAlert = GetLoading.getDialog(TestActivity.this, "登录中");

    }
//        List<EquipmnetAlarmInformation> list = new ArrayList<>();
//        list.add(new EquipmnetAlarmInformation("美国超级电力", "摄像机开电", 0, 1, 1));
//        list.add(new EquipmnetAlarmInformation("美国超级电力", "摄像机开电", 0, 1, 1));
//        Gson gson = new Gson();
//
//        List<Test> mlist = new ArrayList<>();
//        mlist.add(new Test("那么",1,list));
//
//        String str = gson.toJson(list);
//        Log.e("str", str);


//        String str = "{userName:name}";
//        Gson gson = new Gson();
//        UserInformation userInformation = gson.fromJson(str, UserInformation.class);
//
//        Log.i("str",userInformation.toString());


//        findViewById(R.id.default_btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ad = GetLoading.getDialog(TestActivity.this, "登录中...");
//                new Timer().schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        ad.cancel();
//                    }
//                },4000,10000);
//            }
//        })

        //这里获得权限列表并设置到recycleview中

        //这里进行网络操作取得数据
//        CharSequence[] mDatase = getformInternet();
//
//        new MaterialDialog.Builder(TestActivity.this)
//                .title(R.string.jurisdiction_config)
//                .items(mDatase)
//                .itemsCallbackMultiChoice(new Integer[]{1, 3}, new MaterialDialog.ListCallbackMultiChoice() {
//                    @Override
//                    public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
//                        StringBuilder str = new StringBuilder();
//                        for (int i = 0; i < which.length; i++) {
//                            if (i > 0) str.append('\n');
//                            str.append(which[i]);
//                            str.append(": ");
//                            str.append(text[i]);
//                        }
//                        Toast.makeText(TestActivity.this,str.toString(),Toast.LENGTH_LONG).show();
//                        return true; // allow selection
//                    }
//                })
//                .callback(new MaterialDialog.ButtonCallback() {
//                    @Override
//                    public void onNeutral(MaterialDialog dialog) {
//
//                    }
//                })
//                .alwaysCallMultiChoiceCallback()
//                .positiveText(R.string.cancel)
//                .autoDismiss(false)
//                .neutralText(R.string.check_all)
//                .show();
//    }
//
//    private CharSequence[] getformInternet() {
//        List<String> mlists = new ArrayList<>();
//        mlists.add("山火");
//        mlists.add("外破");
//        mlists.add("普通视频");
//        mlists.add("呵呵");
//        mlists.add("哈哈");
//        mlists.add("嘿嘿");
//
//        return mlists.toArray(new CharSequence[mlists.size()]);
//    }
}