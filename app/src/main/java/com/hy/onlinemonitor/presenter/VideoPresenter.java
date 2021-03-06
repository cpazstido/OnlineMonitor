package com.hy.onlinemonitor.presenter;

import android.content.Context;
import android.util.Log;

import com.example.interactor.DefaultSubscriber;
import com.example.interactor.UseCase;
import com.example.interactor.VideoUseCase;
import com.example.repository.VideoRepository;
import com.hy.data.repository.VideoDataRepository;
import com.hy.onlinemonitor.UIThread;
import com.hy.onlinemonitor.utile.ShowUtile;
import com.hy.onlinemonitor.view.Activity.Function.VideoActivity;

import rx.android.schedulers.AndroidSchedulers;
public class VideoPresenter implements Presenter {
    private Context mContext;
    private VideoActivity videoActivity;
    private UseCase videoUseCase;
    private String fileName;
    private int channelID;
    private int streamType;
    private int dvrId;
    private String dvrType;
    private int dvrTypes;
    private VideoRepository videoRepository;
    private String deviceId;
    private int operationType;
    private int urlType = -1;

    public VideoPresenter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        if (this.videoUseCase != null) {
            this.videoUseCase.unsubscribe();
        }
    }

    @Override
    public void showViewLoading() {
        this.videoActivity.showLoading();
    }

    @Override
    public void hideViewLoading() {
        this.videoActivity.hideLoading();

    }

    public void getUrlByFileName(String fileName, int dvrType, int dvrID) {
        this.urlType = 1;   //录像
        this.fileName = fileName;
        this.dvrTypes = dvrType;
        this.dvrId = dvrID;
        this.loadVideoUrl();
    }

    private void loadVideoUrl() {
        this.showViewLoading();
        this.getVideoUrl();
    }

    public void getVideoUrl() {
        videoRepository = new VideoDataRepository(mContext, fileName, dvrTypes, dvrId);
        this.videoUseCase = new VideoUseCase(new UIThread(), AndroidSchedulers.mainThread(), videoRepository, 1);
        this.videoUseCase.execute(new VideoUrlSubscriber());
    }

    public void getUrlForRealPlay(int channleID, int streamType, int dvrId, String dvrType) {
        this.urlType = 2; //实时视频
        this.channelID = channleID;
        this.streamType = streamType;
        this.dvrId = dvrId;
        this.dvrType = dvrType;
        this.loadRealPlayUrl();
    }

    private void loadRealPlayUrl() {
        this.showViewLoading();
        this.getRealPlayUrl();
    }


    public void getRealPlayUrl() {
        videoRepository = new VideoDataRepository(mContext, channelID, streamType, dvrId, dvrType);
        this.videoUseCase = new VideoUseCase(new UIThread(), AndroidSchedulers.mainThread(), videoRepository, 1);
        this.videoUseCase.execute(new VideoUrlSubscriber());
    }

    public void stopPlay(int dvrId, int streamType, int channelID, String dvrType) {
        videoRepository = new VideoDataRepository(mContext, channelID, streamType, dvrId, dvrType);
        this.videoUseCase = new VideoUseCase(new UIThread(), AndroidSchedulers.mainThread(), videoRepository, 12);
        this.videoUseCase.execute(new VideoStopSubscriber());
    }

    private class VideoStopSubscriber extends DefaultSubscriber<String> {
        @Override
        public void onCompleted() {
            ShowUtile.snackBarShow(videoActivity.getRootView(), "切换成功,等待播放中..");
        }

        @Override
        public void onError(Throwable e) {
            VideoPresenter.this.hideViewLoading();
            VideoPresenter.this.videoActivity.showError(e.getMessage());
            super.onError(e);
        }

        @Override
        public void onNext(String videoUrl) {
            Log.e("VideoStopSubscr", "准备重新播放");
            videoActivity.initialize();
        }
    }

    private class VideoUrlSubscriber extends DefaultSubscriber<String> {
        @Override
        public void onCompleted() {
            VideoPresenter.this.prepare();
        }

        @Override
        public void onError(Throwable e) {
            VideoPresenter.this.hideViewLoading();
            VideoPresenter.this.videoActivity.showError(e.getMessage());
            super.onError(e);
        }

        @Override
        public void onNext(String videoUrl) {
            VideoPresenter.this.hideViewLoading();
            VideoPresenter.this.startVideoPlay(videoUrl);
        }
    }

    private void prepare() {
        this.videoActivity.prepare();
    }

    private void startVideoPlay(String videoUrl) {
        if (this.videoActivity != null) {
            this.videoActivity.startVideoPlay(videoUrl, urlType);
        }
    }

    public void setVideoActivity(VideoActivity videoActivity) {
        this.videoActivity = videoActivity;
    }

    public void videoControl(int type) {
        videoRepository = new VideoDataRepository(mContext, channelID, dvrId, dvrType);
        this.videoUseCase = new VideoUseCase(new UIThread(), AndroidSchedulers.mainThread(), videoRepository, type);
        this.videoUseCase.execute(new ControlSubscriber());
    }

    private class ControlSubscriber extends DefaultSubscriber<String> {
        @Override
        public void onCompleted() {
            VideoPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            VideoPresenter.this.hideViewLoading();
            VideoPresenter.this.videoActivity.showError(e.getMessage());
            super.onError(e);
        }

        @Override
        public void onNext(String controlStatus) {
        }
    }

    public void getEquipmentStatus(int sn) {
        videoRepository = new VideoDataRepository(mContext, sn);
        this.videoUseCase = new VideoUseCase(new UIThread(), AndroidSchedulers.mainThread(), videoRepository, 6);
        this.videoUseCase.execute(new StatusSubscriber());
    }

    private class StatusSubscriber extends DefaultSubscriber<String> {
        @Override
        public void onCompleted() {
            VideoPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            VideoPresenter.this.hideViewLoading();
            VideoPresenter.this.videoActivity.showError(e.getMessage());
            super.onError(e);
        }

        @Override
        public void onNext(String controlStatus) {
            VideoPresenter.this.videoActivity.EquipmentStatus(controlStatus);
        }
    }

    public void openFireCameraPower(String equipmentName, int operationType, int dvrId, int channelID, String dvrType) {
        this.deviceId = equipmentName;
        this.operationType = operationType;
        this.dvrId = dvrId;
        this.channelID = channelID;
        this.dvrType = dvrType;
        videoRepository = new VideoDataRepository(mContext, equipmentName, dvrType, operationType, dvrId, channelID);
        this.videoUseCase = new VideoUseCase(new UIThread(), AndroidSchedulers.mainThread(), videoRepository, 8);
        this.videoUseCase.execute(new OpenFirePowerSubscriber());
    }

    private class OpenFirePowerSubscriber extends DefaultSubscriber<String> {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            VideoPresenter.this.hideViewLoading();
            VideoPresenter.this.videoActivity.showError(e.getMessage());
            super.onError(e);
        }

        @Override
        public void onNext(String controlStatus) {
            VideoPresenter.this.openCameraPower(deviceId, operationType,dvrId,channelID,dvrType);
        }
    }

    public void openCameraPower(String deivceId, int operationType, int dvrId, int channelID, String dvrType) {
        videoRepository = new VideoDataRepository(mContext, deivceId, dvrType, operationType, dvrId, channelID);
        this.videoUseCase = new VideoUseCase(new UIThread(), AndroidSchedulers.mainThread(), videoRepository, 7);
        this.videoUseCase.execute(new OpenPowerSubscriber());
    }


    private class OpenPowerSubscriber extends DefaultSubscriber<String> {
        @Override
        public void onCompleted() {
            VideoPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            VideoPresenter.this.hideViewLoading();
            VideoPresenter.this.videoActivity.showError(e.getMessage());
            super.onError(e);
        }

        @Override
        public void onNext(String controlStatus) {
            Log.e("videoPresenter", "打开电源--->" + controlStatus);
            VideoPresenter.this.videoActivity.EquipmentStatus(controlStatus);
        }
    }

    public void changeYun(int dvrId, int channelID, String dvrType, boolean b) {
        videoRepository = new VideoDataRepository(mContext, channelID, dvrId, dvrType);
        if (b) {
            this.videoUseCase = new VideoUseCase(new UIThread(), AndroidSchedulers.mainThread(), videoRepository, 9);
        } else {
            this.videoUseCase = new VideoUseCase(new UIThread(), AndroidSchedulers.mainThread(), videoRepository, 10);
        }
        this.videoUseCase.execute(new YunChangeSubscriber());
    }

    private class YunChangeSubscriber extends DefaultSubscriber<String> {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            VideoPresenter.this.hideViewLoading();
            VideoPresenter.this.videoActivity.showError(e.getMessage());
            super.onError(e);
        }

        @Override
        public void onNext(String controlStatus) {
            Log.e("YunChangeSubscriber", controlStatus);
            VideoPresenter.this.videoActivity.changeControlFlag();
        }
    }
}
