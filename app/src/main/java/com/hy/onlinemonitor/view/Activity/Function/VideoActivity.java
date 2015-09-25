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
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.AlarmInformation;
import com.hy.onlinemonitor.bean.EquipmentInformation;
import com.hy.onlinemonitor.bean.OwnJurisdiction;
import com.hy.onlinemonitor.presenter.VideoPresenter;
import com.hy.onlinemonitor.utile.GetLoading;
import com.hy.onlinemonitor.utile.ShowUtile;
import com.hy.onlinemonitor.utile.TransformationUtils;
import com.hy.onlinemonitor.view.InitView;
import com.hy.onlinemonitor.view.LoadDataView;
import com.rey.material.widget.Button;
import com.rey.material.widget.RadioButton;

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
    @Bind(R.id.switches_auto)
    RadioButton switchesAuto;
    @Bind(R.id.switches_manual)
    RadioButton switchesManual;
    @Bind(R.id.yun_control_show)
    TextView yunControlShow;
    private AlertDialog loadingDialog;
    private String type;
    private Timer timer;

    private AlarmInformation alarmInformation;
    private VideoPresenter videoPresenter;
    private EquipmentInformation equipmentInformation;
    private String choiceType;
    private int channelID = -1; //6789,一般用7
    private int streamType = 7;
    private int dvrId;
    private String dvrType;
    private String videoUrl = new String();
    private boolean haveControl = false;//是否拥有权限
    private boolean isHaveUrl = false;
    private boolean isOpenPower = false;
    private boolean controlFlag = true;//true代表自动,false代表手动

    private static String CONTROL_SHOW = "云台控制:";
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

        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switchesAuto.setChecked(switchesAuto == buttonView);
                    switchesManual.setChecked(switchesManual == buttonView);
                    if (controlFlag && switchesManual.isChecked()) {//切换成手动,当前为自动 controlFlag=true,并且手动选中
                        videoPresenter.changeYun(dvrId, channelID, dvrType, true);
                    } else if (!controlFlag && switchesAuto.isChecked()) {//切换为自动,当前为手动 controlFlag =false,并且自动选中
                        videoPresenter.changeYun(dvrId, channelID, dvrType, false);
                        yunControlShow.setText(CONTROL_SHOW+"自动");
                    }
                }
            }
        };

        loadingDialog = GetLoading.getDialog(VideoActivity.this, "加载中.....");
        Intent intent = getIntent();
        type = intent.getStringExtra("type");

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                ShowUtile.toastShow(VideoActivity.this, "出现错误");
                return false;
            }
        });
        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });
        switch (type) {
            case "real":
                switchesAuto.setOnCheckedChangeListener(listener);
                switchesManual.setOnCheckedChangeListener(listener);

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
                                changeToManual();
                                yunControlShow.setText(CONTROL_SHOW + "左转中,请稍等");
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
                                changeToManual();
                                yunControlShow.setText(CONTROL_SHOW + "上转中,请稍等");
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
                                changeToManual();
                                yunControlShow.setText(CONTROL_SHOW + "下转中,请稍等");
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
                                changeToManual();
                                yunControlShow.setText(CONTROL_SHOW + "右转中,请稍等");
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

    public void startVideoPlay(String videoUrls) {
        this.videoUrl = TransformationUtils.getRealVideoUrl(videoUrls);
        Log.e("startVideoPlay", videoUrl);
        if (!videoUrl.isEmpty()) {
            isHaveUrl = true;
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
        if (videoPresenter != null)
            this.videoPresenter.destroy();
        ButterKnife.unbind(this);
    }

    public void EquipmentStatus(String controlStatus) {
        videoEquipmentStatusTv.setText(controlStatus);
        switch (controlStatus) {
            case "\"摄像机电源已打开\"":
                if (videoView.isPlaying()) {
                    videoPlayTv.setText("正在播放中");
                } else {
                    if (isHaveUrl && !videoUrl.isEmpty()) {
                        break;
                    } else {
                        videoPlayTv.setText("获取地址中");
//                        startVideoPlay("{\"RtspURL\":\"rtsp://218.204.223.237:554/live/1/66251FC11353191F/e7ooqwcfbqjoo80j.sdp\"}");
                        dvrId = equipmentInformation.getDvrId();
                        dvrType = equipmentInformation.getDvrType();
                        this.videoPresenter.getUrlForRealPlay(channelID, streamType, equipmentInformation.getDvrId(), equipmentInformation.getDvrType());
                    }
                }
                break;
            case "\"前端监测设备电源关闭\"":
                videoPlayTv.setText("打开电源中");
                switch (choiceType) {
                    case "video":
                        this.videoPresenter.openCameraPower(equipmentInformation.getEquipmnetName(), OPEN_SYSTEM_POWER);
                        break;
                    default:
                        this.videoPresenter.openCameraPower(equipmentInformation.getEquipmnetName(), OPEN_POWER);
                        break;
                }
                break;
            case "\"摄像机电源已关闭\"":
                if (!isOpenPower) {
                    isOpenPower = true;
                    videoPlayTv.setText("打开电源中");
                    switch (choiceType) {
                        case "video":
                            this.videoPresenter.openCameraPower(equipmentInformation.getEquipmnetName(), OPEN_SYSTEM_POWER);
                            break;
                        case "fire":
                            this.videoPresenter.openFireCameraPower(equipmentInformation.getEquipmnetName(), OPEN_SYSTEM_POWER, equipmentInformation.getDvrId(), channelID, equipmentInformation.getDvrType());
                            break;
                        default:
                            this.videoPresenter.openCameraPower(equipmentInformation.getEquipmnetName(), OPEN_SYSTEM_POWER);
                            break;
                    }
                } else {
                    break;
                }
                break;
            case "\"正在打开摄像机\"":
                videoPlayTv.setText("等待摄像机打开");
                break;
            case "\"通信主机关闭\"":
            case "\"前端监测设备通信中\"":
                videoPlayTv.setText("播放失败");
                //do nothing
                videoUrl = "";
                break;
            case "\"命令执行成功，请等待10秒\"":
                break;
            default:
                break;
        }
    }

    public void changeToManual() {
        if (switchesAuto.isChecked()) {
            switchesAuto.setChecked(false);
            switchesManual.setChecked(true);
        }
    }

    public void changeControlFlag() {
        this.controlFlag = (!this.controlFlag);
    }
}