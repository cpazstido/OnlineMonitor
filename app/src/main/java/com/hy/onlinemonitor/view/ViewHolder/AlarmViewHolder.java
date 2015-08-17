package com.hy.onlinemonitor.view.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hy.onlinemonitor.R;


/**
 * Created by wsw on 2015/7/15.
 */
public class AlarmViewHolder extends RecyclerView.ViewHolder{

    public ImageView alarmCardImage;
    public TextView alarmCardTitle,equipmentIdentifier,alarmInformation,collectionTime;
    public AlarmViewHolder(View itemView, int showType) {
        super(itemView);
        if (showType == 1) {
            alarmCardImage = (ImageView) itemView.findViewById(R.id.alarm_card_image);
            alarmCardTitle = (TextView) itemView.findViewById(R.id.alarm_card_title);
        }else if(showType == 0){
            itemView.setEnabled(false);
            alarmCardTitle = (TextView) itemView.findViewById(R.id.alarm_card_title);
            equipmentIdentifier = (TextView) itemView.findViewById(R.id.equipment_identifier);
            alarmInformation = (TextView) itemView.findViewById(R.id.alarm_information);
            collectionTime = (TextView) itemView.findViewById(R.id.collection_time);
        }
    }
}
