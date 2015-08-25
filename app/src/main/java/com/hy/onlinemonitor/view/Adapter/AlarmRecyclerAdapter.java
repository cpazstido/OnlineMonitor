package com.hy.onlinemonitor.view.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.AlarmInformation;
import com.hy.onlinemonitor.bean.AlarmPage;
import com.hy.onlinemonitor.view.ViewHolder.AlarmViewHolder;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.List;

public class AlarmRecyclerAdapter extends RecyclerView.Adapter<AlarmViewHolder> {

    public interface OnItemClickListener {
        void onAlarmItemClicked(AlarmInformation alarmInformation);
    }

    private List<AlarmInformation> mList;
    private Context mContext;
    private int showType;
    private String queryAlarmType;
    private int status;

    private OnItemClickListener onItemClickListener;

    public AlarmRecyclerAdapter(AlarmPage alarmPage, Context mContext, int showType , String queryAlarmType,int status) {
        validateAlarmsCollection(alarmPage.getList());
        this.mList = alarmPage.getList();
        this.mContext = mContext;
        this.showType = showType;
        this.queryAlarmType = queryAlarmType;
        this.status = status;
    }

    @Override
    public int getItemViewType(int position) {
        return showType;
    }

    @Override
    public int getItemCount() {
        return (this.mList != null) ? this.mList.size() : 0;
    }

    @Override
    public AlarmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         * 设置每一个的显示界面
         */
        View view = null;
        switch (queryAlarmType){
            case "fire":
                view = LayoutInflater.from(mContext)
                        .inflate(R.layout.card_alarm, parent, false);
                break;
            case "break":
                view = LayoutInflater.from(mContext)
                        .inflate(R.layout.card_alarm, parent, false);
                break;
            case "sensor":
                view = LayoutInflater.from(mContext)
                        .inflate(R.layout.card_alarm_tv, parent, false);
                break;
        }

        return new AlarmViewHolder(view,showType);
    }

    @Override
    public void onBindViewHolder(AlarmViewHolder holder, final int position) {
        final AlarmInformation alarmInformation = this.mList.get(position);
        if (showType == 1) {
            holder.alarmCardTitle.setText(mList.get(position).getCollectionTime());
            switch (queryAlarmType){
                case "fire":
                    break;
                case "break":
                    break;
            }
            Picasso.with(mContext).load(mList.get(position).getVisibleLightImage()).into(holder.alarmCardImage);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("info", "启动另一界面");
                    if (AlarmRecyclerAdapter.this.onItemClickListener != null) {
                        AlarmRecyclerAdapter.this.onItemClickListener.onAlarmItemClicked(alarmInformation);
                    }
//                Toast.makeText(mContext,"点击了"+mList.get(position).toString(),Toast.LENGTH_LONG).show();
//                    Intent itemIntent = new Intent(mContext, DetailedAlarmActivity.class);
//                    itemIntent.putExtra("detailedAlarm", mList.get(position));
//                    v.getContext().startActivity(itemIntent);
                }
            });
        }else if(showType == 0){
            holder.alarmCardTitle.setText(mList.get(position).getCollectionTime());
            holder.equipmentIdentifier.setText(mList.get(position).getDeviceSn());
            holder.alarmInformation.setText(mList.get(position).getAlarmInformation());
            holder.collectionTime.setText(mList.get(position).getIsBlowingEquipment());
        }
    }

    public void setAlarmCollection(Collection<AlarmInformation> alarmInformationCollection) {
        this.validateAlarmsCollection(alarmInformationCollection);
        this.mList = (List<AlarmInformation>) alarmInformationCollection;
        this.notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private void validateAlarmsCollection(Collection<AlarmInformation> alarmInformationCollection) {
        if (alarmInformationCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }
}