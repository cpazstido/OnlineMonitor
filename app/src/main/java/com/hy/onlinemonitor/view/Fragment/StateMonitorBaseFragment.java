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

import java.util.List;

/**
 * Created by 24363 on 2015/10/16.
 */
public abstract class StateMonitorBaseFragment extends Fragment {

    protected int lineSn = 0;
    private static final String TAG = "状态监测BaseFragment";
    protected RecyclerView.Adapter mAdapter;
    RecyclerView rvRecyclerviewData;
    PullRefreshLayout swipeRefreshLayout;

    private static boolean isNoInit = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null)
            lineSn = bundle.getInt("lineSn");
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG, "onViewCreated");
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

        rvRecyclerviewData.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        rvRecyclerviewData.setHasFixedSize(true);
        rvRecyclerviewData.setAdapter(mAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView");
        return inflater.inflate(R.layout.fragment_state_monitoring, container, false);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && isNoInit) {
//            Log.e(TAG,"setUserVisibleHint"+getActivity().getPackageName());
            isNoInit = false;
            this.initializePresenter();
            this.loadData();
        }
        super.setUserVisibleHint(isVisibleToUser);

    }

    /**
     * 加载数据,在变为可见时调用
     */
    protected abstract void loadData();

    /**
     * 创建adapter
     *
     */
    protected abstract void getAdapter();

    /**
     * 下拉刷新时调用
     */
    protected abstract void doRefresh();

    /**
     * @param list 接受到的数据
     * 设置数据
     */
    protected abstract void setListData(List list);

    /**
     * 初始化Presenter
     */
    protected abstract void initializePresenter();

}
