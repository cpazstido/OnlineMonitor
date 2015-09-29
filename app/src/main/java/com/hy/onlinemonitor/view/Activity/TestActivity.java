package com.hy.onlinemonitor.view.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hy.onlinemonitor.R;

import butterknife.ButterKnife;


/**
 * Created by Administrator on 2015/7/14.
 */
public class TestActivity extends AppCompatActivity {

    public static void StartTestView(Context context) {
        Intent intent = new Intent(context, TestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

    }

}