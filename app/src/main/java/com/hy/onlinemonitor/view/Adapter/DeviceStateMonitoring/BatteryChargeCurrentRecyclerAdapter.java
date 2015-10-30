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

//电池充电电流
public class BatteryChargeCurrentRecyclerAdapter extends CMBaseAdapter<StateMonitoringViewHolder> {
    private final Context mContext;

    public BatteryChargeCurrentRecyclerAdapter(Context mContext, List<OnlineDeviceState> mList) {
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
        viewHolder.title11.setVisibility(View.VISIBLE);
        viewHolder.title12.setVisibility(View.VISIBLE);

        viewHolder.title4_data.setVisibility(View.VISIBLE);
        viewHolder.title5_data.setVisibility(View.VISIBLE);
        viewHolder.title6_data.setVisibility(View.VISIBLE);
        viewHolder.title7_data.setVisibility(View.VISIBLE);
        viewHolder.title8_data.setVisibility(View.VISIBLE);
        viewHolder.title9_data.setVisibility(View.VISIBLE);
        viewHolder.title10_data.setVisibility(View.VISIBLE);
        viewHolder.title11_data.setVisibility(View.VISIBLE);
        viewHolder.title12_data.setVisibility(View.VISIBLE);

        viewHolder.title1.setText("1#电池充电电流1");
        viewHolder.title2.setText("1#电池充电电流2");
        viewHolder.title3.setText("2#电池充电电流1");
        viewHolder.title4.setText("2#电池充电电流2");
        viewHolder.title5.setText("3#电池充电电流1");
        viewHolder.title6.setText("3#电池充电电流2");
        viewHolder.title7.setText("4#电池充电电流1");
        viewHolder.title8.setText("4#电池充电电流2");
        viewHolder.title9.setText("5#电池充电电流1");
        viewHolder.title10.setText("5#电池充电电流2");
        viewHolder.title11.setText("在线取电地线电流");
        viewHolder.title12.setText("在线取电充电电流");

        viewHolder.deviceName.setText(mList.get(position).getDeviceId());
        viewHolder.title1_data.setText(TransformationUtils.getAFromFloat(mList.get(position).getCharge_Currrent_1()));
        viewHolder.title2_data.setText(TransformationUtils.getAFromFloat(mList.get(position).getCharge_Currrent_1_2()));
        viewHolder.title3_data.setText(TransformationUtils.getAFromFloat(mList.get(position).getCharge_Currrent_2()));
        viewHolder.title4_data.setText(TransformationUtils.getAFromFloat(mList.get(position).getCharge_Currrent_2_2()));
        viewHolder.title5_data.setText(TransformationUtils.getAFromFloat(mList.get(position).getCharge_Currrent_3()));
        viewHolder.title6_data.setText(TransformationUtils.getAFromFloat(mList.get(position).getCharge_Currrent_3_2()));
        viewHolder.title7_data.setText(TransformationUtils.getAFromFloat(mList.get(position).getCharge_Currrent_4()));
        viewHolder.title8_data.setText(TransformationUtils.getAFromFloat(mList.get(position).getCharge_Currrent_4_2()));
        viewHolder.title9_data.setText(TransformationUtils.getAFromFloat(mList.get(position).getBat5_ChargeCurrrent()));
        viewHolder.title10_data.setText(TransformationUtils.getAFromFloat(mList.get(position).getBat5_ChargeCurrrent2()));

        viewHolder.title11_data.setText(TransformationUtils.getAFromFloat(mList.get(position).getBat_GetChargeCollectCurrent()));
        viewHolder.title12_data.setText(TransformationUtils.getAFromFloat(mList.get(position).getBat_GetChargeCurrrent()));
    }

    @Override
    public int getItemCount() {
        return (this.mList != null) ? this.mList.size() : 0;
    }

}
