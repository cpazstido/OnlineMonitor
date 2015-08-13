package com.hy.onlinemonitor.view.Activity.Function;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.baoyz.widget.PullRefreshLayout;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.EquipmentAlarmInformation;
import com.hy.onlinemonitor.presenter.EquipmentListPresenter;
import com.hy.onlinemonitor.view.Activity.BaseActivity;
import com.hy.onlinemonitor.view.Adapter.EquipmentRecyclerAdapter;
import com.hy.onlinemonitor.view.EquipmentList;

import java.util.Collection;
import java.util.List;

/**
 * Created by wsw on 2015/7/13.
 */
public class EquipmentListActivity extends BaseActivity implements EquipmentList {

    private PullRefreshLayout swipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<EquipmentAlarmInformation> mList;
    private int selectedType;
    private Toolbar toolbar;
    private EquipmentListPresenter equipmentListPresenter;

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

    private void loadEquipmentList() {
        this.equipmentListPresenter.initialize();
    }

    @Override
    public void renderEquipmentList(Collection<EquipmentAlarmInformation> EquipmentInformationCollection) {

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
        return EquipmentListActivity.this;
    }

    @Override
    public void setupUI() {
        selectedType=this.getUser().getSelectionType();
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_recyclerview_data);
        swipeRefreshLayout = (PullRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new EquipmentRecyclerAdapter(selectedType, EquipmentListActivity.this, mList);
        mRecyclerView.setAdapter(mAdapter);
        /*下拉加载更多*/
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
    public void initialize() {
        this.equipmentListPresenter.setView(this);
        this.loadEquipmentList();
    }
}
