package com.hy.onlinemonitor.view.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.hy.onlinemonitor.bean.OnlineDeviceState;
import com.hy.onlinemonitor.bean.OnlineDeviceStatePage;
import com.hy.onlinemonitor.presenter.EquipmentStateMonitorPresenter;
import com.hy.onlinemonitor.view.Activity.ConditionMonitor.EquipmentStateMonitorActivity;
import com.hy.onlinemonitor.view.Adapter.StateMonitoring.BatteryChargeCurrentRecyclerAdapter;
import com.hy.onlinemonitor.view.Adapter.StateMonitoring.BatteryChargeCurrentTwoRecyclerAdapter;
import com.hy.onlinemonitor.view.Adapter.StateMonitoring.BatteryChargeSwitchRecyclerAdapter;
import com.hy.onlinemonitor.view.Adapter.StateMonitoring.BatteryChargeSwitchTwoRecyclerAdapter;
import com.hy.onlinemonitor.view.Adapter.StateMonitoring.BatteryOutputCurrentRecyclerAdapter;
import com.hy.onlinemonitor.view.Adapter.StateMonitoring.BatteryRemainingCapacityRecyclerAdapter;
import com.hy.onlinemonitor.view.Adapter.StateMonitoring.BatteryVoltageRecyclerAdapter;
import com.hy.onlinemonitor.view.Adapter.StateMonitoring.DVRStateRecyclerAdapter;
import com.hy.onlinemonitor.view.Adapter.StateMonitoring.DeviceInformationRecyclerAdapter;
import com.hy.onlinemonitor.view.Adapter.StateMonitoring.DeviceStateRecyclerAdapter;
import com.hy.onlinemonitor.view.Adapter.StateMonitoring.SolarPanelVoltageRecyclerAdapter;
import com.hy.onlinemonitor.view.Adapter.StateMonitoring.SolarPanelVoltageTowRecyclerAdapter;
import com.hy.onlinemonitor.view.Adapter.StateMonitoring.UploadDataInformationRecyclerAdapter;

import java.util.ArrayList;

public class EquipmentStateMonitorFragment extends StateMonitorBaseFragment {
    private static final String TAG = "状态监测Fragment";
    private static Context mContext;

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
            case 5://电池充电电流2
                mAdapter = new BatteryChargeCurrentTwoRecyclerAdapter(mContext, new ArrayList<OnlineDeviceState>());
                break;
            case 6://电池剩余电量
                mAdapter = new BatteryRemainingCapacityRecyclerAdapter(mContext, new ArrayList<OnlineDeviceState>());
                break;
            case 7://电池输出电流
                mAdapter = new BatteryOutputCurrentRecyclerAdapter(mContext, new ArrayList<OnlineDeviceState>());
                break;
            case 8://太阳能板电压
                mAdapter = new SolarPanelVoltageRecyclerAdapter(mContext, new ArrayList<OnlineDeviceState>());
                break;
            case 9://太阳能板电压2
                mAdapter = new SolarPanelVoltageTowRecyclerAdapter(mContext, new ArrayList<OnlineDeviceState>());
                break;
            case 10://上传数据信息
                mAdapter = new UploadDataInformationRecyclerAdapter(mContext, new ArrayList<OnlineDeviceState>());
                break;
            case 11://电池充电开关
                mAdapter = new BatteryChargeSwitchRecyclerAdapter(mContext, new ArrayList<OnlineDeviceState>());
                break;
            case 12://电池充电开关2
                mAdapter = new BatteryChargeSwitchTwoRecyclerAdapter(mContext, new ArrayList<OnlineDeviceState>());
                break;
            default:
                break;
        }
    }

    protected void doRefresh() {//刷新数据
        pageNum = 1;
        mAdapter.getEquipmentInformatics().clear();
        equipmentStateMonitorPresenter.loadOnlineDeviceState(mContext, userId, lineSn, pageNum);
    }

    @Override
    protected void initializePresenter() {
        equipmentStateMonitorPresenter = new EquipmentStateMonitorPresenter(mContext);
        equipmentStateMonitorPresenter.setView((EquipmentStateMonitorActivity) getActivity());
        equipmentStateMonitorPresenter.setEquipmentStateMonitorFragment(this);
        getAdapter();
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
                //TODO 1.显示错误界面
            }
        }
    }
}
