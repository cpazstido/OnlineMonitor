package com.hy.onlinemonitor.view.Activity.Function;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.baoyz.widget.PullRefreshLayout;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.EquipmentInformation;
import com.hy.onlinemonitor.presenter.EquipmentListPresenter;
import com.hy.onlinemonitor.utile.GetLoading;
import com.hy.onlinemonitor.view.Activity.BaseActivity;
import com.hy.onlinemonitor.view.Adapter.EquipmentRecyclerAdapter;
import com.hy.onlinemonitor.view.EquipmentList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wsw on 2015/7/13.
 */
public class EquipmentListActivity extends BaseActivity implements EquipmentList {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.rv_recyclerview_data)
    RecyclerView rvRecyclerviewData;
    @Bind(R.id.swipeRefreshLayout)
    PullRefreshLayout swipeRefreshLayout;
    private EquipmentRecyclerAdapter mAdapter;
    private List<EquipmentInformation> mList;
    private int selectedType;
    private String userName;
    private EquipmentListPresenter equipmentListPresenter;
    private AlertDialog loadingDialog;

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
        this.equipmentListPresenter.initialize(userName,selectedType);
    }

    @Override
    public void renderEquipmentList(Collection<EquipmentInformation> EquipmentInformationCollection) {
        if (EquipmentInformationCollection != null) {
            this.mAdapter.setEquipmentCollection(EquipmentInformationCollection);
        }
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

    }

    @Override
    public Context getContext() {
        return EquipmentListActivity.this;
    }

    @Override
    public void setupUI() {
        loadingDialog = GetLoading.getDialog(EquipmentListActivity.this, "加载数据中");
        selectedType = this.getUser().getSelectionType();
        userName = this.getUser().getUserName();
        initPresenter();
        rvRecyclerviewData = (RecyclerView) findViewById(R.id.rv_recyclerview_data);
        swipeRefreshLayout = (PullRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        rvRecyclerviewData.setLayoutManager(new LinearLayoutManager(this));
        rvRecyclerviewData.setHasFixedSize(true);
        mList = new ArrayList<>();

        mAdapter = new EquipmentRecyclerAdapter(selectedType, EquipmentListActivity.this, mList);
        rvRecyclerviewData.setAdapter(mAdapter);
        /*下拉加载更多*/
        swipeRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mList.add(new EquipmentInformation("罗马电力", "摄像机开电", 0, 0, 0));
                        mAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 4000);
            }
        });
    }

    private void initPresenter() {
        this.equipmentListPresenter = new EquipmentListPresenter(this);
        this.equipmentListPresenter.setView(this);
    }

    @Override
    public void initialize() {
        this.loadEquipmentList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
