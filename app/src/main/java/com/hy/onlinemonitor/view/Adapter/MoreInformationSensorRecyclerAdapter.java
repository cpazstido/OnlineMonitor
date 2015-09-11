package com.hy.onlinemonitor.view.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.Sensor;
import com.hy.onlinemonitor.view.ViewHolder.MoreInformationSensorViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2015/7/27.
 */
public class MoreInformationSensorRecyclerAdapter extends RecyclerView.Adapter<MoreInformationSensorViewHolder>{
    private Context mContext;
    private List<Sensor> sensors;

    public MoreInformationSensorRecyclerAdapter(Context mContext, List<Sensor> sensors) {
        this.mContext = mContext;
        this.sensors = sensors;
    }

    @Override
    public MoreInformationSensorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.more_information_sensor, parent, false);
        return new MoreInformationSensorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoreInformationSensorViewHolder holder, int position) {
        holder.sensorIdentifier.setText(sensors.get(position).getSensorInDeviceID());
        holder.sensorName.setText(sensors.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return sensors.size();
    }
}
