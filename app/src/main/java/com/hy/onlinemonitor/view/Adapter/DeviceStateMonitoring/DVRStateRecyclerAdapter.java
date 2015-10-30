package com.hy.onlinemonitor.view.Adapter.DeviceStateMonitoring;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.OnlineDeviceState;
import com.hy.onlinemonitor.utile.TransformationUtils;
import com.hy.onlinemonitor.view.ViewHolder.StateMonitoringViewHolder;

import java.util.List;

//DVR状态
public class DVRStateRecyclerAdapter extends CMBaseAdapter<StateMonitoringViewHolder> {
    private final Context mContext;

    public DVRStateRecyclerAdapter(Context mContext, List<OnlineDeviceState> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public StateMonitoringViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_cm, parent, false);
        return new StateMonitoringViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        viewHolder = (StateMonitoringViewHolder) holder;

        viewHolder.title4.setVisibility(View.VISIBLE);
        viewHolder.title5.setVisibility(View.VISIBLE);
        viewHolder.title6.setVisibility(View.VISIBLE);
        viewHolder.title7.setVisibility(View.VISIBLE);
        viewHolder.title8.setVisibility(View.VISIBLE);
        viewHolder.title9.setVisibility(View.VISIBLE);
        viewHolder.title10.setVisibility(View.VISIBLE);

        viewHolder.title4_data.setVisibility(View.VISIBLE);
        viewHolder.title5_data.setVisibility(View.VISIBLE);
        viewHolder.title6_data.setVisibility(View.VISIBLE);
        viewHolder.title7_data.setVisibility(View.VISIBLE);
        viewHolder.title8_data.setVisibility(View.VISIBLE);
        viewHolder.title9_data.setVisibility(View.VISIBLE);
        viewHolder.title10_data.setVisibility(View.VISIBLE);

        viewHolder.title1.setText("DVR开电状态");
        viewHolder.title2.setText("可见光相机状态");
        viewHolder.title3.setText("3G路由器状态");
        viewHolder.title4.setText("红外相机状态");
        viewHolder.title5.setText("A9状态");
        viewHolder.title6.setText("云台状态");
        viewHolder.title7.setText("DVR系统3G串口");
        viewHolder.title8.setText("DVR信号强度");
        viewHolder.title9.setText("A9工作电压");
        viewHolder.title10.setText("A9工作温度");

        viewHolder.deviceName.setText(mList.get(position).getDeviceId());
        viewHolder.title1_data.setText(TransformationUtils.getStringByDVR(mList.get(position).getDVR_power_status()));
        viewHolder.title2_data.setText(TransformationUtils.getStringByVisible(mList.get(position).getDVR_VisibleCamera()));
        viewHolder.title3_data.setText(TransformationUtils.getStringByRouter(mList.get(position).getDVR_Router3G()));
        viewHolder.title4_data.setText(TransformationUtils.getStringByVisible(mList.get(position).getDVR_InfraredCamera()));
        viewHolder.title5_data.setText(TransformationUtils.getStringByRouter(mList.get(position).getDVR_A9()));
        viewHolder.title6_data.setText(TransformationUtils.getStringByRouter(mList.get(position).getDVR_PTZ()));
        viewHolder.title7_data.setText(TransformationUtils.getStringByRouter(mList.get(position).getDVR_3G_Uart()));
        viewHolder.title8_data.setText(mList.get(position).getDVR_Signal_Intensity()+"");
        viewHolder.title9_data.setText(TransformationUtils.getVFromFloat(mList.get(position).getA9_Voltage()));
        viewHolder.title10_data.setText(TransformationUtils.getCFromFloat(mList.get(position).getA9_Temperature()));

    }

    @Override
    public int getItemCount() {
        return (this.mList != null) ? this.mList.size() : 0;
    }

}
