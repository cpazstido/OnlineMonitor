package com.example.interactor;

import com.example.executor.PostExecutionThread;
import com.example.repository.UserRepository;

import rx.Observable;

/**
 * Created by wsw on 2015/8/10.
 */
public class LoginUseCase extends UseCase {
    private UserRepository userRepository;
    private String loginAccount, loginPwd;

    public LoginUseCase(PostExecutionThread postExecutionThread, UserRepository userRepository, String loginAccount, String loginPwd) {
        super(postExecutionThread);
        this.userRepository = userRepository;
        this.loginAccount = loginAccount;
        this.loginPwd = loginPwd;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.userRepository.user(this.loginAccount, this.loginPwd);
    }

}
