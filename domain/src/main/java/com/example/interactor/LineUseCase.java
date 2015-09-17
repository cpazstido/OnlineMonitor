package com.example.interactor;

import com.example.executor.PostExecutionThread;
import com.example.repository.SMLineRepository;

import rx.Observable;
import rx.Scheduler;

public class LineUseCase extends UseCase{ //用于data与Presenter交互

    private final SMLineRepository smLineRepository;
    private int type; //类型

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
                observable = smLineRepository.getCompanyList(); //得到公司列表
                break;
            case 2:
                observable = smLineRepository.addLine(); //添加线路
                break;
            case 3:
                observable = smLineRepository.deleteLine(); //删除线路
                break;
            case 4:
                observable = smLineRepository.changeLine(); //修改线路
                break;
            case 5:
                observable = smLineRepository.getLinePage(); //得到线路page
                break;
            case 6:
                observable = smLineRepository.getAllLine();
                break;
        }

        return observable;
    }
}
