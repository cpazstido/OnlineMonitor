package com.example.interactor;

import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.repository.UserRepository;

import rx.Observable;

/**
 * Created by 24363 on 2015/8/11.
 */
public class UserInformationCase extends UseCase {
    private UserRepository userRepository;

    public UserInformationCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, UserRepository userRepository) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.userRepository.equipmentList();
    }
}
