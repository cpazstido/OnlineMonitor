package com.hy.onlinemonitor.view.Activity.SystemManagement;

import android.content.Context;
import android.text.InputType;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.daimajia.swipe.util.Attributes;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.Role;
import com.hy.onlinemonitor.bean.RolePage;
import com.hy.onlinemonitor.presenter.SMJurisdictionPresenter;
import com.hy.onlinemonitor.view.Adapter.SMJurisdictionRecyclerAdapter;

import java.util.ArrayList;

public class JurisdictionManageActivity extends SMBaseActivity{
    private SMJurisdictionPresenter smJurisdictionPresenter;
    private SMJurisdictionRecyclerAdapter mAdapter;
    private MaterialDialog dialog;
    private RolePage rolePage;
    @Override
    protected void menuActionAdd() {

    }

    @Override
    protected void initTitle() {
        toolbar.setTitle(R.string.system_management);
        toolbar.setSubtitle(R.string.jurisdiction_management);
    }

    @Override
    protected void initViewDisplay() {
        roleName.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initRvAdapter() {
        mAdapter = new SMJurisdictionRecyclerAdapter(JurisdictionManageActivity.this,new ArrayList<Role>(),getUser().getUserId());
        ((SMJurisdictionRecyclerAdapter) mAdapter).setMode(Attributes.Mode.Single);
        smRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initRvData() {
        smJurisdictionPresenter = new SMJurisdictionPresenter(JurisdictionManageActivity.this);
        smJurisdictionPresenter.setJurisdictionManageActivity(this);
        smJurisdictionPresenter.loadRole(getUser().getUserId());
    }

    @Override
    protected void menuDataLoad() {
        dialog = new MaterialDialog.Builder(this)
                .title(R.string.role_add)
                .content(R.string.input_content_role_add)
                .inputType(InputType.TYPE_CLASS_TEXT |
                        InputType.TYPE_TEXT_VARIATION_PERSON_NAME |
                        InputType.TYPE_TEXT_FLAG_CAP_WORDS)
                .positiveText(R.string.submit)
                .negativeText(R.string.cancel)
                .alwaysCallInputCallback() // this forces the callback to be invoked with every input change
                .input(R.string.role_add_hint, 0, false, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        if (input.toString().equalsIgnoreCase("")) {
                            dialog.getActionButton(DialogAction.POSITIVE).setEnabled(false);
                        } else {
                            dialog.getActionButton(DialogAction.POSITIVE).setEnabled(true);
                        }
                    }
                })
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onAny(MaterialDialog dialog) {
                        super.onAny(dialog);
                    }

                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        smJurisdictionPresenter.addRole(getUser().getUserId(),dialog.getInputEditText().getText().toString());
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
                .show();
    }

    @Override
    protected void dialogShow() {

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
        return null;
    }

    public void renderRolePage(RolePage rolePage) {
        if(rolePage != null){
            this.rolePage = rolePage;
            mAdapter.setRolePage(rolePage.getList());
            mAdapter.setPresenter(smJurisdictionPresenter);
        }
    }
}
