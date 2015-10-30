package com.hy.onlinemonitor.view.Activity.SystemManagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.Equipment;
import com.hy.onlinemonitor.utile.ActivityCollector;
import com.hy.onlinemonitor.view.Adapter.MoreInformationSensorRecyclerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/7/27.
 */
public class EquipmentMoreInformationActivity extends AppCompatActivity {

    private RecyclerView.Adapter mAdapter;
    private Equipment equipment;
    @Bind(R.id.detailed_toolbar)
    Toolbar detailedToolbar;
    @Bind(R.id.equipment_identifier)
    TextView equipmentIdentifier;
    @Bind(R.id.equipment_type)
    TextView equipmentType;
    @Bind(R.id.alarm_information_send)
    TextView alarmInformationSend;
    @Bind(R.id.equipment_dvrid)
    TextView equipmentDvrid;
    @Bind(R.id.equipment_cmaid)
    TextView equipmentCmaid;
    @Bind(R.id.equipment_eqmenid)
    TextView equipmentEqmenid;
    @Bind(R.id.equipment_sensorid)
    TextView equipmentSensorid;
    @Bind(R.id.equipment_preset)
    TextView equipmentPreset;
    @Bind(R.id.sensor_show_message)
    TextView sensorShowMessage;

    @Bind(R.id.more_information_rv)
    RecyclerView moreInformationRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityCollector.addActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_information);
        ButterKnife.bind(this);

        setSupportActionBar(detailedToolbar);

        getSupportActionBar().setTitle(R.string.equipment_manage);
        getSupportActionBar().setSubtitle(R.string.more_information);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initData();

        equipmentIdentifier.setText(equipment.getDeviceID());
        String showDeviceType ="";
        switch (equipment.getDeviceType()){
            case "fire":
                showDeviceType="山火";
                break;
            case "break":
                showDeviceType="外破";
                break;
            case "uav":
                showDeviceType="无人机";
                break;
            case "video":
                showDeviceType="普通视频";
                break;
        }
        equipmentType.setText(showDeviceType);
        if(equipment.getSendMmsState() ==0 ){
            alarmInformationSend.setText("否");
        }else{
            alarmInformationSend.setText("是");
        }
        equipmentDvrid.setText(equipment.getDvrID());
        equipmentCmaid.setText(equipment.getCma_ID());
        equipmentEqmenid.setText(equipment.getEquipment_ID());
        equipmentSensorid.setText(equipment.getSensor_ID());
        equipmentPreset.setText(""+equipment.getAngleRelativeToNorthPole());

        if(equipment.getSensorInDeviceSet().size() !=0) {
            moreInformationRv.setLayoutManager(new LinearLayoutManager(this));
            mAdapter = new MoreInformationSensorRecyclerAdapter(EquipmentMoreInformationActivity.this, equipment.getSensorInDeviceSet());
            moreInformationRv.setAdapter(mAdapter);
        }else{
            sensorShowMessage.setText("暂无传感器");
        }
    }

    private void initData() {
        Intent intent = getIntent();
        this.equipment = (Equipment) intent.getSerializableExtra("equipmentInformation");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                EquipmentMoreInformationActivity.this.finish();
                break;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
