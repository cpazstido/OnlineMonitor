package com.hy.onlinemonitor.view.Adapter.MonitoringState;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.AeolianVibration;
import com.hy.onlinemonitor.view.ViewHolder.StateMonitoringViewHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by 24363 on 2015/10/29.
 */
public class AeolianVibrationRecyclerAdapter extends RecyclerView.Adapter<StateMonitoringViewHolder> {

    private final Context mContext;
    private List<AeolianVibration> mList;
    private LinkedHashSet<AeolianVibration> linkedHashSet = new LinkedHashSet<>();

    public AeolianVibrationRecyclerAdapter(Context mContext, List<AeolianVibration> aeolianVibrations) {
        this.mContext = mContext;
        this.mList = aeolianVibrations;
    }
    public LinkedHashSet<AeolianVibration> getLinkedHashSet() {
        return linkedHashSet;
    }
    @Override
    public StateMonitoringViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_cm, parent, false);
        return new StateMonitoringViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StateMonitoringViewHolder holder, int position) {
        AeolianVibration aeolianVibration = mList.get(position);
        holder.deviceName.setVisibility(View.GONE);
        holder.title4.setVisibility(View.VISIBLE);
        holder.title5.setVisibility(View.VISIBLE);

        holder.title4_data.setVisibility(View.VISIBLE);
        holder.title5_data.setVisibility(View.VISIBLE);

        holder.title1.setText("传感器编号");
        holder.title2.setText("数据收集时间");
        holder.title3.setText("振动幅值(mm)");
        holder.title4.setText("振动频率(HZ)");
        holder.title5.setText("动弯应变");

        holder.title1_data.setText(aeolianVibration.getSensorName());
        holder.title2_data.setText(aeolianVibration.getCollectDataTimeStr());
        holder.title3_data.setText(aeolianVibration.getVibrationAmplitude() + "");
        holder.title4_data.setText(aeolianVibration.getVibrationFrequencyround() + "");
        holder.title5_data.setText(aeolianVibration.getDynamicBendingStrain() + "");

    }

    @Override
    public int getItemCount() {
        return (this.mList != null) ? this.mList.size() : 0;
    }

    public void setCollection(Collection<AeolianVibration> aeolianVibrations) {
        this.validateCollection(aeolianVibrations);
        linkedHashSet.addAll(aeolianVibrations);

        Iterator<AeolianVibration> titer = linkedHashSet.iterator();
        List<AeolianVibration> list1 = new ArrayList<>();
        while (titer.hasNext()) {
            list1.add(titer.next());
        }
        mList = list1;
        this.notifyDataSetChanged();
    }

    private void validateCollection(Collection<AeolianVibration> aeolianVibrations) {
        if (aeolianVibrations == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }
}
