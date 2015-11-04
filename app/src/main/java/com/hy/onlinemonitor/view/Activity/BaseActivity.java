package com.hy.onlinemonitor.view.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.OwnJurisdiction;
import com.hy.onlinemonitor.bean.User;
import com.hy.onlinemonitor.presenter.UserPresenter;
import com.hy.onlinemonitor.utile.ActivityCollector;
import com.hy.onlinemonitor.view.Activity.ConditionMonitor.EquipmentConditionMonitorActivity;
import com.hy.onlinemonitor.view.Activity.ConditionMonitor.EquipmentStateMonitorActivity;
import com.hy.onlinemonitor.view.Activity.ConditionMonitor.MonitoringStateAcitvity;
import com.hy.onlinemonitor.view.Activity.Function.AlarmInformationActivity;
import com.hy.onlinemonitor.view.Activity.Function.EquipmentListViewActivity;
import com.hy.onlinemonitor.view.Activity.Function.MapActivity;
import com.hy.onlinemonitor.view.Activity.SystemManagement.AdministratorManageActivity;
import com.hy.onlinemonitor.view.Activity.SystemManagement.CompanyManageActivity;
import com.hy.onlinemonitor.view.Activity.SystemManagement.EquipmentManageActivity;
import com.hy.onlinemonitor.view.Activity.SystemManagement.JurisdictionManageActivity;
import com.hy.onlinemonitor.view.Activity.SystemManagement.LineManageActivity;
import com.hy.onlinemonitor.view.Activity.SystemManagement.PoleManageActivity;
import com.hy.onlinemonitor.view.InitView;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

/**
 * Created by wsw on 2015/7/16.
 */
public abstract class BaseActivity extends AppCompatActivity implements InitView {
    private Drawer result = null;
    private Toolbar toolbar;
    private User user;
    private View headView;
    private UserPresenter userPresenter;
    private TextView userNameTV;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public TextView getUserNameTV() {
        return userNameTV;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityCollector.addActivity(this);
        Log.e("recy", "onCreate");
        super.onCreate(savedInstanceState);
        setOwnContentView();
        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        initUser();
        toolbar = getToolbar();
        setSupportActionBar(toolbar);
        headView = LayoutInflater.from(this).inflate(R.layout.drawer_header, null);
        userNameTV = (TextView) headView.findViewById(R.id.tv_user_name);
        Drawable companyDrawable = getResources().getDrawable(R.drawable.ic_company);
        Drawable towerDrawable = getResources().getDrawable(R.drawable.ic_tower);
        result = new DrawerBuilder().withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.main).withIcon(GoogleMaterial.Icon.gmd_home).withIdentifier(2).withCheckable(true),
                        new PrimaryDrawerItem().withName(R.string.change_equipment).withIcon(GoogleMaterial.Icon.gmd_swap_horiz).withIdentifier(1).withCheckable(true),
                        new SectionDrawerItem().withName(R.string.function),
                        new SecondaryDrawerItem().withName(R.string.equipment_list).withIcon(FontAwesome.Icon.faw_list).withIdentifier(3).withCheckable(true),
                        new SecondaryDrawerItem().withName(R.string.alarm_information).withIcon(FontAwesome.Icon.faw_bell).withIdentifier(4).withTag("Bullhorn"),
                        new SecondaryDrawerItem().withName(R.string.map).withIcon(GoogleMaterial.Icon.gmd_pin_drop).withIdentifier(5).withTag("Bullhorn"),
                        new SectionDrawerItem().withName(R.string.system_management),
                        new SecondaryDrawerItem().withName(R.string.company).withIcon(companyDrawable).withIdentifier(6).withCheckable(true),
                        new SecondaryDrawerItem().withName(R.string.administrator).withIcon(GoogleMaterial.Icon.gmd_perm_identity).withIdentifier(7).withTag("Bullhorn"),
                        new SecondaryDrawerItem().withName(R.string.jurisdiction).withIcon(FontAwesome.Icon.faw_key).withIdentifier(8).withTag("Bullhorn"),
                        new SecondaryDrawerItem().withName(R.string.line).withIcon(FontAwesome.Icon.faw_minus).withIdentifier(9).withTag("Bullhorn"),
                        new SecondaryDrawerItem().withName(R.string.tower).withIcon(towerDrawable).withIdentifier(10).withTag("Bullhorn"),
                        new SecondaryDrawerItem().withName(R.string.equipment).withIcon(GoogleMaterial.Icon.gmd_devices).withIdentifier(11).withTag("Bullhorn"),
                        new SectionDrawerItem().withName(R.string.condition_monitoring),
                        new SecondaryDrawerItem().withName(R.string.equipment_statistics).withIcon(GoogleMaterial.Icon.gmd_equalizer).withIdentifier(15).withTag("Bullhorn"),
                        new SecondaryDrawerItem().withName(R.string.equipment_state).withIcon(getResources().getDrawable(R.drawable.ic_device_state_24dp)).withIdentifier(16).withTag("Bullhorn"),
                        new SecondaryDrawerItem().withName(R.string.monitoring_statistics).withIcon(getResources().getDrawable(R.drawable.ic_statistics_24)).withIdentifier(17).withTag("Bullhorn"),
                        new SecondaryDrawerItem().withName(R.string.monitoring_condition).withIcon(getResources().getDrawable(R.drawable.ic_monitor_24)).withIdentifier(18).withTag("Bullhorn"),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName(R.string.return_login).withIcon(GoogleMaterial.Icon.gmd_account_circle).withIdentifier(12).withCheckable(true),
//                        new PrimaryDrawerItem().withName(R.string.about).withIcon(GoogleMaterial.Icon.gmd_info_outline).withIdentifier(13).withCheckable(true),
                        new PrimaryDrawerItem().withName(R.string.exit_app).withIcon(GoogleMaterial.Icon.gmd_exit_to_app).withIdentifier(14).withCheckable(true)
                )
                .withOnDrawerItemClickListener(
                        new Drawer.OnDrawerItemClickListener() {
                            @Override
                            public boolean onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
                                if (iDrawerItem != null) {
                                    Intent intent = null;
                                    switch (iDrawerItem.getIdentifier()) {
                                        case 1://切换设备
                                            if (OwnJurisdiction.haveJurisdiction(24)) //拥有查看的权限
                                                intent = new Intent(BaseActivity.this, TypeSelectionActivity.class);
                                            break;
                                        case 2://主界面
                                            intent = new Intent(BaseActivity.this, MainActivity.class);
                                            break;
                                        case 3://设备列表
                                            intent = new Intent(BaseActivity.this, EquipmentListViewActivity.class);
                                            break;
                                        case 4://报警信息
                                            if (OwnJurisdiction.haveJurisdiction(3)) //拥有查看的权限
                                                intent = new Intent(BaseActivity.this, AlarmInformationActivity.class);
                                            break;
                                        case 5://电子地图
                                            if (OwnJurisdiction.haveJurisdiction(65))//拥有查看的权限
                                                intent = new Intent(BaseActivity.this, MapActivity.class);
                                            break;
                                        case 6://系统管理-公司
                                            if (OwnJurisdiction.haveJurisdiction(17))
                                                intent = new Intent(BaseActivity.this, CompanyManageActivity.class);
                                            break;
                                        case 7://系统管理-管理员
                                            intent = new Intent(BaseActivity.this, AdministratorManageActivity.class);
                                            break;
                                        case 8://系统管理-权限
                                            if (OwnJurisdiction.haveJurisdiction(16))
                                                intent = new Intent(BaseActivity.this, JurisdictionManageActivity.class);
                                            break;
                                        case 9://系统管理-线路
                                            intent = new Intent(BaseActivity.this, LineManageActivity.class);
                                            break;
                                        case 10://系统管理-杆塔
                                            intent = new Intent(BaseActivity.this, PoleManageActivity.class);
                                            break;
                                        case 11://系统管理-设备
                                            intent = new Intent(BaseActivity.this, EquipmentManageActivity.class);
                                            break;
                                        case 12://返回登录
                                            intent = new Intent(BaseActivity.this, LoginActivity.class);
                                            break;
                                        case 13://关于
//                                            intent = new Intent(BaseActivity.this,);
                                            break;
                                        case 14://退出
                                            new MaterialDialog.Builder(BaseActivity.this)
                                                    .content(R.string.exit_information)
                                                    .positiveText(R.string.yes)
                                                    .negativeText(R.string.no)
                                                    .callback(new MaterialDialog.ButtonCallback() {
                                                        @Override
                                                        public void onPositive(MaterialDialog dialog) {
                                                            ActivityCollector.finishAll();
                                                            super.onPositive(dialog);
                                                        }
                                                    })
                                                    .show();
                                            break;
                                        case 15://设备统计
                                            intent = new Intent(BaseActivity.this, EquipmentConditionMonitorActivity.class);
                                            intent.putExtra("projectFlag", true);
                                            break;
                                        case 16://设备状态
                                            intent = new Intent(BaseActivity.this, EquipmentStateMonitorActivity.class);
                                            break;
                                        case 17://监测统计
                                            intent = new Intent(BaseActivity.this, EquipmentConditionMonitorActivity.class);
                                            intent.putExtra("projectFlag", false);
                                        case 18://监测状态
                                            intent = new Intent(BaseActivity.this, MonitoringStateAcitvity.class);
                                    }
                                    if (intent != null) {
                                        BaseActivity.this.startActivity(intent);
                                    }
                                }
                                return false;
                            }
                        }
                )
                .withHeader(headView)
                .withShowDrawerOnFirstLaunch(true)
                .build();
        result.closeDrawer();
    }

    private void initUser() {
        userPresenter = new UserPresenter();
        userPresenter.setBaseActivity(this);
        userPresenter.getUserInformation(this);
    }

    protected abstract Toolbar getToolbar();

    protected abstract void setOwnContentView();

    public Drawer getDrawer() {
        return this.result;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

}
