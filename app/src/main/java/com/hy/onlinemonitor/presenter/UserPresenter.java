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
import com.rey.material.widget.SnackBar;

import java.util.List;

/**
 * Created by 24363 on 2015/8/12.
 */
public class UserPresenter extends DefaultSubscriber implements Presenter {
    private TypeSelectionActivity typeSelectionActivity;
    private BaseActivity baseActivity;
    private UserRepository userRepository;
    private UseCase UserInformationCase;
    private String message;
    private Context mContext;

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
        this.UserInformationCase = new UserInformationUseCase(new UIThread(), userRepository, "getUserInformation");
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
            baseActivity.getUserNameTV().setText(domainUser.getUserName());
        }
    }


    public void upDataUser(int choiceType, Context mContext) {
        this.mContext = mContext;
        userRepository = new UserDataRepository(new UserDataStoreFactory(mContext));
        this.UserInformationCase = new UserInformationUseCase(new UIThread(), userRepository, choiceType);
        this.UserInformationCase.execute(new upDataUserSubscriber());
    }

    private final class upDataUserSubscriber extends DefaultSubscriber<String> {
        @Override
        public void onCompleted() {
            if (message != null) {
                if ("success".equals(message))
                    typeSelectionActivity.GotoActivity();
                else
                    new SnackBar(mContext).text("错误").show();
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
        this.UserInformationCase = new UserInformationUseCase(new UIThread(), userRepository);
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
            typeSelectionActivity.setOwnedEquipmentList(ownedEquipmentList);
        }
    }

    public void setBaseAcitvity(@NonNull BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    public void setTypeSelectionActivity(@NonNull TypeSelectionActivity typeSelectionActivity) {
        this.typeSelectionActivity = typeSelectionActivity;
    }

}
