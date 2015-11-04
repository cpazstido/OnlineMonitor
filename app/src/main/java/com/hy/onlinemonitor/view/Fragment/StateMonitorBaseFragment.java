package com.hy.onlinemonitor.view.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.OnlineDeviceStatePage;
import com.hy.onlinemonitor.presenter.ConditionMonitoringPresenter;
import com.hy.onlinemonitor.presenter.EquipmentStateMonitorPresenter;
import com.hy.onlinemonitor.view.Adapter.DeviceStateMonitoring.CMBaseAdapter;
import com.rey.material.widget.Button;

/**
 * Created by 24363 on 2015/10/16.
 */
public abstract class StateMonitorBaseFragment extends Fragment {

    private static final String TAG = "状态监测BaseFragment";
    public CMBaseAdapter mAdapter;
    protected RecyclerView rvRecyclerviewData;
    protected PullRefreshLayout swipeRefreshLayout;
    protected EquipmentStateMonitorPresenter equipmentStateMonitorPresenter;
    protected ConditionMonitoringPresenter conditionMonitoringPresenter;
    protected RelativeLayout errorRl;
    protected TextView errorShow;
    protected Button errorRefresh;
    protected Bundle bundle;
    protected int lineSn = 0;
    protected int userId = 0;
    protected int pageNum = 1;
    public OnlineDeviceStatePage onlineDeviceStatePage;
    public boolean isLoadingMore =false;
    protected int lastVisibleItem;
    protected LinearLayoutManager layoutManager;
    protected boolean isHaveData = false;

    public void setLineSn(int lineSn) {
        this.lineSn = lineSn;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        if (bundle != null) {
            userId = bundle.getInt("userId");
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG, "onViewCreated");
        this.initializePresenter();
        rvRecyclerviewData = (RecyclerView) view.findViewById(R.id.rv_recyclerview_data);
        swipeRefreshLayout = (PullRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        errorRl = (RelativeLayout) view.findViewById(R.id.error_message_ll);
        errorShow = (TextView) view.findViewById(R.id.error_message_tv);
        errorRefresh = (Button) view.findViewById(R.id.refresh_button);

        swipeRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doRefresh();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 300);
            }
        });
        this.initRecyclerView();
    }

    protected abstract void initRecyclerView();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView");
        return inflater.inflate(R.layout.fragment_state_monitoring, container, false);
    }

    public void showError(String message){
        swipeRefreshLayout.setVisibility(View.GONE);
        errorRl.setVisibility(View.VISIBLE);
        errorShow.setText(message);
        errorRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toErrorRefresh();
            }
        });
    }

    /**
     * 加载数据,在变为可见时调用
     */
    protected abstract void loadData();

    /**
     * 创建adapter
     */
    protected abstract void getAdapter();

    /**
     * 下拉刷新时调用
     */
    protected abstract void doRefresh();


    /**
     * 初始化Presenter
     */
    protected abstract void initializePresenter();

    protected abstract void toErrorRefresh();
}
