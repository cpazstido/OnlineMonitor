package com.hy.onlinemonitor.view.Activity;


import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.view.Activity.Function.AlarmInformationActivity;
import com.hy.onlinemonitor.view.Activity.Function.EquipmentListViewActivity;
import com.hy.onlinemonitor.view.Activity.Function.MapActivity;
import com.hy.onlinemonitor.view.Activity.SystemManagement.AdministratorManageActivity;
import com.hy.onlinemonitor.view.Activity.SystemManagement.CompanyManageActivity;
import com.hy.onlinemonitor.view.Activity.SystemManagement.EquipmentManageActivity;
import com.hy.onlinemonitor.view.Activity.SystemManagement.JurisdictionManageActivity;
import com.hy.onlinemonitor.view.Activity.SystemManagement.LineManageActivity;
import com.hy.onlinemonitor.view.Activity.SystemManagement.PoleManageActivity;
import com.hy.onlinemonitor.view.Adapter.MainGridAdapter;
import com.hy.onlinemonitor.view.Component.MyGridView;

/**
 * Created by wsw on 2015/7/11.
 */
public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private MyGridView gvFunction, gvConfig, gvManage, gvMonitor;
    private Toolbar toolbar;

    private int selectedType;
    private static int FIREGV = 0;
    private static int BREAKGV = 1;
    private static int NORMALGV = 2;
    private static int AUTOPLANEGV = 3;

    @Override
    public void setupUI() {
        selectedType = this.getUser().getSelectionType();    //得到选择到的是哪一个类型的监控设备
        Log.e("selectedType",""+selectedType);
        if (selectedType == 3) {//若是无人机,则隐藏状态监测这一项
            findViewById(R.id.main_ll_monitor).setVisibility(View.GONE);
        }
        //先隐藏未实现的功能界面
        findViewById(R.id.main_ll_monitor).setVisibility(View.GONE);
        findViewById(R.id.main_ll_config).setVisibility(View.GONE);

        gvFunction = (MyGridView) findViewById(R.id.main_gv_function);
        //gvConfig = (MyGridView) findViewById(R.id.main_gv_config);
        gvManage = (MyGridView) findViewById(R.id.main_gv_manage);
        //gvMonitor = (MyGridView) findViewById(R.id.main_gv_monitor);


        gvFunction.setAdapter(new MainGridAdapter(MainActivity.this, FIREGV, selectedType));
        gvFunction.setOnItemClickListener(this);

//        gvConfig.setAdapter(new GridAdapter(MainActivity.this, BREAKGV, selectedType));
//        gvConfig.setOnItemClickListener(this);

        gvManage.setAdapter(new MainGridAdapter(MainActivity.this, NORMALGV, selectedType));
        gvManage.setOnItemClickListener(this);

//        gvMonitor.setAdapter(new GridAdapter(MainActivity.this, AUTOPLANEGV, selectedType));
//        gvMonitor.setOnItemClickListener(this);
    }

    @Override
    public void initialize() {

    }

    @Override
    protected Toolbar getToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.main);
        return toolbar;
    }

    @Override
    protected void setOwnContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("GridView点击了： ", "position" + position);
        switch (parent.getId()) {
            case R.id.main_gv_function: //功能gridiewV
                switch (position) {
                    case 0: //设备列表
                        Intent gridEquipmentListIntent = new Intent(MainActivity.this, EquipmentListViewActivity.class);
                        Log.e("lok","设备列表");
                        startActivity(gridEquipmentListIntent);
                        break;
                    case 1: //报警信息
                        Intent gridAlarmIntent = new Intent(MainActivity.this, AlarmInformationActivity.class);
                        Log.e("lok","报警信息");
                        startActivity(gridAlarmIntent);
                        break;
                    case 2: //地图
                        Intent gridMapIntent = new Intent(MainActivity.this,MapActivity.class);
                        Log.e("lok","地图");
                        startActivity(gridMapIntent);
                        break;
                }
                break;
            case R.id.main_gv_manage:    //系统管理gridView
                switch(position) {
                    case 0://公司
                        Intent gridCompanyIntent = new Intent(MainActivity.this, CompanyManageActivity.class);
                        startActivity(gridCompanyIntent);
                        break;
                    case 1://管理员
                        Intent gridAdministratorIntent = new Intent(MainActivity.this, AdministratorManageActivity.class);
                        startActivity(gridAdministratorIntent);
                        break;
                    case 2://权限
                        Intent gridAuthorityIntent = new Intent(MainActivity.this, JurisdictionManageActivity.class);
                        startActivity(gridAuthorityIntent);
                        break;
                    case 3://线路
                        Intent gridLineIntent = new Intent(MainActivity.this, LineManageActivity.class);
                        startActivity(gridLineIntent);
                        break;
                    case 4://杆塔
                        Intent gridTowerIntent = new Intent(MainActivity.this, PoleManageActivity.class);
                        startActivity(gridTowerIntent);
                        break;
                    case 5://设备
                        Intent gridEquipmentIntent = new Intent(MainActivity.this, EquipmentManageActivity.class);
                        startActivity(gridEquipmentIntent);
                        break;
                }
                break;
        }
    }
}