package com.hy.onlinemonitor.view.Activity.Function;

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
import com.hy.onlinemonitor.bean.EquipmentMapAndVideo;
import com.hy.onlinemonitor.view.Activity.BaseActivity;
import com.rey.material.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wsw on 2015/7/13.
 */
public class MapActivity extends BaseActivity {
    private Toolbar toolbar;
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private InfoWindow mInfoWindow;
    private BitmapDescriptor bd;
    private MapStatusUpdate msu;
    /**
     * 给地图上添加设备的位置的标记
     */
    private void initOverlay() {
        /**
         * 这里本来应该是访问网络获得数据的....
         */
        List<EquipmentMapAndVideo> list = new ArrayList<>();
        list.add(new EquipmentMapAndVideo(39.963175, 116.400244, "盘梁山", 1));
        list.add(new EquipmentMapAndVideo(39.933175, 116.400244, "观冰战", 2));

        for (final EquipmentMapAndVideo emv : list) {
            LatLng ll = new LatLng(emv.getLatitude(), emv.getLongitude());
            OverlayOptions oo = new MarkerOptions().position(ll).icon(bd)
                    .zIndex(emv.getSN());
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
                    information.setText(emv.getEquipmentName());
                    if(marker == Marker){
                        listener = new InfoWindow.OnInfoWindowClickListener(){

                            @Override
                            public void onInfoWindowClick() {
                                /**
                                 * 这里弹出视频播放窗口
                                 */
//                                new SnackBar(MapActivity.this,"播放视频"+emv.getSN(),null,null).show();
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
        bd = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_gcoding);
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        //缩放级别
        msu = MapStatusUpdateFactory.zoomTo(14.0f);
        mBaiduMap.setMapStatus(msu);
    }

    @Override
    public void initialize() {
        initOverlay();
    }
}