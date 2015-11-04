package com.example.interactor;

import com.example.executor.PostExecutionThread;
import com.example.repository.ConditionMonitoringRepository;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by 24363 on 2015/10/29.
 */
public class ConditionMonitoringUseCase extends UseCase {

    private final ConditionMonitoringRepository conditionMonitoringRepository;
    private int type;

    public void setType(int type) {
        this.type = type;
    }

    public ConditionMonitoringUseCase(PostExecutionThread postExecutionThread, Scheduler subExecutionThread, ConditionMonitoringRepository conditionMonitoringRepository, int type) {
        super(postExecutionThread, subExecutionThread);
        this.conditionMonitoringRepository = conditionMonitoringRepository;
        this.type = type;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        Observable observable = null;
        switch (type) {
            case 1:
                observable = conditionMonitoringRepository.getConditionMonitoringType();
                break;
            case 2:
                observable = conditionMonitoringRepository.getAeolianVibration();
                break;
            case 3:
                observable = conditionMonitoringRepository.getIceCoating();
                break;
            case 4:
                observable = conditionMonitoringRepository.getConductorSag();
                break;
            case 5:
                observable = conditionMonitoringRepository.getConductorSwingWithWind();
                break;
            case 6:
                observable = conditionMonitoringRepository.getPoleStatus();
                break;
            case 7:
                observable = conditionMonitoringRepository.getMicroclimate();
                break;
            case 8:
                observable = conditionMonitoringRepository.getEquipmentList();
        }
        return observable;
    }
}
