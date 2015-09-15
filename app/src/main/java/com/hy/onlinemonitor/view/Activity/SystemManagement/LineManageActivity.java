package com.hy.onlinemonitor.view.Activity.SystemManagement;

import android.content.Context;
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
import com.hy.onlinemonitor.presenter.SMLinePresenter;
import com.hy.onlinemonitor.utile.ShowUtile;
import com.hy.onlinemonitor.view.Adapter.SMLineRecyclerAdapter;
import com.rey.material.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 24363 on 2015/9/8.
 */
public class LineManageActivity extends SMBaseActivity {
    private SMLineRecyclerAdapter mAdapter;
    private SMLinePresenter smLinePresenter;
    private LinePage linePage;
    private boolean isLoadingMore = false;
    private List<Company> companyList;
    private List<String> companyNameList;
    private int pageNumber = 1;
    private int lastVisibleItem;
    private int theCompanySn = -1;

    @Override
    protected void initTitle() {
        toolbar.setTitle(R.string.system_management);
        toolbar.setSubtitle(R.string.line_management);
    }

    @Override
    protected void initViewDisplay() {
        spinnerChoiceCompany.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initRvAdapter() {
        mAdapter = new SMLineRecyclerAdapter(LineManageActivity.this, new ArrayList<Line>());
        ((SMLineRecyclerAdapter) mAdapter).setMode(Attributes.Mode.Single);
        smRecyclerView.setAdapter(mAdapter);

        smRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount() && mAdapter.getItemCount() >= linePage.getRowCount()) {
                    Log.e("hell", "到达底部");
                    ShowUtile.toastShow(LineManageActivity.this, "没有更多数据....");

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                int totalItemCount = mAdapter.getItemCount();
                int pageSize = linePage.getPageSize();
                Log.e("show", "lastVisibleItem" + lastVisibleItem + "--totalItemCount" + totalItemCount+"pageSize"+pageSize);
                if (lastVisibleItem >= totalItemCount - 1 && dy > 0 && linePage.getRowCount() > totalItemCount) {
                    if (!isLoadingMore) {
                        ShowUtile.toastShow(LineManageActivity.this, "加载更多");
                        isLoadingMore = true;
                        pageNumber++;
                        //根据pageNumber加载更多
                        switch (theCompanySn) {
                            case 0:
                                LineManageActivity.this.smLinePresenter.loadAllLine(getUser().getUserId(), pageNumber);
                                break;
                            default:
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

    @Override
    protected void initRvData() {
        smLinePresenter = new SMLinePresenter(LineManageActivity.this);
        smLinePresenter.setLineManageActivity(this);
        smLinePresenter.loadCompany(getUser().getUserId());
    }

    @Override
    protected void menuDataLoad() {
        MaterialDialog dialog = new MaterialDialog.Builder(LineManageActivity.this)
                .title(R.string.line_add)
                .customView(R.layout.dialog_line_change, true)
                .positiveText(R.string.submit)
                .negativeText(R.string.cancel)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        String newName = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_line_name)).getText().toString();
                        String newTrend = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_line_trend)).getText().toString();
                        String newFinish = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_line_finish)).getText().toString();
                        String newStart = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_line_start)).getText().toString();
                        String newVoltageLevel = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_line_voltage_level)).getText().toString();
                        int companySn = companyList.get(((android.widget.Spinner) dialog.getCustomView().findViewById(R.id.dialog_line_company)).getSelectedItemPosition()).getSn();
                        LineManageActivity.this.smLinePresenter.addLine(companySn, newName, newStart, newFinish, newTrend, newVoltageLevel);
                        spinnerChoiceCompany.setSelection(0);
                        super.onPositive(dialog);
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        super.onNegative(dialog);
                    }
                })
                .build();

        android.widget.Spinner companySpinner = (android.widget.Spinner) dialog.getCustomView().findViewById(R.id.dialog_line_company);
        companyNameList = new ArrayList<>();
        for (Company company : companyList) {
            companyNameList.add(company.getCompanyName());
        }
        companySpinner.setAdapter(new ArrayAdapter<>(dialog.getContext(), android.R.layout.simple_list_item_1, companyNameList));

        dialog.show();
    }

    @Override
    public void showLoading() {
        alertDialog.show();
    }

    @Override
    public void hideLoading() {
        alertDialog.cancel();
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public Context getContext() {
        return LineManageActivity.this;
    }

    public void setCompanyList(List<Company> mList) {
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

    public void firstLoadAll() {
        LineManageActivity.this.smLinePresenter.loadAllLine(getUser().getUserId(), pageNumber);
    }

    public void renderLinePage(LinePage linePage) {
        if (linePage != null) {
            this.linePage = linePage;
            mAdapter.setLinePage(linePage.getList());
            mAdapter.setPresenter(smLinePresenter);
        }
    }

    public void setLoading() {
        isLoadingMore = false;
    }
}
