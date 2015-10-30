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

//装置信息
public class DeviceInformationRecyclerAdapter extends CMBaseAdapter<StateMonitoringViewHolder> {
    private final Context mContext;

    public DeviceInformationRecyclerAdapter(Context mContext, List<OnlineDeviceState> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public StateMonitoringViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_cm, parent, false);
        return new StateMonitoringViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,int position) {
        viewHolder = (StateMonitoringViewHolder) holder;

        viewHolder.title4.setVisibility(View.VISIBLE);
        viewHolder.title5.setVisibility(View.VISIBLE);
        viewHolder.title6.setVisibility(View.VISIBLE);
        viewHolder.title7.setVisibility(View.VISIBLE);

        viewHolder.title4_data.setVisibility(View.VISIBLE);
        viewHolder.title5_data.setVisibility(View.VISIBLE);
        viewHolder.title6_data.setVisibility(View.VISIBLE);
        viewHolder.title7_data.setVisibility(View.VISIBLE);

        viewHolder.title1.setText("所在杆塔");
        viewHolder.title2.setText("本月已接收流量");
        viewHolder.title3.setText("本月已发送流量");
        viewHolder.title4.setText("主机软件版本");
        viewHolder.title5.setText("主机硬件版本");
        viewHolder.title6.setText("DVR系统MK60版本号");
        viewHolder.title7.setText("DTU信号强度");

        viewHolder.deviceName.setText(mList.get(position).getDeviceId());
        viewHolder.title1_data.setText(mList.get(position).getPoleName());
        viewHolder.title2_data.setText(TransformationUtils.getMFromLong(mList.get(position).getReceive_flow()));
        viewHolder.title3_data.setText(TransformationUtils.getMFromLong(mList.get(position).getSend_flow()));
        viewHolder.title4_data.setText(mList.get(position).getK60_SoftwareVer());
        viewHolder.title5_data.setText(mList.get(position).getK60_HardWareVer());
        viewHolder.title6_data.setText(mList.get(position).getDVR_k60_SoftwareVer()+"");
        viewHolder.title7_data.setText(TransformationUtils.getSignFromFloat(mList.get(position).getSignal_Intensity()));
    }

    @Override
    public int getItemCount() {
        return (this.mList != null) ? this.mList.size() : 0;
    }

}
