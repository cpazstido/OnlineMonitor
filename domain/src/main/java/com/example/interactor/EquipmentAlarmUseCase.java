package com.example.interactor;

import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.repository.EquipmentRepository;

import rx.Observable;

/**
 * Created by 24363 on 2015/8/13.
 */
public class EquipmentAlarmUseCase extends UseCase{

    private EquipmentRepository equipmentRepository;

    public EquipmentAlarmUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, EquipmentRepository equipmentRepository) {
        super(threadExecutor, postExecutionThread);
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.equipmentRepository.equipmentAlarmList();
    }
}
