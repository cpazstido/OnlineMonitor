package com.hy.onlinemonitor.view.Activity.SystemManagement;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.daimajia.swipe.util.Attributes;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.Company;
import com.hy.onlinemonitor.bean.Line;
import com.hy.onlinemonitor.bean.Pole;
import com.hy.onlinemonitor.bean.PolePage;
import com.hy.onlinemonitor.presenter.SMPolePresenter;
import com.hy.onlinemonitor.view.Adapter.SMTowerRecyclerAdapter;
import com.hy.onlinemonitor.view.ViewHolder.IconTreeItemHolder;
import com.hy.onlinemonitor.view.ViewHolder.SelectableHeaderHolder;
import com.hy.onlinemonitor.view.ViewHolder.SelectableItemHolder;
import com.rey.material.widget.Spinner;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.ArrayList;
import java.util.List;

public class PoleManageActivity extends SMBaseActivity {
    private AndroidTreeView tView;
    private TreeNode root;
    private List<Company> companyList;
    private List<Line> lineList = new ArrayList<>();
    private SMTowerRecyclerAdapter mAdapter;
    private SMPolePresenter smPolePresenter;
    private PolePage polePage;
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
                Log.e("choiceBtn", "调用了");
                final MaterialDialog dialog = new MaterialDialog.Builder(PoleManageActivity.this)
                        .title(R.string.line_choice)
                        .customView(R.layout.dialog_tree_choice, true)
                        .negativeText(R.string.cancel)
                        .build();
                LinearLayout ll = (LinearLayout) dialog.getCustomView().findViewById(R.id.dialog_tree_show);
                root = TreeNode.root();
                for (Company company : companyList) {
                    TreeNode companyTree = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_earth, company.getCompanyName())).setViewHolder(new SelectableHeaderHolder(PoleManageActivity.this));
                    for (final Line line : company.getLineList()) {
                        TreeNode lineTree = new TreeNode(line.getLineName()).setViewHolder(new SelectableItemHolder(PoleManageActivity.this));
                        lineTree.setClickListener(new TreeNode.TreeNodeClickListener() {
                            @Override
                            public void onClick(TreeNode treeNode, Object o) {
                                dialog.cancel();
                                smPolePresenter.getPolePage(line.getLineSn());
                            }
                        });

                        companyTree.addChild(lineTree);
                    }
                    companyTree.setSelectable(false);
                    root.addChild(companyTree);
                }

                tView = new AndroidTreeView(PoleManageActivity.this, root);

                tView.setDefaultAnimation(true);
                tView.setSelectionModeEnabled(false);
                ll.addView(tView.getView());
                dialog.show();
            }
        });

    }

    @Override
    protected void initRvAdapter() {
        mAdapter = new SMTowerRecyclerAdapter(PoleManageActivity.this, new ArrayList<Pole>());
        ((SMTowerRecyclerAdapter) mAdapter).setMode(Attributes.Mode.Single);
        smRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initRvData() {
        smPolePresenter = new SMPolePresenter(PoleManageActivity.this);
        smPolePresenter.setPoleManageActivity(this);
        mAdapter.setPresenter(smPolePresenter);
        smPolePresenter.loadAllLine(getUser().getUserId());
    }

    @Override
    protected void menuDataLoad() {
        MaterialDialog dialog = new MaterialDialog.Builder(PoleManageActivity.this)
                .title(R.string.tower_add)
                .customView(R.layout.dialog_tower_change, true)
                .positiveText(R.string.submit)
                .negativeText(R.string.cancel)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        String name = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_tower_name)).getText().toString();
                        String longitude = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_longitude)).getText().toString();
                        String latitude = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_latitude)).getText().toString();
                        String altitude = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_altitude)).getText().toString();
                        int lineSn = lineList.get(( (Spinner)dialog.getCustomView().findViewById(R.id.spinner_choice_line)).getSelectedItemPosition()).getLineSn();
                        smPolePresenter.addPole(lineSn,name,longitude,latitude,altitude);
                        super.onPositive(dialog);
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        super.onNegative(dialog);
                    }
                })
                .build();

        Spinner spinnerChoiceLine = (Spinner) dialog.getCustomView().findViewById(R.id.spinner_choice_line);
        spinnerChoiceLine.setVisibility(View.VISIBLE);
        List<String> lineNameList = new ArrayList<>();
        for(Line line :lineList){
            lineNameList.add(line.getLineName());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(PoleManageActivity.this, R.layout.row_spn, lineNameList);

        arrayAdapter.setDropDownViewResource(R.layout.row_spn_dropdown);
        spinnerChoiceLine.setAdapter(arrayAdapter);
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
        return PoleManageActivity.this;
    }

    public void setCompanyList(List<Company> companyList) {
        this.companyList = companyList;
        lineList.clear();
        for(Company company :companyList){
            for(Line line:company.getLineList()){
                this.lineList.add(line);
            }
        }
        mAdapter.setLineList(lineList);
    }

    public void renderPoleList(PolePage polePage) {
        if (polePage != null) {
            this.polePage = polePage;
            this.mAdapter.setPoleCollection(polePage.getList());
        }
    }
}
