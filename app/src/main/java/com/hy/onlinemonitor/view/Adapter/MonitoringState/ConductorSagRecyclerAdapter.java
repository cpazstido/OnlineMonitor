package com.hy.onlinemonitor.view.Adapter.MonitoringState;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.ConductorSag;
import com.hy.onlinemonitor.view.ViewHolder.StateMonitoringViewHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by 24363 on 2015/10/29.
 */
public class ConductorSagRecyclerAdapter extends RecyclerView.Adapter<StateMonitoringViewHolder> {
    private final Context mContext;
    private List<ConductorSag> mList;
    private LinkedHashSet<ConductorSag> linkedHashSet = new LinkedHashSet<>();

    public ConductorSagRecyclerAdapter(Context mContext, List<ConductorSag> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public LinkedHashSet<ConductorSag> getLinkedHashSet() {
        return linkedHashSet;
    }
    @Override
    public StateMonitoringViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_cm, parent, false);
        return new StateMonitoringViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StateMonitoringViewHolder holder, int position) {
        ConductorSag conductorSag = mList.get(position);
        holder.deviceName.setVisibility(View.GONE);
        
        holder.title4.setVisibility(View.VISIBLE);
        holder.title5.setVisibility(View.VISIBLE);

        holder.title4_data.setVisibility(View.VISIBLE);
        holder.title5_data.setVisibility(View.VISIBLE);

        holder.title1.setText("传感器编号");
        holder.title2.setText("数据收集时间");
        holder.title3.setText("导线弧垂(m)");
        holder.title4.setText("线夹出口处导线切线与水平线夹角(°)");
        holder.title5.setText("测量方法");

        holder.title1_data.setText(conductorSag.getSensorName());
        holder.title2_data.setText(conductorSag.getCollectDataTimeStr());
        holder.title3_data.setText(conductorSag.getSag() + "");
        holder.title4_data.setText(conductorSag.getAngleabs() + "");
        holder.title5_data.setText(conductorSag.getTestmeasure());
    }

    @Override
    public int getItemCount() {
        return (this.mList != null) ? this.mList.size() : 0;
    }

    public void setCollection(Collection<ConductorSag> conductorSags) {
        this.validateCollection(conductorSags);
        linkedHashSet.addAll(conductorSags);
        Iterator<ConductorSag> titer = linkedHashSet.iterator();
        List<ConductorSag> list1 = new ArrayList<>();
        while (titer.hasNext()) {
            list1.add(titer.next());
        }
        mList = list1;
        this.notifyDataSetChanged();
    }

    private void validateCollection(Collection<ConductorSag> conductorSags) {
        if (conductorSags == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }
}
