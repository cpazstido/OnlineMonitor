package com.hy.onlinemonitor.presenter;

import android.content.Context;

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
public class SMLinePresenter implements Presenter {
    private final Context mContext;
    private LineManageActivity lineManageActivity;
    private LineUseCase lineUseCase;
    private PageDataMapper pageDataMapper; //数据转换
    private LineDataRepository lineDataRepository; //数据仓库,用于获取数据
    private int userId;

    public void setLineManageActivity(LineManageActivity lineManageActivity) {
        this.lineManageActivity = lineManageActivity;
    }

    public SMLinePresenter(Context mContext) {
        this.mContext = mContext;
        this.pageDataMapper = new PageDataMapper();
    }

    @Override
    public void resume() {//在于用户交互前进行的操作

    }

    @Override
    public void pause() {//暂停时进行的操作

    }

    @Override
    public void destroy() {//销毁时进行的操作
        this.lineUseCase.unsubscribe();
    }

    @Override
    public void showViewLoading() {//显示等待对话框
        this.lineManageActivity.showLoading();
    }

    @Override
    public void hideViewLoading() {//隐藏等待对话框
        this.lineManageActivity.hideLoading();
    }

    public void loadCompany(int userId) {    //加载公司列表
        lineDataRepository = new LineDataRepository(mContext, userId); //创建一个线路数据仓库
        this.lineUseCase = new LineUseCase(new UIThread(), new UIThread().getScheduler(), lineDataRepository, 1);//创一个UseCase,通过该useCase用于与data层交互
        this.lineUseCase.execute(new CompanyListSubscriber());
    }

    private class CompanyListSubscriber extends DefaultSubscriber<List<DomainCompany>> {//订阅者

        @Override
        public void onCompleted() {//完成后,进行的操作
            lineManageActivity.firstLoadAll(); //调用lineManageActivity的第一次加载所有函数
        }

        @Override
        public void onError(Throwable e) { //错误时,调用的函数
            SMLinePresenter.this.hideViewLoading();
            lineManageActivity.showError(e.getMessage());
            super.onError(e);
        }

        @Override
        public void onNext(List<DomainCompany> companyList) { //在接受到数据时
            List<Company> mList = CompanyDataMapper.transform(companyList); //将domain的数据转换成当前bean类型数据
            lineManageActivity.setCompanyList(mList); //设置公司列表
        }
    }

    public void loadAllLine(int userId, int pageNumber) { //加载所有的线路
        showViewLoading(); //显示等待对话框
        this.userId = userId;
        lineDataRepository = new LineDataRepository(mContext, userId, -1, pageNumber);//创建线路数据仓库,并将参数传入
        this.lineUseCase = new LineUseCase(new UIThread(), AndroidSchedulers.mainThread(), lineDataRepository, 5);//创一个UseCase,通过该useCase用于与data层交互,通过最后的type来调用不同的函数
        this.lineUseCase.execute(new LinePageSubscriber());
    }

    public void loadLine(int userId, int companySn, int pageNumber) {//加载线路
        showViewLoading();//显示等待对话框
        this.userId = userId;
        lineDataRepository = new LineDataRepository(mContext, userId, companySn, pageNumber);//创建线路数据仓库,并将参数传入
        this.lineUseCase = new LineUseCase(new UIThread(), AndroidSchedulers.mainThread(), lineDataRepository, 5);//创一个UseCase,通过该useCase用于与data层交互,通过最后的type来调用不同的函数
        this.lineUseCase.execute(new LinePageSubscriber());
    }

    //添加线路
    public void addLine(int companySn, String lineName, String lineStart, String lineFinish, String lineTrend, String voltageLevel) {
        showViewLoading();
        SMLineRepository smLineRepository = new LineDataRepository(mContext, userId, companySn, lineName, lineStart, lineFinish, lineTrend, voltageLevel);
        this.lineUseCase = new LineUseCase(new UIThread(), AndroidSchedulers.mainThread(), smLineRepository, 2);
        this.lineUseCase.execute(new LinePageSubscriber());
    }

    //修改线路
    public void changeLine(int lineSn, int companySn, String lineName, String lineStart, String lineFinish, String lineTrend, String voltageLevel) {
        showViewLoading();
        SMLineRepository smLineRepository = new LineDataRepository(mContext, userId, companySn, lineName, lineStart, lineFinish, lineTrend, voltageLevel, lineSn);
        this.lineUseCase = new LineUseCase(new UIThread(), AndroidSchedulers.mainThread(), smLineRepository, 4);
        this.lineUseCase.execute(new LinePageSubscriber());
    }

    //删除线路
    public void deleteLine(int lineSn) {
        showViewLoading();
        SMLineRepository smLineRepository = new LineDataRepository(mContext, userId, lineSn, "isDelete");
        this.lineUseCase = new LineUseCase(new UIThread(), AndroidSchedulers.mainThread(), smLineRepository, 3);
        this.lineUseCase.execute(new LinePageSubscriber());
    }

    //线路对象的订阅者
    private class LinePageSubscriber extends DefaultSubscriber<DomainLinePage> {

        @Override
        public void onCompleted() {//完成后,进行的操作
            SMLinePresenter.this.hideViewLoading(); //隐藏等待对话框
        }

        @Override
        public void onError(Throwable e) {
            SMLinePresenter.this.hideViewLoading();
            lineManageActivity.showError(e.getMessage());
            lineManageActivity.setLoading();
            super.onError(e);
        }

        @Override
        public void onNext(DomainLinePage domainLinePage) {
            SMLinePresenter.this.showLinePageInView(domainLinePage);
            lineManageActivity.setLoading();
        }
    }

    private void showLinePageInView(DomainLinePage domainLinePage) { //显示线路列表在view中
        LinePage linePage = this.pageDataMapper.transform(domainLinePage); //将线路对象转换
        this.lineManageActivity.renderLinePage(linePage); //通知Activity线路数据改变
    }
}
