package com.example.interactor;

import com.example.executor.PostExecutionThread;
import com.example.repository.SMJurisdictionRepository;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by 24363 on 2015/9/7.
 */
public class JurisdictionUseCase extends UseCase{

    private SMJurisdictionRepository smJurisdictionRepository;
    private int type;

    public JurisdictionUseCase(PostExecutionThread postExecutionThread, Scheduler subExecutionThread, SMJurisdictionRepository smJurisdictionRepository, int type) {
        super(postExecutionThread, subExecutionThread);
        this.smJurisdictionRepository = smJurisdictionRepository;
        this.type= type;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        Observable observable = null;
        switch (type){
            case 1:
                observable = smJurisdictionRepository.getRolePage();
                break;
            case 2:
                observable = smJurisdictionRepository.addRole();
                break;
            case 3:
                observable = smJurisdictionRepository.deleteRole();
                break;
            case 4:
                observable = smJurisdictionRepository.changeRole();
                break;
            case 5:
                observable = smJurisdictionRepository.jurisdictionChange();
                break;
        }

        return observable;
    }
}
