package com.hy.onlinemonitor.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.bean.DomainAlarmPage;
import com.example.interactor.AlarmUseCase;
import com.example.interactor.DefaultSubscriber;
import com.example.interactor.UseCase;
import com.example.repository.AlarmRepository;
import com.hy.data.repository.AlarmDataRepository;
import com.hy.onlinemonitor.UIThread;
import com.hy.onlinemonitor.bean.AlarmPage;
import com.hy.onlinemonitor.mapper.PageDataMapper;
import com.hy.onlinemonitor.view.AlarmListView;

import rx.android.schedulers.AndroidSchedulers;

public class AlarmPresenter implements Presenter {

    private AlarmListView alarmView;
    private int userId;
    private Context mContext;
    private UseCase getAlarmListUseCase;
    private String queryAlarmType;
    private String curProject;
    private PageDataMapper pageDataMapper;
    private int status;
    private AlarmRepository alarmDataRepository;

    public AlarmPresenter(Context mContext) {
        this.mContext = mContext;
        this.pageDataMapper = new PageDataMapper();
    }

    /**
     * 查看所有的报警
     *
     * @param queryAlarmType 查看的报警类型
     * @param status         查看的报警类型的状态(历史,或者新报警)
     * @param userId         唯一标示用户
     */
    public void initialize(int userId, String queryAlarmType, int status, int pageNumber, String curProject) {
        this.userId = userId;
        this.status = status;
        this.curProject = curProject;
        this.queryAlarmType = queryAlarmType;
        if (curProject != null)
            this.loadUserList(pageNumber, curProject);
        else
            this.loadUserList(pageNumber);

    }

    /**
     * 查看特定 equipmentName的报警信息
     *
     * @param userId         唯一标示用户
     * @param equipmentName  设备名(唯一标示)
     * @param queryAlarmType 查看的报警类型
     * @param status         查看的报警类型的状态(历史,或者新报警)
     */
    public void initialize(int userId, String equipmentName, String queryAlarmType, int status, int pageNumber) {
        this.userId = userId;
        this.status = status;
        this.queryAlarmType = queryAlarmType;
        this.loadUserList(equipmentName, pageNumber);
    }

    public void initialize(int userId, String curProject, String equipmentName, String queryAlarmType, int status, int pageNumber) {
        this.userId = userId;
        this.status = status;
        this.curProject = curProject;
        this.queryAlarmType = queryAlarmType;
        this.loadUserList(equipmentName, curProject, pageNumber);
    }

    private void loadUserList(String equipmentName, String curProject, int pageNumber) {
        this.showViewLoading();
        this.getAlarmList(equipmentName, curProject, pageNumber);
    }

    private void loadUserList(String equipmentName, int pageNumber) {
        this.showViewLoading();
        this.getAlarmList(equipmentName, pageNumber);
    }

    private void loadUserList(int pageNumber) {
        this.showViewLoading();
        this.getAlarmList(pageNumber,null);
    }

    private void loadUserList(int pageNumber, String curProject) {
        this.showViewLoading();
        this.getAlarmList(pageNumber,curProject);
    }
    @Override
    public void showViewLoading() {
        this.alarmView.showLoading();
    }

    @Override
    public void hideViewLoading() {
        this.alarmView.hideLoading();
    }

    private void getAlarmList(int pageNumber,String curProject) {   //获取所有的设备的报警
        alarmDataRepository = new AlarmDataRepository(mContext, userId, queryAlarmType, curProject, status, pageNumber);
        this.getAlarmListUseCase = new AlarmUseCase(new UIThread(), AndroidSchedulers.mainThread(), alarmDataRepository);
        this.getAlarmListUseCase.execute(new AlarmListSubscriber());
    }

    //山火,外破报警
    private void getAlarmList(String equipmentName, int pageNumber) {   //获取名称为equipmentName的设备的报警
        alarmDataRepository = new AlarmDataRepository(mContext, userId, queryAlarmType, null, status, pageNumber);
        this.getAlarmListUseCase = new AlarmUseCase(new UIThread(), AndroidSchedulers.mainThread(), alarmDataRepository, equipmentName);
        this.getAlarmListUseCase.execute(new AlarmListSubscriber());
    }

    //传感器的报警
    private void getAlarmList(String equipmentName, String curProject, int pageNumber) {
        alarmDataRepository = new AlarmDataRepository(mContext, userId, queryAlarmType, curProject, status, pageNumber);
        this.getAlarmListUseCase = new AlarmUseCase(new UIThread(), AndroidSchedulers.mainThread(), alarmDataRepository, equipmentName);
        this.getAlarmListUseCase.execute(new AlarmListSubscriber());
    }

    private class AlarmListSubscriber extends DefaultSubscriber<DomainAlarmPage> {

        @Override
        public void onCompleted() {
            AlarmPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            AlarmPresenter.this.hideViewLoading();
            Toast.makeText(mContext, "出现错误", Toast.LENGTH_SHORT).show();
            alarmView.showError(e.getMessage());
            super.onError(e);
        }

        @Override
        public void onNext(DomainAlarmPage domainAlarmPage) {
            AlarmPresenter.this.showAlarmCollectionInView(domainAlarmPage);
        }
    }

    private void showAlarmCollectionInView(DomainAlarmPage domainAlarmPage) {
        AlarmPage alarmPage = this.pageDataMapper.transform(domainAlarmPage);
        this.alarmView.renderAlarmList(alarmPage, queryAlarmType);
    }

    public void setView(@NonNull AlarmListView view) {
        this.alarmView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.getAlarmListUseCase.unsubscribe();
    }

}
