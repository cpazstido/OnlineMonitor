package com.hy.onlinemonitor.view.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hy.onlinemonitor.R;
import com.rey.material.widget.Button;

public class EquipmentListViewHolder extends RecyclerView.ViewHolder{
    public TextView stateShow,equipmentName,equipmentAlarmNull;
    public Button newSensorAlarm,newFireAlarm,newBreakAlarm,historySensorAlarm,historyFireAlarm,historyBreakAlarm,realVideo;
    public ImageView newAlarmImageView;
    public EquipmentListViewHolder(View itemView, int equipmentType) {
        super(itemView);
        stateShow = (TextView) itemView.findViewById(R.id.equipment_state_show);
        equipmentName = (TextView) itemView.findViewById(R.id.equipment_name);
        equipmentAlarmNull = (TextView) itemView.findViewById(R.id.new_alarm_null_tv);
        newSensorAlarm = (Button) itemView.findViewById(R.id.new_sensor_alarm);
        newFireAlarm = (Button) itemView.findViewById(R.id.new_fire_alarm);
        newBreakAlarm = (Button) itemView.findViewById(R.id.new_break_alarm);
        historySensorAlarm = (Button) itemView.findViewById(R.id.history_sensor_alarm);
        historyFireAlarm = (Button) itemView.findViewById(R.id.history_fire_alarm);
        historyBreakAlarm = (Button) itemView.findViewById(R.id.history_break_alarm);
        newAlarmImageView = (ImageView) itemView.findViewById(R.id.equipment_title_new_alarm);
        realVideo = (Button) itemView.findViewById(R.id.real_time_video);
        switch (equipmentType){
            case 0: //山火
                itemView.findViewById(R.id.new_break_alarm).setVisibility(View.GONE);
                itemView.findViewById(R.id.history_break_alarm).setVisibility(View.GONE);
                break;
            case 1://外破
                itemView.findViewById(R.id.new_fire_alarm).setVisibility(View.GONE);
                itemView.findViewById(R.id.history_fire_alarm).setVisibility(View.GONE);
                break;
            case 2://普通视频
                itemView.findViewById(R.id.new_break_alarm).setVisibility(View.GONE);
                itemView.findViewById(R.id.history_break_alarm).setVisibility(View.GONE);
                itemView.findViewById(R.id.new_fire_alarm).setVisibility(View.GONE);
                itemView.findViewById(R.id.history_fire_alarm).setVisibility(View.GONE);
                break;
        }

    }
}
