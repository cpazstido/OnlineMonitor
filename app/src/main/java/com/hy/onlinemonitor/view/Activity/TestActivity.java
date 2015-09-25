package com.hy.onlinemonitor.view.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.hy.onlinemonitor.R;
import com.rey.material.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2015/7/14.
 */
public class TestActivity extends AppCompatActivity {

    @Bind(R.id.choice_btn)
    Button choiceBtn;

    public static void StartTestView(Context context) {
        Intent intent = new Intent(context, TestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        choiceBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP){
                    Log.e("test", "cansal button ---> ACTION_UP");
                }
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Log.e("test", "cansal button ---> ACTION_DOWN");
                }
                return true;
            }
        });
    }

}