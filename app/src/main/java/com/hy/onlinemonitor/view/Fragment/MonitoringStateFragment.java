package com.hy.onlinemonitor.view.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.hy.onlinemonitor.bean.AeolianVibration;
import com.hy.onlinemonitor.bean.ConditionMonitoringPage;
import com.hy.onlinemonitor.bean.ConductorSag;
import com.hy.onlinemonitor.bean.ConductorSwingWithWind;
import com.hy.onlinemonitor.bean.IceCoatings;
import com.hy.onlinemonitor.bean.Microclimate;
import com.hy.onlinemonitor.bean.PoleStatus;
import com.hy.onlinemonitor.presenter.ConditionMonitoringPresenter;
import com.hy.onlinemonitor.utile.ShowUtile;
import com.hy.onlinemonitor.view.Activity.ConditionMonitor.MonitoringStateAcitvity;
import com.hy.onlinemonitor.view.Adapter.MonitoringState.AeolianVibrationRecyclerAdapter;
import com.hy.onlinemonitor.view.Adapter.MonitoringState.ConductorSagRecyclerAdapter;
import com.hy.onlinemonitor.view.Adapter.MonitoringState.ConductorSwingWithWindRecyclerAdapter;
import com.hy.onlinemonitor.view.Adapter.MonitoringState.IceCoatingRecyclerAdapter;
import com.hy.onlinemonitor.view.Adapter.MonitoringState.MicroclimateRecyclerAdapter;
import com.hy.onlinemonitor.view.Adapter.MonitoringState.PoleStatusRecyclerAdapter;

import java.util.ArrayList;

/**
 * Created by 24363 on 2015/10/28.
 */
public class MonitoringStateFragment extends StateMonitorBaseFragment {
    private static final String TAG = "监测状态Fragment";
    private static Context mContext;
    private RecyclerView.Adapter adapter;
    private ConditionMonitoringPage conditionMonitoringPage;
    private String title;

    @Override
    protected void initRecyclerView() {
        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        rvRecyclerviewData.setLayoutManager(layoutManager);
        rvRecyclerviewData.setHasFixedSize(true);
        rvRecyclerviewData.setAdapter(adapter);
        rvRecyclerviewData.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        if ((lastVisibleItem + 1) == adapter.getItemCount() && isHaveData) {
                            if (pageNum < conditionMonitoringPage.getTotalPage() && !isLoadingMore) {
                                isLoadingMore = true;
                                pageNum++;
                                loadData();
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
        this.loadData();
    }

    @Override
    protected void loadData() {
        Log.e(TAG, title + "deviceSn" + bundle.getString("deviceSn") + "pageNum" + pageNum);
        switch (title) {
            case "微气象传感器":
                conditionMonitoringPresenter.getMicroclimate(bundle.getString("deviceSn"), pageNum);
                break;
            case "杆塔倾斜传感器":
                conditionMonitoringPresenter.getPoleStatus(bundle.getString("deviceSn"), pageNum);
                break;
            case "微风振动传感器":
                conditionMonitoringPresenter.getAeolianVibration(bundle.getString("deviceSn"), pageNum);
                break;
            case "导线弧垂传感器":
                conditionMonitoringPresenter.getConductorSag(bundle.getString("deviceSn"), pageNum);
                break;
            case "覆冰传感器":
                conditionMonitoringPresenter.getIceCoating(bundle.getString("deviceSn"), pageNum);
                break;
            case "风偏传感器":
                conditionMonitoringPresenter.getConductorSwingWithWind(bundle.getString("deviceSn"), pageNum);
                break;
        }
    }

    @Override
    protected void getAdapter() {
        title = bundle.getString("title");
        if (title != null) {
            switch (title) {
                case "微气象传感器":
                    adapter = new MicroclimateRecyclerAdapter(mContext, new ArrayList<Microclimate>());
                    break;
                case "杆塔倾斜传感器":
                    adapter = new PoleStatusRecyclerAdapter(mContext, new ArrayList<PoleStatus>());
                    break;
                case "微风振动传感器":
                    adapter = new AeolianVibrationRecyclerAdapter(mContext, new ArrayList<AeolianVibration>());
                    break;
                case "导线弧垂传感器":
                    adapter = new ConductorSagRecyclerAdapter(mContext, new ArrayList<ConductorSag>());
                    break;
                case "覆冰传感器":
                    adapter = new IceCoatingRecyclerAdapter(mContext, new ArrayList<IceCoatings>());
                    break;
                case "风偏传感器":
                    adapter = new ConductorSwingWithWindRecyclerAdapter(mContext, new ArrayList<ConductorSwingWithWind>());
                    break;
            }
        }
    }

    @Override
    protected void doRefresh() {
        pageNum = 1;

        switch (title) {
            case "微气象传感器":
                ((MicroclimateRecyclerAdapter) adapter).getLinkedHashSet().clear();
                break;
            case "杆塔倾斜传感器":
                ((PoleStatusRecyclerAdapter) adapter).getLinkedHashSet().clear();
                break;
            case "微风振动传感器":
                ((AeolianVibrationRecyclerAdapter) adapter).getLinkedHashSet().clear();
                break;
            case "导线弧垂传感器":
                ((ConductorSagRecyclerAdapter) adapter).getLinkedHashSet().clear();
                break;
            case "覆冰传感器":
                ((IceCoatingRecyclerAdapter) adapter).getLinkedHashSet().clear();
                break;
            case "风偏传感器":
                ((ConductorSwingWithWindRecyclerAdapter) adapter).getLinkedHashSet().clear();
                break;
        }
        loadData();
    }

    @Override
    protected void initializePresenter() {
        conditionMonitoringPresenter = new ConditionMonitoringPresenter(mContext);
        conditionMonitoringPresenter.setView((MonitoringStateAcitvity) getActivity());
        conditionMonitoringPresenter.setMonitoringStateFragment(this);
        getAdapter();
    }

    public static MonitoringStateFragment newInstance(Context context, String title, int userId, String deviceSn) {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putInt("userId", userId);
        bundle.putString("deviceSn", deviceSn);
        mContext = context;
        MonitoringStateFragment fragment = new MonitoringStateFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public void renderDataList(ConditionMonitoringPage conditionMonitoringPage) {
        isLoadingMore = false;
        if (conditionMonitoringPage != null) {
            if (conditionMonitoringPage.getList().size() != 0) {
                isHaveData = true;
                pageNum = conditionMonitoringPage.getPageNum();
                this.conditionMonitoringPage = conditionMonitoringPage;
                switch (title) {
                    case "微气象传感器":
                        ((MicroclimateRecyclerAdapter) adapter).setCollection(conditionMonitoringPage.getList());
                        break;
                    case "杆塔倾斜传感器":
                        ((PoleStatusRecyclerAdapter) adapter).setCollection(conditionMonitoringPage.getList());
                        break;
                    case "微风振动传感器":
                        ((AeolianVibrationRecyclerAdapter) adapter).setCollection(conditionMonitoringPage.getList());
                        break;
                    case "导线弧垂传感器":
                        ((ConductorSagRecyclerAdapter) adapter).setCollection(conditionMonitoringPage.getList());
                        break;
                    case "覆冰传感器":
                        ((IceCoatingRecyclerAdapter) adapter).setCollection(conditionMonitoringPage.getList());
                        break;
                    case "风偏传感器":
                        ((ConductorSwingWithWindRecyclerAdapter) adapter).setCollection(conditionMonitoringPage.getList());
                        break;
                }
            } else {
                Toast.makeText(mContext, "暂无数据", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
