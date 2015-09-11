package com.hy.onlinemonitor.view.ViewHolder;

import android.view.View;
import android.widget.TextView;

import com.hy.onlinemonitor.R;
import com.rey.material.widget.Button;

public class EquipmentViewHolder extends SMBaseViewHolder{
    public TextView equipmentIdentifier,equipmentDvrid,alarmInformationSend,equipmentType,sensorManage;
    public Button moreInformation;
    public EquipmentViewHolder(View itemView) {
        super(itemView);
        equipmentIdentifier = (TextView) itemView.findViewById(R.id.equipment_identifier);
        equipmentDvrid = (TextView) itemView.findViewById(R.id.equipment_dvrid);
        alarmInformationSend = (TextView) itemView.findViewById(R.id.alarm_information_send);
        equipmentType = (TextView) itemView.findViewById(R.id.equipment_type);
        sensorManage = (TextView) itemView.findViewById(R.id.sensor_manage);
        moreInformation = (Button) itemView.findViewById(R.id.more_information);
    }

}
