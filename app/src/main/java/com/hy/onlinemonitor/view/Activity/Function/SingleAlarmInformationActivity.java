package com.hy.onlinemonitor.view.Activity.Function;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.AlarmPage;
import com.hy.onlinemonitor.presenter.AlarmPresenter;
import com.hy.onlinemonitor.utile.GetLoading;
import com.hy.onlinemonitor.view.Adapter.AlarmRecyclerAdapter;
import com.hy.onlinemonitor.view.AlarmListView;
import com.hy.onlinemonitor.view.InitView;
import com.rey.material.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 24363 on 2015/8/20.
 */
public class SingleAlarmInformationActivity extends AppCompatActivity implements AlarmListView, InitView {
    @Bind(R.id.equipment_toolbar)
    Toolbar toolbar;
    @Bind(R.id.rv_recyclerview_data)
    RecyclerView rvRecyclerviewData;
    @Bind(R.id.swipeRefreshLayout)
    PullRefreshLayout swipeRefreshLayout;
    @Bind(R.id.error_message_tv)
    TextView errorMessageTv;
    @Bind(R.id.refresh_button)
    Button refreshButton;
    @Bind(R.id.error_message_ll)
    RelativeLayout errorMessageLl;

    private AlarmRecyclerAdapter mAdapter;
    private int showType;
    private AlarmPresenter alarmPresenter;
    private int status;
    private AlertDialog loadingDialog;
    private AlarmPage alarmPage;
    private String equipmentName;
    private String queryAlarmType;
    private int userId;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipmentlist);
        ButterKnife.bind(this);
        Intent intent = getIntent();

        queryAlarmType = intent.getStringExtra("queryAlarmType");
        equipmentName = intent.getStringExtra("equipmentName");
        userId = intent.getIntExtra("userId", -1);
        status = intent.getIntExtra("status", -1);
        showType = intent.getIntExtra("showType", -1);
        toolbar.setTitle(intent.getStringExtra("title"));
        toolbar.setSubtitle(equipmentName);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupUI();
        initialize();

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
        alarmPresenter.destroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void showLoading() {
        loadingDialog.show();
    }

    @Override
    public void hideLoading() {
        loadingDialog.cancel();
    }

    @Override
    public void showError(String message) {
        errorMessageLl.setVisibility(View.VISIBLE);
        errorMessageTv.setText(message);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                errorMessageLl.setVisibility(View.GONE);
                initialize();
            }
        });
    }

    @Override
    public Context getContext() {
        return SingleAlarmInformationActivity.this;
    }

    @Override
    public void setupUI() {
        loadingDialog = GetLoading.getDialog(SingleAlarmInformationActivity.this, "加载数据中");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvRecyclerviewData.setLayoutManager(layoutManager);
        rvRecyclerviewData.setHasFixedSize(true);
        alarmPage = new AlarmPage();

        mAdapter = new AlarmRecyclerAdapter(alarmPage, SingleAlarmInformationActivity.this, showType, queryAlarmType, status);
        rvRecyclerviewData.setAdapter(mAdapter);
        alarmPresenter = new AlarmPresenter(SingleAlarmInformationActivity.this);
        this.alarmPresenter.setView(this);
    }

    @Override
    public void initialize() {
        loadAlarmList(userId, equipmentName, queryAlarmType, status, 1);
    }

    @Override
    public void renderAlarmList(AlarmPage alarmPage, String queryAlarmType) {
        if (alarmPage != null) {
            if (alarmPage.getList().size() != 0) {
                errorMessageLl.setVisibility(View.GONE);
                this.alarmPage = alarmPage;
                this.mAdapter.setAlarmCollection(alarmPage.getList());
            } else {
                errorMessageLl.setVisibility(View.VISIBLE);
                errorMessageTv.setText(R.string.not_data);
                refreshButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initialize();
                    }
                });
            }
        }
    }

    private void loadAlarmList(int userId, String equipmentName, String queryAlarmType, int status, int pageNumber) {
        this.alarmPresenter.initialize(userId, equipmentName, queryAlarmType, status, pageNumber);
    }
}
