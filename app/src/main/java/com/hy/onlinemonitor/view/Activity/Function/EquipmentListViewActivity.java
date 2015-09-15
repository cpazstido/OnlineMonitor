package com.hy.onlinemonitor.view.Activity.Function;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.baoyz.widget.PullRefreshLayout;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.EquipmentInforPage;
import com.hy.onlinemonitor.presenter.EquipmentListPresenter;
import com.hy.onlinemonitor.utile.GetLoading;
import com.hy.onlinemonitor.utile.ShowUtile;
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
    private EquipmentInforPage equipmentInforPage;
    private int selectedType;
    private int userId;
    private EquipmentListPresenter equipmentListPresenter;
    private AlertDialog loadingDialog;
    private LinearLayoutManager linearLayoutManager;
    private int pageNumber = 1 ;
    private int lastVisibleItem;
    private boolean isLoadingMore = false;
    @Override
    protected Toolbar getToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.equipment_list);
        return toolbar;
    }

    @Override
    protected void setOwnContentView() {
        setContentView(R.layout.activity_equipmentlist);
        ButterKnife.bind(this);
    }

    private void loadEquipmentList(int pageNumber) {
        this.equipmentListPresenter.initialize(userId,selectedType,pageNumber);
    }
    public void renderEquipmentList(EquipmentInforPage equipmentInforPage) {
        if (equipmentInforPage != null) {
            this.equipmentInforPage = equipmentInforPage;
            this.mAdapter.setEquipmentCollection(equipmentInforPage.getList());
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
        equipmentInforPage = new EquipmentInforPage();

        mAdapter = new EquipmentRecyclerAdapter(selectedType, EquipmentListViewActivity.this, equipmentInforPage,getUser().getUserId());
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
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount() && mAdapter.getItemCount() >= equipmentInforPage.getRowCount()) {
                    Log.e("hell", "到达底部");
                    ShowUtile.toastShow(EquipmentListViewActivity.this, "没有更多数据....");
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                int totalItemCount = mAdapter.getItemCount();
//                Log.e("show","lastVisibleItem"+lastVisibleItem+"--totalItemCount"+totalItemCount);
                if(lastVisibleItem == totalItemCount -1 && dy > 0 && equipmentInforPage.getRowCount() >totalItemCount){
                    if (!isLoadingMore) {
                        isLoadingMore = true;
                        ShowUtile.toastShow(EquipmentListViewActivity.this, "加载更多");
                        pageNumber++;
                        loadEquipmentList(pageNumber);//这里多线程也要手动控制isLoadingMore
                    }else{
                        ShowUtile.toastShow(EquipmentListViewActivity.this, "正在加载中..");
                    }
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

}
