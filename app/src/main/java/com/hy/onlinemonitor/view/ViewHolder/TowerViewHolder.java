package com.hy.onlinemonitor.view.ViewHolder;

import android.view.View;
import android.widget.TextView;

import com.hy.onlinemonitor.R;


/**
 * Created by Administrator on 2015/7/27.
 */
public class TowerViewHolder extends SMBaseViewHolder {

    public TextView towerName, longitude, latitude, altitude;

    public TowerViewHolder(View itemView) {
        super(itemView);
        towerName = (TextView) itemView.findViewById(R.id.tower_name);
        longitude = (TextView) itemView.findViewById(R.id.longitude);
        latitude = (TextView) itemView.findViewById(R.id.latitude);
        altitude = (TextView) itemView.findViewById(R.id.altitude);
    }

}
