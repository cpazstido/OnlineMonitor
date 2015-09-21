package com.hy.onlinemonitor.view.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hy.data.utile.SystemRestClient;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.AlarmInformation;
import com.hy.onlinemonitor.bean.AlarmPage;
import com.hy.onlinemonitor.view.Activity.Function.DetailedAlarmActivity;
import com.hy.onlinemonitor.view.ViewHolder.AlarmViewHolder;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.List;

public class AlarmRecyclerAdapter extends RecyclerView.Adapter<AlarmViewHolder> {

    private List<AlarmInformation> mList;
    private Context mContext;
    private int showType;

    public void setQueryAlarmType(String queryAlarmType) {
        this.queryAlarmType = queryAlarmType;
    }

    private String queryAlarmType;
    private int status;

    public AlarmRecyclerAdapter(AlarmPage alarmPage, Context mContext, int showType,String queryAlarmType, int status) {
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
        View view = null;
        switch (queryAlarmType) {
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
        return new AlarmViewHolder(view, showType);
    }

    @Override
    public void onBindViewHolder(AlarmViewHolder holder, final int position) {
        final AlarmInformation alarmInformation = this.mList.get(position);
        switch (queryAlarmType) {
            case "sensor":
                holder.equipmentIdentifier.setText(alarmInformation.getDeviceId());
                holder.alarmInformation.setText(alarmInformation.getAlarmInformation());
                holder.collectionTime.setText(alarmInformation.getCollectionTime());
                break;
            case "fire":
                holder.alarmCardTitle.setText(alarmInformation.getDeviceId());
                Log.e("fire", SystemRestClient.BASE_PICTURE_URL + alarmInformation.getVisibleLightImage());
                Picasso.with(mContext).load(SystemRestClient.BASE_PICTURE_URL+alarmInformation.getVisibleLightImage()).placeholder(R.drawable.picture_loading).error(R.drawable.loading_error).into(holder.alarmCardImage);
//                Picasso.with(mContext).load("http://172.16.8.129:8081/eMonitorApp/alarm/visiblePicture/217-1-20150916-150333.jpg").into(holder.alarmCardImage);
                holder.alarmCardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, DetailedAlarmActivity.class);
                        intent.putExtra("queryAlarmType", queryAlarmType);
                        intent.putExtra("status", status);
                        intent.putExtra("detailedAlarm", alarmInformation);
                        mContext.startActivity(intent);
                    }
                });
                break;
            case "break":
                holder.alarmCardTitle.setText(alarmInformation.getCollectionTime());
                Log.e("break", SystemRestClient.BASE_PICTURE_URL +alarmInformation.getBreakImage());
                Picasso.with(mContext).load(SystemRestClient.BASE_PICTURE_URL+alarmInformation.getBreakImage()).placeholder(R.drawable.picture_loading).error(R.drawable.loading_error).into(holder.alarmCardImage);
                holder.alarmCardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, DetailedAlarmActivity.class);
                        intent.putExtra("queryAlarmType", queryAlarmType);
                        intent.putExtra("status", status);
                        intent.putExtra("detailedAlarm", alarmInformation);
                        mContext.startActivity(intent);
                    }
                });
                break;
        }
    }

    public void setAlarmCollection(Collection<AlarmInformation> alarmInformationCollection) {
        this.validateAlarmsCollection(alarmInformationCollection);
        this.mList = (List<AlarmInformation>) alarmInformationCollection;
        this.notifyDataSetChanged();
    }

    private void validateAlarmsCollection(Collection<AlarmInformation> alarmInformationCollection) {
        if (alarmInformationCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }
}