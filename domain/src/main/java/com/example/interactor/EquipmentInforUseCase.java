package com.example.interactor;

import com.example.executor.PostExecutionThread;
import com.example.repository.EquipmentInforRepository;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by 24363 on 2015/8/13.
 */
public class EquipmentInforUseCase extends UseCase{

    final private EquipmentInforRepository equipmentInforRepository;
    private int type;
    public EquipmentInforUseCase(PostExecutionThread postExecutionThread, Scheduler subExecutionThread, EquipmentInforRepository equipmentInforRepository, int type) {
        super(postExecutionThread,subExecutionThread);
        this.equipmentInforRepository = equipmentInforRepository;
        this.type = type;
    }

    @Override
    protected Observable buildUseCaseObservable() {

        Observable observable = null;
        switch (type){
            case 1:
                observable = equipmentInforRepository.equipmentList();
                break;
            case 2:
                observable = equipmentInforRepository.getAllTower();
                break;
            case 3:
                observable = equipmentInforRepository.searchByLineSn();
                break;
            case 4:
                observable = equipmentInforRepository.searchByName();
                break;
        }
        return observable;
    }
}
