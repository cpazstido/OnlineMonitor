package com.hy.onlinemonitor.view.Activity.ConditionMonitor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
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
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.EquipmentInformation;
import com.hy.onlinemonitor.presenter.ConditionMonitoringPresenter;
import com.hy.onlinemonitor.utile.GetLoading;
import com.hy.onlinemonitor.utile.ShowUtile;
import com.hy.onlinemonitor.utile.TransformationUtils;
import com.hy.onlinemonitor.view.Activity.BaseActivity;
import com.hy.onlinemonitor.view.Fragment.EquipmentStateMonitorFragment;
import com.hy.onlinemonitor.view.Fragment.MonitoringStateFragment;
import com.hy.onlinemonitor.view.LoadDataView;
import com.hy.onlinemonitor.view.ViewHolder.IconTreeItemHolder;
import com.hy.onlinemonitor.view.ViewHolder.SelectableHeaderHolder;
import com.hy.onlinemonitor.view.ViewHolder.SelectableItemHolder;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.rey.material.widget.Button;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeMap;

import butterknife.Bind;
import butterknife.ButterKnife;
//监测状态
public class MonitoringStateAcitvity extends BaseActivity implements LoadDataView, DatePickerDialog.OnDateSetListener {
    private static final String TAG = "MonitoringStateAcitvity";

    public static void startView(Context mContext) {
        mContext.startActivity(new Intent(mContext, MonitoringStateAcitvity.class));
    }

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
    @Bind(R.id.ll_background)
    LinearLayout LlBackGround;
    @Bind(R.id.choice_equipment)
    Button choiceEquipment;
    @Bind(R.id.device_status_title)
    SmartTabLayout deviceStatusTitle;
    @Bind(R.id.pager)
    ViewPager pager;

    private int selectedType = -9;//设置初始值为负,代表为不选择任何项目
    private TreeNode root, towerTree, lineTree;
    private AndroidTreeView tView;
    private TreeMap<String, EquipmentStateMonitorFragment> fragmentTreeMap = new TreeMap<>();
    private boolean timeFlag; //判断是开始按钮还是结束按钮
    private List<EquipmentInformation> equipmentList = new ArrayList<>();
    private List<String> equipmentNameList = new ArrayList<>();
    private AlertDialog loadingDialog;
    private ConditionMonitoringPresenter conditionMonitoringPresenter;
    private HashMap<String, String> deviceSnHashMap = new HashMap<>();
    private String[] typeAutoPlaneConfig;

    public void setEquipmentList(Collection equipmentLists) {
        Iterator<EquipmentInformation> it = equipmentLists.iterator();
        while (it.hasNext()) {
            EquipmentInformation equipmentInformation = it.next();
            this.equipmentList.add(equipmentInformation);
            this.equipmentNameList.add(equipmentInformation.getLineName() + "--" + equipmentInformation.getPoleName() + "--" + TransformationUtils.getDeviceNameLastSix(equipmentInformation.getEquipmnetName()));
        }
    }

    @Override
    protected Toolbar getToolbar() {
        toolbar.setTitle("状态监测");
        toolbar.setSubtitle("监测状态");
        return toolbar;
    }

    @Override
    protected void setOwnContentView() {
        setContentView(R.layout.activity_monitoring_state);
        ButterKnife.bind(this);
    }

    public void setupUI() {
        selectedType = getUser().getSelectionType();
        loadingDialog = GetLoading.getDialog(MonitoringStateAcitvity.this, "加载数据中");
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
        initPresenter();
        //开始加载第一页设备列表
        conditionMonitoringPresenter.getEquipmentList(getUser().getUserId(), selectedType, 1);
    }

    private void initPresenter() {
        this.conditionMonitoringPresenter = new ConditionMonitoringPresenter(this);
        this.conditionMonitoringPresenter.setView(this);
    }

    public void initialize() {
        choiceEquipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "equipmentNameList.size->" + equipmentNameList.size());
                if (equipmentList != null && equipmentList.size() != 0) {
                    LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
                    for (EquipmentInformation equipmentInformation : equipmentList) {
                        linkedHashSet.add(equipmentInformation.getLineName());
                    }
                    Iterator it = linkedHashSet.iterator();
                    List<String> lineList = new ArrayList<>();
                    while (it.hasNext()) {
                        String lineStr = it.next().toString();
                        lineList.add(lineStr);
                    }
                    final MaterialDialog dialog = new MaterialDialog.Builder(getContext())
                            .title(R.string.equipment_choice)
                            .customView(R.layout.dialog_tree_choice, true)
                            .positiveText(R.string.cancel)
                            .build();
                    LinearLayout ll = (LinearLayout) dialog.getCustomView().findViewById(R.id.dialog_tree_show);

                    root = TreeNode.root();
                    for (String lineName : lineList) {
                        lineTree = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_earth, lineName)).setViewHolder((new SelectableHeaderHolder(getContext())));

                        for (EquipmentInformation equipmentInformation : equipmentList) {
                            if (lineName.equals(equipmentInformation.getLineName())) {
                                final String realShowStr = equipmentInformation.getPoleName() + "--" + equipmentInformation.getEquipmnetName();
                                towerTree = new TreeNode(realShowStr).setViewHolder(new SelectableItemHolder(getContext()));
                                deviceSnHashMap.put(realShowStr, equipmentInformation.getSn() + "");
                                towerTree.setClickListener(new TreeNode.TreeNodeClickListener() {
                                    @Override
                                    public void onClick(TreeNode node, Object value) {
                                        dialog.cancel();
                                        choiceEquipment.setText(realShowStr);
                                    }
                                });
                                lineTree.addChild(towerTree);
                            }
                        }
                        root.addChild(lineTree);
                    }

                    tView = new AndroidTreeView(getContext(), root);
                    tView.setDefaultAnimation(true);
                    tView.setSelectionModeEnabled(false);
                    ll.addView(tView.getView());
                    dialog.show();
                } else {
                    ShowUtile.snackBarShow(getRootView(), "设备列表为空");
                }
            }
        });
    }

    @Override
    public void showLoading() {
        if (!loadingDialog.isShowing())
            loadingDialog.show();
    }

    @Override
    public void hideLoading() {
        loadingDialog.cancel();
    }

    @Override
    public void showError(String message) {
        deviceStatusTitle.setVisibility(View.GONE);
        pager.removeAllViews();
        timeLlShow.setVisibility(View.VISIBLE);
        choiceBtnLl.setVisibility(View.VISIBLE);
        ShowUtile.snackBarShow(getRootView(),message);
    }

    @Override
    public Context getContext() {
        return MonitoringStateAcitvity.this;
    }

    @Override
    public View getRootView() {
        return LlBackGround;
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
                loadDataBySn();
                break;
            case R.id.action_change://切换设备
                if (choiceBtnLl.getVisibility() == View.GONE)
                    choiceBtnLl.setVisibility(View.VISIBLE);
                else if (choiceBtnLl.getVisibility() == View.VISIBLE)
                    choiceBtnLl.setVisibility(View.GONE);
        }
        return true;
    }

    private void loadDataBySn() {
        pager.removeAllViews();
        if ("选择设备".equals(choiceEquipment.getText().toString())) {
            timeLlShow.setVisibility(View.VISIBLE);
            choiceBtnLl.setVisibility(View.VISIBLE);
            ShowUtile.snackBarShow(getRootView(), "请选择设备");
        } else {
            String deviceSn = deviceSnHashMap.get(choiceEquipment.getText().toString());
            conditionMonitoringPresenter.getConditionMonitoringType(deviceSn);
        }
    }

    private void getTimerPicker() {
        String[] times;
        if (timeFlag) {//开始时间
            times = startTimeBtn.getText().toString().split("-");
        } else {//结束时间
            times = endTimeBtn.getText().toString().split("-");
        }
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                MonitoringStateAcitvity.this,
                Integer.parseInt(times[0]),
                Integer.parseInt(times[1]) - 1,
                Integer.parseInt(times[2])
        );
        dpd.vibrate(true);
        dpd.dismissOnPause(true);
        dpd.setAccentColor(Color.parseColor("#03A9F4"));
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    public void pagerInit(List<String> titles) {
        if (titles.size() == 0) {
            deviceStatusTitle.setVisibility(View.GONE);
            timeLlShow.setVisibility(View.VISIBLE);
            choiceBtnLl.setVisibility(View.VISIBLE);
            Snackbar.make(LlBackGround, "暂无传感器数据", Snackbar.LENGTH_LONG).show();
        } else {
            typeAutoPlaneConfig = titles.toArray(new String[titles.size()]);
            pager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    String deviceSn = deviceSnHashMap.get(choiceEquipment.getText().toString());
                    return MonitoringStateFragment.newInstance(getContext(),typeAutoPlaneConfig[position],getUser().getUserId(),deviceSn,startTimeBtn.getText().toString(),endTimeBtn.getText().toString());
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
            deviceStatusTitle.setVisibility(View.VISIBLE);
            pager.setOffscreenPageLimit(pager.getAdapter().getCount());
        }
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

        assert dt1 != null;
        assert dt2 != null;
        //如果dt1>dt2返回false;
        Log.e(TAG, "" + dt1.getTime());
        Log.e(TAG, "" + dt2.getTime());
        return dt1.getTime() <= dt2.getTime();
    }

}
