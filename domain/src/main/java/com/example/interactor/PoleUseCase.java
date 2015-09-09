package com.example.interactor;

import com.example.executor.PostExecutionThread;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by 24363 on 2015/9/9.
 */
public class PoleUseCase extends UseCase{

    protected PoleUseCase(PostExecutionThread postExecutionThread, Scheduler subExecutionThread) {
        super(postExecutionThread, subExecutionThread);
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return null;
    }
}
