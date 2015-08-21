package com.hy.onlinemonitor.view.Activity.Function;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.baoyz.widget.PullRefreshLayout;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.AlarmInformation;
import com.hy.onlinemonitor.presenter.AlarmPresenter;
import com.hy.onlinemonitor.view.Adapter.AlarmRecyclerAdapter;
import com.hy.onlinemonitor.view.AlarmListView;
import com.hy.onlinemonitor.view.Component.RecyclerViewFragment;
import com.hy.onlinemonitor.view.InitView;

import java.util.ArrayList;
import java.util.Collection;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 24363 on 2015/8/20.
 */
public class SingleAlarmInformationActivity extends AppCompatActivity implements AlarmListView,RecyclerViewFragment.AlarmListListener,InitView {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.rv_recyclerview_data)
    RecyclerView rvRecyclerviewData;
    @Bind(R.id.swipeRefreshLayout)
    PullRefreshLayout swipeRefreshLayout;
    private AlarmRecyclerAdapter mAdapter;
    private int showType;
    private AlarmPresenter alarmPresenter;
    private int equipmentSn;

    private int visibleLastIndex = 0;   //最后的可视项索引
    private int visibleItemCount;       // 当前窗口可见项总数
    private int datasize = 38;          //数据集的条数
    private int page=0;                 //页数

    private AlarmRecyclerAdapter.OnItemClickListener onItemClickListener =
            new AlarmRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onAlarmItemClicked(AlarmInformation alarmInformation) {
                    Log.e("OnItemClickListener", "OnItemClickListener");
                    if (SingleAlarmInformationActivity.this.alarmPresenter != null && alarmInformation != null) {
                        SingleAlarmInformationActivity.this.alarmPresenter.onAlarmClicked(alarmInformation);
                    }
                }
            };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipmentlist);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String title = intent.getStringExtra("titleName");
        String subTitle = intent.getStringExtra("equipmentName");
        String userId = intent.getStringExtra("userId");
        equipmentSn = intent.getIntExtra("equipmentSn",-1);
        showType = intent.getIntExtra("showType",-1);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setSubtitle(subTitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupUI();
        initialize();
        loadAlarmList(userId,equipmentSn,1);
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

    @Override
    public void onAlarmClicked(AlarmInformation alarmInformation) {
        Intent intent = new Intent(SingleAlarmInformationActivity.this,DetailedAlarmActivity.class);
        intent.putExtra("detailedAlarm",alarmInformation);
        startActivity(intent);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public Context getContext() {
        return SingleAlarmInformationActivity.this;
    }

    @Override
    public void setupUI() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvRecyclerviewData.setLayoutManager(layoutManager);
        rvRecyclerviewData.setHasFixedSize(true);
        mAdapter = new AlarmRecyclerAdapter(new ArrayList<AlarmInformation>(), SingleAlarmInformationActivity.this, showType);
        mAdapter.setOnItemClickListener(onItemClickListener);
        rvRecyclerviewData.setAdapter(mAdapter);
    }

    @Override
    public void initialize() {
        alarmPresenter = new AlarmPresenter(SingleAlarmInformationActivity.this);
        this.alarmPresenter.setView(this);
    }

    @Override
    public void renderAlarmList(Collection<AlarmInformation> alarmInformationCollection) {
        if (alarmInformationCollection != null) {
            this.mAdapter.setAlarmCollection(alarmInformationCollection);
        }
    }
    @Override
    public void viewAlarm(AlarmInformation alarmInformation) {
            this.onAlarmClicked(alarmInformation);
    }

    private void loadAlarmList(String userId,int equipmentSn,int page) {
        this.alarmPresenter.initialize(userId, equipmentSn,page);
    }
}
