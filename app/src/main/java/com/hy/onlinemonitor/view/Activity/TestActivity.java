package com.hy.onlinemonitor.view.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.data.StatisticsData;
import com.hy.onlinemonitor.utile.ShowUtile;
import com.hy.onlinemonitor.view.InitView;
import com.rey.material.widget.Button;
import com.rey.material.widget.RadioButton;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.ViewportChangeListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PreviewLineChartView;


/**
 * Created by Administrator on 2015/7/14.
 */
public class TestActivity extends AppCompatActivity implements InitView, DatePickerDialog.OnDateSetListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.start_time_btn)
    Button startTimeBtn;
    @Bind(R.id.end_time_btn)
    Button endTimeBtn;
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
    @Bind(R.id.select_btn)
    Button selectBtn;
    @Bind(R.id.container)
    FrameLayout container;
    private boolean timeFlag; //判断是开始按钮还是结束按钮
    private boolean projectFlag = true;//判断是哪一个界面 true为设备状态统计 false为监测状态统计
    private String res = "[[1444608002000,32.25],[1444608064000,32.25]," +
            "[1444608093000,32.5],[1444608168000,32.5],[1444608232000,32.25]," +
            "[1444608302000,32.25],[1444608358000,32.5],[1444608421000,32.25]," +
            "[1444608483000,32.25],[1444608513000,32.5],[1444608602000,32.25]," +
            "[1444608664000,32.25],[1444608713000,32.0],[1444608776000,32.5]," +
            "[1444608839000,32.25],[1444608905000,32.5],[1444608968000,32.25]," +
            "[1444608993000,32.0],[1444609053000,32.25],[1444609113000,32.25]," +
            "[1444609202000,32.0],[1444609265000,32.0],[1444609313000,32.0]," +
            "[1444609376000,32.25],[1444609439000,32.25],[1444609502000,32.25]," +
            "[1444609566000,32.0],[1444609629000,32.0],[1444609653000,32.25]," +
            "[1444609713000,31.75],[1444609802000,32.0],[1444609865000,32.0]," +
            "[1444609913000,32.0],[1444609977000,32.0],[1444610040000,32.25]," +
            "[1444610104000,31.75],[1444610133000,32.0],[1444610193000,31.75]," +
            "[1444610253000,32.0],[1444610314000,32.0],[1444610402000,31.75]," +
            "[1444610466000,31.75],[1444610519000,31.75],[1444610582000,32.0]," +
            "[1444610646000,32.0],[1444610710000,32.0],[1444610734000,32.0]," +
            "[1444610794000,31.5],[1444610854000,31.75],[1444610914000,32.0]," +
            "[1444611002000,31.75],[1444611067000,31.75],[1444611123000,31.75]," +
            "[1444611186000,31.75],[1444611230000,31.5],[1444611302000,31.75]," +
            "[1444611350000,31.25],[1444611413000,31.5],[1444611476000,31.75]," +
            "[1444611540000,31.5],[1444611602000,31.5],[1444611669000,31.5]," +
            "[1444611710000,31.5],[1444611770000,31.5],[1444611830000,31.5]," +
            "[1444611901000,31.5],[1444611955000,31.5],[1444612018000,31.5]," +
            "[1444612082000,31.5],[1444612145000,31.5],[1444612208000,31.5]," +
            "[1444612268000,31.5],[1444612310000,31.75],[1444612370000,31.25]," +
            "[1444612432000,31.25],[1444612502000,31.5],[1444612558000,31.5]," +
            "[1444612621000,31.5],[1444612685000,31.5],[1444612748000,31.5]," +
            "[1444612802000,31.5],[1444612868000,31.5],[1444612910000,31.25]," +
            "[1444612970000,31.25],[1444613031000,31.25],[1444613102000,31.25]," +
            "[1444613157000,31.25],[1444613220000,31.25],[1444613286000,31.25]," +
            "[1444613350000,31.5],[1444613402000,31.25],[1444613469000,31.25]," +
            "[1444613510000,31.25],[1444613572000,31.0],[1444613635000,31.5]," +
            "[1444613702000,31.25],[1444613762000,31.0],[1444613825000,31.25]," +
            "[1444613888000,31.5],[1444613930000,31.25],[1444614002000,31.0],[" +
            "1444614069000,31.25],[1444614110000,31.25],[1444614175000,31.0]," +
            "[1444614238000,31.0],[1444614302000,31.25],[1444614365000,31.25]," +
            "[1444614410000,31.0],[1444614470000,31.25],[1444614530000,31.25]," +
            "[1444614602000,30.75],[1444614670000,31.0],[1444614710000,31.0]," +
            "[1444614770000,31.0],[1444614830000,31.25],[1444614902000,31.25]," +
            "[1444614957000,31.0],[1444615019000,31.0],[1444615082000,31.0]," +
            "[1444615145000,31.25],[1444615209000,31.25],[1444615270000,31.25]," +
            "[1444615310000,31.0],[1444615370000,31.0],[1444615430000,31.0]," +
            "[1444615502000,31.0],[1444615556000,31.25],[1444615619000,31.25]" +
            ",[1444615682000,31.5],[1444615745000,31.25],[1444615808000,31.25]," +
            "[1444615870000,31.25],[1444615910000,31.75],[1444615970000,31.5]," +
            "[1444616030000,31.25],[1444616102000,31.25],[1444616155000,31.25]," +
            "[1444616219000,31.5],[1444616284000,31.25],[1444616347000,31.25],[" +
            "1444616411000,31.5],[1444616471000,31.25],[1444616510000,31.25],[1" +
            "444616570000,31.5],[1444616630000,31.5],[1444616701000,31.5],[1444" +
            "616759000,31.25],[1444616822000,31.25],[1444616885000,31.25],[1444" +
            "616948000,31.5],[1444617002000,31.25],[1444617051000,31.5],[144461" +
            "7111000,31.5],[1444617171000,31.25],[1444617231000,31.75],[14446173020" +
            "00,31.5],[1444617361000,31.5],[1444617424000,31.5],[1444617488000,31." +
            "25],[1444617550000,31.5],[1444617602000,31.25],[1444617651000,31.25]," +
            "[1444617711000,31.25],[1444617771000,31.5],[1444617831000,31.5],[1444" +
            "617902000,31.25],[1444617959000,31.25],[1444618025000,31.25],[1444618" +
            "086000,31.25],[1444618147000,31.5],[1444618208000,31.5],[144461825100" +
            "0,31.25],[1444618311000,31.25],[1444618371000,31.25],[1444618431000,3" +
            "1.5],[1444618502000,31.75],[1444618555000,31.0],[1444618618000,31.0]," +
            "[1444618683000,31.25],[1444618748000,31.5],[1444618802000,31.25],[144" +
            "4618851000,31.25],[1444618911000,31.5],[1444618971000,31.25],[1444619" +
            "031000,31.0],[1444619102000,31.25],[1444619161000,31.25],[14446192240" +
            "00,31.5],[1444619286000,31.25],[1444619331000,31.25],[1444619402000,31." +
            "25],[1444619451000,31.25],[1444619511000,31.0],[1444619571000,31.25],[1" +
            "444619639000,31.0],[1444619702000,31.25],[1444619765000,31.0],[14446198" +
            "28000,31.25],[1444619871000,31.25],[1444619932000,30.75],[1444620002000" +
            ",31.25],[1444620052000,31.25],[1444620112000,31.0],[1444620172000,31.0]" +
            ",[1444620238000,31.0],[1444620302000,30.75],[1444620365000,31.0],[14446" +
            "20428000,31.25],[1444620490000,31.0],[1444620532000,31.0],[144462060200" +
            "0,31.25],[1444620652000,31.0],[1444620712000,31.0],[1444620772000,31.25" +
            "],[1444620839000,31.0],[1444620902000,31.0],[1444620965000,31.0],[14446" +
            "21026000,31.0],[1444621072000,31.0],[1444621132000,31.0],[1444621202000" +
            ",31.0],[1444621252000,30.75],[1444621312000,31.0],[1444621372000,30.75]" +
            ",[1444621442000,31.0],[1444621505000,31.0],[1444621568000,31.0],[144462" +
            "1630000,31.25],[1444621672000,31.0],[1444621732000,30.75],[144462180200" +
            "0,30.75],[1444621852000,30.75],[1444621912000,31.0],[1444621981000,31.0" +
            "],[1444622044000,30.75],[1444622107000,30.75],[1444622170000,30.75],[14" +
            "44622213000,30.75],[1444622273000,31.0],[1444622333000,30.75],[14446224" +
            "01000,31.0],[1444622453000,30.75],[1444622513000,31.0],[1444622581000,3" +
            "0.75],[1444622644000,31.0],[1444622707000,30.75],[1444622770000,31.0],[" +
            "1444622813000,30.5],[1444622873000,30.75],[1444622933000,30.75],[144462" +
            "3002000,30.75],[1444623053000,30.75],[1444623113000,30.75],[14446231810" +
            "00,30.75],[1444623243000,31.0],[1444623306000,30.75],[1444623370000,30." +
            "75],[1444623413000,30.75],[1444623473000,30.75],[1444623533000,31.0],[1" +
            "444623602000,30.75],[1444623653000,30.75],[1444623713000,31.0],[1444623" +
            "773000,30.75],[1444623844000,31.0],[1444623910000,30.75],[1444623953000" +
            ",30.75],[1444624013000,30.75],[1444624073000,31.0],[1444624133000,30.5]" +
            ",[1444624202000,31.0],[1444624253000,30.75],[1444624322000,30.75],[1444" +
            "624385000,30.75],[1444624447000,30.75],[1444624502000,30.75],[144462455" +
            "3000,30.5],[1444624613000,30.5],[1444624673000,30.5],[1444624733000,30." +
            "5],[1444624802000,30.5],[1444624854000,30.75],[1444624914000,30.75],[14" +
            "44624989000,31.0],[1444625051000,30.75],[1444625094000,30.75],[14446251" +
            "54000,30.75],[1444625214000,30.75],[1444625274000,30.5],[1444625334000," +
            "30.5],[1444625394000,30.5],[1444625464000,30.25],[1444625527000,30.5],[" +
            "1444625590000,30.5],[1444625634000,30.5],[1444625694000,30.5],[14446257" +
            "55000,30.75],[1444625815000,30.5],[1444625875000,30.5],[1444625935000,3" +
            "0.5],[1444626003000,30.75],[1444626066000,30.75],[1444626131000,30.5],[" +
            "1444626175000,30.5],[1444626235000,30.75],[1444626295000,30.5],[1444626" +
            "355000,30.5],[1444626415000,30.75],[1444626475000,30.25],[1444626545000" +
            ",30.5],[1444626608000,30.5],[1444626671000,30.75],[1444626715000,30.5]," +
            "[1444626775000,30.5],[1444626835000,30.25],[1444626895000,30.5],[144462" +
            "6955000,30.5],[1444627015000,30.25],[1444627075000,30.5],[1444627147000" +
            ",30.25],[1444627209000,30.25],[1444627255000,30.5],[1444627315000,30.25" +
            "],[1444627375000,30.5],[1444627435000,30.25],[1444627495000,30.25],[144" +
            "4627555000,30.25],[1444627615000,30.25],[1444627675000,30.25],[14446277" +
            "35000,30.5],[1444627812000,30.25],[1444627855000,30.25],[1444627915000," +
            "30.5],[1444627976000,30.0],[1444628036000,30.25],[1444628096000,30.0],[" +
            "1444628156000,30.25],[1444628216000,30.25],[1444628286000,30.25],[14446" +
            "28349000,30.5],[1444628411000,30.25],[1444628456000,30.5],[144462851600" +
            "0,30.0],[1444628571000,30.25],[1444628636000,30.25],[1444628696000,30.5" +
            "],[1444628756000,30.0],[1444628816000,30.0],[1444628888000,30.0],[14446" +
            "28936000,30.25],[1444628996000,30.25],[1444629056000,30.25],[1444629116" +
            "000,30.0],[1444629176000,30.25],[1444629236000,30.0],[1444629296000,30." +
            "25],[1444629356000,30.0],[1444629427000,30.25],[1444629490000,30.0],[14" +
            "44629536000,30.0],[1444629596000,30.0],[1444629656000,29.75],[144462971" +
            "6000,30.0],[1444629776000,30.0],[1444629836000,30.25],[1444629896000,30" +
            ".0],[1444629968000,30.25],[1444630032000,30.0],[1444630076000,30.0],[14" +
            "44630136000,30.0],[1444630196000,30.0],[1444630256000,30.0],[1444630316000" +
            ",30.25],[1444630376000,30.0],[1444630436000,29.75],[1444630496000,30.0],[1" +
            "444630568000,30.0],[1444630631000,30.0],[1444630676000,30.25],[14446307360" +
            "00,30.25],[1444630796000,30.25],[1444630856000,30.0],[1444630917000,30.5]," +
            "[1444630977000,30.0],[1444631037000,30.25],[1444631110000,29.75],[14446311" +
            "57000,30.0],[1444631217000,30.0],[1444631277000,30.25],[1444631337000,30.0" +
            "],[1444631397000,30.0],[1444631457000,30.25],[1444631517000,30.0],[1444631" +
            "577000,30.25],[1444631637000,30.25],[1444631712000,30.25],[1444631757000,3" +
            "0.0],[1444631817000,30.25],[1444631877000,29.75],[1444631937000,29.75],[14" +
            "44631997000,29.75],[1444632057000,30.25],[1444632117000,30.0],[1444632190000," +
            "29.75],[1444632237000,30.0],[1444632298000,29.75],[1444632358000,30.0],[1444" +
            "632418000,30.25],[1444632478000,30.0],[1444632538000,29.75],[1444632598000,3" +
            "0.25],[1444632658000,29.75],[1444632731000,30.25],[1444632778000,30.25],[" +
            "1444632838000,30.0],[1444632898000,29.75],[1444632958000,30.25],[1444633018" +
            "000,30.0],[1444633078000,30.25],[1444633138000,30.0],[1444633198000,29.75]," +
            "[1444633258000,30.25],[1444633331000,30.0],[1444633378000,30.0],[1444633438" +
            "000,29.75],[1444633498000,29.75],[1444633558000,30.0],[1444633618000,30.0]," +
            "[1444633678000,30.0],[1444633738000,30.0],[1444633798000,30.25],[144463387" +
            "0000,29.75],[1444633918000,30.25],[1444633978000,30.25],[1444634038000,30.2" +
            "5],[1444634098000,30.0],[1444634158000,30.0],[1444634218000,29.75],[1444634" +
            "278000,30.0],[1444634338000,30.0],[1444634398000,30.0],[1444634458000,30.0" +
            "],[1444634518000,30.0],[1444634578000,30.0],[1444634638000,29.75],[1444634" +
            "698000,30.0],[1444634758000,30.0],[1444634818000,29.75],[1444634878000,29." +
            "75],[1444634952000,29.75],[1444634998000,30.0],[1444635059000,30.0],[14446" +
            "35113000,29.75],[1444635176000,30.0],[1444635239000,30.0],[1444635302000,2" +
            "9.75],[1444635366000,30.0],[1444635429000,29.75],[1444635492000,29.75],[1444635" +
            "509000,29.75],[1444635602000,29.75],[1444635651000,29.75],[1444635713000,30.0]" +
            ",[1444635776000,30.0],[1444635838000,30.0],[1444635902000,30.0],[1444635969000" +
            ",29.75],[1444636003000,30.0],[1444636066000,30.0],[1444636132000,29.5],[144463" +
            "6202000,30.0],[1444636261000,29.75],[1444636325000,29.75],[1444636388000,29.75" +
            "],[1444636451000,29.75],[1444636502000,30.0],[1444636546000,29.75],[1444636611" +
            "000,30.25],[1444636673000,30.0],[1444636740000,29.75],[1444636803000,29.5],[14" +
            "44636866000,29.75],[1444636930000,30.0],[1444636950000,29.75],[1444637026000,2" +
            "9.75],[1444637102000,29.75],[1444637152000,30.25],[1444637215000,30.25],[14446" +
            "37279000,30.0],[1444637342000,30.0],[1444637405000,30.0],[1444637468000,30.25]" +
            ",[1444637532000,30.5],[1444637550000,30.25],[1444637630000,30.25],[14446377020" +
            "00,30.0],[1444637758000,30.25],[1444637821000,30.25],[1444637884000,30.25],[14" +
            "44637948000,30.5],[1444638010000,30.25],[1444638073000,30.25],[1444638091000,30" +
            ".25],[1444638168000,30.5],[1444638231000,30.5],[1444638301000,30.75],[144463835" +
            "7000,30.75],[1444638420000,30.5],[1444638484000,30.5],[1444638547000,30.75],[14" +
            "44638610000,30.25],[1444638631000,30.5],[1444638708000,30.75],[1444638771000,30" +
            ".75],[1444638834000,30.75],[1444638902000,30.75],[1444638961000,31.0],[14446390" +
            "24000,31.25],[1444639087000,31.0],[1444639149000,31.0],[1444639202000,31.25],[1" +
            "444639249000,31.0],[1444639312000,31.25],[1444639376000,31.25],[1444639440000,3" +
            "1.5],[1444639505000,31.0],[1444639568000,31.5],[1444639631000,31.25],[144463966" +
            "7000,31.25],[1444639728000,31.25],[1444639802000,31.5],[1444639855000,31.25],[1" +
            "444639919000,31.25],[1444639981000,31.75],[1444640047000,31.75],[1444640110000," +
            "32.0],[1444640173000,31.75],[1444640208000,31.75],[1444640268000,31.75],[1444640" +
            "332000,31.75],[1444640402000,31.75],[1444640458000,31.75],[1444640522000,32.0]," +
            "[1444640585000,32.0],[1444640648000,32.0],[1444640712000,32.25],[1444640749000," +
            "32.25],[1444640809000,32.25],[1444640869000,32.0],[1444640932000,32.25],[144464" +
            "1002000,32.25],[1444641060000,32.5]]";


    public static void StartTestView(Context context) {
        Intent intent = new Intent(context, TestActivity.class);
        context.startActivity(intent);
    }

    private static final String TAG = "MediaPlayerDemo";

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_state_monitor);
        ButterKnife.bind(this);
        setupUI();
        initialize();
    }

    @Override
    public void setupUI() {
        toolbar.setTitle("状态监测");
        toolbar.setSubtitle("统计状态");

        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    realTimeStatistics.setChecked(realTimeStatistics == buttonView);
                    dailyStatistics.setChecked(dailyStatistics == buttonView);
                    monthStatistics.setChecked(monthStatistics == buttonView);

                }
            }
        };
        if (projectFlag) {//判断是哪一个界面 true为设备状态统计 false为监测状态统计
            selectStatisticsLl.setVisibility(View.VISIBLE);
            statisticalParametersLl.setVisibility(View.GONE);
            specificParametersLl.setVisibility(View.GONE);
        } else {
            selectStatisticsLl.setVisibility(View.GONE);
            statisticalParametersLl.setVisibility(View.VISIBLE);
            specificParametersLl.setVisibility(View.VISIBLE);
        }

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

    }

    @Override
    public void initialize() {
        selectStatistics.setAdapter(new ArrayAdapter<String>(TestActivity.this, android.R.layout.simple_list_item_1, StatisticsData.selectStatistics));
        statisticalParameters.setAdapter(new ArrayAdapter<String>(TestActivity.this, android.R.layout.simple_list_item_1, StatisticsData.statisticalParameters));
        statisticalParameters.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("onItemSelected", "onItemSelected" + position);
                ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(TestActivity.this, android.R.layout.simple_list_item_1, StatisticsData.getStatisticalParametersKey(StatisticsData.statisticalParameters[position]));
                Log.e("onItemSelected", StatisticsData.getStatisticalParametersKey(StatisticsData.statisticalParameters[position]) + "");
                specificParameters.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int width = size.x;
                int height = size.y -100;
                LinearLayout.LayoutParams linearParams =  (LinearLayout.LayoutParams)container.getLayoutParams();
                linearParams.height = height;
                linearParams.width = width;
                container.setLayoutParams(linearParams);

                getSupportFragmentManager().beginTransaction().replace(R.id.container, new PlaceholderFragment()).commit();

                List<String> mLabelsThree = new ArrayList<>();
                List<Double> mValuesThree = new ArrayList<>();
                try {
                    JSONArray jsonArray = new JSONArray(res);
                    Log.e("tag", "length" + jsonArray.length());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONArray jsonArray1 = jsonArray.getJSONArray(i);
                        long f1 = (long) jsonArray1.get(0);
                        Double f2 = (Double) jsonArray1.get(1);
                        Log.e("tag", f1 + ":" + f2);
                        Date date = new Date(f1);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String str = sdf.format(date);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

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

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int monthOfYear, int dayOfMonth) {
        int realMonth = monthOfYear + 1;
        if (timeFlag) {//选择开始时间
            if (checkTime(year + "-" + realMonth + "-" + dayOfMonth, endTimeBtn.getText().toString()))
                startTimeBtn.setText(year + "-" + realMonth + "-" + dayOfMonth);
            else
                ShowUtile.toastShow(TestActivity.this, "请选择正确的开始与结束时间");
        } else {//选择结束时间
            if (checkTime(startTimeBtn.getText().toString(), year + "-" + realMonth + "-" + dayOfMonth))
                endTimeBtn.setText(year + "-" + realMonth + "-" + dayOfMonth);
            else
                ShowUtile.toastShow(TestActivity.this, "请选择正确的开始与结束时间");
        }
    }

    private boolean checkTime(String startTime, String endTime) {

        Date dt1 = null;
        Date dt2 = null;
        try {
            dt1 = DateFormat.getDateInstance().parse(startTime);
            dt2 = DateFormat.getDateInstance().parse(endTime);
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

    public static class PlaceholderFragment extends Fragment {

        private LineChartView chart;
        private PreviewLineChartView previewChart;
        private LineChartData data;
        /**
         * Deep copy of data.
         */
        private LineChartData previewData;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            setHasOptionsMenu(true);
            View rootView = inflater.inflate(R.layout.fragment_preview_line_chart, container, false);

            chart = (LineChartView) rootView.findViewById(R.id.chart);
            previewChart = (PreviewLineChartView) rootView.findViewById(R.id.chart_preview);

            // Generate data for previewed chart and copy of that data for preview chart.
            generateDefaultData();

            chart.setLineChartData(data);
            // Disable zoom/scroll for previewed chart, visible chart ranges depends on preview chart viewport so
            // zoom/scroll is unnecessary.
            chart.setZoomEnabled(false);
            chart.setScrollEnabled(false);

            previewChart.setLineChartData(previewData);
            previewChart.setViewportChangeListener(new ViewportListener());

            previewX(false);

            return rootView;
        }

        private void generateDefaultData() {
            int numValues = 50;

            List<PointValue> values = new ArrayList<PointValue>();
            for (int i = 0; i < numValues; ++i) {
                values.add(new PointValue(i, (float) Math.random() * 100f));
            }

            Line line = new Line(values);
            line.setColor(ChartUtils.COLOR_GREEN);
            line.setHasPoints(false);// too many values so don't draw points.

            List<Line> lines = new ArrayList<Line>();
            lines.add(line);

            data = new LineChartData(lines);
            data.setAxisXBottom(new Axis());
            data.setAxisYLeft(new Axis().setHasLines(true));

            // prepare preview data, is better to use separate deep copy for preview chart.
            // Set color to grey to make preview area more visible.
            previewData = new LineChartData(data);
            previewData.getLines().get(0).setColor(ChartUtils.DEFAULT_DARKEN_COLOR);

        }

        private void previewY() {
            Viewport tempViewport = new Viewport(chart.getMaximumViewport());
            float dy = tempViewport.height() / 4;
            tempViewport.inset(0, dy);
            previewChart.setCurrentViewportWithAnimation(tempViewport);
            previewChart.setZoomType(ZoomType.VERTICAL);
        }

        private void previewX(boolean animate) {
            Viewport tempViewport = new Viewport(chart.getMaximumViewport());
            float dx = tempViewport.width() / 4;
            tempViewport.inset(dx, 0);
            if (animate) {
                previewChart.setCurrentViewportWithAnimation(tempViewport);
            } else {
                previewChart.setCurrentViewport(tempViewport);
            }
            previewChart.setZoomType(ZoomType.HORIZONTAL);
        }

        private void previewXY() {
            // Better to not modify viewport of any chart directly so create a copy.
            Viewport tempViewport = new Viewport(chart.getMaximumViewport());
            // Make temp viewport smaller.
            float dx = tempViewport.width() / 4;
            float dy = tempViewport.height() / 4;
            tempViewport.inset(dx, dy);
            previewChart.setCurrentViewportWithAnimation(tempViewport);
        }

        /**
         * Viewport listener for preview chart(lower one). in {@link #onViewportChanged(Viewport)} method change
         * viewport of upper chart.
         */
        private class ViewportListener implements ViewportChangeListener {

            @Override
            public void onViewportChanged(Viewport newViewport) {
                // don't use animation, it is unnecessary when using preview chart.
                chart.setCurrentViewport(newViewport);
            }

        }

    }

}