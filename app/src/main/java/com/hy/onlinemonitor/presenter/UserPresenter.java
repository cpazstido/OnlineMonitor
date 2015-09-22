package com.hy.onlinemonitor.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.bean.DomainUser;
import com.example.interactor.DefaultSubscriber;
import com.example.interactor.UseCase;
import com.example.interactor.UserInformationUseCase;
import com.example.repository.UserRepository;
import com.hy.data.entity.mapper.UserEntityDataMapper;
import com.hy.data.repository.UserDataRepository;
import com.hy.data.repository.datasource.UserDataStoreFactory;
import com.hy.onlinemonitor.UIThread;
import com.hy.onlinemonitor.bean.User;
import com.hy.onlinemonitor.mapper.UserDataMapper;
import com.hy.onlinemonitor.view.Activity.BaseActivity;
import com.hy.onlinemonitor.view.Activity.TypeSelectionActivity;

import java.util.List;

import rx.schedulers.Schedulers;

/**
 * Created by 24363 on 2015/8/12.
 */
public class UserPresenter extends DefaultSubscriber implements Presenter {
    private TypeSelectionActivity typeSelectionActivity;

    private BaseActivity baseActivity;
    private UserRepository userRepository;
    private UseCase UserInformationCase;
    private String message;
    private String setResult;
    private Context mContext;
    private int choiceType;
    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.UserInformationCase.unsubscribe();
    }

    @Override
    public void showViewLoading() {
    }

    @Override
    public void hideViewLoading() {

    }

    public void getUserInformation(Context mContext) {
        userRepository = new UserDataRepository(new UserDataStoreFactory(mContext), new UserEntityDataMapper());
        this.UserInformationCase = new UserInformationUseCase(new UIThread(),new UIThread().getScheduler(), userRepository,0);
        this.UserInformationCase.execute(new UserInformationSubscriber());
    }

    private final class UserInformationSubscriber extends DefaultSubscriber<DomainUser> {
        @Override
        public void onCompleted() {
            baseActivity.setupUI();
            baseActivity.initialize();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(DomainUser domainUser) {
            User user = new UserDataMapper().transform(domainUser);
            baseActivity.setUser(user);
            baseActivity.getUserNameTV().setText(domainUser.getCompanyName());

        }
    }

    public void upDataUser(int choiceType, Context mContext) {
        this.mContext = mContext;
        userRepository = new UserDataRepository(new UserDataStoreFactory(mContext));
        this.UserInformationCase = new UserInformationUseCase(new UIThread(),new UIThread().getScheduler(), userRepository, choiceType,1);
        this.UserInformationCase.execute(new upDataUserSubscriber());
    }

    private final class upDataUserSubscriber extends DefaultSubscriber<String> {
        @Override
        public void onCompleted() {
            hideViewLoading();
            if (message != null) {
                if ("success".equals(message))
                    typeSelectionActivity.GotoActivity();
            }
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(String str) {
            message = str;
        }
    }

    public void getUserEquipmentList(Context mContext) {
        userRepository = new UserDataRepository(new UserDataStoreFactory(mContext));
        this.UserInformationCase = new UserInformationUseCase(new UIThread(), Schedulers.io(), userRepository,2);
        this.UserInformationCase.execute(new EquipmentListSubscriber());
    }

    private final class EquipmentListSubscriber extends DefaultSubscriber<List<String>> {
        @Override
        public void onCompleted() {
            typeSelectionActivity.initialize();
        }

        @Override
        public void onError(Throwable e) {
            Log.e("sub", "onError");
        }

        @Override
        public void onNext(List<String> ownedEquipmentList) {
            for(String ll:ownedEquipmentList){
                Log.e("List<String>",ll);
            }
            typeSelectionActivity.setOwnedEquipmentList(ownedEquipmentList);
        }
    }

    public void setBaseActivity(@NonNull BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    public void setTypeSelectionActivity(@NonNull TypeSelectionActivity typeSelectionActivity) {
        this.typeSelectionActivity = typeSelectionActivity;
    }

    public void setCurrentPorject(int choiceType, Context mContext,String curProject){
        this.showViewLoading();
        this.mContext = mContext;
        this.choiceType =choiceType;
        userRepository = new UserDataRepository(new UserDataStoreFactory(mContext),curProject);
        this.UserInformationCase = new UserInformationUseCase(new UIThread(), new UIThread().getScheduler(), userRepository,3);
        this.UserInformationCase.execute(new CurrentPorjectSubscriber());
    }

    private final class CurrentPorjectSubscriber extends DefaultSubscriber<String> {
        @Override
        public void onCompleted() {
            UserPresenter.this.upDataUser(choiceType,mContext);
        }

        @Override
        public void onError(Throwable e) {
            Log.e("sub", "onError");
        }

        @Override
        public void onNext(String result) {
            setResult = result;
        }
    }
}
