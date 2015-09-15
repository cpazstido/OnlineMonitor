package com.hy.onlinemonitor.view.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.hy.onlinemonitor.R;


/**
 * Created by Administrator on 2015/7/27.
 */
public class SensorViewHolder extends RecyclerView.ViewHolder {

    public TextView sensorName,sensorIdentifier;
    public SwipeLayout swipeLayout;
    public ImageView ActionDelete;
    public SensorViewHolder(View itemView) {
        super(itemView);
        sensorName = (TextView) itemView.findViewById(R.id.sensor_name);
        sensorIdentifier = (TextView) itemView.findViewById(R.id.sensor_identifier);
        swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);
        ActionDelete = (ImageView) itemView.findViewById(R.id.item_delete);
    }
}
