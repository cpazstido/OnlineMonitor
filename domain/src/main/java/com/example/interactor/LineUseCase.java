package com.example.interactor;

import com.example.executor.PostExecutionThread;
import com.example.repository.SMLineRepository;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by 24363 on 2015/9/8.
 */
public class LineUseCase extends UseCase{

    private final SMLineRepository smLineRepository;
    private int type;

    public void setType(int type) {
        this.type = type;
    }

    public LineUseCase(PostExecutionThread postExecutionThread, Scheduler subExecutionThread, SMLineRepository smLineRepository, int type) {
        super(postExecutionThread, subExecutionThread);
        this.smLineRepository = smLineRepository;
        this.type = type;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        Observable observable = null;
        switch (type){
            case 1:
                observable = smLineRepository.getCompanyList();
                break;
            case 2:
                observable = smLineRepository.addLine();
                break;
            case 3:
                observable = smLineRepository.deleteLine();
                break;
            case 4:
                observable = smLineRepository.changeLine();
                break;
            case 5:
                observable = smLineRepository.getLinePage();
                break;
            case 6:
                observable = smLineRepository.getAllLine();
                break;
        }

        return observable;
    }
}
