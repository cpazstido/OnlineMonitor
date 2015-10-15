package com.example.interactor;

import com.example.executor.PostExecutionThread;
import com.example.repository.EquipmentConditionMonitorRepository;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by 24363 on 2015/10/13.
 */
public class EquipmentConditionMonitorUseCase extends UseCase{
    private final EquipmentConditionMonitorRepository equipmentConditionMonitorRepository;
    private int type;

    public void setType(int type) {
        this.type = type;
    }

    public EquipmentConditionMonitorUseCase(PostExecutionThread postExecutionThread, Scheduler subExecutionThread, EquipmentConditionMonitorRepository equipmentConditionMonitorRepository,int type) {
        super(postExecutionThread, subExecutionThread);
        this.type = type;
        this.equipmentConditionMonitorRepository = equipmentConditionMonitorRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        Observable observable = null;
        switch (type) {
            case 1:
                observable = equipmentConditionMonitorRepository.getEquipmentList();
                break;
            case 2:
                observable = equipmentConditionMonitorRepository.queryConditionMonitorData();
                break;
        }

        return observable;
    }
}
