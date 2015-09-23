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
import com.hy.onlinemonitor.bean.OwnJurisdiction;
import com.hy.onlinemonitor.presenter.VideoPresenter;
import com.hy.onlinemonitor.utile.GetLoading;
import com.hy.onlinemonitor.utile.ShowUtile;
import com.hy.onlinemonitor.view.InitView;
import com.hy.onlinemonitor.view.LoadDataView;
import com.rey.material.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

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
    @Bind(R.id.video_tower_show)
    TextView videoTowerShow;
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
    private Timer timer;

    private AlarmInformation alarmInformation;
    private VideoPresenter videoPresenter;
    private EquipmentInformation equipmentInformation;
    private String choiceType;
    private int channelID = -1; //6789,一般用7
    private int streamType = 7;
    private String videoUrl = new String();
    private boolean haveControl = false;//是否拥有权限

    private static int CONTROL_LEFT = 2;
    private static int CONTROL_RIGHT = 3;
    private static int CONTROL_UP = 4;
    private static int CONTROL_DOWN = 5;

    private static int OPEN_POWER = 3;
    private static int OPEN_SYSTEM_POWER = 5;

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
                haveControl = OwnJurisdiction.haveJurisdiction(1);
                choiceType = intent.getStringExtra("choiceType");
                toolbar.setTitle(R.string.real_time_video);
                equipmentInformation = (EquipmentInformation) intent.getSerializableExtra("EquipmentInformation");
                toolbar.setSubtitle(equipmentInformation.getEquipmnetName());
                videoEquipmentStatusTv.setText(equipmentInformation.getEquipmnetState());
                videoPlayTv.setText("获取地址中...");
                controlLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (haveControl) {
                            if (videoView.isPlaying()) {
                                videoContrlStatusTv.setText("手动控制中");
                                ShowUtile.toastShow(VideoActivity.this, "左转中...");
                                videoPresenter.videoControl(CONTROL_LEFT);
                            } else {
                                ShowUtile.toastShow(VideoActivity.this, "没有在播放状态，请先播放！");
                            }
                        } else {
                            ShowUtile.noJurisdictionToast(VideoActivity.this);
                        }

                    }
                });

                controlUp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (haveControl) {
                            if (videoView.isPlaying()) {
                                videoContrlStatusTv.setText("手动控制中");
                                ShowUtile.toastShow(VideoActivity.this, "上转中...");
                                videoPresenter.videoControl(CONTROL_UP);
                            } else {
                                ShowUtile.toastShow(VideoActivity.this, "没有在播放状态，请先播放！");
                            }
                        } else {
                            ShowUtile.noJurisdictionToast(VideoActivity.this);
                        }
                    }
                });

                controlDown.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (haveControl) {
                            if (videoView.isPlaying()) {
                                videoContrlStatusTv.setText("手动控制中");
                                ShowUtile.toastShow(VideoActivity.this, "下转中...");
                                videoPresenter.videoControl(CONTROL_DOWN);
                            } else {
                                ShowUtile.toastShow(VideoActivity.this, "没有在播放状态，请先播放！");
                            }
                        } else {
                            ShowUtile.noJurisdictionToast(VideoActivity.this);
                        }
                    }
                });

                controlRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (haveControl) {
                            if (videoView.isPlaying()) {
                                videoContrlStatusTv.setText("手动控制中");
                                ShowUtile.toastShow(VideoActivity.this, "右转中...");
                                videoPresenter.videoControl(CONTROL_RIGHT);
                            } else {
                                ShowUtile.toastShow(VideoActivity.this, "没有在播放状态，请先播放！");
                            }
                        } else {
                            ShowUtile.noJurisdictionToast(VideoActivity.this);
                        }
                    }
                });
                historyVideoShow.setVisibility(View.GONE);
                break;
            case "history":
                alarmInformation = (AlarmInformation) intent.getSerializableExtra("AlarmInformation");
                realPlayShow.setVisibility(View.GONE);
                toolbar.setTitle(R.string.play_alarm_video);
                toolbar.setSubtitle(alarmInformation.getDeviceId());

                videoView.setMediaController(new MediaController(this));
                videoView.requestFocus();
                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        mediaPlayer.setPlaybackSpeed(1.0f);
                    }
                });

                if (alarmInformation.getLineName() != null) {
                    videoLineTv.setText(alarmInformation.getLineName());
                } else {
                    videoLineTv.setVisibility(View.GONE);
                }
                if (alarmInformation.getPoleName() != null) {
                    videoTowerTv.setText(alarmInformation.getPoleName());
                } else {
                    videoTowerTv.setVisibility(View.GONE);
                    videoTowerShow.setVisibility(View.GONE);
                }

                videoTimeTv.setText(alarmInformation.getCollectionTime());
                break;
        }
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        this.videoPresenter = new VideoPresenter(VideoActivity.this);
        this.videoPresenter.setVideoActivity(VideoActivity.this);
    }

    @Override
    public void initialize() {
        switch (type) {
            case "real":
                switch (choiceType) {
                    case "break":
                        /**
                         * 这里取得用户选择的ChannelID;
                         */
                        channelID = 2;
                        break;
                    default:
                        channelID = 1;
                        break;
                }
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        VideoActivity.this.videoPresenter.getEquipmentStatus(equipmentInformation.getSn());
                    }
                }, 0, 10000);
                break;

            case "history":
                this.videoPresenter.getUrlByFileName(alarmInformation.getVideoFileName());
                break;
        }
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
        if (timer != null)
            timer.cancel();
        ShowUtile.toastShow(VideoActivity.this, message);
    }

    @Override
    public Context getContext() {
        return VideoActivity.this;
    }

    public void startVideoPlay(String videoUrl) {
        this.videoUrl = videoUrl;
        Log.e("startVideoPlay", videoUrl);
        if (!videoUrl.isEmpty()) {
            videoView.setVideoPath(videoUrl);
            videoView.start();
        } else {
            videoPlayTv.setText("播放地址获取失败");
            initialize();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (timer != null)
            timer.cancel();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.videoPresenter.destroy();
        ButterKnife.unbind(this);
    }

    public void EquipmentStatus(String controlStatus) {
        videoEquipmentStatusTv.setText(controlStatus);
        switch (controlStatus) {
            case "\"摄像机电源打开\"":
                if (!videoUrl.isEmpty()) { //已经在播放
                    videoPlayTv.setText("正在播放中");
                    break;
                } else {
                    videoPlayTv.setText("获取地址中");
                    this.videoPresenter.getUrlForRealPlay(channelID, streamType, equipmentInformation.getDvrId(), equipmentInformation.getDvrType());
                }
                break;
            case "\"前端监测设备电源关闭\"":
                videoPlayTv.setText("打开电源中");
                switch (choiceType) {
                    case "video":
                        this.videoPresenter.openCameraPower(equipmentInformation.getSn(), OPEN_SYSTEM_POWER);
                        break;
                    default:
                        this.videoPresenter.openCameraPower(equipmentInformation.getSn(), OPEN_POWER);
                        break;
                }
                break;
            case "\"摄像机电源关闭\"":
                videoPlayTv.setText("打开电源中");
                switch (choiceType) {
                    case "video":
                        this.videoPresenter.openCameraPower(equipmentInformation.getSn(), OPEN_SYSTEM_POWER);
                        break;
                    default:
                        this.videoPresenter.openCameraPower(equipmentInformation.getSn(), OPEN_SYSTEM_POWER);
                        break;
                }
                break;
            case "\"通信主机关闭\"":
            case "\"前端监测设备通信中\"":
                videoPlayTv.setText("播放失败");
                //do nothing
                videoUrl = "";
                break;
            default:
                break;
        }
    }
}