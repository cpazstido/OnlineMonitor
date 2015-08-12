package com.hy.onlinemonitor.view.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.User;
import com.hy.onlinemonitor.presenter.UserPresenter;
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
public abstract class BaseActivity extends AppCompatActivity implements InitView{
    private Drawer result = null;
    private Toolbar toolbar;
    private User user;
    private View headView;
    private UserPresenter userPresenter;
    private TextView userNameTV;

    public TextView getUserNameTV() {
        return userNameTV;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setOwnContentView();
        initUser();

        toolbar = getToolbar();
        setSupportActionBar(toolbar);
        headView = LayoutInflater.from(this).inflate(R.layout.drawer_header, null);
        userNameTV = (TextView) headView.findViewById(R.id.tv_user_name);

        result = new DrawerBuilder().withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.main).withIcon(GoogleMaterial.Icon.gmd_menu).withIdentifier(2).withCheckable(true),
                        new PrimaryDrawerItem().withName(R.string.change_equipment).withIcon(GoogleMaterial.Icon.gmd_track_changes).withIdentifier(1).withCheckable(true),
                        new SectionDrawerItem().withName(R.string.function),
                        new SecondaryDrawerItem().withName(R.string.equipment_list).withIcon(FontAwesome.Icon.faw_legal).withIdentifier(3).withCheckable(true),
                        new SecondaryDrawerItem().withName(R.string.alarm_information).withIcon(GoogleMaterial.Icon.gmd_format_color_fill).withIdentifier(4).withTag("Bullhorn"),
                        new SecondaryDrawerItem().withName(R.string.map).withIcon(GoogleMaterial.Icon.gmd_format_color_fill).withIdentifier(5).withTag("Bullhorn"),
                        new SectionDrawerItem().withName(R.string.system_management),
                        new SecondaryDrawerItem().withName(R.string.company).withIcon(FontAwesome.Icon.faw_github).withIdentifier(6).withCheckable(true),
                        new SecondaryDrawerItem().withName(R.string.administrator).withIcon(GoogleMaterial.Icon.gmd_format_color_fill).withIdentifier(7).withTag("Bullhorn"),
                        new SecondaryDrawerItem().withName(R.string.jurisdiction).withIcon(GoogleMaterial.Icon.gmd_airline_seat_flat).withIdentifier(8).withTag("Bullhorn"),
                        new SecondaryDrawerItem().withName(R.string.line).withIcon(GoogleMaterial.Icon.gmd_format_color_fill).withIdentifier(9).withTag("Bullhorn"),
                        new SecondaryDrawerItem().withName(R.string.tower).withIcon(GoogleMaterial.Icon.gmd_format_color_fill).withIdentifier(10).withTag("Bullhorn"),
                        new SecondaryDrawerItem().withName(R.string.equipment).withIcon(GoogleMaterial.Icon.gmd_format_color_fill).withIdentifier(11).withTag("Bullhorn"),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName(R.string.personal_information).withIcon(GoogleMaterial.Icon.gmd_wb_sunny).withIdentifier(12).withCheckable(true),
                        new PrimaryDrawerItem().withName(R.string.about).withIcon(GoogleMaterial.Icon.gmd_wb_sunny).withIdentifier(13).withCheckable(true),
                        new PrimaryDrawerItem().withName(R.string.exit).withIcon(GoogleMaterial.Icon.gmd_wb_sunny).withIdentifier(14).withCheckable(true)
                )
               .withOnDrawerItemClickListener(
                        new Drawer.OnDrawerItemClickListener() {
                            @Override
                            public boolean onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
                                if (iDrawerItem != null) {
                                    Intent intent = null;
                                    switch (iDrawerItem.getIdentifier()) {
                                        case 1://切换设备
                                            intent = new Intent(BaseActivity.this, TypeSelectionActivity.class);
                                            break;
                                        case 2://主界面
                                            intent = new Intent(BaseActivity.this, MainActivity.class);
                                            break;
                                      /*  case 3://设备列表
                                            intent = new Intent(BaseActivity.this, EquipmentListActivity.class);
                                            break;
                                        case 4://报警信息
                                            intent = new Intent(BaseActivity.this, AlarmInformationActivity.class);
                                            break;
                                        case 5://电子地图
                                            intent = new Intent(BaseActivity.this, MapActivity.class);
                                            break;
                                        case 6:
//                                            intent = new Intent(BaseActivity.this,);
                                            break;
                                        case 7:
//                                            intent = new Intent(BaseActivity.this,);
                                            break;
                                        case 8:
//                                            intent = new Intent(BaseActivity.this,);
                                            break;
                                        case 9:
//                                            intent = new Intent(BaseActivity.this,);
                                            break;
                                        case 10:
//                                            intent = new Intent(BaseActivity.this,);
                                            break;
                                        case 11:
//                                            intent = new Intent(BaseActivity.this,);
                                            break;
                                        case 12://个人信息
//                                            intent = new Intent(BaseActivity.this,);
                                            break;
                                        case 13://关于
//                                            intent = new Intent(BaseActivity.this,);
                                            break;
                                        case 14://退出
//                                            intent = new Intent(BaseActivity.this,);
                                            break;
                                            */
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

        initViews();
        initDatas();
        initAdapter();

    }

    private  void initUser(){
        userPresenter = new UserPresenter();
        userPresenter.setBaseAcitvity(this);
        userPresenter.getUserInformation(this);
    }

    public void afterGetUser(){
        Log.e("msg","afterGetUser");
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

}
