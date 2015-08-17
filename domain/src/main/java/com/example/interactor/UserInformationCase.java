package com.example.interactor;

import com.example.executor.PostExecutionThread;
import com.example.repository.UserRepository;

import rx.Observable;

/**
 * Created by 24363 on 2015/8/11.
 */
public class UserInformationCase extends UseCase {
    private UserRepository userRepository;
    private int choiceType = -1;
    private String getUser = "";
    public UserInformationCase(PostExecutionThread postExecutionThread, UserRepository userRepository, int choiceType) {
        super(postExecutionThread);
        this.userRepository = userRepository;
        this.choiceType = choiceType;
    }

    public UserInformationCase(PostExecutionThread postExecutionThread, UserRepository userRepository) {
        super(postExecutionThread);
        this.userRepository = userRepository;
    }

    public UserInformationCase(PostExecutionThread postExecutionThread, UserRepository userRepository, String getUser) {
        super(postExecutionThread);
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
