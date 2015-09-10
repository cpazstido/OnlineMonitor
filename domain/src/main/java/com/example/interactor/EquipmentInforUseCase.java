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

    public EquipmentInforUseCase(PostExecutionThread postExecutionThread, Scheduler subExecutionThread, EquipmentInforRepository equipmentInforRepository) {
        super(postExecutionThread,subExecutionThread);
        this.equipmentInforRepository = equipmentInforRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.equipmentInforRepository.equipmentList();
    }
}
