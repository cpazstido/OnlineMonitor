package com.hy.onlinemonitor.view.Adapter.StateMonitoring;

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

//装置状态
public class DeviceStateRecyclerAdapter extends CMBaseAdapter<StateMonitoringViewHolder> {
    private final Context mContext;

    public DeviceStateRecyclerAdapter(Context mContext, List<OnlineDeviceState> mList) {
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

        viewHolder.title1.setText("工作温度");
        viewHolder.title2.setText("工作电流");
        viewHolder.title3.setText("工作电压");
        viewHolder.title4.setText("本次连续工作时间");
        viewHolder.title5.setText("累计工作时间");
        viewHolder.title6.setText("剩余电量");
        viewHolder.title7.setText("摄像机开电状态");
        viewHolder.title8.setText("摄像机连续工作时间");
        viewHolder.title9.setText("3G开电状态");
        viewHolder.title10.setText("密钥协商状态");

        viewHolder.deviceName.setText(mList.get(position).getDeviceId());
        viewHolder.title1_data.setText(TransformationUtils.getCFromFloat(mList.get(position).getOperation_Temperature()));
        viewHolder.title2_data.setText(TransformationUtils.getAFromFloat(mList.get(position).getWork_Current()));
        viewHolder.title3_data.setText(TransformationUtils.getVFromFloat(mList.get(position).getBat_Capacity()));
        viewHolder.title4_data.setText(mList.get(position).getTerminalworktime());
        viewHolder.title5_data.setText(mList.get(position).getTotalWorkingtime());
        viewHolder.title6_data.setText(TransformationUtils.getSignFromFloat(mList.get(position).getBat_Dump_Energy()));
        viewHolder.title7_data.setText(TransformationUtils.getStringByByte(mList.get(position).getCamer_power_status()));
        viewHolder.title8_data.setText(mList.get(position).getCamerWorktime());
        viewHolder.title9_data.setText(TransformationUtils.getStringByByte(mList.get(position).getPower_3G_status()));
        viewHolder.title10_data.setText(TransformationUtils.getStringByKey(mList.get(position).getKEY_Negotiation_status()));

    }

    @Override
    public int getItemCount() {
        return (this.mList != null) ? this.mList.size() : 0;
    }

}
