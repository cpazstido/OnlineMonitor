package com.hy.onlinemonitor.view.Activity.Function;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.AlarmInformation;
import com.hy.onlinemonitor.bean.EquipmentInformation;
import com.hy.onlinemonitor.bean.OwnJurisdiction;
import com.hy.onlinemonitor.presenter.VideoPresenter;
import com.hy.onlinemonitor.utile.ActivityCollector;
import com.hy.onlinemonitor.utile.GetLoading;
import com.hy.onlinemonitor.utile.ShowUtile;
import com.hy.onlinemonitor.utile.TransformationUtils;
import com.hy.onlinemonitor.view.InitView;
import com.hy.onlinemonitor.view.LoadDataView;
import com.rey.material.widget.Button;
import com.rey.material.widget.RadioButton;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;


public class VideoActivity extends AppCompatActivity implements InitView, LoadDataView, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener, SurfaceHolder.Callback, MediaPlayer.OnErrorListener {
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
    SurfaceView surfaceView;
    @Bind(R.id.switches_auto)
    RadioButton switchesAuto;
    @Bind(R.id.switches_manual)
    RadioButton switchesManual;
    @Bind(R.id.yun_control_show)
    TextView yunControlShow;
    private static final String TAG = "VideoActivity";

    private MediaPlayer mediaPlayer;
    private int mVideoWidth;
    private int mVideoHeight;
    private SurfaceHolder holder;
    private boolean mIsVideoSizeKnown = false;
    private boolean mIsVideoReadyToBePlayed = false;

    private AlertDialog loadingDialog;
    private String type;
    private Timer timer;

    private int dvrTypes;
    private AlarmInformation alarmInformation;
    private VideoPresenter videoPresenter;
    private EquipmentInformation equipmentInformation;
    private String choiceType;
    private int channelID = -1; //6789,一般用7
    private int streamType = 7;
    private int dvrId;
    private String dvrType;
    private String videoUrl = "";
    private boolean haveControl = false;//是否拥有权限
    private boolean isHaveUrl = false;
    private boolean isOpenPower = false;
    private boolean controlFlag = true;//true代表自动,false代表手动

    private static String CONTROL_SHOW = "云台控制:";
    private static int CONTROL_LEFT = 2;
    private static int CONTROL_RIGHT = 3;
    private static int CONTROL_UP = 4;
    private static int CONTROL_DOWN = 5;
    private static int CONTROL_STOP = 11;

    private static int OPEN_POWER = 3;
    private static int OPEN_SYSTEM_POWER = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Vitamio.initialize(VideoActivity.this);
        if (!LibsChecker.checkVitamioLibs(this))
            return;
        ActivityCollector.addActivity(this);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        try {
            setupUI();
            initialize();
        } catch (Exception e) {
            Log.e("aaaa", "aaaa");
        }
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
                        yunControlShow.setText(CONTROL_SHOW + "自动");
                    }
                }
            }
        };

        holder = surfaceView.getHolder();
        holder.addCallback(this);
        holder.setFormat(PixelFormat.RGBA_8888);

        loadingDialog = GetLoading.getDialog(VideoActivity.this, "加载中.....");
        Intent intent = getIntent();
        type = intent.getStringExtra("type");

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

                controlLeft.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (haveControl) {
                            switch (event.getAction()) {
                                case MotionEvent.ACTION_UP: //手放开时停止转动命令
                                    if (mediaPlayer.isPlaying()) {
                                        yunControlShow.setText(CONTROL_SHOW + "转动停止中.");
                                        ShowUtile.toastShow(VideoActivity.this, "转动停止中.");
                                        videoPresenter.videoControl(CONTROL_STOP);
                                    } else {
                                        ShowUtile.toastShow(VideoActivity.this, "没有在播放状态，请先播放！");
                                    }

                                    break;
                                case MotionEvent.ACTION_DOWN: //按下时发送转动命令
                                    if (mediaPlayer.isPlaying()) {
                                        changeToManual();
                                        yunControlShow.setText(CONTROL_SHOW + "左转中,请稍等");
                                        ShowUtile.toastShow(VideoActivity.this, "左转中...");
                                        videoPresenter.videoControl(CONTROL_LEFT);
                                    } else {
                                        ShowUtile.toastShow(VideoActivity.this, "没有在播放状态，请先播放！");
                                    }
                                    break;
                            }
                        } else {
                            ShowUtile.noJurisdictionToast(VideoActivity.this);
                        }
                        return true;
                    }
                });


                controlUp.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (haveControl) {
                            switch (event.getAction()) {
                                case MotionEvent.ACTION_UP: //手放开时停止转动命令
                                    if (mediaPlayer.isPlaying()) {
                                        yunControlShow.setText(CONTROL_SHOW + "转动停止中.");
                                        ShowUtile.toastShow(VideoActivity.this, "转动停止中.");
                                        videoPresenter.videoControl(CONTROL_STOP);
                                    } else {
                                        ShowUtile.toastShow(VideoActivity.this, "没有在播放状态，请先播放！");
                                    }

                                    break;
                                case MotionEvent.ACTION_DOWN: //按下时发送转动命令
                                    if (mediaPlayer.isPlaying()) {
                                        changeToManual();
                                        yunControlShow.setText(CONTROL_SHOW + "上转中,请稍等");
                                        ShowUtile.toastShow(VideoActivity.this, "上转中...");
                                        videoPresenter.videoControl(CONTROL_UP);
                                    } else {
                                        ShowUtile.toastShow(VideoActivity.this, "没有在播放状态，请先播放！");
                                    }
                                    break;
                            }
                        } else {
                            ShowUtile.noJurisdictionToast(VideoActivity.this);
                        }
                        return true;
                    }
                });

                controlDown.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (haveControl) {
                            switch (event.getAction()) {
                                case MotionEvent.ACTION_UP://手放开时停止转动命令
                                    if (mediaPlayer.isPlaying()) {
                                        yunControlShow.setText(CONTROL_SHOW + "转动停止中.");
                                        ShowUtile.toastShow(VideoActivity.this, "转动停止中.");
                                        videoPresenter.videoControl(CONTROL_STOP);
                                    } else {
                                        ShowUtile.toastShow(VideoActivity.this, "没有在播放状态，请先播放！");
                                    }

                                    break;
                                case MotionEvent.ACTION_DOWN: //按下时发送转动命令
                                    if (mediaPlayer.isPlaying()) {
                                        changeToManual();
                                        yunControlShow.setText(CONTROL_SHOW + "下转中,请稍等");
                                        ShowUtile.toastShow(VideoActivity.this, "下转中...");
                                        videoPresenter.videoControl(CONTROL_DOWN);
                                    } else {
                                        ShowUtile.toastShow(VideoActivity.this, "没有在播放状态，请先播放！");
                                    }
                                    break;
                            }
                        } else {
                            ShowUtile.noJurisdictionToast(VideoActivity.this);
                        }
                        return true;
                    }
                });

                controlRight.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (haveControl) {
                            switch (event.getAction()) {
                                case MotionEvent.ACTION_UP://手放开时停止转动命令
                                    if (mediaPlayer.isPlaying()) {
                                        yunControlShow.setText(CONTROL_SHOW + "转动停止中.");
                                        ShowUtile.toastShow(VideoActivity.this, "转动停止中.");
                                        videoPresenter.videoControl(CONTROL_STOP);
                                    } else {
                                        ShowUtile.toastShow(VideoActivity.this, "没有在播放状态，请先播放！");
                                    }

                                    break;
                                case MotionEvent.ACTION_DOWN: //按下时发送转动命令
                                    if (mediaPlayer.isPlaying()) {
                                        changeToManual();
                                        yunControlShow.setText(CONTROL_SHOW + "右转中,请稍等");
                                        ShowUtile.toastShow(VideoActivity.this, "右转中...");
                                        videoPresenter.videoControl(CONTROL_RIGHT);
                                    } else {
                                        ShowUtile.toastShow(VideoActivity.this, "没有在播放状态，请先播放！");
                                    }
                                    break;
                            }
                        } else {
                            ShowUtile.noJurisdictionToast(VideoActivity.this);
                        }
                        return true;
                    }
                });

                historyVideoShow.setVisibility(View.GONE);
                break;
            case "history":
                alarmInformation = (AlarmInformation) intent.getSerializableExtra("AlarmInformation");
                dvrTypes = intent.getIntExtra("dvrType", -1);
                dvrId = intent.getIntExtra("dvrId", -1);
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
                this.videoPresenter.getUrlByFileName(alarmInformation.getVideoFileName(), dvrTypes, dvrId);
                break;
        }
    }

    @Override
    public void showLoading() {
        loadingDialog.show();
    }

    @Override
    public void hideLoading() {
        loadingDialog.dismiss();
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

    public void startVideoPlay(String videoUrls, int urlType) {
        switch (urlType) {
            case 1://录像
                this.videoUrl = TransformationUtils.getRecordVideoUrl(videoUrls);
                break;
            case 2://实时视频
                this.videoUrl = TransformationUtils.getRealVideoUrl(videoUrls);
                break;
        }
        videoPlayTv.setText("地址获取成功,请等待...");

        Log.e("startVideoPlay", videoUrl);
        if (!videoUrl.isEmpty()) {
            isHaveUrl = true;
            try {
                mediaPlayer.setDataSource(videoUrl);
//                mediaPlayer.setDataSource("rtsp://218.204.223.237:554/live/1/66251FC11353191F/e7ooqwcfbqjoo80j.sdp");
                mediaPlayer.setDisplay(holder);
                mediaPlayer.setOnBufferingUpdateListener(this);
                mediaPlayer.setOnCompletionListener(this);
                mediaPlayer.setOnPreparedListener(this);
                mediaPlayer.setOnVideoSizeChangedListener(this);
                mediaPlayer.setOnErrorListener(this);
                setVolumeControlStream(AudioManager.STREAM_MUSIC);

                mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                    @Override
                    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {
                        Log.e(TAG, "onInfo");
                        return false;
                    }
                });

            } catch (IOException e) {
                Log.e(TAG, "error:" + e.getMessage(), e);
                e.printStackTrace();
            }
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
    protected void onPause() {
        super.onPause();
        releaseMediaPlayer();
        doCleanUp();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
        doCleanUp();
        ActivityCollector.removeActivity(this);
        if (videoPresenter != null)
            this.videoPresenter.destroy();
        ButterKnife.unbind(this);
    }

    public void EquipmentStatus(String controlStatus) {
        videoEquipmentStatusTv.setText(controlStatus);
        switch (controlStatus) {
            case "\"摄像机电源已打开\"":
                if (mediaPlayer.isPlaying()) {
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
            case "\"前端监测设备通信中。。。\"":   //
                videoPlayTv.setText("发送指令中");
                break;
            case "\"通信主机关闭\"":
            case "\"摄像机状态查询失败\"":
                videoPlayTv.setText("播放失败");
                //do nothing
                videoUrl = "";
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

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d(TAG, "surfaceCreated called");
        doCleanUp();
//        mediaPlayer = new MediaPlayer(this);
        mediaPlayer = new MediaPlayer(this);
    }

    private void doCleanUp() {
        mVideoWidth = 0;
        mVideoHeight = 0;
        mIsVideoReadyToBePlayed = false;
        mIsVideoSizeKnown = false;
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void startVideoPlayback() {
        Log.v(TAG, "startVideoPlayback");
        holder.setFixedSize(mVideoWidth, mVideoHeight);
        mediaPlayer.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d(TAG, "surfaceChanged called");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, "surfaceDestroyed called");
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int percent) {
        Log.d(TAG, "onBufferingUpdate percent:" + percent);
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        Log.d(TAG, "onCompletion called");
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        Log.d(TAG, "onPrepared called");
        mIsVideoReadyToBePlayed = true;

        if (mIsVideoSizeKnown) {
            startVideoPlayback();
        }
    }

    @Override
    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int width, int height) {
        Log.v(TAG, "onVideoSizeChanged called");
        if (width == 0 || height == 0) {
            Log.e(TAG, "invalid video width(" + width + ") or height(" + height + ")");
            return;
        }
        mIsVideoSizeKnown = true;
        mVideoWidth = width;
        mVideoHeight = height;
        if (mIsVideoReadyToBePlayed) {
            startVideoPlayback();
        }
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        Log.e("onError", "onError调用了!!!");
        videoPlayTv.setText("播放出错,错误代码"+"("+i+", "+i1+")");
        ShowUtile.toastShow(VideoActivity.this,"视频暂无法播放,错误代码"+"("+i+", "+i1+")");
        doCleanUp();
        releaseMediaPlayer();
        return true;
    }

    public void prepare() {
        Log.e(TAG, "prepare");
        mediaPlayer.prepareAsync();
    }

}