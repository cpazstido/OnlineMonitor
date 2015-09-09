package com.example.interactor;

import com.example.executor.PostExecutionThread;
import com.example.repository.SMPoleRepository;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by 24363 on 2015/9/9.
 */
public class PoleUseCase extends UseCase{
    private final SMPoleRepository smPoleRepository;
    private int type;

    public void setType(int type) {
        this.type = type;
    }

    public PoleUseCase(PostExecutionThread postExecutionThread, Scheduler subExecutionThread, SMPoleRepository smPoleRepository, int type) {
        super(postExecutionThread, subExecutionThread);
        this.smPoleRepository = smPoleRepository;
        this.type = type;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        Observable observable = null;
        switch (type){
            case 1:
                observable = smPoleRepository.getAllLine();
                break;
            case 2:
                observable = smPoleRepository.getPolePage();
                break;
            case 3:
                observable = smPoleRepository.addPole();
                break;
            case 4:
                observable = smPoleRepository.deletePole();
                break;
            case 5:
                observable = smPoleRepository.changePole();
                break;
        }

        return observable;
    }
}
