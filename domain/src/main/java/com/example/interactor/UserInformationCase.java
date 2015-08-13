package com.example.interactor;

import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.repository.UserRepository;

import java.util.Objects;

import rx.Observable;

/**
 * Created by 24363 on 2015/8/11.
 */
public class UserInformationCase extends UseCase {
    private UserRepository userRepository;
    private int choiceType = -1;
    private String getUser = "";
    public UserInformationCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, UserRepository userRepository, int choiceType) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
        this.choiceType = choiceType;
    }

    public UserInformationCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, UserRepository userRepository) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    public UserInformationCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, UserRepository userRepository, String getUser) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
        this.getUser = getUser;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        Observable observable =null;
        if(choiceType != -1 && Objects.equals("", getUser)) {
            observable = this.userRepository.upDataUser(choiceType);
        }
        if (choiceType == -1 && !Objects.equals(getUser, "")){
            observable = this.userRepository.getUserInfor();
        }
        if(choiceType == -1 && Objects.equals(getUser, "")){
            observable =  this.userRepository.equipmentList();
        }
        return observable;

    }
}
