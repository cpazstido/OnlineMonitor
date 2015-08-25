package com.example.interactor;

import com.example.executor.PostExecutionThread;
import com.example.repository.AlarmRepository;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by 24363 on 2015/8/18.
 */
public class AlarmUseCase extends UseCase{

    final private AlarmRepository alarmRepository;
    private String equipmentName;
    private int returnType =-1;
    public AlarmUseCase(PostExecutionThread postExecutionThread, Scheduler subExecutionThread, AlarmRepository alarmRepository) {
        super(postExecutionThread,subExecutionThread);
        this.returnType = 0;
        this.alarmRepository = alarmRepository;
    }
    public AlarmUseCase(PostExecutionThread postExecutionThread,Scheduler subExecutionThread, AlarmRepository alarmRepository,String equipmentName) {
        super(postExecutionThread,subExecutionThread);
        this.returnType = 1;
        this.equipmentName = equipmentName;
        this.alarmRepository = alarmRepository;
    }

    @Override
    public Observable buildUseCaseObservable() {
        if(returnType == 1) {
            return this.alarmRepository.alarmList(equipmentName);
        }
        if(returnType == 0){
            return this.alarmRepository.alarmList();
        }
        else
            return null;
    }
}
