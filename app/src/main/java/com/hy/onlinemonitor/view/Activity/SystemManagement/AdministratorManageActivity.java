package com.hy.onlinemonitor.view.Activity.SystemManagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.afollestad.materialdialogs.MaterialDialog;
import com.daimajia.swipe.util.Attributes;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.AdminLine;
import com.hy.onlinemonitor.bean.AdminTower;
import com.hy.onlinemonitor.bean.AdministratorInformation;
import com.hy.onlinemonitor.bean.AdministratorPage;
import com.hy.onlinemonitor.bean.CompanyInformation;
import com.hy.onlinemonitor.bean.Role;
import com.hy.onlinemonitor.presenter.SMAdministratorPresenter;
import com.hy.onlinemonitor.utile.ShowUtile;
import com.hy.onlinemonitor.view.Adapter.SMAdministratorRecyclerAdapter;
import com.hy.onlinemonitor.view.ViewHolder.IconTreeItemHolder;
import com.hy.onlinemonitor.view.ViewHolder.SelectableHeaderHolder;
import com.hy.onlinemonitor.view.ViewHolder.SelectableItemHolder;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

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
    private TreeNode root;
    private AndroidTreeView tView;
    private List<AdminLine> adminLines;
    private List<Integer> ownTowerSn;

    public void setOwnTowerSn(List<Integer> ownTowerSn) {
        this.ownTowerSn = ownTowerSn;
    }

    public void setAdminLines(List<AdminLine> adminLines) {
        this.adminLines = adminLines;
    }

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
        mAdapter.setOnTowerManageClickListener(onTowerManageClickListener);
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
        ShowUtile.toastShow(AdministratorManageActivity.this, "完成");
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

    private SMAdministratorRecyclerAdapter.OnTowerManageClickListener onTowerManageClickListener =
            new SMAdministratorRecyclerAdapter.OnTowerManageClickListener() {
                @Override
                public void onTowerManageClicked(AdministratorInformation administratorInformation) {
                    smAdministratorPresenter.loadAllTower(getUser().getUserId(),administratorInformation.getSn());
                }
            };

    public void LineDialogShow(){
        MaterialDialog dialog = new MaterialDialog.Builder(AdministratorManageActivity.this)
                .title(R.string.tower_management)
                .customView(R.layout.dialog_tree_choice, true)
                .positiveText(R.string.submit)
                .negativeText(R.string.cancel)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onAny(MaterialDialog dialog) {
                        super.onAny(dialog);
                    }

                    @Override
                    public void onPositive(MaterialDialog dialog) {
//                        List<TreeNode> treeNode = tView.getSelected();
//                        for (TreeNode treeNode1 : treeNode) {
//                            if (treeNode1.getParent() != root) {
//                                int baseId = treeNode1.getParent().getId();//这取到的是某条线路的位置
//                                String LineName = mTowers.get(baseId).getLineName();//得到线路名
//
//                                int towerId = treeNode1.getId();
//                                String towerName = mTowers.get(baseId).getTowers().get(towerId).getTowerName();//得到杆塔名
//                                Log.e("id", LineName + ":" + towerName);//这取到的是某个具体杆塔在list内的位置
//                            }
//                        }
                        super.onPositive(dialog);
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        super.onNegative(dialog);
                    }
                })
                .build();
        ViewGroup containerView = (ViewGroup) dialog.getCustomView().findViewById(R.id.dialog_tree_show);

        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(AdministratorManageActivity.this).inflate(R.layout.item_check, null);

        CheckBox checkAll = (CheckBox) relativeLayout.findViewById(R.id.check_box);

        checkAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tView.selectAll(true);
                } else {
                    tView.deselectAll();
                }
            }
        });
        root = TreeNode.root();
        for (AdminLine adminLine : adminLines) {
            TreeNode lineTree = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_earth, adminLine.getName())).setViewHolder(new SelectableHeaderHolder(AdministratorManageActivity.this));
            for (AdminTower adminTower : adminLine.getTowers()) {
                TreeNode towerTree = new TreeNode(adminTower.getTowerName()).setViewHolder(new SelectableItemHolder(AdministratorManageActivity.this));
                for(int sn :ownTowerSn){
                    if(sn == adminTower.getSn()){
                        towerTree.setSelected(true);
                    }
                }
                lineTree.addChild(towerTree);
            }
            root.addChild(lineTree);
        }

        tView = new AndroidTreeView(AdministratorManageActivity.this, root);

        tView.setDefaultAnimation(true);
        tView.setSelectionModeEnabled(true);
        containerView.addView(relativeLayout);
        containerView.addView(tView.getView());
        dialog.show();
    }
}
