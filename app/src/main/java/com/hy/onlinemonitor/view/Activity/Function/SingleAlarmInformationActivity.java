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
import com.hy.onlinemonitor.utile.ActivityCollector;
import com.hy.onlinemonitor.utile.GetLoading;
import com.hy.onlinemonitor.utile.ShowUtile;
import com.hy.onlinemonitor.view.Activity.LoginActivity;
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
    private String curProject;
    private int userId;
    private int dvrType;
    private int dvrId;
    private int lastVisibleItem;
    private LinearLayoutManager layoutManager;
    private boolean isLoadingMore = false;
    private int pageNum = 1;
    private boolean isHaveData = false;

    protected void onCreate(Bundle savedInstanceState) {
        ActivityCollector.addActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipmentlist);
        ButterKnife.bind(this);
        Intent intent = getIntent();

        queryAlarmType = intent.getStringExtra("queryAlarmType");
        equipmentName = intent.getStringExtra("equipmentName");
        userId = intent.getIntExtra("userId", -1);
        dvrType = intent.getIntExtra("dvrType", -1);
        dvrId = intent.getIntExtra("dvrId", -1);
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
        ActivityCollector.removeActivity(this);
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
        swipeRefreshLayout.setVisibility(View.GONE);
        errorMessageTv.setText(message);
        if (message.equals("请重新登录")) {
            refreshButton.setText("登录界面");
            refreshButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LoginActivity.goLoginView(SingleAlarmInformationActivity.this);
                }
            });
        } else
            refreshButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
        layoutManager = new LinearLayoutManager(this);
        rvRecyclerviewData.setLayoutManager(layoutManager);
        rvRecyclerviewData.setHasFixedSize(true);
        alarmPage = new AlarmPage();

        mAdapter = new AlarmRecyclerAdapter(alarmPage, SingleAlarmInformationActivity.this, showType, queryAlarmType, status);
        mAdapter.setDvrType(dvrType);
        mAdapter.setDvrId(dvrId);
        rvRecyclerviewData.setAdapter(mAdapter);
        alarmPresenter = new AlarmPresenter(SingleAlarmInformationActivity.this);
        this.alarmPresenter.setView(this);
        rvRecyclerviewData.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        if ((lastVisibleItem+1) == mAdapter.getItemCount() && isHaveData) {
                            if (pageNum < alarmPage.getTotalPage() && !isLoadingMore) {
                                isLoadingMore = true;
                                pageNum++;
                                loadAlarmList(userId, curProject, queryAlarmType, status, pageNum);
                            } else {
                                ShowUtile.toastShow(getContext(), "无更多数据...");
                            }
                        }
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }

        });
        swipeRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.getInformationTreeMap().clear();
                        pageNum=1;
                        loadAlarmList(userId, curProject, equipmentName, queryAlarmType, status, pageNum);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 300);
            }
        });

    }

    @Override
    public void initialize() {
        pageNum = 1;
        switch (queryAlarmType) {
            case "sensor":
                curProject = getIntent().getStringExtra("curProject");
                loadAlarmList(userId, curProject, equipmentName, queryAlarmType, status, pageNum);
                break;
            default:
                loadAlarmList(userId, equipmentName, queryAlarmType, status, pageNum);
                break;
        }
    }

    @Override
    public void renderAlarmList(AlarmPage alarmPage, final String queryAlarmType) {
        isLoadingMore = false;
        this.alarmPage = alarmPage;
        if (alarmPage != null) {
            if (alarmPage.getList().size() != 0) {
                isHaveData = true;
                pageNum = alarmPage.getPageNum();
                swipeRefreshLayout.setVisibility(View.VISIBLE);
                errorMessageLl.setVisibility(View.GONE);
                this.alarmPage = alarmPage;
                this.mAdapter.setAlarmCollection(alarmPage.getList());
            } else {
                isHaveData = false;
                showError(getResources().getString(R.string.not_data));
                refreshButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pageNum = 1;
                        loadAlarmList(userId, curProject, equipmentName, queryAlarmType, status, pageNum);
                    }
                });
            }
        }
    }

    private void loadAlarmList(int userId, String curProject, String equipmentName, String queryAlarmType, int status, int pageNumber) {
        this.alarmPresenter.initialize(userId, curProject, equipmentName, queryAlarmType, status, pageNumber);
    }

    private void loadAlarmList(int userId, String equipmentName, String queryAlarmType, int status, int pageNumber) {
        this.alarmPresenter.initialize(userId, equipmentName, queryAlarmType, status, pageNumber);
    }
}
