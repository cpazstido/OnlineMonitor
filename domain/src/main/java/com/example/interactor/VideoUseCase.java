package com.example.interactor;

import com.example.executor.PostExecutionThread;
import com.example.repository.VideoRepository;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by 24363 on 2015/8/27.
 */
public class VideoUseCase extends UseCase{

    private final VideoRepository videoRepository;

    public VideoUseCase(PostExecutionThread postExecutionThread, Scheduler subExecutionThread, VideoRepository videoRepository) {
        super(postExecutionThread, subExecutionThread);
        this.videoRepository = videoRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return videoRepository.getVideoUrl();
    }
}
