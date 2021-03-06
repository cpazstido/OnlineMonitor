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

//太阳能板电压
public class SolarPanelVoltageRecyclerAdapter extends CMBaseAdapter<StateMonitoringViewHolder> {
    private final Context mContext;
    public SolarPanelVoltageRecyclerAdapter(Context mContext, List<OnlineDeviceState> mList) {
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

        viewHolder.title1.setText("1#电池太阳能板电压1");
        viewHolder.title2.setText("1#电池太阳能板电压2");
        viewHolder.title3.setText("2#电池太阳能板电压1");
        viewHolder.title4.setText("2#电池太阳能板电压2");
        viewHolder.title5.setText("3#电池太阳能板电压1");
        viewHolder.title6.setText("3#电池太阳能板电压2");
        viewHolder.title7.setText("4#电池太阳能板电压1");
        viewHolder.title8.setText("4#电池太阳能板电压2");
        viewHolder.title9.setText("5#电池太阳能板电压1");
        viewHolder.title10.setText("5#电池太阳能板电压2");

        viewHolder.deviceName.setText(mList.get(position).getDeviceId());
        viewHolder.title1_data.setText(TransformationUtils.getVFromFloat(mList.get(position).getBat1_SolarPanelVoltage()));
        viewHolder.title2_data.setText(TransformationUtils.getVFromFloat(mList.get(position).getBat1_SolarPanelVoltage2()));
        viewHolder.title3_data.setText(TransformationUtils.getVFromFloat(mList.get(position).getBat2_SolarPanelVoltage()));
        viewHolder.title4_data.setText(TransformationUtils.getVFromFloat(mList.get(position).getBat2_SolarPanelVoltage2()));
        viewHolder.title5_data.setText(TransformationUtils.getVFromFloat(mList.get(position).getBat3_SolarPanelVoltage()));
        viewHolder.title6_data.setText(TransformationUtils.getVFromFloat(mList.get(position).getBat3_SolarPanelVoltage2()));
        viewHolder.title7_data.setText(TransformationUtils.getVFromFloat(mList.get(position).getBat4_SolarPanelVoltage()));
        viewHolder.title8_data.setText(TransformationUtils.getVFromFloat(mList.get(position).getBat4_SolarPanelVoltage2()));
        viewHolder.title9_data.setText(TransformationUtils.getVFromFloat(mList.get(position).getBat5_SolarPanelVoltage()));
        viewHolder.title10_data.setText(TransformationUtils.getVFromFloat(mList.get(position).getBat5_SolarPanelVoltage2()));
    }

    @Override
    public int getItemCount() {
        return (this.mList != null) ? this.mList.size() : 0;
    }

}
