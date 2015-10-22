package com.hy.onlinemonitor.view.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;

import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.data.TypeDef;
import com.hy.onlinemonitor.presenter.EquipmentStateMonitorPresenter;
import com.hy.onlinemonitor.utile.GetLoading;
import com.hy.onlinemonitor.view.Component.RecyclerViewFragment;
import com.hy.onlinemonitor.view.LoadDataView;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TestActivity extends BaseActivity implements LoadDataView {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.device_status_title)
    SmartTabLayout deviceStatusTitle;
    @Bind(R.id.pager)
    ViewPager pager;


    private EquipmentStateMonitorPresenter equipmentStateMonitorPresenter;
    private List<String> alarmTitles;
    private String curProject;
    private AlertDialog LoadAlert;
    public static void StartTestView(Context context) {
        Intent intent = new Intent(context, TestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected Toolbar getToolbar() {
        toolbar.setTitle("报警信息");
        return toolbar;
    }

    @Override
    protected void setOwnContentView() {
        setContentView(R.layout.activity_equipment_monitor);
        ButterKnife.bind(this);
    }

    public void setupUI() {
        LoadAlert = GetLoading.getDialog(TestActivity.this, "加载数据中....");
        int selectedType = this.getUser().getSelectionType();
        alarmTitles = new ArrayList<>();
        switch (selectedType) {
            case 0://山火
                curProject = "fire";
                for (String alarmTitle : TypeDef.typeFireAlarmTitle) {
                    alarmTitles.add(alarmTitle);
                }
                break;
            case 1://外破
                curProject = "break";
                for (String alarmTitle : TypeDef.typeBreakAlarmTitle) {
                    alarmTitles.add(alarmTitle);
                }
                break;
            case 2://普通视频
                curProject = "video";
                for (String alarmTitle : TypeDef.typeNormalAlarmTitle) {
                    alarmTitles.add(alarmTitle);
                }
                break;
        }

        pager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return RecyclerViewFragment.newInstance(getContext(),alarmTitles, position, TestActivity.this.getUser().getUserId(), curProject);
            }

            @Override
            public int getCount() {
                return alarmTitles.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return alarmTitles.get(position);
            }

        });
        deviceStatusTitle.setViewPager(pager);
        pager.setOffscreenPageLimit(pager.getAdapter().getCount());
    }

    public void initialize() {

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

    }

    @Override
    public Context getContext() {
        return TestActivity.this;
    }
}