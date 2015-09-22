package com.example.interactor;

import com.example.executor.PostExecutionThread;
import com.example.repository.UserRepository;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by 24363 on 2015/8/11.
 */
public class UserInformationUseCase extends UseCase {
    private final UserRepository userRepository;
    private int choiceType = -1;
    private int type;
    public UserInformationUseCase(PostExecutionThread postExecutionThread, Scheduler subExecutionThread, UserRepository userRepository, int type) {
        super(postExecutionThread,subExecutionThread);
        this.userRepository = userRepository;
        this.type = type;
    }

    public UserInformationUseCase(PostExecutionThread postExecutionThread,Scheduler subExecutionThread, UserRepository userRepository,int choiceType,int type) {
        super(postExecutionThread,subExecutionThread);
        this.userRepository = userRepository;
        this.type = type;
        this.choiceType = choiceType;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        Observable observable =null;
        switch (type){
            case 0:
                observable = this.userRepository.getUserInfor();
                break;
            case 1:
                observable = this.userRepository.upDataUser(choiceType);
                break;
            case 2:
                observable =  this.userRepository.equipmentList();
                break;
            case 3:
                observable =  this.userRepository.setCurrentPorject();
                break;
        }
        return observable;

    }
}
