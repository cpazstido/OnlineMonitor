package com.hy.onlinemonitor.view.Adapter.MonitoringState;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.PoleStatus;
import com.hy.onlinemonitor.view.ViewHolder.StateMonitoringViewHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by 24363 on 2015/10/29.
 */
public class PoleStatusRecyclerAdapter extends RecyclerView.Adapter<StateMonitoringViewHolder> {
    private final Context mContext;
    private List<PoleStatus> mList;
    private LinkedHashSet<PoleStatus> linkedHashSet = new LinkedHashSet<>();

    public PoleStatusRecyclerAdapter(Context mContext, List<PoleStatus> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public LinkedHashSet<PoleStatus> getLinkedHashSet() {
        return linkedHashSet;
    }

    @Override
    public StateMonitoringViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_cm, parent, false);
        return new StateMonitoringViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StateMonitoringViewHolder holder, int position) {
        PoleStatus poleStatus = mList.get(position);

        holder.deviceName.setVisibility(View.GONE);
        holder.title4.setVisibility(View.VISIBLE);

        holder.title4_data.setVisibility(View.VISIBLE);

        holder.title1.setText("传感器编号");
        holder.title2.setText("数据收集时间");
        holder.title3.setText("顺线倾斜角(°)");
        holder.title4.setText("横向倾斜角((°))");

        holder.title1_data.setText(poleStatus.getSensorName());
        holder.title2_data.setText(poleStatus.getCollectDataTimeStr());
        holder.title3_data.setText(poleStatus.getAngleX() + "");
        holder.title4_data.setText(poleStatus.getAngleY() + "");
    }

    @Override
    public int getItemCount() {
        return (this.mList != null) ? this.mList.size() : 0;
    }

    public void setCollection(Collection<PoleStatus> poleStatuses) {
        this.validateCollection(poleStatuses);
        linkedHashSet.addAll(poleStatuses);
        Iterator<PoleStatus> titer = linkedHashSet.iterator();
        List<PoleStatus> list1 = new ArrayList<>();
        while (titer.hasNext()) {
            list1.add(titer.next());
        }
        mList = list1;
        this.notifyDataSetChanged();
    }

    private void validateCollection(Collection<PoleStatus> poleStatuses) {
        if (poleStatuses == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }
}
