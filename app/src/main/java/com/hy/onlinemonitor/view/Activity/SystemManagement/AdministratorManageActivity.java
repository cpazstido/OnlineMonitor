package com.hy.onlinemonitor.view.Activity.SystemManagement;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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
import com.hy.onlinemonitor.bean.AdministratorInformation;
import com.hy.onlinemonitor.bean.AdministratorPage;
import com.hy.onlinemonitor.bean.Company;
import com.hy.onlinemonitor.bean.Line;
import com.hy.onlinemonitor.bean.OwnJurisdiction;
import com.hy.onlinemonitor.bean.Pole;
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
    private List<Company> companies;
    private MaterialDialog dialog;
    private AdministratorPage administratorPage;
    private SMAdministratorRecyclerAdapter mAdapter;
    private SMAdministratorPresenter smAdministratorPresenter;
    private AdministratorInformation administratorInformation;
    private TreeNode root, towerTree, lineTree;
    private AndroidTreeView tView;
    private List<Line> lines;
    private List<Integer> ownTowerSn;
    private CheckBox checkAll;
    private SelectableItemHolder itemHolder;
    private SelectableHeaderHolder selectableHeaderHolder;
    private boolean flag = true;

    public void setOwnTowerSn(List<Integer> ownTowerSn) {
        this.ownTowerSn = ownTowerSn;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public void setRoleNameList(List<Role> roleList) {
        this.roleList = roleList;
        this.roleNameList = new ArrayList<>();
        for (Role role : roleList) {
            this.roleNameList.add(role.getRoleName());
        }
    }

    public void setCompanyNameList(List<Company> companies) {
        this.companies = companies;
        this.companyNameList = new ArrayList<>();
        for (Company company : companies) {
            this.companyNameList.add(company.getCompanyName());
        }
    }

    @Override
    protected void menuDataLoad() {
        if (OwnJurisdiction.haveJurisdiction(38)) {//拥有的权限
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
                            int companySn = companies.get(((Spinner) dialog.getCustomView().findViewById(R.id.dialog_administrator_company)).getSelectedItemPosition()).getSn();
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
        } else {
            ShowUtile.noJurisdictionToast(AdministratorManageActivity.this);
        }
    }

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
        mAdapter.setCompanies(this.companies);
        mAdapter.setRoleList(this.roleList);
        mAdapter.setCompanyNameList(this.companyNameList);
        mAdapter.setRoleNameList(this.roleNameList);
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
       ErrorThing(message);
    }

    @Override
    protected void doRefresh() {
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smAdministratorPresenter.loadAdminData(getUser().getUserId());
            }
        });
    }

    @Override
    public Context getContext() {
        return AdministratorManageActivity.this;
    }

    public void renderEquipmentList(AdministratorPage administratorPage) {
        if (administratorPage != null &&administratorPage.getList().size() !=0) {
            errorMessageLl.setVisibility(View.GONE);
            smRecyclerView.setVisibility(View.VISIBLE);
            this.administratorPage = administratorPage;
            this.mAdapter.setAdministratorCollection(administratorPage.getList());
        }else{
            showError(Resources.getSystem().getString(R.string.not_data));
        }
    }

    private SMAdministratorRecyclerAdapter.onTowerManageClickListener onTowerManageClickListener =
            new SMAdministratorRecyclerAdapter.onTowerManageClickListener() {
                @Override
                public void onTowerManageClicked(AdministratorInformation administratorInformation) {
                    AdministratorManageActivity.this.administratorInformation = administratorInformation;
                    if (administratorInformation.getAllPoleSeleceted() != 1) {
                        smAdministratorPresenter.loadAllTower(getUser().getUserId(), administratorInformation.getSn());
                    } else {
                        smAdministratorPresenter.onlyLoadAllTower(getUser().getUserId(), administratorInformation.getSn());
                    }
                }
            };

    private SelectableItemHolder.CheckBoxClick checkBoxClick = new SelectableItemHolder.CheckBoxClick() {
        @Override
        public void checkBoxClick(TreeNode node, SelectableItemHolder selectableItemHolder) {
            flag = false;
            boolean isItemCheckAll = true;
            for (TreeNode treeNode : node.getRoot().getChildren()) {
                boolean itemCheck =true;
                for(TreeNode treeNode1: treeNode.getChildren()){
                    if(!treeNode1.isSelected()){
                        itemCheck =false;
                    }
                }
                treeNode.setSelected(itemCheck);
                if(!treeNode.isSelected()){
                    isItemCheckAll = false;
                }
            }

            checkAll.setChecked(isItemCheckAll);
            flag = true;
        }
    };

    public void LineDialogShow() {
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
                        if (!checkAll.isChecked()) {//非全选时
                            List<Integer> TowerSn = new ArrayList<>();
                            List<TreeNode> treeNode = tView.getSelected();
                            for (TreeNode treeNode1 : treeNode) {
                                if (treeNode1.getParent() != root) {
                                    int baseId = treeNode1.getParent().getId();//这取到的是某条线路的位置
                                    String LineName = lines.get(baseId).getLineName();//得到线路名
                                    int towerId = treeNode1.getId();    //取得杆塔的Id
                                    String towerName = lines.get(baseId).getPoleList().get(towerId).getPoleName();//得到杆塔名
                                    int towerSn = lines.get(baseId).getPoleList().get(towerId).getPoleSn();//得到杆塔sn

                                    Log.e("id", LineName + ":" + towerName);//这取到的是某个具体杆塔在list内的位置
                                    TowerSn.add(towerSn);
                                }
                            }
                            //非全选时参数为0
                            smAdministratorPresenter.changeManageTower(getUser().getUserId(), TowerSn, 0);
                        } else {//全选时,为1
                            ShowUtile.toastShow(AdministratorManageActivity.this, "全选");
                            smAdministratorPresenter.changeManageTower(getUser().getUserId(), null, 1);
                        }
                        super.onPositive(dialog);
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        smAdministratorPresenter.hideViewLoading();
                        super.onNegative(dialog);
                    }
                })
                .build();
        ViewGroup containerView = (ViewGroup) dialog.getCustomView().findViewById(R.id.dialog_tree_show);

        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(AdministratorManageActivity.this).inflate(R.layout.item_check, null);

        checkAll = (CheckBox) relativeLayout.findViewById(R.id.check_box);

        checkAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (flag) {
                    if (isChecked) {
                        tView.selectAll(true);
                    } else {
                        tView.deselectAll();
                    }
                }
            }
        });

        root = TreeNode.root();
        for (Line line : lines) {
            selectableHeaderHolder = new SelectableHeaderHolder(AdministratorManageActivity.this);
            lineTree = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_earth, line.getLineName())).setViewHolder(selectableHeaderHolder);
            for (Pole pole : line.getPoleList()) {
                itemHolder = new SelectableItemHolder(AdministratorManageActivity.this);
                itemHolder.setCheckBoxClick(checkBoxClick);
                towerTree = new TreeNode(pole.getPoleName()).setViewHolder(itemHolder);
                if (administratorInformation.getAllPoleSeleceted() != 1) {
                    for (int sn : ownTowerSn) {
                        if (sn == pole.getPoleSn()) {
                            towerTree.setSelected(true);
                        }
                    }
                }else{
                    lineTree.setSelected(true);
                }
                lineTree.addChild(towerTree);
            }
            root.addChild(lineTree);
        }
        if(administratorInformation.getAllPoleSeleceted() ==1){
            Log.e("tag","全选了");
        }

        tView = new AndroidTreeView(AdministratorManageActivity.this, root);
        tView.setDefaultAnimation(true);
        tView.setSelectionModeEnabled(true);
        containerView.addView(relativeLayout);
        containerView.addView(tView.getView());
        dialog.show();
    }

    public void setAdmin(int allPoleSelected) {
        this.administratorInformation.setAllPoleSeleceted(allPoleSelected);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        smAdministratorPresenter.destroy();
    }
}
