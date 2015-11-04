package com.hy.onlinemonitor.view.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.hy.onlinemonitor.bean.OnlineDeviceState;
import com.hy.onlinemonitor.bean.OnlineDeviceStatePage;
import com.hy.onlinemonitor.presenter.EquipmentStateMonitorPresenter;
import com.hy.onlinemonitor.utile.ShowUtile;
import com.hy.onlinemonitor.view.Activity.ConditionMonitor.EquipmentStateMonitorActivity;
import com.hy.onlinemonitor.view.Adapter.DeviceStateMonitoring.BatteryChargeCurrentRecyclerAdapter;
import com.hy.onlinemonitor.view.Adapter.DeviceStateMonitoring.BatteryChargeSwitchRecyclerAdapter;
import com.hy.onlinemonitor.view.Adapter.DeviceStateMonitoring.BatteryOutputCurrentRecyclerAdapter;
import com.hy.onlinemonitor.view.Adapter.DeviceStateMonitoring.BatteryRemainingCapacityRecyclerAdapter;
import com.hy.onlinemonitor.view.Adapter.DeviceStateMonitoring.BatteryVoltageRecyclerAdapter;
import com.hy.onlinemonitor.view.Adapter.DeviceStateMonitoring.DVRStateRecyclerAdapter;
import com.hy.onlinemonitor.view.Adapter.DeviceStateMonitoring.DeviceInformationRecyclerAdapter;
import com.hy.onlinemonitor.view.Adapter.DeviceStateMonitoring.DeviceStateRecyclerAdapter;
import com.hy.onlinemonitor.view.Adapter.DeviceStateMonitoring.SolarPanelVoltageRecyclerAdapter;
import com.hy.onlinemonitor.view.Adapter.DeviceStateMonitoring.UploadDataInformationRecyclerAdapter;

import java.util.ArrayList;

public class EquipmentStateMonitorFragment extends StateMonitorBaseFragment {
    private static final String TAG = "状态监测Fragment";
    private static Context mContext;

    @Override
    protected void initRecyclerView() {
        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        rvRecyclerviewData.setLayoutManager(layoutManager);
        rvRecyclerviewData.setHasFixedSize(true);
        rvRecyclerviewData.setAdapter(mAdapter);
        rvRecyclerviewData.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        if ((lastVisibleItem + 1) == mAdapter.getItemCount() && isHaveData) {
                            if (pageNum < onlineDeviceStatePage.getTotalPage() && !isLoadingMore) {
                                isLoadingMore = true;
                                pageNum++;
                                loadData();
                            } else {
                                ShowUtile.snackBarShow(getView(), "无更多数据...");
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
        this.loadData();
    }

    public void loadData() {//加载数据
        Log.e(TAG, "lineSn->" + lineSn + "userId" + userId + "pageNum->" + pageNum);
        equipmentStateMonitorPresenter.loadOnlineDeviceState(mContext, userId, lineSn, pageNum);
    }

    protected void getAdapter() {//创建adapter
        switch (bundle.getInt("postion")) {
            case 0://装置信息
                mAdapter = new DeviceInformationRecyclerAdapter(mContext, new ArrayList<OnlineDeviceState>());
                break;
            case 1://装置状态
                mAdapter = new DeviceStateRecyclerAdapter(mContext, new ArrayList<OnlineDeviceState>());
                break;
            case 2://DVR系统状态
                mAdapter = new DVRStateRecyclerAdapter(mContext, new ArrayList<OnlineDeviceState>());
                break;
            case 3://电池电压
                mAdapter = new BatteryVoltageRecyclerAdapter(mContext, new ArrayList<OnlineDeviceState>());
                break;
            case 4://电池充电电流
                mAdapter = new BatteryChargeCurrentRecyclerAdapter(mContext, new ArrayList<OnlineDeviceState>());
                break;
            case 5://电池剩余电量
                mAdapter = new BatteryRemainingCapacityRecyclerAdapter(mContext, new ArrayList<OnlineDeviceState>());
                break;
            case 6://电池输出电流
                mAdapter = new BatteryOutputCurrentRecyclerAdapter(mContext, new ArrayList<OnlineDeviceState>());
                break;
            case 7://太阳能板电压
                mAdapter = new SolarPanelVoltageRecyclerAdapter(mContext, new ArrayList<OnlineDeviceState>());
                break;
            case 8://上传数据信息
                mAdapter = new UploadDataInformationRecyclerAdapter(mContext, new ArrayList<OnlineDeviceState>());
                break;
            case 9://电池充电开关
                mAdapter = new BatteryChargeSwitchRecyclerAdapter(mContext, new ArrayList<OnlineDeviceState>());
                break;
            default:
                break;
        }
    }

    protected void doRefresh() {//刷新数据
        pageNum = 1;
        mAdapter.getEquipmentInformatics().clear();
        loadData();
    }

    @Override
    protected void initializePresenter() {
        equipmentStateMonitorPresenter = new EquipmentStateMonitorPresenter(mContext);
        equipmentStateMonitorPresenter.setView((EquipmentStateMonitorActivity) getActivity());
        equipmentStateMonitorPresenter.setEquipmentStateMonitorFragment(this);
        getAdapter();
    }

    @Override
    protected void toErrorRefresh() {
        doRefresh();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public static EquipmentStateMonitorFragment newInstance(Context context, int postion, int userId) {
        Bundle bundle = new Bundle();
        bundle.putInt("postion", postion);
        bundle.putInt("userId", userId);
        mContext = context;
        EquipmentStateMonitorFragment fragment = new EquipmentStateMonitorFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public void renderOnlineDeviceStateList(OnlineDeviceStatePage onlineDeviceStatePage) {
        isLoadingMore = false;
        if (onlineDeviceStatePage != null) {
            if (onlineDeviceStatePage.getList().size() != 0) {
                isHaveData = true;
                pageNum = onlineDeviceStatePage.getPageNum();
                this.onlineDeviceStatePage = onlineDeviceStatePage;
                this.mAdapter.setCollection(onlineDeviceStatePage.getList());
            } else {
                ((EquipmentStateMonitorActivity)getActivity()).showError("暂无数据");
            }
        }
    }
}
