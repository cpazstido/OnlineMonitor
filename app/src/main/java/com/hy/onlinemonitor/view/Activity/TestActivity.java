package com.hy.onlinemonitor.view.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.hy.onlinemonitor.R;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2015/7/14.
 */
public class TestActivity extends AppCompatActivity {
    public static String aaa = new String();
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.equipment_manage);
        getSupportActionBar().setSubtitle(R.string.sensor_manage_2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(888);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            setResult(777);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}