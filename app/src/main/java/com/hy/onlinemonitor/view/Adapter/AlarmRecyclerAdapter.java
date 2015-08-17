package com.hy.onlinemonitor.view.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.AlarmInformation;
import com.hy.onlinemonitor.view.ViewHolder.AlarmViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by wsw on 2015/7/15.
 */

public class AlarmRecyclerAdapter extends RecyclerView.Adapter<AlarmViewHolder> {

    List<AlarmInformation> mList;
    Context mContext;
    int showType;

    public AlarmRecyclerAdapter(List<AlarmInformation> mList, Context mContext, int showType) {
        this.mList = mList;
        this.mContext = mContext;
        this.showType = showType;
    }

    @Override
    public int getItemViewType(int position) {
        return showType;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public AlarmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         * 设置每一个的显示界面
         */
        AlarmViewHolder holder = null;
        if(viewType == 1) { //山火外破等
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_alarm, parent, false);
            holder = new AlarmViewHolder(view,showType);
        }else if(viewType == 0){//传感器
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_alarm_tv, parent, false);
            holder = new AlarmViewHolder(view,showType);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(AlarmViewHolder holder, final int position) {

        if (showType == 1) {
            holder.alarmCardTitle.setText(mList.get(position).getAlarmName());
            Picasso.with(mContext).load(mList.get(position).getVisibleLightImage()).into(holder.alarmCardImage);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("info", "启动另一界面");
//                Toast.makeText(mContext,"点击了"+mList.get(position).toString(),Toast.LENGTH_LONG).show();
//                    Intent itemIntent = new Intent(mContext, DetailedAlarmActivity.class);
//                    itemIntent.putExtra("detailedAlarm", mList.get(position));
//                    v.getContext().startActivity(itemIntent);
                }
            });
        }else if(showType == 0){
            holder.alarmCardTitle.setText(mList.get(position).getAlarmName());
            holder.equipmentIdentifier.setText(mList.get(position).getAlarmName());
            holder.alarmInformation.setText(mList.get(position).getAlarmInformation());
            holder.collectionTime.setText(mList.get(position).getIsBlowingEquipment());
        }
    }
}