package com.hy.onlinemonitor.view.Adapter.StateMonitoring;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.DeviceInformation;
import com.hy.onlinemonitor.view.ViewHolder.StateMonitoringViewHolder;

import java.util.List;

//装置状态
public class DeviceStateRecyclerAdapter extends RecyclerView.Adapter<StateMonitoringViewHolder> {
    private final Context mContext;
    private List<DeviceInformation> mList;

    public DeviceStateRecyclerAdapter(Context mContext, List<DeviceInformation> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public StateMonitoringViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_cm, parent, false);
        return new StateMonitoringViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StateMonitoringViewHolder holder, int position) {
        holder.title4.setVisibility(View.VISIBLE);
        holder.title5.setVisibility(View.VISIBLE);
        holder.title6.setVisibility(View.VISIBLE);
        holder.title7.setVisibility(View.VISIBLE);
        holder.title8.setVisibility(View.VISIBLE);
        holder.title9.setVisibility(View.VISIBLE);
        holder.title10.setVisibility(View.VISIBLE);

        holder.title4_data.setVisibility(View.VISIBLE);
        holder.title5_data.setVisibility(View.VISIBLE);
        holder.title6_data.setVisibility(View.VISIBLE);
        holder.title7_data.setVisibility(View.VISIBLE);
        holder.title8_data.setVisibility(View.VISIBLE);
        holder.title9_data.setVisibility(View.VISIBLE);
        holder.title10_data.setVisibility(View.VISIBLE);

        holder.title1.setText("工作温度");
        holder.title2.setText("工作电流");
        holder.title3.setText("工作电压");
        holder.title4.setText("本次连续工作时间");
        holder.title5.setText("累计工作时间");
        holder.title6.setText("剩余电量");
        holder.title7.setText("摄像机开电状态");
        holder.title8.setText("摄像机连续工作时间");
        holder.title9.setText("3G开电状态");
        holder.title10.setText("密钥协商状态");

        holder.deviceName.setText(mList.get(position).getDeviceId());
        holder.title1_data.setText(mList.get(position).getPoleName());
        holder.title2_data.setText(mList.get(position).getReceiveTraffic());
        holder.title3_data.setText(mList.get(position).getSendTraffic());
        holder.title4_data.setText(mList.get(position).getSoftwareVersion());
        holder.title5_data.setText(mList.get(position).getHardwareVersion());
        holder.title6_data.setText(mList.get(position).getDVRVersion());
        holder.title7_data.setText(mList.get(position).getSignalIntensity());

    }

    @Override
    public int getItemCount() {
        return (this.mList != null) ? this.mList.size() : 0;
    }

    public void setList(List<DeviceInformation> mList) {
        this.mList = mList;
        this.notifyDataSetChanged();
    }
}
