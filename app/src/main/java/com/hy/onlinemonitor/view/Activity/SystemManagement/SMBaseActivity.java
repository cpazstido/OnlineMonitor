package com.hy.onlinemonitor.view.Activity.SystemManagement;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.utile.GetLoading;
import com.hy.onlinemonitor.view.Activity.BaseActivity;
import com.hy.onlinemonitor.view.LoadDataView;
import com.rey.material.widget.Button;
import com.rey.material.widget.Spinner;

import butterknife.Bind;
import butterknife.ButterKnife;

public abstract class SMBaseActivity extends BaseActivity implements LoadDataView{

    public AlertDialog alertDialog;
    @Bind(R.id.toolbar)
    public Toolbar toolbar;
    @Bind(R.id.sm_rv)
    public RecyclerView smRecyclerView;
    @Bind(R.id.role_name)
    public TextView roleName;
    @Bind(R.id.spinner_choice_company)
    Spinner spinnerChoiceCompany;
    @Bind(R.id.choice_btn)
    Button choiceBtn;

    @Override
    protected void setOwnContentView() {
        setContentView(R.layout.activity_sm);
        ButterKnife.bind(this);
        alertDialog = GetLoading.getDialog(SMBaseActivity.this,"加载数据中....");
        initTitle();
        initViewDisplay();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sm_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                menuDataLoad();
                break;
        }
        return true;
    }

    @Override
    public void setupUI() {
        smRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        smRecyclerView.setHasFixedSize(true);
        this.initRvAdapter();
    }

    @Override
    public void initialize() {
        this.initRvData();
    }

    @Override
    protected Toolbar getToolbar() {
        return toolbar;
    }

    protected abstract void menuActionAdd();//右上添加按钮的监听内部触发函数

    protected abstract void initTitle();//初始化标题

    protected abstract void initViewDisplay();//初始化activity_sm中部分隐藏控件的显示以及数据监听等

    protected abstract void initRvAdapter();//设置smRecyclerView的adapter

    protected abstract void initRvData();//设置数据

    protected abstract void menuDataLoad();

    protected abstract void dialogShow();
}
