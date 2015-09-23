package com.example.interactor;

import com.example.executor.PostExecutionThread;
import com.example.repository.HandleRepository;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by 24363 on 2015/9/23.
 */
public class HandleUseCase extends UseCase{

    private final HandleRepository handleRepository;

    public HandleUseCase(PostExecutionThread postExecutionThread, Scheduler subExecutionThread, HandleRepository handleRepository) {
        super(postExecutionThread, subExecutionThread);
        this.handleRepository = handleRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return handleRepository.handleAlarm();
    }
}
