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
    private int choiceType =-1;
    private int channelID;
    private int streamType ;

    public VideoDataRepository(Context mContext, String fileName) {
        this.choiceType = 0;
        this.mContext = mContext;
        this.fileName = fileName;
    }

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
    @Override
    public Observable<String> getVideoUrl() {
        RestApiImpl restApi = new RestApiImpl(mContext);
        Observable<String>  observable =null;

        switch (choiceType){
            case 0:
                observable = restApi.videoUrl(fileName);
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
}
