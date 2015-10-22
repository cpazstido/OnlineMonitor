package com.hy.onlinemonitor.view.Activity.Function;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;

import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.data.TypeDef;
import com.hy.onlinemonitor.utile.GetLoading;
import com.hy.onlinemonitor.view.Activity.BaseActivity;
import com.hy.onlinemonitor.view.Component.RecyclerViewFragment;
import com.hy.onlinemonitor.view.LoadDataView;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wsw on 2015/7/13.
 */
public class AlarmInformationActivity extends BaseActivity implements LoadDataView{

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.device_status_title)
    SmartTabLayout deviceStatusTitle;
    @Bind(R.id.pager)
    ViewPager pager;

    private List<String> alarmTitles;
    private String curProject;
    private AlertDialog LoadAlert;

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

    @Override
    public void setupUI() {
        LoadAlert = GetLoading.getDialog(AlarmInformationActivity.this, "加载数据中....");
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
                return RecyclerViewFragment.newInstance(getContext(), alarmTitles, position, AlarmInformationActivity.this.getUser().getUserId(), curProject);
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

    @Override
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
        return AlarmInformationActivity.this;
    }

}