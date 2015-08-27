package com.hy.onlinemonitor.view.Activity.SystemManagement;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.daimajia.swipe.util.Attributes;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.AdministratorInformation;
import com.hy.onlinemonitor.bean.AdministratorPage;
import com.hy.onlinemonitor.bean.CompanyInformation;
import com.hy.onlinemonitor.bean.Role;
import com.hy.onlinemonitor.presenter.SMAdministratorPresenter;
import com.hy.onlinemonitor.view.Adapter.SMAdministratorRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdministratorManageActivity extends SMBaseActivity {

    private List<String> roleNameList;
    private List<String> companyNameList;
    private List<Role> roleList;
    private List<CompanyInformation> companyInformations;
    private MaterialDialog dialog;
    private AdministratorPage administratorPage;
    private SMAdministratorRecyclerAdapter mAdapter;
    private SMAdministratorPresenter smAdministratorPresenter;

    public void setRoleNameList(List<Role> roleList) {
        this.roleList = roleList;
        this.roleNameList = new ArrayList<>();
        for (Role role : roleList) {
            this.roleNameList.add(role.getRoleName());
        }
    }

    public void setCompanyNameList(List<CompanyInformation> companyInformations) {
        this.companyInformations = companyInformations;
        this.companyNameList = new ArrayList<>();
        for (CompanyInformation companyInformation : companyInformations) {
            this.companyNameList.add(companyInformation.getCompanyName());
        }
    }

    @Override
    protected void menuDataLoad() {
        menuActionAdd();
    }

    @Override
    public void dialogShow() {
        setSpinner();
        dialog.show();
    }

    public void setSpinner() {
        Spinner companySpinner = (Spinner) dialog.getCustomView().findViewById(R.id.dialog_administrator_company);
        companySpinner.setAdapter(new ArrayAdapter<>(dialog.getContext(), android.R.layout.simple_list_item_1, companyNameList));
        Spinner jurisdictionSpinner = (Spinner) dialog.getCustomView().findViewById(R.id.dialog_administrator_role);
        jurisdictionSpinner.setAdapter(new ArrayAdapter<>(dialog.getContext(), android.R.layout.simple_list_item_1, roleNameList));
    }

    @Override
    public void menuActionAdd() {
        dialog = new MaterialDialog.Builder(AdministratorManageActivity.this)
                .title(R.string.administrator_add)
                .customView(R.layout.dialog_administrator_change, true)
                .positiveText(R.string.submit)
                .negativeText(R.string.cancel)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onAny(MaterialDialog dialog) {
                        super.onAny(dialog);
                    }

                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        String loginName = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_administrator_loginName)).getText().toString();
                        String realName = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_administrator_realName)).getText().toString();
                        String password = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_administrator_password)).getText().toString();
                        String phoneNumber = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_administrator_number)).getText().toString();
                        String isMessage = ((Spinner) dialog.getCustomView().findViewById(R.id.dialog_administrator_message)).getSelectedItem().toString();
                        int companySn = companyInformations.get(((Spinner) dialog.getCustomView().findViewById(R.id.dialog_administrator_company)).getSelectedItemPosition()).getSn();
                        int roleSn = roleList.get(((Spinner) dialog.getCustomView().findViewById(R.id.dialog_administrator_role)).getSelectedItemPosition()).getSn();

                        smAdministratorPresenter.addAdministrator(roleSn, companySn, loginName, realName, password, phoneNumber, isMessage);
                        mAdapter.notifyDataSetChanged();
                        super.onPositive(dialog);
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        super.onNegative(dialog);
                    }

                    @Override
                    public void onNeutral(MaterialDialog dialog) {
                        super.onNeutral(dialog);
                    }
                })
                .build();
        dialogShow();
    }

    @Override
    protected void initTitle() {
        toolbar.setTitle(R.string.system_management);
        toolbar.setSubtitle(R.string.administrator);
    }

    @Override
    protected void initViewDisplay() {

    }

    @Override
    protected void initRvAdapter() {
        mAdapter = new SMAdministratorRecyclerAdapter(AdministratorManageActivity.this, new ArrayList<AdministratorInformation>());
        ((SMAdministratorRecyclerAdapter) mAdapter).setMode(Attributes.Mode.Single);
        smRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initRvData() {
        smAdministratorPresenter = new SMAdministratorPresenter(AdministratorManageActivity.this);
        smAdministratorPresenter.setAdministratorManageActivity(this);
        smAdministratorPresenter.loadData(getUser().getUserId());
        smAdministratorPresenter.loadAdminData(getUser().getUserId());
    }

    public void setList() {
        mAdapter.setPresenter(smAdministratorPresenter);
        mAdapter.setCompanyInformations(this.companyInformations);
        mAdapter.setRoleList(this.roleList);
        mAdapter.setCompanyNameList(this.companyNameList);
        mAdapter.setRoleNameList(this.roleNameList);
        mAdapter.setRoleNameList(this.roleNameList);
    }

    @Override
    public void showLoading() {
        alertDialog.show();
    }

    @Override
    public void hideLoading() {
        alertDialog.cancel();
        Toast.makeText(AdministratorManageActivity.this,"完成",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public Context getContext() {
        return AdministratorManageActivity.this;
    }

    public void renderEquipmentList(AdministratorPage administratorPage) {
        if (administratorPage != null) {
            this.administratorPage = administratorPage;
            this.mAdapter.setAdministratorCollection(administratorPage.getList());
        }
    }
}
