package com.example.interactor;

import com.example.executor.PostExecutionThread;
import com.example.repository.AlarmRepository;

import rx.Observable;

/**
 * Created by 24363 on 2015/8/18.
 */
public class AlarmUseCase extends UseCase{

    final private AlarmRepository alarmRepository;

    public AlarmUseCase(PostExecutionThread postExecutionThread, AlarmRepository alarmRepository) {
        super(postExecutionThread);
        this.alarmRepository = alarmRepository;
    }

    @Override
    public Observable buildUseCaseObservable() {
        return this.alarmRepository.alarmList();
    }
}
