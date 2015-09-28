package com.hy.data.repository;

import android.content.Context;

import com.example.repository.VideoRepository;
import com.hy.data.net.RestApiImpl;

import rx.Observable;

public class VideoDataRepository implements VideoRepository{
    private Context mContext;
    private String fileName;
    private String dvrType;
    private int dvrId;
    private int dvrTypes;
    private int choiceType =-1;
    private int channelID;
    private int streamType ;
    private int equipmentSn ;
    private int operationType;
    private String deivceId;

    public VideoDataRepository(Context mContext,int channelID, int streamType,int dvrId,String dvrType) {
        this.choiceType = 1;
        this.mContext = mContext;
        this.dvrId = dvrId;
        this.dvrType = dvrType;
        this.channelID = channelID;
        this.streamType = streamType;
    }

    public VideoDataRepository(Context mContext,int channelID,int dvrId,String dvrType) {
        this.mContext = mContext;
        this.dvrId = dvrId;
        this.dvrType = dvrType;
        this.channelID = channelID;
    }

    public VideoDataRepository(Context mContext, String deviceId,int operationType) {
        this.mContext = mContext;
        this.deivceId = deviceId;
        this.operationType = operationType;
    }

    public VideoDataRepository(Context mContext, int sn) {
        this.mContext = mContext;
        this.equipmentSn = sn;
    }

    public VideoDataRepository(Context mContext, String equipmnetName, String dvrType, int operationType, int dvrId, int channelID) {
        this.mContext = mContext;
        this.deivceId = equipmnetName;
        this.dvrType = dvrType;
        this.operationType = operationType;
        this.dvrId = dvrId;
        this.channelID = channelID;
    }

    public VideoDataRepository(Context mContext, String fileName, int dvrType, int dvrId) {
        this.choiceType = 0;
        this.mContext = mContext;
        this.fileName = fileName;
        this.dvrTypes = dvrType;
        this.dvrId = dvrId;
    }

    @Override
    public Observable<String> getVideoUrl() {
        RestApiImpl restApi = new RestApiImpl(mContext);
        Observable<String>  observable =null;

        switch (choiceType){
            case 0:
                observable = restApi.videoUrl(fileName,dvrId,dvrTypes);
                break;
            case 1:
                observable =  restApi.videoUrl(dvrType,dvrId,channelID,streamType);
            break;
        }

        return observable;
    }

    @Override
    public Observable<String> leftControl() {
        RestApiImpl restApi = new RestApiImpl(mContext);
        return restApi.videoControl("left",dvrId,channelID,dvrType);
    }

    @Override
    public Observable<String> rightControl() {
        RestApiImpl restApi = new RestApiImpl(mContext);
        return restApi.videoControl("right",dvrId,channelID,dvrType);

    }

    @Override
    public Observable<String> upControl() {
        RestApiImpl restApi = new RestApiImpl(mContext);
        return restApi.videoControl("up",dvrId,channelID,dvrType);
    }

    @Override
    public Observable<String> downControl() {
        RestApiImpl restApi = new RestApiImpl(mContext);
        return restApi.videoControl("down",dvrId,channelID,dvrType);
    }

    @Override
    public Observable<String> getStatus() {
        RestApiImpl restApi = new RestApiImpl(mContext);
        return restApi.getEquipmentStatus(equipmentSn);
    }

    @Override
    public Observable<String> openPower() {
        RestApiImpl restApi = new RestApiImpl(mContext);
        return restApi.openPower(deivceId,operationType);
    }

    @Override
    public Observable<String> openFirePower() {
        RestApiImpl restApi = new RestApiImpl(mContext);
        return restApi.openFirePower(dvrId, channelID, dvrType);
    }

    @Override
    public Observable<String> changePtz(boolean isAuto) { //true 自动切手动 false 手动切自动
        RestApiImpl restApi = new RestApiImpl(mContext);
        return restApi.changePtz(dvrId, channelID, dvrType,isAuto);
    }

    @Override
    public Observable<String> stopPtz() {
        RestApiImpl restApi = new RestApiImpl(mContext);
        return restApi.videoControl("stop", dvrId, channelID, dvrType);
    }
}
