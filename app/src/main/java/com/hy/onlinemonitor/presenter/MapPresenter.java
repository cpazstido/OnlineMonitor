package com.hy.onlinemonitor.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.bean.DomainMap;
import com.example.interactor.DefaultSubscriber;
import com.example.interactor.MapUseCase;
import com.example.interactor.UseCase;
import com.hy.data.repository.MapDataRepository;
import com.hy.onlinemonitor.UIThread;
import com.hy.onlinemonitor.bean.Map;
import com.hy.onlinemonitor.mapper.MapDataMapper;
import com.hy.onlinemonitor.view.Activity.Function.MapActivity;

import java.util.Collection;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by 24363 on 2015/8/19.
 */

public class MapPresenter implements Presenter{
    private Context mContext;
    private MapActivity mapActivity;
    private int userId;
    private int selectedType;
    private MapDataMapper mapDataMapper;
    private UseCase getMapListUseCase;
    public MapPresenter(Context mContext) {
        this.mContext = mContext;
        this.mapDataMapper = new MapDataMapper();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.getMapListUseCase.unsubscribe();
    }

    @Override
    public void showViewLoading() {
        this.mapActivity.showLoading();
    }

    @Override
    public void hideViewLoading() {
        this.mapActivity.hideLoading();
    }

    public void setView(MapActivity mapActivity) {
        this.mapActivity = mapActivity;
    }

    public void initialize(int userId,int selectedType) {
        this.selectedType = selectedType;
        this.userId = userId;
        this.loadMapList();
    }

    private void loadMapList() {
        this.showViewLoading();
        this.getMapList();
    }

    private void getMapList() {
        MapDataRepository mapDataRepository = new MapDataRepository(mContext, userId,selectedType);
        this.getMapListUseCase = new MapUseCase(new UIThread(), AndroidSchedulers.mainThread(), mapDataRepository);
        this.getMapListUseCase.execute(new MapListSubscriber());

    }

    private class MapListSubscriber extends DefaultSubscriber<List<DomainMap>> {
        @Override
        public void onCompleted() {
            MapPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            MapPresenter.this.hideViewLoading();
            Toast.makeText(mContext,"出现错误",Toast.LENGTH_SHORT).show();
            super.onError(e);
        }

        @Override
        public void onNext(List<DomainMap> domainMaps) {
            MapPresenter.this.showMapCollectionInView(domainMaps);
        }
    }

    private void showMapCollectionInView(List<DomainMap> domainMaps) {
        final Collection<Map> maps =this.mapDataMapper.transform(domainMaps);
        this.mapActivity.renderMapList(maps);
    }

}
