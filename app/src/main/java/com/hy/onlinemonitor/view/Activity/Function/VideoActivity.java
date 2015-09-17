package com.hy.onlinemonitor.view.Activity.Function;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.AlarmInformation;
import com.hy.onlinemonitor.bean.EquipmentInformation;
import com.hy.onlinemonitor.presenter.VideoPresenter;
import com.hy.onlinemonitor.utile.GetLoading;
import com.hy.onlinemonitor.view.InitView;
import com.hy.onlinemonitor.view.LoadDataView;
import com.rey.material.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;


public class VideoActivity extends AppCompatActivity implements InitView, LoadDataView {
    @Bind(R.id.video_toolbar)
    Toolbar toolbar;
    @Bind(R.id.video_line_tv)
    TextView videoLineTv;
    @Bind(R.id.video_tower_tv)
    TextView videoTowerTv;
    @Bind(R.id.video_time_tv)
    TextView videoTimeTv;
    @Bind(R.id.history_video_show)
    LinearLayout historyVideoShow;
    @Bind(R.id.video_equipment_status_tv)
    TextView videoEquipmentStatusTv;
    @Bind(R.id.video_play_tv)
    TextView videoPlayTv;
    @Bind(R.id.video_contrl_status_tv)
    TextView videoContrlStatusTv;
    @Bind(R.id.control_left)
    Button controlLeft;
    @Bind(R.id.control_up)
    Button controlUp;
    @Bind(R.id.control_right)
    Button controlRight;
    @Bind(R.id.control_down)
    Button controlDown;
    @Bind(R.id.real_play_show)
    LinearLayout realPlayShow;
    @Bind(R.id.video_view)
    VideoView videoView;
    private AlertDialog loadingDialog;
    private String type;

    private AlarmInformation alarmInformation;
    private VideoPresenter videoPresenter;
    private EquipmentInformation equipmentInformation;
    private String choiceType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!LibsChecker.checkVitamioLibs(this))
            return;
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);

        setupUI();
        initialize();
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
        loadingDialog = GetLoading.getDialog(VideoActivity.this, "加载中.....");
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        switch (type) {
            case "real":
                choiceType = intent.getStringExtra("choiceType");
                toolbar.setTitle(R.string.real_time_video);
                equipmentInformation = (EquipmentInformation) intent.getSerializableExtra("EquipmentInformation");
                toolbar.setSubtitle(equipmentInformation.getEquipmnetName());
                videoEquipmentStatusTv.setText(equipmentInformation.getEquipmnetState());
                videoPlayTv.setText("获取地址中...");
                historyVideoShow.setVisibility(View.GONE);
                break;
            case "history":
                alarmInformation = (AlarmInformation) intent.getSerializableExtra("AlarmInformation");
                realPlayShow.setVisibility(View.GONE);
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
        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });
    }

    @Override
    public void initialize() {
        this.videoPresenter = new VideoPresenter(VideoActivity.this);
        this.videoPresenter.setVideoActivity(VideoActivity.this);
        int channleID = -1;
        //6789,一般用7
        int streamType = 7;
        switch (type) {
            case "real":
                switch (choiceType) {
                    case "break":
                        /**
                         * 这里取得用户选择的channleId;
                         */
                        channleID = 2;
                        break;
                    default:
                        channleID = 1;
                        break;
                }
                this.videoPresenter.getUrlForRealPlay(channleID, streamType, equipmentInformation.getDvrId(), equipmentInformation.getDvrType());
                break;

            case "history":
                this.videoPresenter.getUrlByFileName(alarmInformation.getVideoFileName());
                break;
        }

//        videoView.setVideoPath("rtsp://218.204.223.237:554/live/1/66251FC11353191F/e7ooqwcfbqjoo80j.sdp");
//        videoView.start();
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
        return VideoActivity.this;
    }

    public void startVideoPlay(String videoUrl) {
        Log.e("startVideoPlay", videoUrl);
        if(!videoUrl.isEmpty()){
            videoView.setVideoPath(videoUrl);
            videoView.start();
        }else{
            videoPlayTv.setText("播放地址获取失败");
            videoView.setVideoPath("rtsp://218.204.223.237:554/live/1/66251FC11353191F/e7ooqwcfbqjoo80j.sdp");
            videoView.requestFocus();
            videoView.setMediaController(new MediaController(this));
            videoView.start();

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.videoPresenter.destroy();
        ButterKnife.unbind(this);
    }
}