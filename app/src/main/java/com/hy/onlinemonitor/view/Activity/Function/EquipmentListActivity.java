package com.hy.onlinemonitor.view.Activity.Function;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.baoyz.widget.PullRefreshLayout;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.EquipmentAlarmInformation;
import com.hy.onlinemonitor.view.Activity.BaseActivity;
import com.hy.onlinemonitor.view.Adapter.EquipmentRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wsw on 2015/7/13.
 */
public class EquipmentListActivity extends BaseActivity {

    private PullRefreshLayout swipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<EquipmentAlarmInformation> mList;
    private int selectedType;
    private Toolbar toolbar;

    @Override
    public void initAdapter() {
        mAdapter = new EquipmentRecyclerAdapter(selectedType, EquipmentListActivity.this, mList);
        mRecyclerView.setAdapter(mAdapter);

        /*
            下拉加载更多
         */
        swipeRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mList.add(new EquipmentAlarmInformation("罗马电力", "摄像机开电", 0, 0, 0));
                        mAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 4000);
            }
        });
    }

    @Override
    public void initDatas() {
        mList = new ArrayList<>();
        mList.add(new EquipmentAlarmInformation("美国超级电力", "摄像机开电", 0, 1, 1));
        mList.add(new EquipmentAlarmInformation("英国核电力", "摄像机正在开电", 1, 0, 1));
        mList.add(new EquipmentAlarmInformation("火星电力", "摄像机断电", 1, 1, 0));
    }

    @Override
    public void initViews() {

        selectedType=this.getUser().getSelectionType();
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_recyclerview_data);
        swipeRefreshLayout = (PullRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    protected Toolbar getToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.equipment_list);
        return toolbar;
    }


    @Override
    protected void setOwnContentView() {
        setContentView(R.layout.activity_equipmentlist);
    }
}
