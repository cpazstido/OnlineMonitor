package com.hy.onlinemonitor.view.Activity.SystemManagement;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.hy.onlinemonitor.R;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.ArrayList;
import java.util.List;

public class PoleManageActivity extends SMBaseActivity{
    private AndroidTreeView tView;
    private TreeNode root;

    @Override
    protected void initTitle() {
        toolbar.setTitle(R.string.system_management);
        toolbar.setSubtitle(R.string.tower_management);
    }

    @Override
    protected void initViewDisplay() {
        choiceBtn.setVisibility(View.VISIBLE);
        choiceBtn.setText(R.string.line_choice);

        choiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//获取数据,并创建树形菜单对话框
                MaterialDialog dialog = new MaterialDialog.Builder(PoleManageActivity.this)
                        .title(R.string.line_choice)
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
                                List<String> choicedLine = new ArrayList<>();
                                List<TreeNode> treeNode = tView.getSelected();
                                for (TreeNode treeNode1 : treeNode) {
                                    if (treeNode1.getParent() != root) {
                                        int baseId = treeNode1.getParent().getId();//这取到的是某条线路的位置
                                        int lineId = treeNode1.getId();
//                                        String treeLineName = mCompanyList.get(baseId).getLineList().get(lineId).getLineName();//得到线路名
//                                        choicedLine.add(treeLineName);
//                                        Log.e("id", treeLineName);//这取到的是某个具体杆塔在list内的位置
                                    }
                                }

                                //这里网络请求,通知adapter改变;
//                                mList.add(mCompanyList.get(0).getLineList().get(0).getTowerList().get(0));
//                                mList.add(mCompanyList.get(0).getLineList().get(0).getTowerList().get(1));

//                                mAdapter.notifyDataSetChanged();

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
                LinearLayout ll = (LinearLayout) dialog.getCustomView().findViewById(R.id.dialog_tree_show);

                root = TreeNode.root();
    /*            initMoni();

                for (CompanyInformation companyInformation : mCompanyList) {

                    TreeNode companyTree = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_earth, companyInformation.getCompanyName())).setViewHolder(new SelectableHeaderHolder(TowerManageActivity.this));
                    for (LineInofrmation lineInofrmation : companyInformation.getLineList()) {
                        TreeNode lineTree = new TreeNode(lineInofrmation.getLineName()).setViewHolder(new SelectableItemHolder(TowerManageActivity.this));
                        companyTree.addChild(lineTree);
                    }
                    companyTree.setSelectable(false);
                    root.addChild(companyTree);
                }

                tView = new AndroidTreeView(TowerManageActivity.this, root);

                tView.setDefaultAnimation(true);
                tView.setSelectionModeEnabled(false);
                ll.addView(tView.getView());

                dialog.show();
                */
            }
        });

    }

    @Override
    protected void initRvAdapter() {

    }

    @Override
    protected void initRvData() {

    }

    @Override
    protected void menuDataLoad() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public Context getContext() {
        return PoleManageActivity.this;
    }
}
