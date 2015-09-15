package com.hy.onlinemonitor.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.bean.DomainCompany;
import com.example.bean.DomainLinePage;
import com.example.interactor.DefaultSubscriber;
import com.example.interactor.LineUseCase;
import com.example.repository.SMLineRepository;
import com.hy.data.repository.LineDataRepository;
import com.hy.onlinemonitor.UIThread;
import com.hy.onlinemonitor.bean.Company;
import com.hy.onlinemonitor.bean.LinePage;
import com.hy.onlinemonitor.mapper.CompanyDataMapper;
import com.hy.onlinemonitor.mapper.PageDataMapper;
import com.hy.onlinemonitor.view.Activity.SystemManagement.LineManageActivity;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by 24363 on 2015/9/8.
 */
public class SMLinePresenter implements Presenter{
    private final Context mContext;
    private LineManageActivity lineManageActivity;
    private LineUseCase lineUseCase;
    private PageDataMapper pageDataMapper;
    private LineDataRepository lineDataRepository;
    private int userId;

    public void setLineManageActivity(LineManageActivity lineManageActivity) {
        this.lineManageActivity = lineManageActivity;
    }

    public SMLinePresenter(Context mContext) {
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
        this.lineUseCase.unsubscribe();
    }

    @Override
    public void showViewLoading() {
        this.lineManageActivity.showLoading();
    }

    @Override
    public void hideViewLoading() {
        this.lineManageActivity.hideLoading();
    }

    public void loadCompany(int userId) {
        lineDataRepository = new LineDataRepository(mContext,userId);
        this.lineUseCase = new LineUseCase(new UIThread(),new UIThread().getScheduler(), lineDataRepository,1);
        this.lineUseCase.execute(new CompanyListSubscriber());
    }

    private class CompanyListSubscriber extends DefaultSubscriber<List<DomainCompany>> {

        @Override
        public void onCompleted() {
            lineManageActivity.firstLoadAll();
        }

        @Override
        public void onError(Throwable e) {
            SMLinePresenter.this.hideViewLoading();
            Toast.makeText(mContext, "出现错误", Toast.LENGTH_SHORT).show();
            super.onError(e);
        }

        @Override
        public void onNext(List<DomainCompany> companyList) {
            List<Company> mList = CompanyDataMapper.transform(companyList);
            lineManageActivity.setCompanyList(mList);
        }
    }

    public void loadAllLine(int userId,int pageNumber) {
        showViewLoading();
        this.userId = userId;
        lineDataRepository = new LineDataRepository(mContext, userId,pageNumber);
        this.lineUseCase = new LineUseCase(new UIThread(), AndroidSchedulers.mainThread(), lineDataRepository, 5);
        this.lineUseCase.execute(new LinePageSubscriber());

    }

    public void loadLine(int userId,int companySn,int pageNumber) {
        showViewLoading();
        this.userId = userId;
        lineDataRepository = new LineDataRepository(mContext, userId,companySn,pageNumber);
        this.lineUseCase = new LineUseCase(new UIThread(), AndroidSchedulers.mainThread(), lineDataRepository, 5);
        this.lineUseCase.execute(new LinePageSubscriber());
    }

    public void addLine(int companySn,String lineName,String lineStart, String lineFinish,String lineTrend,String voltageLevel) {
        showViewLoading();
        SMLineRepository smLineRepository = new LineDataRepository(mContext, userId,companySn,lineName,lineStart,lineFinish,lineTrend,voltageLevel);
        this.lineUseCase = new LineUseCase(new UIThread(), AndroidSchedulers.mainThread(), smLineRepository, 2);
        this.lineUseCase.execute(new LinePageSubscriber());
    }

    public void changeLine(int lineSn,int companySn,String lineName,String lineStart, String lineFinish,String lineTrend,String voltageLevel){
        showViewLoading();
        SMLineRepository smLineRepository = new LineDataRepository(mContext, userId,companySn,lineName,lineStart,lineFinish,lineTrend,voltageLevel,lineSn);
        this.lineUseCase = new LineUseCase(new UIThread(), AndroidSchedulers.mainThread(), smLineRepository, 4);
        this.lineUseCase.execute(new LinePageSubscriber());
    }

    public void deleteLine(int lineSn){
        showViewLoading();
        SMLineRepository smLineRepository = new LineDataRepository(mContext, userId,lineSn,"isDelete");
        this.lineUseCase = new LineUseCase(new UIThread(), AndroidSchedulers.mainThread(), smLineRepository, 3);
        this.lineUseCase.execute(new LinePageSubscriber());
    }

    private class LinePageSubscriber extends DefaultSubscriber<DomainLinePage> {

        @Override
        public void onCompleted() {
            SMLinePresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            SMLinePresenter.this.hideViewLoading();
            Toast.makeText(mContext, "LinePageSubscriber+出现错误", Toast.LENGTH_SHORT).show();
            lineManageActivity.setLoading();
            super.onError(e);
        }

        @Override
        public void onNext(DomainLinePage domainLinePage) {
            SMLinePresenter.this.showLinePageInView(domainLinePage);
            lineManageActivity.setLoading();
        }
    }

    private void showLinePageInView(DomainLinePage domainLinePage) {
        LinePage linePage = this.pageDataMapper.transform(domainLinePage);
        this.lineManageActivity.renderLinePage(linePage);
    }
}
