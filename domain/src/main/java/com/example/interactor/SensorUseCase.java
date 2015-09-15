package com.example.interactor;

import com.example.executor.PostExecutionThread;
import com.example.repository.SMSensorRepository;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by 24363 on 2015/9/14.
 */
public class SensorUseCase extends UseCase{
    private final SMSensorRepository smSensorRepository;
    private int type;

    public void setType(int type) {
        this.type = type;
    }

    public SensorUseCase(PostExecutionThread postExecutionThread, Scheduler subExecutionThread, SMSensorRepository smSensorRepository, int type) {
        super(postExecutionThread, subExecutionThread);
        this.smSensorRepository = smSensorRepository;
        this.type = type;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        Observable observable = null;
        switch (type){
            case 1:
                observable = smSensorRepository.getAllSensorType();
                break;
            case 2:
                observable = smSensorRepository.changeSensor();
                break;
        }

        return observable;
    }
}
