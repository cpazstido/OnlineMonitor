package com.example.interactor;

import com.example.executor.PostExecutionThread;
import com.example.repository.VideoRepository;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by 24363 on 2015/8/27.
 */
public class VideoUseCase extends UseCase {

    private final VideoRepository videoRepository;

    public void setType(int type) {
        this.type = type;
    }

    private int type;

    public VideoUseCase(PostExecutionThread postExecutionThread, Scheduler subExecutionThread, VideoRepository videoRepository, int type) {
        super(postExecutionThread, subExecutionThread);
        this.videoRepository = videoRepository;
        this.type = type;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        Observable observable = null;
        switch (type){
            case 1:
                observable = videoRepository.getVideoUrl();
                break;
            case 2:
                observable = videoRepository.leftControl();
                break;
            case 3:
                observable = videoRepository.rightControl();
                break;
            case 4:
                observable = videoRepository.upControl();
                break;
            case 5:
                observable = videoRepository.downControl();
                break;
        }
        return observable;
    }
}
