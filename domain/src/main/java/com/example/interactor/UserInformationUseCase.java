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
    private String getUser = "";
    public UserInformationUseCase(PostExecutionThread postExecutionThread, Scheduler subExecutionThread, UserRepository userRepository, int choiceType) {
        super(postExecutionThread,subExecutionThread);
        this.userRepository = userRepository;
        this.choiceType = choiceType;
    }

    public UserInformationUseCase(PostExecutionThread postExecutionThread,Scheduler subExecutionThread, UserRepository userRepository) {
        super(postExecutionThread,subExecutionThread);
        this.userRepository = userRepository;
    }

    public UserInformationUseCase(PostExecutionThread postExecutionThread,Scheduler subExecutionThread, UserRepository userRepository, String getUser) {
        super(postExecutionThread,subExecutionThread);
        this.userRepository = userRepository;
        this.getUser = getUser;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        Observable observable =null;
        if(choiceType != -1 && getUser =="") {
            observable = this.userRepository.upDataUser(choiceType);
        }
        if (choiceType == -1 && getUser !=""){
            observable = this.userRepository.getUserInfor();
        }
        if(choiceType == -1 && getUser == ""){
            observable =  this.userRepository.equipmentList();
        }
        return observable;

    }
}
