package com.example.interactor;

import com.example.executor.PostExecutionThread;
import com.example.repository.AlarmRepository;

import rx.Observable;

/**
 * Created by 24363 on 2015/8/18.
 */
public class AlarmUseCase extends UseCase{

    final private AlarmRepository alarmRepository;
    private int equipmentId;
    private String title;
    private int returnType =-1;
    public AlarmUseCase(PostExecutionThread postExecutionThread, AlarmRepository alarmRepository,String title) {
        super(postExecutionThread);
        this.returnType = 0;
        this.alarmRepository = alarmRepository;
    }
    public AlarmUseCase(PostExecutionThread postExecutionThread, AlarmRepository alarmRepository,int equipmentId) {
        super(postExecutionThread);
        this.returnType = 1;
        this.alarmRepository = alarmRepository;
    }

    @Override
    public Observable buildUseCaseObservable() {
        if(returnType ==0) {
            return this.alarmRepository.alarmList(equipmentId);
        }
        if(returnType == 1){
            return this.alarmRepository.alarmList(title);
        }
        else
            return null;
    }
}
