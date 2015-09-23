package com.example.interactor;

import com.example.executor.PostExecutionThread;
import com.example.repository.UserRepository;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by wsw on 2015/8/10.
 */
public class LoginUseCase extends UseCase {
    final private UserRepository userRepository;
    private String loginAccount, loginPwd;
    private int type,userId,roleSn;
    public LoginUseCase(PostExecutionThread postExecutionThread, Scheduler subExecutionThread, UserRepository userRepository, String loginAccount, String loginPwd,int type) {
        super(postExecutionThread,subExecutionThread);
        this.userRepository = userRepository;
        this.loginAccount = loginAccount;
        this.loginPwd = loginPwd;
        this.type = type;
    }

    public LoginUseCase(PostExecutionThread postExecutionThread, Scheduler subExecutionThread, UserRepository userRepository, int userId, int roleSn,int type) {
        super(postExecutionThread,subExecutionThread);
        this.userRepository = userRepository;
        this.type = type;
        this.userId = userId;
        this.roleSn = roleSn;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        Observable observable =null;
        switch (type){
            case 0:
                observable = this.userRepository.user(this.loginAccount, this.loginPwd);
                break;
            case 1:
                observable = this.userRepository.getJurisdiction(this.userId, this.roleSn);
                break;
        }
        return observable;
    }

}
