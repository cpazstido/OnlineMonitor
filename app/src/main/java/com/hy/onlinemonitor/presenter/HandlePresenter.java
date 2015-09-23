package com.hy.onlinemonitor.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.interactor.DefaultSubscriber;
import com.example.interactor.HandleUseCase;
import com.example.repository.HandleRepository;
import com.hy.data.repository.HandleDataRepository;
import com.hy.onlinemonitor.UIThread;
import com.hy.onlinemonitor.utile.ShowUtile;
import com.hy.onlinemonitor.view.Activity.Function.DetailedAlarmActivity;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by 24363 on 2015/9/23.
 */
public class HandlePresenter extends DefaultSubscriber implements Presenter {
    private Context mContext;
    private DetailedAlarmActivity detailedAlarmActivity;
    private HandleUseCase handleUseCase;

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        handleUseCase.unsubscribe();
    }

    @Override
    public void showViewLoading() {
        detailedAlarmActivity.showLoading();
    }

    @Override
    public void hideViewLoading() {
        detailedAlarmActivity.hideLoading();
    }

    public void setView(@NonNull DetailedAlarmActivity view) {
        mContext = view.getBaseContext();
        detailedAlarmActivity = view;
    }

    public void handlerAlarm(int alarmSn,String queryAlarmType) {
        this.showViewLoading();
        HandleRepository handleDataRepository = new HandleDataRepository(mContext, alarmSn,queryAlarmType);
        this.handleUseCase = new HandleUseCase(new UIThread(), AndroidSchedulers.mainThread(), handleDataRepository);
        this.handleUseCase.execute(new HandleSubscriber());
    }

    private class HandleSubscriber extends DefaultSubscriber<String> {

        @Override
        public void onCompleted() {
            HandlePresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            HandlePresenter.this.hideViewLoading();
            ShowUtile.toastShow(mContext, "处理失败,请重试");
            super.onError(e);
        }

        @Override
        public void onNext(String result) {
            if ("\"true\"".equals(result))
                ShowUtile.toastShow(mContext, "处理成功");
            else
                ShowUtile.toastShow(mContext, "处理失败");
        }
    }
}
