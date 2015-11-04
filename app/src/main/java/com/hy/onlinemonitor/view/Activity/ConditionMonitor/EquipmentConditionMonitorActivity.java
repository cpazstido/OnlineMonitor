package com.hy.onlinemonitor.view.Activity.ConditionMonitor;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.EquipmentInformation;
import com.hy.onlinemonitor.data.StatisticsData;
import com.hy.onlinemonitor.presenter.EquipmentConditionMonitorPresenter;
import com.hy.onlinemonitor.utile.GetLoading;
import com.hy.onlinemonitor.utile.ShowUtile;
import com.hy.onlinemonitor.utile.TransformationUtils;
import com.hy.onlinemonitor.view.Activity.BaseActivity;
import com.hy.onlinemonitor.view.Component.MyMarkerView;
import com.hy.onlinemonitor.view.LoadDataView;
import com.hy.onlinemonitor.view.ViewHolder.IconTreeItemHolder;
import com.hy.onlinemonitor.view.ViewHolder.SelectableHeaderHolder;
import com.hy.onlinemonitor.view.ViewHolder.SelectableItemHolder;
import com.rey.material.widget.Button;
import com.rey.material.widget.RadioButton;
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
import java.util.Map;
import java.util.TreeMap;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EquipmentConditionMonitorActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener, LoadDataView {
    private static String TAG = "EquipmentConditionMonitorActivity";
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.start_time_btn)
    Button startTimeBtn;
    @Bind(R.id.end_time_btn)
    Button endTimeBtn;
    @Bind(R.id.tv_start_time)
    TextView startTimeTv;
    @Bind(R.id.tv_end_time)
    TextView endTimeTv;
    @Bind(R.id.real_time_statistics)
    RadioButton realTimeStatistics;
    @Bind(R.id.daily_statistics)
    RadioButton dailyStatistics;
    @Bind(R.id.month_statistics)
    RadioButton monthStatistics;
    @Bind(R.id.choice_equipment)
    Button choiceEquipment;
    @Bind(R.id.select_statistics)
    Spinner selectStatistics;
    @Bind(R.id.select_statistics_ll)
    LinearLayout selectStatisticsLl;
    @Bind(R.id.statistical_parameters)
    Spinner statisticalParameters;
    @Bind(R.id.statistical_parameters_ll)
    LinearLayout statisticalParametersLl;
    @Bind(R.id.specific_parameters)
    Spinner specificParameters;
    @Bind(R.id.specific_parameters_ll)
    LinearLayout specificParametersLl;
    @Bind(R.id.container)
    FrameLayout container;
    private int selectedType = -9;//设置初始值为负,代表为不选择任何项目
    private static LineChart mChart = null;
    private boolean timeFlag; //判断是开始按钮还是结束按钮
    private EquipmentConditionMonitorPresenter equipmentConditionMonitorPresenter;
    private List<EquipmentInformation> equipmentList = new ArrayList<>();
    private List<String> equipmentNameList = new ArrayList<>();
    private AlertDialog loadingDialog;
    private String statisticByTime = "allTime"; //allTime 实时统计
    private static TreeMap<Float, Float> treeMap = new TreeMap<>();
    private static String selectStatisticsStr = "";//Spinner上的显示的选择统计
    private static String statisticalParametersStr = "";//Spinner上的显示的统计参数
    private static String specificParametersStr = "";//Spinner上的显示的具体参数
    private TreeNode root, towerTree, lineTree;
    private AndroidTreeView tView;
    private HashMap<String, String> deviceIdHashMap = new HashMap<>();
    private HashMap<String, String> deviceSnHashMap = new HashMap<>();

    private boolean projectFlag;//判断是哪一个界面 true为设备状态统计 false为监测状态统计

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
        return toolbar;
    }

    @Override
    protected void setOwnContentView() {
        setContentView(R.layout.activity_state_monitor);
        ButterKnife.bind(this);
    }

    @Override
    public View getRootView() {
        return container;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                searchData();
                break;
        }
        return true;
    }

    private void searchData() {
        if ("选择设备".equals(choiceEquipment.getText().toString())) {
            ShowUtile.snackBarShow(getRootView(), "请选择设备");
        } else {
            String startTime = startTimeBtn.getText().toString(); //开始时间 2015-10-12
            String endTime = endTimeBtn.getText().toString(); //结束时间2015-10-12
            String deviceSn = deviceSnHashMap.get(choiceEquipment.getText().toString());
            String deviceID = deviceIdHashMap.get(choiceEquipment.getText().toString());
            if (projectFlag) {//设备状态统计
                //选择统计Str
                selectStatisticsStr = StatisticsData.selectStatistics[selectStatistics.getSelectedItemPosition()];
                String fieldName = StatisticsData.getValueByStatisticsKey(selectStatisticsStr); //工作温度等对应的
                if (realTimeStatistics.isChecked()) {
                    EquipmentConditionMonitorActivity.this.equipmentConditionMonitorPresenter.queryConditionMonitorData(getUser().getUserId(), fieldName, startTime, startTime, deviceSn, statisticByTime, deviceID);
                } else {
                    EquipmentConditionMonitorActivity.this.equipmentConditionMonitorPresenter.queryConditionMonitorData(getUser().getUserId(), fieldName, startTime, endTime, deviceSn, statisticByTime, deviceID);
                }
            } else {//为监测状态统计
                //统计参数Str
                statisticalParametersStr = statisticalParameters.getSelectedItem().toString();
                //具体参数Str
                specificParametersStr = specificParameters.getSelectedItem().toString();
                String fieldName = StatisticsData.getValueBySpecificAttributesKey(specificParametersStr);
                if (realTimeStatistics.isChecked()) {
                    EquipmentConditionMonitorActivity.this.equipmentConditionMonitorPresenter.queryMonitoringStateData(statisticalParametersStr, getUser().getUserId(), fieldName, startTime, startTime, deviceSn, statisticByTime);
                } else {
                    EquipmentConditionMonitorActivity.this.equipmentConditionMonitorPresenter.queryMonitoringStateData(statisticalParametersStr, getUser().getUserId(), fieldName, startTime, endTime, deviceSn, statisticByTime);
                }
            }
        }
    }

    @Override
    public void setupUI() {
        selectedType = getUser().getSelectionType();
        loadingDialog = GetLoading.getDialog(EquipmentConditionMonitorActivity.this, "加载数据中");
        projectFlag = getIntent().getBooleanExtra("projectFlag", false);

        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    realTimeStatistics.setChecked(realTimeStatistics == buttonView);
                    dailyStatistics.setChecked(dailyStatistics == buttonView);
                    monthStatistics.setChecked(monthStatistics == buttonView);
                    if (realTimeStatistics.isChecked()) {
                        startTimeTv.setText("统计时间");
                        endTimeBtn.setVisibility(View.GONE);
                        endTimeTv.setVisibility(View.GONE);
                        statisticByTime = "allTime";
                    } else if (dailyStatistics.isChecked()) {
                        startTimeTv.setText("开始时间");
                        endTimeBtn.setVisibility(View.VISIBLE);
                        endTimeTv.setVisibility(View.VISIBLE);
                        statisticByTime = "day";
                    } else if (monthStatistics.isChecked()) {
                        startTimeTv.setText("开始时间");
                        endTimeBtn.setVisibility(View.VISIBLE);
                        endTimeTv.setVisibility(View.VISIBLE);
                        statisticByTime = "month";
                    }
                }
            }
        };

        if (projectFlag) {
            toolbar.setSubtitle("设备状态统计");
            startTimeTv.setText("统计时间");
            endTimeBtn.setVisibility(View.GONE);
            endTimeTv.setVisibility(View.GONE);
            selectStatisticsLl.setVisibility(View.VISIBLE);
            statisticalParametersLl.setVisibility(View.GONE);
            specificParametersLl.setVisibility(View.GONE);
        } else {
            toolbar.setSubtitle("监测状态统计");
            startTimeTv.setText("统计时间");
            endTimeBtn.setVisibility(View.GONE);
            endTimeTv.setVisibility(View.GONE);
            selectStatisticsLl.setVisibility(View.GONE);
            statisticalParametersLl.setVisibility(View.VISIBLE);
            specificParametersLl.setVisibility(View.VISIBLE);
        }

        initPresenter();
        realTimeStatistics.setOnCheckedChangeListener(listener);
        dailyStatistics.setOnCheckedChangeListener(listener);
        monthStatistics.setOnCheckedChangeListener(listener);
        //设置按钮为当前时间
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

        //首先先加载第一页
        equipmentConditionMonitorPresenter.getEquipmentList(getUser().getUserId(), selectedType, 1);

    }

    private void initPresenter() {
        this.equipmentConditionMonitorPresenter = new EquipmentConditionMonitorPresenter(this);
        this.equipmentConditionMonitorPresenter.setView(this);
    }

    @Override
    public void initialize() {
        choiceEquipment.setOnClickListener(new View.OnClickListener() {//公司选择按钮
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
                                deviceIdHashMap.put(realShowStr, equipmentInformation.getEquipmnetName());
                                deviceSnHashMap.put(realShowStr, equipmentInformation.getSn()+"");
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

        selectStatistics.setAdapter(new ArrayAdapter<>(EquipmentConditionMonitorActivity.this, android.R.layout.simple_list_item_1, StatisticsData.selectStatistics));
        statisticalParameters.setAdapter(new ArrayAdapter<>(EquipmentConditionMonitorActivity.this, android.R.layout.simple_list_item_1, StatisticsData.statisticalParameters));
        statisticalParameters.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter<String> mAdapter = new ArrayAdapter<>(EquipmentConditionMonitorActivity.this, android.R.layout.simple_list_item_1, StatisticsData.getStatisticalParametersKey(StatisticsData.statisticalParameters[position]));
                Log.e("onItemSelected", StatisticsData.getStatisticalParametersKey(StatisticsData.statisticalParameters[position]) + "");
                specificParameters.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getTimerPicker() {
        String[] times;
        if (timeFlag) {//开始时间
            times = startTimeBtn.getText().toString().split("-");
        } else {//结束时间
            times = endTimeBtn.getText().toString().split("-");
        }
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                EquipmentConditionMonitorActivity.this,
                Integer.parseInt(times[0]),
                Integer.parseInt(times[1]) - 1,
                Integer.parseInt(times[2])
        );
        dpd.vibrate(true);
        dpd.dismissOnPause(true);
        dpd.setAccentColor(Color.parseColor("#03A9F4"));
        dpd.show(getFragmentManager(), "Datepickerdialog");
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
        if (mChart != null) {
            mChart.getLineData().clearValues();
            mChart.invalidate();
        }else{
            ShowUtile.snackBarShow(getRootView(),message);
        }
    }

    @Override
    public Context getContext() {
        return EquipmentConditionMonitorActivity.this;
    }

    public void showChart(TreeMap<Float, Float> treeMaps) {
        treeMap = treeMaps;
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new PlaceholderFragment()).commit();

    }

    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_preview_line_chart, container, false);

            mChart = (LineChart) rootView.findViewById(R.id.chart1);

            generateDefaultData(); //获取数据,同时设置图表

            initChart();//初始化图表

            modifyChart();//修改部分参数

            return rootView;
        }

        private void modifyChart() {
            Legend l = mChart.getLegend();

            YAxis rightAxis = mChart.getAxisRight();
            rightAxis.setEnabled(false);

            YAxis leftAxis = mChart.getAxisLeft();
            leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
//            leftAxis.setAxisMaxValue(100f);
//            leftAxis.setAxisMinValue(-20f);
            leftAxis.setStartAtZero(false);
            leftAxis.setYOffset(20f);
            leftAxis.enableGridDashedLine(10f, 10f, 0f);

            // limit lines are drawn behind data (and not on top)
            leftAxis.setDrawLimitLinesBehindData(true);

            l.setForm(Legend.LegendForm.LINE);
            mChart.invalidate();
            mChart.animateX(2500);
        }

        private void initChart() {
            mChart.setOnChartGestureListener(new OnChartGestureListener() {
                @Override
                public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

                }

                @Override
                public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

                }

                @Override
                public void onChartLongPressed(MotionEvent me) {

                }

                @Override
                public void onChartDoubleTapped(MotionEvent me) {

                }

                @Override
                public void onChartSingleTapped(MotionEvent me) {

                }

                @Override
                public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
                }

                @Override
                public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
                }

                @Override
                public void onChartTranslate(MotionEvent me, float dX, float dY) {
                }
            });

            mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                @Override
                public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                }

                @Override
                public void onNothingSelected() {
                }
            });

            mChart.setDrawGridBackground(false);

            // no description text
            mChart.setDescription("");
            mChart.setNoDataTextDescription("暂无数据可显示");

            // enable value highlighting
            mChart.setHighlightEnabled(true);

            // enable touch gestures
            mChart.setTouchEnabled(true);

            // enable scaling and dragging
            mChart.setDragEnabled(true);
            mChart.setScaleEnabled(true);
            // mChart.setScaleXEnabled(true);
            // mChart.setScaleYEnabled(true);
            // if disabled, scaling can be done on x- and y-axis separately
            mChart.setPinchZoom(true);

            MyMarkerView mv = new MyMarkerView(getContext(), R.layout.custom_marker_view);
            mChart.setMarkerView(mv);

        }

        private void generateDefaultData() {
            ArrayList<String> xValues = new ArrayList<>();
            ArrayList<Float> values = new ArrayList<>();
            ArrayList<Entry> yValues = new ArrayList<>();
            Iterator titer = treeMap.entrySet().iterator();

            while (titer.hasNext()) {
                Map.Entry ent = (Map.Entry) titer.next();
                String keyT = ent.getKey().toString();
                float valueT = (float) ent.getValue();
                xValues.add(keyT);//横坐标
                values.add(valueT);//纵坐标的值
            }

            for (int i = 0; i < xValues.size(); i++) {
                yValues.add(new Entry(values.get(i), i));
            }

            // create a dataset and give it a type
            LineDataSet set1 = new LineDataSet(yValues, selectStatisticsStr);
            set1.setLineWidth(1.5f);
            //set Circles is enable
            set1.setDrawCircles(false);

            set1.setColor(getResources().getColor(R.color.primary));
            set1.setCircleSize(4f);

            // create a data object with the datasets
            LineData data = new LineData(xValues, set1);
            // set data
            mChart.setData(data);
        }

    }
}