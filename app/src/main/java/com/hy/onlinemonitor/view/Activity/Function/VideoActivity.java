package com.hy.onlinemonitor.view.Activity.Function;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.AlarmInformation;
import com.hy.onlinemonitor.view.InitView;
import com.rey.material.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;


public class VideoActivity extends AppCompatActivity implements InitView {
    private String videoUrl="";

    @Bind(R.id.video_toolbar)
    Toolbar toolbar;
    @Bind(R.id.control_left)
    Button controlLeft;
    @Bind(R.id.control_up)
    Button controlUp;
    @Bind(R.id.control_right)
    Button controlRight;
    @Bind(R.id.control_down)
    Button controlDown;
    @Bind(R.id.control_layout)
    LinearLayout controlLayout;
    @Bind(R.id.history_video_infor)
    LinearLayout historyVideoInfor;
    @Bind(R.id.real_time_infor)
    LinearLayout realTimeInfor;
    @Bind(R.id.video_view)
    VideoView videoView;
    @Bind(R.id.video_line_tv)
    TextView videoLineTv;
    @Bind(R.id.video_tower_tv)
    TextView videoTowerTv;
    @Bind(R.id.video_time_tv)
    TextView videoTimeTv;
    @Bind(R.id.video_equipment_status_tv)
    TextView videoEquipmentStatusTv;
    @Bind(R.id.video_play_tv)
    TextView videoPlayTv;
    @Bind(R.id.video_contrl_status_tv)
    TextView videoContrlStatusTv;

    private AlarmInformation alarmInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!LibsChecker.checkVitamioLibs(this))
            return;
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);

//        setupUI();
        initialize();
      /*  new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                initDoAction();
            }
        },0,1000);*/
    }

    private void initDoAction() {

    }

    private void openCameraPower() {//打开电源函数

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setupUI() {
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        switch (type) {
            case "real":
                toolbar.setTitle(R.string.real_time_video);
                historyVideoInfor.setVisibility(View.GONE);
                controlLayout.setVisibility(View.GONE);
                break;
            case "history":
                alarmInformation = (AlarmInformation) intent.getSerializableExtra("AlarmInformation");
                realTimeInfor.setVisibility(View.GONE);
                toolbar.setTitle(R.string.play_alarm_video);
                toolbar.setSubtitle(alarmInformation.getDeviceId());
                videoLineTv.setText(alarmInformation.getLineName());
                videoTowerTv.setText(alarmInformation.getPoleName());
                videoTimeTv.setText(alarmInformation.getCollectionTime());
                break;
        }

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void initialize() {
        videoView.setVideoPath("rtsp://218.204.223.237:554/live/1/66251FC11353191F/e7ooqwcfbqjoo80j.sdp");
        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });
        videoView.start();
    }
}