package com.hy.onlinemonitor.view.Activity.Function;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.AlarmInformation;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrConfig;
import com.r0adkll.slidr.model.SlidrPosition;
import com.rey.material.widget.Button;
import com.squareup.picasso.Picasso;


/**
 * Created by wsw on 2015/7/15.
 */
public class DetailedAlarmActivity extends AppCompatActivity {
    TextView isBlowing,alarmMessage;
    ImageView detailedInfraredPicture,detailedVisablePicture;
    Button playVideo,handleAlarm;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailedalarm);
        Intent itemIntent = getIntent();
        AlarmInformation alarmInformation = (AlarmInformation) itemIntent.getSerializableExtra("detailedAlarm");

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

        toolbar = (Toolbar) findViewById(R.id.detailed_toolbar);
        isBlowing = (TextView) findViewById(R.id.detailed_blowing_equipment);
        alarmMessage = (TextView) findViewById(R.id.detailed_alarm_information);
        detailedInfraredPicture = (ImageView) findViewById(R.id.detailed_infrared_picture);
        detailedVisablePicture = (ImageView) findViewById(R.id.detailed_visible_picture);
        playVideo = (Button) findViewById(R.id.detailed_play_video);
        handleAlarm = (Button) findViewById(R.id.handle_alarm);
        if(alarmInformation.getTypeAlarm() == 2||alarmInformation.getTypeAlarm() == 0||alarmInformation.getTypeAlarm() == 4){
            handleAlarm.setVisibility(View.GONE);
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(alarmInformation.getAlarmName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        isBlowing.setText(alarmInformation.getIsBlowingEquipment());
        alarmMessage.setText(alarmInformation.getAlarmInformation());

        Picasso.with(this).load(alarmInformation.getVisibleLightImage()).into(detailedVisablePicture);
        Picasso.with(this).load(alarmInformation.getInfraredImage()).into(detailedInfraredPicture);

        playVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailedAlarmActivity.this, "播放视频!!", Toast.LENGTH_LONG).show();
            }
        });

        handleAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailedAlarmActivity.this,"处理报警",Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.e("getItemId", "" + item.getItemId());

        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
