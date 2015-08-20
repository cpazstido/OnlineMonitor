package com.hy.onlinemonitor.view.Activity.Function;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.baoyz.widget.PullRefreshLayout;
import com.hy.onlinemonitor.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 24363 on 2015/8/20.
 */
public class SingleAlarmInformationActivtity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.rv_recyclerview_data)
    RecyclerView rvRecyclerviewData;
    @Bind(R.id.swipeRefreshLayout)
    PullRefreshLayout swipeRefreshLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipmentlist);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String title = intent.getStringExtra("titleName");
        int equipmentSN = intent.getIntExtra("equipmentSn",-1);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
