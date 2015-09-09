package com.hy.onlinemonitor.view.ViewHolder;

import android.view.View;
import android.widget.TextView;

import com.hy.onlinemonitor.R;


/**
 * Created by Administrator on 2015/7/24.
 */
public class LineViewHolder extends SMBaseViewHolder{
    public TextView lineName,lineStart,lineFinish,lineTrend,voltageLevel;

    public LineViewHolder(View itemView) {
        super(itemView);
        lineName = (TextView) itemView.findViewById(R.id.line_name);
        lineStart = (TextView) itemView.findViewById(R.id.line_start);
        lineFinish = (TextView) itemView.findViewById(R.id.line_finish);
        lineTrend = (TextView) itemView.findViewById(R.id.line_trend);
        voltageLevel = (TextView) itemView.findViewById(R.id.voltage_level);
    }
}
