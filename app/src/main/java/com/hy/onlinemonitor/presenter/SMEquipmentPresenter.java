package com.hy.onlinemonitor.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.bean.DomainEquipmentPage;
import com.example.bean.DomainLine;
import com.example.interactor.DefaultSubscriber;
import com.example.interactor.EquipmentUseCase;
import com.example.repository.SMEquipmentRepository;
import com.hy.data.repository.EquipmentDataRepository;
import com.hy.onlinemonitor.UIThread;
import com.hy.onlinemonitor.bean.EquipmentPage;
import com.hy.onlinemonitor.bean.Line;
import com.hy.onlinemonitor.mapper.LineDataMapper;
import com.hy.onlinemonitor.mapper.PageDataMapper;
import com.hy.onlinemonitor.view.Activity.SystemManagement.EquipmentManageActivity;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by 24363 on 2015/9/11.
 */
public class SMEquipmentPresenter implements Presenter{
    private EquipmentManageActivity equipmentManageActivity;
    private final Context mContext;
    private EquipmentUseCase equipmentUseCase;
    private PageDataMapper pageDataMapper;
    private int userId;

    public SMEquipmentPresenter(Context mContext) {
        this.mContext = mContext;
        this.pageDataMapper = new PageDataMapper();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        equipmentUseCase.unsubscribe();
    }

    @Override
    public void showViewLoading() {
        equipmentManageActivity.showLoading();
    }

    @Override
    public void hideViewLoading() {
        equipmentManageActivity.hideLoading();
    }

    public void setEquipmentManageActivity(EquipmentManageActivity equipmentManageActivity) {
        this.equipmentManageActivity = equipmentManageActivity;
    }

    public void loadAllPole(int userId) {
        showViewLoading();
        this.userId =userId;
        SMEquipmentRepository smEquipmentRepository = new EquipmentDataRepository( userId,-1,mContext);
        this.equipmentUseCase = new EquipmentUseCase(new UIThread(), AndroidSchedulers.mainThread(), smEquipmentRepository, 1);
        this.equipmentUseCase.execute(new TowerListSubscriber());
    }

    private class TowerListSubscriber extends DefaultSubscriber<List<DomainLine>> {
        @Override
        public void onCompleted() {
            hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            SMEquipmentPresenter.this.hideViewLoading();
            Toast.makeText(mContext, "AdminLineListOnlySubscriber出现错误", Toast.LENGTH_SHORT).show();
            super.onError(e);
        }

        @Override
        public void onNext(List<DomainLine> domainLines) {
            List<Line> mList = LineDataMapper.transform(domainLines);
            equipmentManageActivity.setLines(mList);
        }
    }

    public void getEquipmentPage(int poleSn) {
        showViewLoading();
        SMEquipmentRepository smEquipmentRepository = new EquipmentDataRepository(mContext, userId,poleSn);
        this.equipmentUseCase = new EquipmentUseCase(new UIThread(), AndroidSchedulers.mainThread(), smEquipmentRepository, 2);
        this.equipmentUseCase.execute(new EquipmentPageSubscriber());
    }

    public void addEquipment(int poleSn,String deviceID, String dvrId, String angleRelativeToNorthPole, String deviceType, int isSendMessage, String cma_id, String sensor_id, String equipment_id) {
        showViewLoading();
        SMEquipmentRepository smEquipmentRepository = new EquipmentDataRepository(mContext, userId,poleSn,deviceID,dvrId,angleRelativeToNorthPole,deviceType,isSendMessage,cma_id,sensor_id,equipment_id);
        this.equipmentUseCase = new EquipmentUseCase(new UIThread(), AndroidSchedulers.mainThread(), smEquipmentRepository, 3);
        this.equipmentUseCase.execute(new EquipmentPageSubscriber());
    }

    public void deleteEquipment(int sn) {
        showViewLoading();
        SMEquipmentRepository smEquipmentRepository = new EquipmentDataRepository(sn,mContext, userId);
        this.equipmentUseCase = new EquipmentUseCase(new UIThread(), AndroidSchedulers.mainThread(), smEquipmentRepository, 4);
        this.equipmentUseCase.execute(new EquipmentPageSubscriber());

    }

    public void changeEquipment(String deviceID, String dvrId, String angleRelativeToNorthPole, String deviceType, int isSendMessage, String cma_id, String sensor_id, String equipment_id,int equipmentSn) {
        showViewLoading();
        SMEquipmentRepository smEquipmentRepository = new EquipmentDataRepository(mContext, userId,deviceID,dvrId,angleRelativeToNorthPole,deviceType,isSendMessage,cma_id,sensor_id,equipment_id,equipmentSn);
        this.equipmentUseCase = new EquipmentUseCase(new UIThread(), AndroidSchedulers.mainThread(), smEquipmentRepository, 5);
        this.equipmentUseCase.execute(new EquipmentPageSubscriber());

    }
    private class EquipmentPageSubscriber extends DefaultSubscriber<DomainEquipmentPage> {
        @Override
        public void onCompleted() {
            SMEquipmentPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            SMEquipmentPresenter.this.hideViewLoading();
            Toast.makeText(mContext, "AdminLineListOnlySubscriber出现错误", Toast.LENGTH_SHORT).show();
            super.onError(e);
        }

        @Override
        public void onNext(DomainEquipmentPage domainEquipmentPage) {
            EquipmentPage equipmentPage = pageDataMapper.transform(domainEquipmentPage);
            equipmentManageActivity.renderEquipmentList(equipmentPage);
        }
    }
}
