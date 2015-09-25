package com.hy.onlinemonitor.view.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.utile.ShowUtile;
import com.rey.material.widget.RadioButton;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2015/7/14.
 */
public class TestActivity extends AppCompatActivity {

    @Bind(R.id.switches_rb1)
    RadioButton switchesRb1;
    @Bind(R.id.switches_rb2)
    RadioButton switchesRb2;
    @Bind(R.id.test_btn)
    Button testBtn;

    public static void StartTestView(Context context) {
        Intent intent = new Intent(context, TestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Log.e("equls", "onCheck->" + (switchesRb1 == buttonView));
                    Log.e("equls", "onCheck->" + (switchesRb2 == buttonView));
                    switchesRb1.setChecked(switchesRb1 == buttonView);
                    switchesRb2.setChecked(switchesRb2 == buttonView);
                }
                if (switchesRb1.isChecked()) {
                    ShowUtile.toastShow(TestActivity.this, "switchesRb1 is checked");
                } else if (switchesRb2.isChecked()) {
                    ShowUtile.toastShow(TestActivity.this, "switchesRb2 is checked");
                }else{
                    Log.e("tag","show->switchesRb1"+switchesRb1.isChecked()+"switchesRb1"+switchesRb2.isChecked());
                }

            }
        };

        switchesRb1.setOnCheckedChangeListener(listener);
        switchesRb2.setOnCheckedChangeListener(listener);


        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchesRb1.isChecked()) {
                    switchesRb1.setChecked(false);
                    Log.e("tag", "switchesRb1 is checked");
                } else {
                    Log.e("tag", "switchesRb1 is not checked");
                }
            }
        });
    }

}