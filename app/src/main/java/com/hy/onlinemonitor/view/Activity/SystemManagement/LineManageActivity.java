package com.hy.onlinemonitor.view.Activity.SystemManagement;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.daimajia.swipe.util.Attributes;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.Company;
import com.hy.onlinemonitor.bean.Line;
import com.hy.onlinemonitor.bean.LinePage;
import com.hy.onlinemonitor.bean.OwnJurisdiction;
import com.hy.onlinemonitor.presenter.SMLinePresenter;
import com.hy.onlinemonitor.utile.ShowUtile;
import com.hy.onlinemonitor.view.Adapter.SMLineRecyclerAdapter;
import com.rey.material.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class LineManageActivity extends SMBaseActivity {//系统管理-线路管理界面
    private SMLineRecyclerAdapter mAdapter; //RecycleView的适配器
    private SMLinePresenter smLinePresenter;//线路Presenter
    private LinePage linePage;//线路page对象
    private boolean isLoadingMore = false; //是否正在加载中
    private List<Company> companyList; //公司列表
    private int pageNumber = 1; //当前显示页数
    private int lastVisibleItem; //最后一个课件的item的序号
    private int theCompanySn = -1; //公司sn

    //第一步执行
    @Override
    protected void initTitle() {//初始化标题
        toolbar.setTitle(R.string.system_management); //主标题设置
        toolbar.setSubtitle(R.string.line_management); //副标题设置
    }

    //第二步执行
    @Override
    protected void initViewDisplay() {//初始化页面显示
        spinnerChoiceCompany.setVisibility(View.VISIBLE); //设置spinner显示
    }

    //第三部执行
    @Override
    protected void initRvAdapter() {//初始化适配器
        mAdapter = new SMLineRecyclerAdapter(LineManageActivity.this, new ArrayList<Line>());//创建一个适配器,传入context对象以及一个ArrayList
        mAdapter.setMode(Attributes.Mode.Single);//设置适配器模式
        smRecyclerView.setAdapter(mAdapter); //为RecyclerView设置适配器

        smRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {//为RecyclerView添加滚动事件监听
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) { //滚动完成后出发
                super.onScrollStateChanged(recyclerView, newState);
                //判断是否到达底部
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount() && mAdapter.getItemCount() >= linePage.getRowCount()) {
                    Log.e("hell", "到达底部");
                    ShowUtile.toastShow(LineManageActivity.this, "没有更多数据....");//显示
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) { //滚动中监听
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();//得到最后一个可见的item的序号
                int totalItemCount = mAdapter.getItemCount(); //得到当前显示的总个数
//                Log.e("show", "lastVisibleItem" + lastVisibleItem + "--totalItemCount" + totalItemCount+"pageSize"+pageSize);
                //当可见到达总个数-1是,并且page对象总总数大于当前加载了的数目,则进行加载更多
                if (lastVisibleItem >= totalItemCount - 1 && dy > 0 && linePage.getRowCount() > totalItemCount) {
                    if (!isLoadingMore) { //没有处于正在加载中
                        ShowUtile.toastShow(LineManageActivity.this, "加载更多");
                        isLoadingMore = true; //正在加载中..
                        pageNumber++; //页数+1
                        //根据CompanySn来判断是加载所有的还是加载部分
                        switch (theCompanySn) {
                            case 0: //加载所有
                                LineManageActivity.this.smLinePresenter.loadAllLine(getUser().getUserId(), pageNumber);
                                break;
                            default: //加载指定公司的线路
                                LineManageActivity.this.smLinePresenter.loadLine(getUser().getUserId(), theCompanySn, pageNumber);
                                break;
                        }
                    } else {
                        ShowUtile.toastShow(LineManageActivity.this, "正在加载中..");
                    }
                }
            }
        });
    }

    //第四部执行
    @Override
    protected void initRvData() {//初始化数据
        smLinePresenter = new SMLinePresenter(LineManageActivity.this); //创建一个presenter
        smLinePresenter.setLineManageActivity(this); //将当前Activity对象传入到presenter,方便presenter调用Activity中的函数控制界面显示
        smLinePresenter.loadCompany(getUser().getUserId());//加载公司列表
    }

    @Override
    protected void menuDataLoad() { //点击了右上角的添加按钮触发的函数
        if (OwnJurisdiction.haveJurisdiction(29)) {//拥有的权限
            //创建一个对话框,进行线路的添加
            MaterialDialog dialog = new MaterialDialog.Builder(LineManageActivity.this)
                    .title(R.string.line_add) //对话框标题
                    .customView(R.layout.dialog_line_change, true) //对话框使用的自定义界面
                    .positiveText(R.string.submit) //positive按钮文字
                    .negativeText(R.string.cancel)//negative按钮文字
                    .callback(new MaterialDialog.ButtonCallback() { //回调函数
                        @Override
                        public void onPositive(MaterialDialog dialog) {
                            String newName = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_line_name)).getText().toString(); //取得对话框上的线路名
                            String newTrend = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_line_trend)).getText().toString(); //取得对话框上的线路走向
                            String newFinish = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_line_finish)).getText().toString(); ////取得对话框上的重点
                            String newStart = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_line_start)).getText().toString();//取得对话框上的起点
                            String newVoltageLevel = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_line_voltage_level)).getText().toString(); //取得对话框上的电压等级
                            int companySn = companyList.get(((android.widget.Spinner) dialog.getCustomView().findViewById(R.id.dialog_line_company)).getSelectedItemPosition()).getSn(); ////取得对话框上的spinner上的选择,并获取公司sn
                            LineManageActivity.this.smLinePresenter.addLine(companySn, newName, newStart, newFinish, newTrend, newVoltageLevel);//添加线路
                            spinnerChoiceCompany.setSelection(0);//设置spinner的selection
                            super.onPositive(dialog);
                        }

                    })
                    .build();

            android.widget.Spinner companySpinner = (android.widget.Spinner) dialog.getCustomView().findViewById(R.id.dialog_line_company);
            List<String> companyNameList = new ArrayList<>();
            for (Company company : companyList) {
                companyNameList.add(company.getCompanyName()); //取得所有的公司名
            }
            //为spinner设置适配器
            companySpinner.setAdapter(new ArrayAdapter<>(dialog.getContext(), android.R.layout.simple_list_item_1, companyNameList));

            //显示对话框
            dialog.show();
        } else {
            ShowUtile.noJurisdictionToast(LineManageActivity.this);
        }
    }

    @Override
    public void showLoading() {//显示等待对话框
        alertDialog.show();
    }

    @Override
    public void hideLoading() {//隐藏等待对话框
        alertDialog.cancel();
    }

    @Override
    public void showError(String message) {//显示错误信息
        errorMessageLl.setVisibility(View.VISIBLE);
        errorMessageTv.setText(message);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                errorMessageLl.setVisibility(View.GONE);

                smLinePresenter.loadCompany(getUser().getUserId());//加载公司列表
            }
        });
    }

    @Override
    public Context getContext() {
        return LineManageActivity.this;
    }

    public void setCompanyList(List<Company> mList) { //通过presenter调用的函数,设置公司列表
        List<String> list = new ArrayList<>();
        list.add("所有公司");
        for (Company company : mList) {
            list.add(company.getCompanyName());
        }
        companyList = mList;
        mAdapter.setCompanyList(companyList);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(LineManageActivity.this, R.layout.row_spn, list);
        arrayAdapter.setDropDownViewResource(R.layout.row_spn_dropdown);
        spinnerChoiceCompany.setAdapter(arrayAdapter);
        spinnerChoiceCompany.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(Spinner spinner, View view, int i, long l) {
                mAdapter.cleanList(); //先清除所有数据
                pageNumber = 1; //设置加载第一页数据
                if (i == 0) {
                    //代表选中的是全部公司,加载第一页
                    theCompanySn = 0;
                    LineManageActivity.this.smLinePresenter.loadAllLine(getUser().getUserId(), pageNumber);
                } else {
                    //加载特定的公司sn的线路
                    for (Company company : companyList) {
                        String a = spinner.getSelectedItem().toString();
                        String b = company.getCompanyName();
                        if (company.getCompanyName().equals(spinner.getSelectedItem().toString())) {
                            theCompanySn = company.getSn();
                        }
                    }
                    LineManageActivity.this.smLinePresenter.loadLine(getUser().getUserId(), theCompanySn, pageNumber);
                }
            }
        });
        spinnerChoiceCompany.setSelection(0);
    }

    public void firstLoadAll() { //第一次进入时,加载所有的数据
        LineManageActivity.this.smLinePresenter.loadAllLine(getUser().getUserId(), pageNumber);
    }

    public void renderLinePage(LinePage linePage) { //通知数据改变
        if (linePage != null && linePage.getList().size() != 0) {
            this.linePage = linePage;
            mAdapter.setLinePage(linePage.getList());
            mAdapter.setPresenter(smLinePresenter);
        } else {
            showError(Resources.getSystem().getString(R.string.not_data));
        }
    }

    public void setLoading() { //设置加载状态
        isLoadingMore = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        smLinePresenter.destroy();
    }
}
