package com.hy.onlinemonitor.view.Activity.SystemManagement;

import android.content.Context;
import android.content.res.Resources;
import android.text.InputType;
import android.util.Log;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.daimajia.swipe.util.Attributes;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.Privilege;
import com.hy.onlinemonitor.bean.Role;
import com.hy.onlinemonitor.bean.RolePage;
import com.hy.onlinemonitor.data.PrivilegeData;
import com.hy.onlinemonitor.presenter.SMJurisdictionPresenter;
import com.hy.onlinemonitor.utile.TransformationUtils;
import com.hy.onlinemonitor.view.Adapter.SMJurisdictionRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class JurisdictionManageActivity extends SMBaseActivity {
    private SMJurisdictionPresenter smJurisdictionPresenter;
    private SMJurisdictionRecyclerAdapter mAdapter;
    private MaterialDialog dialog;
    private RolePage rolePage;
    private List<Privilege> privileges;
    private List<Privilege> ownPrivileges;
    private int roleSn;

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
        mAdapter = new SMJurisdictionRecyclerAdapter(JurisdictionManageActivity.this, new ArrayList<Role>());
        mAdapter.setChangeJurisdictionClickListener(changeJurisdictionClickListener);
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
                        smJurisdictionPresenter.addRole(dialog.getInputEditText().getText().toString());
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
        errorMessageTv.setText(message);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smJurisdictionPresenter.loadRole(getUser().getUserId());
            }
        });
    }

    @Override
    public Context getContext() {
        return null;
    }

    public void renderRolePage(RolePage rolePage) {
        if (rolePage != null &&rolePage.getList().size() != 0) {
            this.rolePage = rolePage;
            mAdapter.setRolePage(rolePage.getList());
            mAdapter.setPresenter(smJurisdictionPresenter);
        }else {
            showError(Resources.getSystem().getString(R.string.not_data));
        }
    }

    public void changeJurisdictionDialogShow() {
        Log.e("changeJClickListener", "调用了!!!!!");
        final CharSequence[] allJurisdiction = PrivilegeData.getAllPrivilege(privileges);
        Integer[] selectedIndices = PrivilegeData.getOwnPrivilege(privileges,ownPrivileges);
        dialog = new MaterialDialog.Builder(JurisdictionManageActivity.this)
                .title(R.string.jurisdiction_config)
                .items(allJurisdiction)
                .itemsCallbackMultiChoice(selectedIndices, new MaterialDialog.ListCallbackMultiChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                        StringBuilder str = new StringBuilder();
                        for (int i = 0; i < which.length; i++) {
                            if (i > 0) str.append('\n');
                            str.append(which[i]);
                            str.append(": ");
                            str.append(text[i]);
                        }
                        Log.e("StringBuilder", str.toString());
                        return true; // allow selection
                    }
                })
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onNeutral(MaterialDialog dialog) {
                        if (dialog.getSelectedIndices().length == allJurisdiction.length) {
                            dialog.clearSelectedIndices();
                        } else {
                            int[] a = new int[allJurisdiction.length];
                            for (int i = 0; i < allJurisdiction.length; i++) {
                                a[i] = i;
                            }
                            Integer[] integes = TransformationUtils.getIntegerFromInt(a);

                            dialog.setSelectedIndices(integes);
                        }
                    }

                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        Integer[] a = dialog.getSelectedIndices();
                        List<Integer> snList = new ArrayList<>();
                        for(Integer integer :a){
                            snList.add(privileges.get(integer).getSn());
                        }
                        smJurisdictionPresenter.changeJurisdiction(roleSn, snList);
                        dialog.dismiss();
                        super.onPositive(dialog);

                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        dialog.cancel();
                        super.onNegative(dialog);
                    }
                })
                .alwaysCallMultiChoiceCallback()
                .positiveText(R.string.submit)
                .negativeText(R.string.cancel)
                .neutralText(R.string.check_all)
                .autoDismiss(false)
                .show();
    }

    private SMJurisdictionRecyclerAdapter.changeJurisdictionClickListener changeJurisdictionClickListener =
            new SMJurisdictionRecyclerAdapter.changeJurisdictionClickListener() {
                @Override
                public void onChangeJurisdictionClick(Role role) {
                    roleSn = role.getSn();
                    smJurisdictionPresenter.getOwnJurisdiction(roleSn);
                }
            };

    public void setPrivileges(List<Privilege> privileges) {
        Log.e("tag", "here");
        this.privileges = privileges;
    }

    public void setOwnPrivileges(List<Privilege> privileges) {
        this.ownPrivileges = privileges;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        smJurisdictionPresenter.destroy();
    }
}
