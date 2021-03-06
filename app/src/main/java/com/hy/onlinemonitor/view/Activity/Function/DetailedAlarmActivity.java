package com.hy.onlinemonitor.view.Activity.Function;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hy.data.utile.SystemRestClient;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.AlarmInformation;
import com.hy.onlinemonitor.bean.OwnJurisdiction;
import com.hy.onlinemonitor.presenter.HandlePresenter;
import com.hy.onlinemonitor.utile.ActivityCollector;
import com.hy.onlinemonitor.utile.GetLoading;
import com.hy.onlinemonitor.utile.ShowUtile;
import com.hy.onlinemonitor.view.LoadDataView;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrConfig;
import com.r0adkll.slidr.model.SlidrPosition;
import com.rey.material.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailedAlarmActivity extends AppCompatActivity implements LoadDataView {
    @Bind(R.id.detailed_toolbar)
    Toolbar detailedToolbar;
    @Bind(R.id.detailed_play_video)
    Button detailedPlayVideo;
    @Bind(R.id.handle_alarm)
    Button handleAlarm;
    @Bind(R.id.detailed_blowing_equipment)
    TextView detailedBlowingEquipment;
    @Bind(R.id.show_is_blowing)
    RelativeLayout showIsBlowing;
    @Bind(R.id.detailed_alarm_information)
    TextView detailedAlarmInformation;
    @Bind(R.id.detailed_infrared_picture)
    ImageView detailedInfraredPicture;
    @Bind(R.id.detailed_visible_picture)
    ImageView detailedVisiblePicture;
    @Bind(R.id.rootView)
    RelativeLayout rootView;

    private String queryAlarmType;
    private int status;
    private AlarmInformation alarmInformation;
    private AlertDialog loadingDialog;
    private int dvrType;
    private int dvrId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityCollector.addActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailedalarm);
        ButterKnife.bind(this);
        loadingDialog = GetLoading.getDialog(DetailedAlarmActivity.this, "处理报警中");
        Intent itemIntent = getIntent();
        alarmInformation = (AlarmInformation) itemIntent.getSerializableExtra("detailedAlarm");
        queryAlarmType = itemIntent.getStringExtra("queryAlarmType");
        status = itemIntent.getIntExtra("status", -1);

        dvrId = itemIntent.getIntExtra("dvrId", -1);
        dvrType = itemIntent.getIntExtra("dvrType", -1);

        SlidrConfig config = new SlidrConfig.Builder()
                .position(SlidrPosition.HORIZONTAL)
                .primaryColor(getResources().getColor(R.color.colorPrimary))
                .secondaryColor(getResources().getColor(R.color.colorPrimaryDark))
                .sensitivity(1f)
                .scrimColor(Color.BLACK)
                .scrimStartAlpha(0.8f)
                .scrimEndAlpha(0f)
                .velocityThreshold(2400)
                .distanceThreshold(0.25f)
                .build();

        Slidr.attach(this, config);

        if (status == 1) {
            handleAlarm.setVisibility(View.GONE);
        }
        detailedToolbar.setTitle(alarmInformation.getDeviceId());
        detailedToolbar.setSubtitle(alarmInformation.getCollectionTime());
        setSupportActionBar(detailedToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        detailedAlarmInformation.setText(alarmInformation.getAlarmInformation());

        switch (queryAlarmType) {
            case "fire":
                switch (alarmInformation.getIsBlowingEquipment()) {
                    case "no":
                        detailedBlowingEquipment.setText("否");
                        break;
                    case "yes":
                        detailedBlowingEquipment.setText("是");
                        break;
                }

                Log.e("详细界面","可见光"+SystemRestClient.BASE_PICTURE_URL + alarmInformation.getVisibleLightImage());
                Log.e("详细界面","红外"+SystemRestClient.BASE_PICTURE_URL + alarmInformation.getInfraredImage());

                Glide.with(this).load(SystemRestClient.BASE_PICTURE_URL + alarmInformation.getVisibleLightImage())
                        .error(R.drawable.loading_error)
                        .placeholder(R.drawable.picture_loading)
                        .dontAnimate()
                        .into(detailedVisiblePicture);
                Glide.with(this).load(SystemRestClient.BASE_PICTURE_URL + alarmInformation.getInfraredImage())
                        .error(R.drawable.loading_error)
                        .placeholder(R.drawable.picture_loading)
                        .dontAnimate()
                        .into(detailedInfraredPicture);
                break;
            case "break":
                detailedVisiblePicture.setVisibility(View.GONE);
                Log.e("详细界面", "外破" + SystemRestClient.BASE_PICTURE_URL + alarmInformation.getBreakImage());
                Glide.with(this).load(SystemRestClient.BASE_PICTURE_URL + alarmInformation.getBreakImage())
                        .error(R.drawable.loading_error)
                        .placeholder(R.drawable.picture_loading)
                        .centerCrop()
                        .dontAnimate()
                        .into(detailedInfraredPicture);
                showIsBlowing.setVisibility(View.GONE);
                break;
        }

        detailedPlayVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OwnJurisdiction.haveJurisdiction(4)) {//拥有查看的权限
                    Intent intent = new Intent(DetailedAlarmActivity.this, VideoActivity.class);
                    intent.putExtra("type", "history");
                    intent.putExtra("AlarmInformation", alarmInformation);
                    intent.putExtra("dvrType", dvrType);
                    intent.putExtra("dvrId", dvrId);
                    startActivity(intent);
                } else {
                    ShowUtile.noJurisdictionToast(DetailedAlarmActivity.this);
                }
            }
        });

        handleAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OwnJurisdiction.haveJurisdiction(56)) {//拥有查看的权限
                    //这里处理报警
                    HandlePresenter handlePresenter = new HandlePresenter();
                    handlePresenter.setView(DetailedAlarmActivity.this);
                    handlePresenter.handlerAlarm(alarmInformation.getAlarmSn(), queryAlarmType);
                } else {
                    ShowUtile.noJurisdictionToast(DetailedAlarmActivity.this);
                }
            }
        });

    }

    @Override
    public void showLoading() {
        loadingDialog.show();
    }

    @Override
    public void hideLoading() {
        loadingDialog.cancel();
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.e("getItemId", "" + item.getItemId());

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    public View getRootView() {
        return rootView;
    }
}
