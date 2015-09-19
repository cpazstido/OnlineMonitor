package com.hy.onlinemonitor.view.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hy.onlinemonitor.R;
import com.rey.material.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;


/**
 * Created by Administrator on 2015/7/14.
 */
public class TestActivity extends AppCompatActivity {
    public static String aaa = new String();
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
    public static void StartTestView(Context context){
        Intent intent = new Intent(context,TestActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.equipment_manage);
        getSupportActionBar().setSubtitle(R.string.sensor_manage_2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        historyVideoShow.setVisibility(View.GONE);
        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });
        videoView.setVideoPath("rtsp://218.204.223.237:554/live/1/66251FC11353191F/e7ooqwcfbqjoo80j.sdp");
        videoView.start();
    }

}