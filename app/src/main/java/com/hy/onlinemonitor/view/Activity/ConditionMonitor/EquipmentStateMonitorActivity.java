package com.hy.onlinemonitor.view.Activity.ConditionMonitor;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.Company;
import com.hy.onlinemonitor.bean.Line;
import com.hy.onlinemonitor.data.TypeDef;
import com.hy.onlinemonitor.presenter.EquipmentStateMonitorPresenter;
import com.hy.onlinemonitor.utile.ShowUtile;
import com.hy.onlinemonitor.view.Activity.BaseActivity;
import com.hy.onlinemonitor.view.Fragment.EquipmentStateMonitorFragment;
import com.hy.onlinemonitor.view.LoadDataView;
import com.hy.onlinemonitor.view.ViewHolder.IconTreeItemHolder;
import com.hy.onlinemonitor.view.ViewHolder.SelectableHeaderHolder;
import com.hy.onlinemonitor.view.ViewHolder.SelectableItemHolder;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EquipmentStateMonitorActivity extends BaseActivity implements LoadDataView {
    private static final String TAG = "设备状态监测Activity";
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.pager)
    ViewPager pager;
    @Bind(R.id.device_status_title)
    SmartTabLayout deviceStatusTitle;

    private TreeMap<String, EquipmentStateMonitorFragment> fragmentTreeMap = new TreeMap<>();
    private AlertDialog LoadAlert;
    private AndroidTreeView tView;
    private TreeNode root;
    private List<Company> companyList;
    private EquipmentStateMonitorPresenter equipmentStateMonitorPresenter;

    public static void startView(Context mContext) {
        mContext.startActivity(new Intent(mContext, EquipmentStateMonitorActivity.class));
    }

    @Override
    protected Toolbar getToolbar() {
        toolbar.setTitle("状态监测");
        toolbar.setSubtitle("设备状态");
        return toolbar;
    }

    @Override
    protected void setOwnContentView() {
        setContentView(R.layout.activity_equipment_monitor);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_change, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_change://切换线路,显示对话框
                final MaterialDialog dialog = new MaterialDialog.Builder(getContext())
                        .title(R.string.line_choice)
                        .customView(R.layout.dialog_tree_choice, true)
                        .negativeText(R.string.cancel)
                        .build();
                LinearLayout ll = (LinearLayout) dialog.getCustomView().findViewById(R.id.dialog_tree_show);
                root = TreeNode.root();
                for (Company company : companyList) {
                    TreeNode companyTree = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_earth, company.getCompanyName())).setViewHolder(new SelectableHeaderHolder(getContext()));
                    for (final Line line : company.getLineList()) {
                        TreeNode lineTree = new TreeNode(line.getLineName()).setViewHolder(new SelectableItemHolder(getContext()));
                        lineTree.setClickListener(new TreeNode.TreeNodeClickListener() {
                            @Override
                            public void onClick(TreeNode treeNode, Object o) {
                                dialog.cancel();
                                int lineSn = line.getLineSn();
//                                EquipmentStateMonitorFragment equipmentStateMonitorFragment = fragmentTreeMap.get(TypeDef.equipmentStatusTitles[pager.getCurrentItem()]);
//                                equipmentStateMonitorFragment.mAdapter.equipmentInformatics.clear();
//                                equipmentStateMonitorFragment.setLineSn(lineSn);
//                                equipmentStateMonitorFragment.loadData();
                                Iterator titer = fragmentTreeMap.entrySet().iterator();
                                while (titer.hasNext()) {
                                    Map.Entry ent = (Map.Entry) titer.next();
                                    EquipmentStateMonitorFragment equipmentStateMonitorFragment = (EquipmentStateMonitorFragment)ent.getValue();
                                    equipmentStateMonitorFragment.mAdapter.equipmentInformatics.clear();
                                    equipmentStateMonitorFragment.setLineSn(lineSn);
                                    equipmentStateMonitorFragment.loadData();
                                }
                            }
                        });
                        companyTree.addChild(lineTree);
                    }
                    companyTree.setSelectable(false);
                    root.addChild(companyTree);
                }
                TreeNode loadAllTreeNode = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_earth, "所有在线设备")).setViewHolder(new SelectableHeaderHolder(getContext()));
                loadAllTreeNode.setClickListener(new TreeNode.TreeNodeClickListener() {
                    @Override
                    public void onClick(TreeNode node, Object value) {
                        dialog.cancel();
                        Log.e(TAG, "加载了全部设备" + "||当前显示的fragment" + pager.getCurrentItem());
//                        EquipmentStateMonitorFragment equipmentStateMonitorFragment = fragmentTreeMap.get(TypeDef.equipmentStatusTitles[pager.getCurrentItem()]);
                        Iterator titer = fragmentTreeMap.entrySet().iterator();
                        while (titer.hasNext()) {
                            Map.Entry ent = (Map.Entry) titer.next();
                            EquipmentStateMonitorFragment equipmentStateMonitorFragment = (EquipmentStateMonitorFragment)ent.getValue();
                            equipmentStateMonitorFragment.mAdapter.equipmentInformatics.clear();
                            equipmentStateMonitorFragment.setLineSn(0);
                            equipmentStateMonitorFragment.loadData();
                        }
                    }
                });
                root.addChild(loadAllTreeNode);
                tView = new AndroidTreeView(getContext(), root);
                tView.setDefaultAnimation(true);
                tView.setSelectionModeEnabled(false);
                ll.addView(tView.getView());
                dialog.show();
                break;
        }
        return true;
    }

    @Override
    public void setupUI() {
        LoadAlert = ShowUtile.getDialog(this, "加载数据中");
        pager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Log.e("titles", TypeDef.equipmentStatusTitles[position]);
                EquipmentStateMonitorFragment fragment = EquipmentStateMonitorFragment.newInstance(getContext(), position, getUser().getUserId());
                fragmentTreeMap.put(TypeDef.equipmentStatusTitles[position], fragment);
                return fragment;
            }

            @Override
            public int getCount() {
                return TypeDef.equipmentStatusTitles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return TypeDef.equipmentStatusTitles[position];
            }
        });
        deviceStatusTitle.setViewPager(pager);
        pager.setOffscreenPageLimit(pager.getAdapter().getCount());
    }

    @Override
    public void initialize() {
        equipmentStateMonitorPresenter = new EquipmentStateMonitorPresenter(getContext());
        equipmentStateMonitorPresenter.setView(this);
        equipmentStateMonitorPresenter.loadAllLine(getUser().getUserId());
    }

    @Override
    public void showLoading() {
        if (!LoadAlert.isShowing())
            LoadAlert.show();
    }

    @Override
    public void hideLoading() {
        if (LoadAlert.isShowing())
            LoadAlert.cancel();
    }

    @Override
    public void showError(String message) {
        ShowUtile.snackBarShow(getRootView(),message);
    }

    @Override
    public Context getContext() {
        return EquipmentStateMonitorActivity.this;
    }

    @Override
    public View getRootView() {
        return pager;
    }

    public void setCompanyList(List<Company> mList) {
        this.companyList = mList;
    }

}
