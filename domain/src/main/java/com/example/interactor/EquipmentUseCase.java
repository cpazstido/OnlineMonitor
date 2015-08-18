package com.example.interactor;

import com.example.executor.PostExecutionThread;
import com.example.repository.EquipmentRepository;

import rx.Observable;

/**
 * Created by 24363 on 2015/8/13.
 */
public class EquipmentUseCase extends UseCase{

    final private EquipmentRepository equipmentRepository;

    public EquipmentUseCase(PostExecutionThread postExecutionThread, EquipmentRepository equipmentRepository) {
        super(postExecutionThread);
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.equipmentRepository.equipmentList();
    }
}
