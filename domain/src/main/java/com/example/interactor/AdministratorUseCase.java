package com.example.interactor;

import com.example.executor.PostExecutionThread;
import com.example.repository.SMAdministratorRepository;

import rx.Observable;
import rx.Scheduler;

public class AdministratorUseCase extends UseCase{
    private SMAdministratorRepository smAdministratorRepository;

    public void setType(int type) {
        this.type = type;
    }
    private int type;

    public AdministratorUseCase(PostExecutionThread postExecutionThread, Scheduler subExecutionThread, SMAdministratorRepository smAdministratorRepository, int type) {
        super(postExecutionThread, subExecutionThread);
        this.smAdministratorRepository = smAdministratorRepository;
        this.type= type;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        Observable observable =null;

        switch (type){
            case 1:
                observable = smAdministratorRepository.companyList();
                break;
            case 2:
                observable = smAdministratorRepository.roleList();
                break;
            case 3:
                observable = smAdministratorRepository.administratorList();
                break;
            case 4:
                observable = smAdministratorRepository.addAdministrator();
                break;
            case 5:
                observable = smAdministratorRepository.changeAdministrator();
                break;
            case 6:
                observable = smAdministratorRepository.deleteAdministrator();
                break;
        }

        return observable;
    }
}
