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

//上传数据信息
public class UploadDataInformationRecyclerAdapter extends CMBaseAdapter<StateMonitoringViewHolder> {
    private final Context mContext;

    public UploadDataInformationRecyclerAdapter(Context mContext, List<OnlineDeviceState> mList) {
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

        viewHolder.title1.setText("未上传成功的气象数据条数");
        viewHolder.title2.setText("未上传成功的杆塔倾斜数据条数");
        viewHolder.title3.setText("未上传成功的导线风偏数据条数");

        viewHolder.deviceName.setText(mList.get(position).getDeviceId());
        viewHolder.title1_data.setText(TransformationUtils.getStringFromLong(mList.get(position).getFailed_send_weather_num(),2));
        viewHolder.title2_data.setText(TransformationUtils.getStringFromLong(mList.get(position).getFailed_send_tower_slop_num(), 2));
        viewHolder.title3_data.setText(TransformationUtils.getStringFromLong(mList.get(position).getFailed_send_angle_num(), 2));

    }

    @Override
    public int getItemCount() {
        return (this.mList != null) ? this.mList.size() : 0;
    }

}
