package com.hy.onlinemonitor.view.Activity.SystemManagement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.daimajia.swipe.util.Attributes;
import com.google.gson.Gson;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.Equipment;
import com.hy.onlinemonitor.bean.Sensor;
import com.hy.onlinemonitor.bean.SensorType;
import com.hy.onlinemonitor.presenter.SMSensorPresenter;
import com.hy.onlinemonitor.utile.GetLoading;
import com.hy.onlinemonitor.view.Adapter.SMSensorRecyclerAdapter;
import com.hy.onlinemonitor.view.LoadDataView;
import com.rey.material.widget.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SensorManageActivity extends AppCompatActivity implements LoadDataView {

    @Bind(R.id.includes)
    Toolbar toolbar;
    @Bind(R.id.sm_rv)
    RecyclerView smRecyclerView;
    @Bind(R.id.button_bt_float_wave)
    FloatingActionButton buttonBtFloatWave;
    private SMSensorPresenter smSensorPresenter;
    private SMSensorRecyclerAdapter mAdapter;
    private List<Sensor> mList;
    private List<SensorType> allSensor;
    private Equipment equipment;
    private int userId;
    public AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        ButterKnife.bind(this);
        alertDialog = GetLoading.getDialog(SensorManageActivity.this, "加载数据中....");
        initDatas();
        initAdapter();
        buttonBtFloatWave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonBtFloatWave.getLineMorphingState() ==0){
                Gson gson = new Gson();
                String sensorJson = gson.toJson(mAdapter.getSensors());
                    Log.e("sensorJson","sensorJson->"+sensorJson);
                smSensorPresenter.changeSensor(equipment.getSn(), sensorJson);
                }else{
                    buttonBtFloatWave.setLineMorphingState((buttonBtFloatWave.getLineMorphingState() + 1) % 2, true);
                }
            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.equipment_manage);
        getSupportActionBar().setSubtitle(R.string.sensor_manage_2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void initAdapter() {
        smRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new SMSensorRecyclerAdapter(SensorManageActivity.this, mList);
        mAdapter.setPresenter(smSensorPresenter);
        mAdapter.setMode(Attributes.Mode.Single);
        smRecyclerView.setAdapter(mAdapter);
    }

    private void initDatas() {
        Intent intent = getIntent();
        equipment = (Equipment) intent.getSerializableExtra("equipment");
        userId = intent.getIntExtra("userId", -1);
        mList = equipment.getSensorInDeviceSet();

        smSensorPresenter = new SMSensorPresenter(SensorManageActivity.this);
        smSensorPresenter.setSensorManageActivity(this);
        smSensorPresenter.loadAllSensor(userId, equipment.getSn());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sm_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.e("getItemId", item.getItemId() + "");
        switch (item.getItemId()) {
            case R.id.action_add:   //传感器添加
                MaterialDialog dialog = new MaterialDialog.Builder(SensorManageActivity.this)
                        .title(R.string.sensor_add)
                        .customView(R.layout.dialog_sensor_change, true)
                        .positiveText(R.string.submit)
                        .negativeText(R.string.cancel)
                        .callback(new MaterialDialog.ButtonCallback() {
                            @Override
                            public void onAny(MaterialDialog dialog) {
                                super.onAny(dialog);
                            }

                            @Override
                            public void onPositive(MaterialDialog dialog) {
                                String sensorName = allSensor.get(((Spinner) dialog.getCustomView().findViewById(R.id.dialog_sensor_name)).getSelectedItemPosition()).getSensorName();
                                String sensorInfo = allSensor.get(((Spinner) dialog.getCustomView().findViewById(R.id.dialog_sensor_name)).getSelectedItemPosition()).getSensorMarke();
                                String sensorInDeviceID = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_sensor_identifier)).getText().toString();
                                Sensor sensor = new Sensor();
                                sensor.setInfo(sensorInfo);
                                sensor.setName(sensorName);
                                sensor.setSensorInDeviceID(sensorInDeviceID);
                                mList.add(sensor);
                                mAdapter.setSensorList(mList);

                                super.onPositive(dialog);
                            }

                            @Override
                            public void onNegative(MaterialDialog dialog) {
                                super.onNegative(dialog);
                            }

                            @Override
                            public void onNeutral(MaterialDialog dialog) {
                                super.onNeutral(dialog);
                            }
                        })
                        .build();

                List<String> sensorName = new ArrayList<>();
                for (SensorType sensorType : allSensor) {
                    sensorName.add(sensorType.getSensorName());
                }

                Spinner sensorNameSpinner = (Spinner) dialog.getCustomView().findViewById(R.id.dialog_sensor_name);
                sensorNameSpinner.setAdapter(new ArrayAdapter<>(dialog.getContext(), android.R.layout.simple_list_item_1, sensorName));

                dialog.show();
                break;

            case android.R.id.home:
                setResult(888);
                finish();
                break;

        }
        return true;
    }

    @Override
    public void showLoading() {
        alertDialog.show();
    }

    @Override
    public void hideLoading() {
        alertDialog.cancel();
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public Context getContext() {
        return null;
    }

    public void setAllSensorList(List<SensorType> mList) {
        this.allSensor = mList;
    }

    public void isChange(String result) {
        if(result.equals("true")){
            buttonBtFloatWave.setLineMorphingState((buttonBtFloatWave.getLineMorphingState() + 1) % 2, true);
            Toast.makeText(SensorManageActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        setResult(888);
        System.out.println("按下了back键 onBackPressed()");
        finish();
    }
}
