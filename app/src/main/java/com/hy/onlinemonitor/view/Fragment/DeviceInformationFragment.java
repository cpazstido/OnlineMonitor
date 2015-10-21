package com.hy.onlinemonitor.view.Fragment;

import android.util.Log;

import com.hy.onlinemonitor.bean.DeviceInformation;
import com.hy.onlinemonitor.view.Adapter.StateMonitoring.DeviceInformationRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class DeviceInformationFragment extends StateMonitorBaseFragment {
    private static final String TAG = "状态监测Fragment";
    List<DeviceInformation> mList;
//    private OnFragmentChangeListener onFragmentChangeListener;
//
//    public interface OnFragmentChangeListener {
//        void setList();
//    }

    protected void loadData() {//加载数据
        Log.e(TAG, "loadData");
        mList = new ArrayList<>();
        DeviceInformation deviceInformation = new DeviceInformation();
        deviceInformation.setDeviceId("HY_OLMS_000000074");
        deviceInformation.setPoleName("车间测试杆塔");
        deviceInformation.setReceiveTraffic("0M");
        deviceInformation.setSendTraffic("0M");
        deviceInformation.setSoftwareVersion("DATA_V43A-V42A-SVA1_150619_R");
        deviceInformation.setHardwareVersion("DATA_V43A-V42A");
        deviceInformation.setDVRVersion("0");
        deviceInformation.setSignalIntensity("99%");
        mList.add(deviceInformation);
        setListData(mList);
    }

    protected void getAdapter() {//创建adapter
        mAdapter = new DeviceInformationRecyclerAdapter(getContext(), new ArrayList<DeviceInformation>());
    }

    protected void doRefresh() {//刷新数据

    }

    protected void setListData(List list) {
        ((DeviceInformationRecyclerAdapter) mAdapter).setList(list);
    }

    @Override
    protected void initializePresenter() {
        getAdapter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public static DeviceInformationFragment newInstance() {
        return new DeviceInformationFragment();
    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            onFragmentChangeListener = (OnFragmentChangeListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString() + " must implement OnArticleSelectedListener");
//        }
//    }
}
