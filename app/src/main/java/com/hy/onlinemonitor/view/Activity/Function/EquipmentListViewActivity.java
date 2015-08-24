package com.hy.onlinemonitor.view.Activity.Function;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.EquipmentPage;
import com.hy.onlinemonitor.presenter.EquipmentListPresenter;
import com.hy.onlinemonitor.utile.GetLoading;
import com.hy.onlinemonitor.view.Activity.BaseActivity;
import com.hy.onlinemonitor.view.Adapter.EquipmentRecyclerAdapter;
import com.hy.onlinemonitor.view.LoadDataView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EquipmentListViewActivity extends BaseActivity implements LoadDataView {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.rv_recyclerview_data)
    RecyclerView rvRecyclerviewData;
    @Bind(R.id.swipeRefreshLayout)
    PullRefreshLayout swipeRefreshLayout;
    private EquipmentRecyclerAdapter mAdapter;
    private EquipmentPage equipmentPage;
    private int selectedType;
    private int userId;
    private EquipmentListPresenter equipmentListPresenter;
    private AlertDialog loadingDialog;
    private LinearLayoutManager linearLayoutManager;
    private int pageNumber = 0 ;
    private int lastVisibleItem;
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

    private void loadEquipmentList(int pageNumber) {
        this.equipmentListPresenter.initialize(userId,selectedType,pageNumber);
    }
    public void renderEquipmentList(EquipmentPage equipmentPage) {
        if (equipmentPage != null) {
            this.equipmentPage = equipmentPage;
            this.mAdapter.setEquipmentCollection(equipmentPage.getList());
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
        return EquipmentListViewActivity.this;
    }

    @Override
    public void setupUI() {
        loadingDialog = GetLoading.getDialog(EquipmentListViewActivity.this, "加载数据中");
        selectedType = this.getUser().getSelectionType();
        userId = this.getUser().getUserId();
        initPresenter();
        rvRecyclerviewData = (RecyclerView) findViewById(R.id.rv_recyclerview_data);
        swipeRefreshLayout = (PullRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        linearLayoutManager = new LinearLayoutManager(this);
        rvRecyclerviewData.setLayoutManager(linearLayoutManager);
        rvRecyclerviewData.setHasFixedSize(true);
        equipmentPage = new EquipmentPage();

        mAdapter = new EquipmentRecyclerAdapter(selectedType, EquipmentListViewActivity.this, equipmentPage,getUser().getUserId());
        rvRecyclerviewData.setAdapter(mAdapter);

        /*下拉刷新更多*/
        swipeRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 4000);
            }
        });

        rvRecyclerviewData.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount() && mAdapter.getItemCount() >= equipmentPage.getRowCount()) {
                    Log.e("hell","到达底部");
                    Toast.makeText(EquipmentListViewActivity.this,"没有更多数据....",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                int totalItemCount = mAdapter.getItemCount();

//                Log.e("show","lastVisibleItem"+lastVisibleItem+"--totalItemCount"+totalItemCount);
//                Log.e("show","equipmentPage.getRowCount()"+equipmentPage.getRowCount());
                if(lastVisibleItem == totalItemCount -1 && dy > 0 && equipmentPage.getRowCount() >totalItemCount){
                    Toast.makeText(EquipmentListViewActivity.this, "加载更多", Toast.LENGTH_SHORT).show();
                    pageNumber ++;
                    loadEquipmentList(pageNumber);//这里多线程也要手动控制isLoadingMore
                }
            }
        });
    }

    private void initPresenter() {
        this.equipmentListPresenter = new EquipmentListPresenter(this);
        this.equipmentListPresenter.setView(this);
    }

    @Override
    public void initialize() {
        this.pageNumber = 1;
        this.loadEquipmentList(1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
