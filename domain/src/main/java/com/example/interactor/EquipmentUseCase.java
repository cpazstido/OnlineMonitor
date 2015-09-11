package com.example.interactor;

import com.example.executor.PostExecutionThread;
import com.example.repository.SMEquipmentRepository;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by 24363 on 2015/9/11.
 */
public class EquipmentUseCase extends UseCase{

    private final SMEquipmentRepository smEquipmentRepository;
    private int type;

    public EquipmentUseCase(PostExecutionThread postExecutionThread, Scheduler subExecutionThread, SMEquipmentRepository smEquipmentRepository, int type) {
        super(postExecutionThread, subExecutionThread);
        this.smEquipmentRepository = smEquipmentRepository;
        this.type = type;
    }

    public void setType(int type) {
        this.type = type;
    }
    @Override
    protected Observable buildUseCaseObservable() {
        Observable observable = null;
        switch (type){
            case 1:
                observable = smEquipmentRepository.getAllTower();
                break;
            case 2:
                observable = smEquipmentRepository.getEquipmentPage();
                break;
            case 3:
                observable = smEquipmentRepository.addEquipment();
                break;
            case 4:
                observable = smEquipmentRepository.deleteEquipment();
                break;
            case 5:
                observable = smEquipmentRepository.changeEquipment();
                break;
        }
        return observable;
    }
}
