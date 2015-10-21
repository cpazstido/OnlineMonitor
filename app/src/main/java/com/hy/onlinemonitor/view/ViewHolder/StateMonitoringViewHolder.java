package com.hy.onlinemonitor.view.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hy.onlinemonitor.R;

/**
 * Created by 24363 on 2015/10/20.
 */
public class StateMonitoringViewHolder extends RecyclerView.ViewHolder {
    public TextView title1, title2, title3, title4, title5, title6, title7, title8, title9, title10;
    public TextView deviceName, title1_data, title2_data, title3_data, title4_data, title5_data, title6_data, title7_data, title8_data, title9_data, title10_data;
    //title1-3和deviceName 始终显示,因为最小也有4个可显示.其他需要在代码中设置其为可显示
    public StateMonitoringViewHolder(View itemView) {
        super(itemView);
        title1 = (TextView) itemView.findViewById(R.id.title_one);
        title2 = (TextView) itemView.findViewById(R.id.title_two);
        title3 = (TextView) itemView.findViewById(R.id.title_three);
        title4 = (TextView) itemView.findViewById(R.id.title_four);
        title5 = (TextView) itemView.findViewById(R.id.title_five);
        title6 = (TextView) itemView.findViewById(R.id.title_six);
        title7 = (TextView) itemView.findViewById(R.id.title_seven);
        title8 = (TextView) itemView.findViewById(R.id.title_eight);
        title9 = (TextView) itemView.findViewById(R.id.title_nine);
        title10 = (TextView) itemView.findViewById(R.id.title_ten);
        deviceName = (TextView) itemView.findViewById(R.id.tv_device_name);
        title1_data = (TextView) itemView.findViewById(R.id.tv_title_one);
        title2_data = (TextView) itemView.findViewById(R.id.tv_title_two);
        title3_data = (TextView) itemView.findViewById(R.id.tv_title_three);
        title4_data = (TextView) itemView.findViewById(R.id.tv_title_four);
        title5_data = (TextView) itemView.findViewById(R.id.tv_title_five);
        title6_data = (TextView) itemView.findViewById(R.id.tv_title_six);
        title7_data = (TextView) itemView.findViewById(R.id.tv_title_seven);
        title8_data = (TextView) itemView.findViewById(R.id.tv_title_eight);
        title9_data = (TextView) itemView.findViewById(R.id.tv_title_nine);
        title10_data = (TextView) itemView.findViewById(R.id.tv_title_ten);
    }
}
