package com.hy.onlinemonitor.view.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hy.onlinemonitor.R;


/**
 * Created by Administrator on 2015/7/27.
 */
public class MoreInformationSensorViewHolder extends RecyclerView.ViewHolder {

    public TextView sensorName,sensorIdentifier;
    public MoreInformationSensorViewHolder(View itemView) {
        super(itemView);
        sensorName= (TextView) itemView.findViewById(R.id.sensor_name);
        sensorIdentifier = (TextView) itemView.findViewById(R.id.sensor_identifier);
    }
}
