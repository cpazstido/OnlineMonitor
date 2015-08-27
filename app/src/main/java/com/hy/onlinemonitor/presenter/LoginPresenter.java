package com.hy.onlinemonitor.presenter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.bean.DomainUser;
import com.example.interactor.DefaultSubscriber;
import com.example.interactor.LoginUseCase;
import com.example.interactor.UseCase;
import com.example.repository.UserRepository;
import com.hy.data.entity.mapper.UserEntityDataMapper;
import com.hy.data.repository.UserDataRepository;
import com.hy.data.repository.datasource.UserDataStoreFactory;
import com.hy.onlinemonitor.UIThread;
import com.hy.onlinemonitor.view.JumpView;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by wsw on 2015/8/10.
 */
public class LoginPresenter extends DefaultSubscriber implements Presenter{

    private JumpView LoginView;
    private Context mContext;
    private String loginAccount;
    private String loginPwd;
    private UseCase loginUseCase;


    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.loginUseCase.unsubscribe();
    }

    public void setView(@NonNull JumpView view){
        this.LoginView = view;
        mContext= view.getContext();
    }

    public void initialize(String loginAccount,String loginPwd){
        this.loginAccount = loginAccount;
        this.loginPwd = loginPwd;
        this.loadUserInfromation();
    }

    private void loadUserInfromation() {
        this.showViewLoading();
        this.userLogin();
    }

    public void showViewLoading() {
        LoginView.showLoading();
    }

    public void hideViewLoading() {
        LoginView.hideLoading();
    }

    private void GotoView() {
        LoginView.GoToView();
    }

    private void userLogin() {
        UserRepository userRepository = new UserDataRepository(new UserDataStoreFactory(mContext), new UserEntityDataMapper());
        this.loginUseCase = new LoginUseCase(new UIThread(), AndroidSchedulers.mainThread(), userRepository,loginAccount,loginPwd);
        this.loginUseCase.execute(new UserSubscriber());
    }

    private final class UserSubscriber extends DefaultSubscriber<DomainUser>{
        @Override
        public void onCompleted() {
            Log.e("error","onCompleted");
            LoginPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            Log.e("LoginPresenter","onError");
            Toast.makeText(mContext, "登录失败,请重试", Toast.LENGTH_SHORT).show();
            LoginPresenter.this.hideViewLoading();
        }

        @Override
        public void onNext(DomainUser domainUser) {
            //这里应该调用函数进行跳转
            LoginPresenter.this.GotoView();
        }
    }
}
