package com.hy.onlinemonitor.view.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.Line;
import com.hy.onlinemonitor.utile.ShowUtile;
import com.hy.onlinemonitor.view.Fragment.EquipmentStateMonitorFragment;
import com.hy.onlinemonitor.view.Fragment.TestFragment;
import com.hy.onlinemonitor.view.LoadDataView;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.rey.material.widget.Button;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TestActivity extends BaseActivity implements LoadDataView, DatePickerDialog.OnDateSetListener {
    private static final String TAG = "TestActivity";
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_start_time)
    TextView tvStartTime;
    @Bind(R.id.start_time_btn)
    Button startTimeBtn;
    @Bind(R.id.tv_end_time)
    TextView tvEndTime;
    @Bind(R.id.end_time_btn)
    Button endTimeBtn;
    @Bind(R.id.time_ll_show)
    LinearLayout timeLlShow;
    @Bind(R.id.choice_btn_ll)
    LinearLayout choiceBtnLl;
    @Bind(R.id.choice_equipment)
    Button choiceEquipment;
    @Bind(R.id.device_status_title)
    SmartTabLayout deviceStatusTitle;
    @Bind(R.id.pager)
    ViewPager pager;

    private TreeNode root, towerTree, lineTree;
    private AndroidTreeView tView;
    private List<Line> lines;
    private TreeMap<String, EquipmentStateMonitorFragment> fragmentTreeMap = new TreeMap<>();
    private static boolean flags = false;
    private boolean timeFlag; //判断是开始按钮还是结束按钮

    public static void StartTestView(Context context) {
        Intent intent = new Intent(context, TestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    protected void setOwnContentView() {
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
    }

    public void setupUI() {
        Calendar now = Calendar.getInstance();
        startTimeBtn.setText(now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.DAY_OF_MONTH));
        endTimeBtn.setText(now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.DAY_OF_MONTH));

        startTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeFlag = true;
                getTimerPicker();
            }
        });

        endTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeFlag = false;
                getTimerPicker();
            }
        });
    }

    public void initialize() {

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
        return TestActivity.this;
    }

    @Override
    public View getRootView() {
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_monitor_state, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_time://选择时间界面的显示
                if (timeLlShow.getVisibility() == View.GONE)
                    timeLlShow.setVisibility(View.VISIBLE);
                else if (timeLlShow.getVisibility() == View.VISIBLE)
                    timeLlShow.setVisibility(View.GONE);
                break;
            case R.id.action_search://获取数据开始
                timeLlShow.setVisibility(View.GONE);
                choiceBtnLl.setVisibility(View.GONE);
                pagerInit();
                break;
            case R.id.action_change://切换设备
                if (choiceBtnLl.getVisibility() == View.GONE)
                    choiceBtnLl.setVisibility(View.VISIBLE);
                else if (choiceBtnLl.getVisibility() == View.VISIBLE)
                    choiceBtnLl.setVisibility(View.GONE);
        }
        return true;
    }
    private void getTimerPicker() {
        String[] times;
        if (timeFlag) {//开始时间
            times = startTimeBtn.getText().toString().split("-");
        } else {//结束时间
            times = endTimeBtn.getText().toString().split("-");
        }
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                TestActivity.this,
                Integer.parseInt(times[0]),
                Integer.parseInt(times[1]) - 1,
                Integer.parseInt(times[2])
        );
        dpd.vibrate(true);
        dpd.dismissOnPause(true);
        dpd.setAccentColor(Color.parseColor("#03A9F4"));
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    private void pagerInit() {
        final String[] typeAutoPlaneConfig;
        if (flags) {
            flags = !flags;
            typeAutoPlaneConfig = new String[]{"设备参数", "阈值参数"};
        } else {
            flags = !flags;
            typeAutoPlaneConfig = new String[]{"设备统计", "设备状态", "监测统计","设别啊的起","爱上大声地"};
        }

        pager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Log.e("titles", typeAutoPlaneConfig[position]);
                return new TestFragment();
            }

            @Override
            public int getCount() {
                return typeAutoPlaneConfig.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return typeAutoPlaneConfig[position];
            }
        });
        deviceStatusTitle.setViewPager(pager);
        pager.setOffscreenPageLimit(pager.getAdapter().getCount());
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int monthOfYear, int dayOfMonth) {
        int realMonth = monthOfYear + 1;
        if (timeFlag) {//选择开始时间
            if (checkTime(year + "-" + realMonth + "-" + dayOfMonth, endTimeBtn.getText().toString()))
                startTimeBtn.setText(year + "-" + realMonth + "-" + dayOfMonth);
            else
                ShowUtile.snackBarShow(getRootView(), "请选择正确的开始与结束时间");
        } else {//选择结束时间
            if (checkTime(startTimeBtn.getText().toString(), year + "-" + realMonth + "-" + dayOfMonth))
                endTimeBtn.setText(year + "-" + realMonth + "-" + dayOfMonth);
            else
                ShowUtile.snackBarShow(getRootView(), "请选择正确的开始与结束时间");
        }
    }

    private boolean checkTime(String startTime, String endTime) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

        Date dt1 = null;
        Date dt2 = null;

        try {
            dt1 = fmt.parse(startTime);
            dt2 = fmt.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        try {
//            dt1 = DateFormat.getDateInstance().parse(startTime);
//            dt2 = DateFormat.getDateInstance().parse(endTime);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        assert dt1 != null;
        assert dt2 != null;
        //如果dt1>dt2返回false;
        Log.e(TAG, "" + dt1.getTime());
        Log.e(TAG, "" + dt2.getTime());
        return dt1.getTime() <= dt2.getTime();
    }

}