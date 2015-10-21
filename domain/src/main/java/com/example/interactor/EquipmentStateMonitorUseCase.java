package com.example.interactor;

import com.example.executor.PostExecutionThread;
import com.example.repository.EquipmentStateMonitorRepository;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by 24363 on 2015/10/17.
 */
public class EquipmentStateMonitorUseCase extends UseCase {
    private final EquipmentStateMonitorRepository equipmentStateMonitorRepository;
    private int type;

    public void setType(int type) {
        this.type = type;
    }

    public EquipmentStateMonitorUseCase(PostExecutionThread postExecutionThread, Scheduler subExecutionThread, EquipmentStateMonitorRepository equipmentStateMonitorRepository, int type) {
        super(postExecutionThread, subExecutionThread);
        this.type = type;
        this.equipmentStateMonitorRepository = equipmentStateMonitorRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        Observable observable = null;
        switch (type) {
            case 1:
                observable = equipmentStateMonitorRepository.getAllLine();
                break;
            case 2:
                observable = equipmentStateMonitorRepository.loadDeviceInformation();
                break;
        }

        return observable;
    }
}
