package com.hy.onlinemonitor.presenter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.bean.DomainUser;
import com.example.interactor.DefaultSubscriber;
import com.example.interactor.LoginUseCase;
import com.example.interactor.UseCase;
import com.example.interactor.UserInformationCase;
import com.example.repository.UserRepository;
import com.hy.data.entity.mapper.UserEntityDataMapper;
import com.hy.data.executor.JobExecutor;
import com.hy.data.repository.UserDataRepository;
import com.hy.data.repository.datasource.UserDataStoreFactory;
import com.hy.onlinemonitor.UIThread;
import com.hy.onlinemonitor.bean.User;
import com.hy.onlinemonitor.view.Activity.BaseActivity;
import com.hy.onlinemonitor.view.Activity.TypeSelectionActivity;
import com.hy.onlinemonitor.view.JumpView;

import java.util.List;

/**
 * Created by wsw on 2015/8/10.
 */
public class LoginPresenter extends DefaultSubscriber implements Presenter{

    private JumpView LoginView;
    private TypeSelectionActivity typeSelectionActivity;
    private BaseActivity baseActivity;
    private Context mContext;
    private String loginAccount;
    private String loginPwd;
    private UserRepository userRepository;
    private UseCase loginUseCase;
    private UseCase UserInformationCase;
    private User userInformation;

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.loginUseCase.unsubscribe();
        this.UserInformationCase.unsubscribe();

    }

    public void setView(@NonNull JumpView view){
        this.LoginView = view;
        mContext= view.getContext();
    }
    public void setTypeSelectionActivity(@NonNull TypeSelectionActivity typeSelectionActivity){
        this.typeSelectionActivity = typeSelectionActivity;
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

    private void showViewLoading() {
        LoginView.showLoading();
    }

    private void hideViewLoading() {
        LoginView.hideLoading();
    }

    private void GotoView() {
        LoginView.GoToView();
    }

    private void userLogin() {
        userRepository = new UserDataRepository(new UserDataStoreFactory(mContext),new UserEntityDataMapper());
        this.loginUseCase = new LoginUseCase(new JobExecutor(),new UIThread(),userRepository,loginAccount,loginPwd);
        this.loginUseCase.execute(new UserSubscriber());
    }

    public void setBaseAcitvity(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    private final class UserSubscriber extends DefaultSubscriber<DomainUser>{
        @Override
        public void onCompleted() {
            LoginPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            LoginPresenter.this.hideViewLoading();
            //显示错误信息
        }

        @Override
        public void onNext(DomainUser domainUser) {
            //这里应该调用函数进行跳转
            LoginPresenter.this.GotoView();
        }
    }

    public void getUserEquipmentList(Context mContext) {
        userRepository = new UserDataRepository(new UserDataStoreFactory(mContext));
        this.UserInformationCase = new UserInformationCase(new JobExecutor(),new UIThread(),userRepository);
        this.UserInformationCase.execute(new EquipmentListSubscriber());
    }

    private final class EquipmentListSubscriber extends DefaultSubscriber<List<String>>{
        @Override
        public void onCompleted() {
            typeSelectionActivity.initDatas();
            typeSelectionActivity.initListener();
        }

        @Override
        public void onError(Throwable e) {
            Log.e("sub","onError");
            LoginPresenter.this.hideViewLoading();
        }

        @Override
        public void onNext(List<String> ownedEquipmentList) {
            Log.e("sub", "onNext");
            //这里返回数据
            typeSelectionActivity.setOwnedEquipmentList(ownedEquipmentList);
        }
    }

    public void getUserInformation(Context mContext){
        userRepository = new UserDataRepository(new UserDataStoreFactory(mContext),new UserEntityDataMapper());
        this.UserInformationCase = new UserInformationCase(new JobExecutor(),new UIThread(),userRepository);
        this.UserInformationCase.execute(new UserInformationSubscriber());
    }

    private final class UserInformationSubscriber extends DefaultSubscriber<User> {
        @Override
        public void onCompleted() {
            baseActivity.setUser(userInformation);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(User user) {
            userInformation = user;
        }
    }

    public void upDataUser(int choiceType) {
        userRepository = new UserDataRepository(new UserDataStoreFactory(mContext));
        userRepository.upDataUser(choiceType);
    }
}
