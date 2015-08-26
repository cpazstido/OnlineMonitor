package com.hy.onlinemonitor.view.Activity.Function;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.Map;
import com.hy.onlinemonitor.presenter.MapPresenter;
import com.hy.onlinemonitor.utile.GetLoading;
import com.hy.onlinemonitor.view.Activity.BaseActivity;
import com.hy.onlinemonitor.view.MapListView;
import com.rey.material.widget.Button;
import com.rey.material.widget.SnackBar;

import java.util.Collection;
import java.util.List;

public class MapActivity extends BaseActivity implements MapListView {
    private Toolbar toolbar;
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private InfoWindow mInfoWindow;
    private BitmapDescriptor bd;
    private MapStatusUpdate msu;
    private List<Map> mapAndVideoList;
    private MapPresenter mapPresenter;
    private AlertDialog loadingDialog;

    /**
     * 给地图上添加设备的位置的标记
     */
    private void initOverlay() {
        for (final Map emv : mapAndVideoList) {
            LatLng ll = new LatLng(emv.getLatitude(), emv.getLongitude());
            OverlayOptions oo = new MarkerOptions().position(ll).icon(bd)
                    .zIndex(emv.getDvrID());
            final Marker Marker = (com.baidu.mapapi.map.Marker) mBaiduMap.addOverlay(oo);
            //设置地图的新中心点
            mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(ll));
            mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(com.baidu.mapapi.map.Marker marker) {
                    InfoWindow.OnInfoWindowClickListener listener = null;
                    LayoutInflater inflater = LayoutInflater.from(MapActivity.this);
                    View layout = inflater.inflate(R.layout.popup_view, null);

                    TextView information = (TextView) layout.findViewById(R.id.map_equipment_information);
                    Button videoBtn = (Button) layout.findViewById(R.id.map_equipment_video);
                    information.setText(emv.getPoleName()+"\n"+emv.getEquipmentName());
                    if(marker == Marker){
                        listener = new InfoWindow.OnInfoWindowClickListener(){

                            @Override
                            public void onInfoWindowClick() {

                                /**
                                 * 这里弹出视频播放窗口
                                 */

                                new SnackBar(MapActivity.this,null).text("播放视频"+emv.getDvrID()).show();
                                mBaiduMap.hideInfoWindow();
                            }
                        };
                        LatLng ll = marker.getPosition();
                        mInfoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(layout), ll, -47, listener);
                        mBaiduMap.showInfoWindow(mInfoWindow);
                    }
                    return true;
                }
            });
        }
    }

    private void loadMapList() {
        this.mapPresenter.initialize(getUser().getUserId(), getUser().getSelectionType());
    }

    private void initPresenter() {
        this.mapPresenter = new MapPresenter(this);
        this.mapPresenter.setView(this);
    }

    @Override
    protected Toolbar getToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("电子地图");
        return toolbar;
    }

    @Override
    protected void setOwnContentView() {
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_map);
        mMapView = (MapView) findViewById(R.id.bmapView);
    }

    /**
     * 清除所有Overlay
     *
     * @param view
     */
    public void clearOverlay(View view) {
        mBaiduMap.clear();
    }

    /**
     * 重新添加Overlay
     *
     * @param view
     */
    public void resetOverlay(View view) {
        clearOverlay(null);
        initOverlay();
    }

    @Override
    protected void onPause() {
        // MapView的生命周期与Activity同步，当activity挂起时需调用MapView.onPause()
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        // MapView的生命周期与Activity同步，当activity恢复时需调用MapView.onResume()
        mMapView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        // MapView的生命周期与Activity同步，当activity销毁时需调用MapView.destroy()
        mMapView.onDestroy();
        super.onDestroy();
        // 回收 bitmap 资源
        bd.recycle();
    }

    @Override
    public void setupUI() {
        loadingDialog = GetLoading.getDialog(MapActivity.this, "加载数据中");
        bd = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_gcoding);
        mBaiduMap = mMapView.getMap();
        //缩放级别
        msu = MapStatusUpdateFactory.zoomTo(14.0f);
        mBaiduMap.setMapStatus(msu);
    }

    @Override
    public void initialize() {
        this.initPresenter();
        this.loadMapList();
    }

    @Override
    public void showLoading() {
        loadingDialog.show();
    }

    @Override
    public void hideLoading() {
        loadingDialog.cancel();
    }


    @Override
    public void showError(String message) {

    }

    @Override
    public Context getContext() {
        return MapActivity.this;
    }

    @Override
    public void renderMapList(Collection<Map> maps) {
        mapAndVideoList = (List<Map>) maps;
        resetOverlay(null);
    }
}