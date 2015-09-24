package com.hy.onlinemonitor.view.Activity.SystemManagement;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.afollestad.materialdialogs.MaterialDialog;
import com.daimajia.swipe.util.Attributes;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.Company;
import com.hy.onlinemonitor.bean.OwnJurisdiction;
import com.hy.onlinemonitor.presenter.SMCompanyPresenter;
import com.hy.onlinemonitor.utile.ShowUtile;
import com.hy.onlinemonitor.view.Adapter.SMCompanyRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/9/10.
 */
public class CompanyManageActivity extends SMBaseActivity {

    private SMCompanyRecyclerAdapter mAdapter;
    private SMCompanyPresenter smCompanyPresenter;
    private List<Company> parentCompanyList;

    @Override
    protected void initTitle() {
        toolbar.setTitle(R.string.system_management);
        toolbar.setSubtitle(R.string.company);
    }

    @Override
    protected void initViewDisplay() {

    }

    @Override
    protected void initRvAdapter() {
        mAdapter = new SMCompanyRecyclerAdapter(CompanyManageActivity.this, new ArrayList<Company>());//新建适配器
        ((SMCompanyRecyclerAdapter) mAdapter).setMode(Attributes.Mode.Single);//设置适配器的模式
        smRecyclerView.setAdapter(mAdapter);//给RecyclerView设置适配器
    }

    @Override
    protected void initRvData() {//初始化数据
        //创建Presenter,用于与domain层进行交互
        smCompanyPresenter = new SMCompanyPresenter(CompanyManageActivity.this);
        smCompanyPresenter.setCompanyManageActivity(this);
        smCompanyPresenter.loadParentCompany(getUser().getUserId());
    }

    @Override
    protected void menuDataLoad() {//添加按钮时触发的函数
        if (OwnJurisdiction.haveJurisdiction(26)) {//拥有的权限
            MaterialDialog dialog = new MaterialDialog.Builder(CompanyManageActivity.this)
                    .title(R.string.company_add)
                    .customView(R.layout.dialog_company_change, true)
                    .positiveText(R.string.submit)
                    .negativeText(R.string.cancel)
                    .callback(new MaterialDialog.ButtonCallback() {
                        @Override
                        public void onPositive(MaterialDialog dialog) {
                            //触发的函数
                            EditText newName = (EditText) dialog.getCustomView().findViewById(R.id.dialog_company_name);
                            EditText newAddress = (EditText) dialog.getCustomView().findViewById(R.id.dialog_company_address);
                            String name = newName.getText().toString();
                            String address = newAddress.getText().toString();
                            int companySn = parentCompanyList.get(((android.widget.Spinner) dialog.getCustomView().findViewById(R.id.dialog_company_parent)).getSelectedItemPosition()).getSn();
                            smCompanyPresenter.addCompany(name, address, companySn);
                            super.onPositive(dialog);
                        }

                    })
                    .build();
            //companyNameList是一个list
            List<String> companyNameList = new ArrayList<>();
            for (Company company : parentCompanyList) {
                companyNameList.add(company.getCompanyName());
            }

            Spinner companySpinner = (Spinner) dialog.getCustomView().findViewById(R.id.dialog_company_parent);
            companySpinner.setAdapter(new ArrayAdapter<>(dialog.getContext(), android.R.layout.simple_list_item_1, companyNameList));
            dialog.show();
        } else {
            ShowUtile.noJurisdictionToast(CompanyManageActivity.this);
        }
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
        errorMessageLl.setVisibility(View.VISIBLE);
        smRecyclerView.setVisibility(View.GONE);
        errorMessageTv.setText(message);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smCompanyPresenter.loadParentCompany(getUser().getUserId());
            }
        });
    }

    @Override
    public Context getContext() {
        return null;
    }

    public void setCompanyList(List<Company> mList) {
        if (mList != null && mList.size() != 0) {
            errorMessageLl.setVisibility(View.GONE);
            smRecyclerView.setVisibility(View.VISIBLE);
            mAdapter.setCompanyList(mList);
            mAdapter.setPresenter(smCompanyPresenter);
        } else {
            showError(Resources.getSystem().getString(R.string.not_data));
        }
    }

    public void setParentCompanyList(List<Company> mList) {
        if (mList != null) {
            parentCompanyList = mList;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        smCompanyPresenter.destroy();
    }
}
