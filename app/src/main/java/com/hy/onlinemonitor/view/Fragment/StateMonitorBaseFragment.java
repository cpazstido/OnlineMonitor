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

import com.baoyz.widget.PullRefreshLayout;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.OnlineDeviceStatePage;
import com.hy.onlinemonitor.presenter.EquipmentStateMonitorPresenter;
import com.hy.onlinemonitor.utile.ShowUtile;
import com.hy.onlinemonitor.view.Adapter.StateMonitoring.CMBaseAdapter;

/**
 * Created by 24363 on 2015/10/16.
 */
public abstract class StateMonitorBaseFragment extends Fragment {

    private static final String TAG = "状态监测BaseFragment";
    protected CMBaseAdapter mAdapter;
    protected RecyclerView rvRecyclerviewData;
    protected PullRefreshLayout swipeRefreshLayout;
    protected EquipmentStateMonitorPresenter equipmentStateMonitorPresenter;
    protected Bundle bundle;
    protected int lineSn = 0;
    protected int userId = 0;
    protected int pageNum = 1;
    public OnlineDeviceStatePage onlineDeviceStatePage;
    public boolean isLoadingMore =false;
    private int lastVisibleItem;
    private LinearLayoutManager layoutManager;
    protected boolean isHaveData = false;

    public void setLineSn(int lineSn) {
        this.lineSn = lineSn;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        if (bundle != null) {
            lineSn = bundle.getInt("lineSn");
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

        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        rvRecyclerviewData.setLayoutManager(layoutManager);
        rvRecyclerviewData.setHasFixedSize(true);
        rvRecyclerviewData.setAdapter(mAdapter);
        rvRecyclerviewData.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView");
        return inflater.inflate(R.layout.fragment_state_monitoring, container, false);
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

}
