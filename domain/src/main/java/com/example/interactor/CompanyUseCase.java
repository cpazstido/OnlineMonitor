package com.example.interactor;

import com.example.executor.PostExecutionThread;
import com.example.repository.SMCompanyRepository;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by Administrator on 2015/9/15.
 */
public class CompanyUseCase extends UseCase{
    private final SMCompanyRepository companyRepository;
    private int type;

    public void setType(int type) {
        this.type = type;
    }

    public CompanyUseCase(PostExecutionThread postExecutionThread, Scheduler subExecutionThread, SMCompanyRepository companyRepository, int type) {
        super(postExecutionThread, subExecutionThread);
        this.companyRepository = companyRepository;
        this.type = type;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        Observable observable = null;
        switch (type){
            case 1:
                observable = companyRepository.getCompanyList();
                break;
            case 2:
                observable = companyRepository.modifCompany();
                break;
            case 3:
                observable = companyRepository.deleteCompany();
                break;
            case 4:
                observable = companyRepository.getParentCompanyList();
                break;
            case 5:
                observable = companyRepository.addCompany();

        }

        return observable;
    }
}
