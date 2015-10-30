package com.hy.onlinemonitor.view.Adapter.MonitoringState;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.ConductorSwingWithWind;
import com.hy.onlinemonitor.view.ViewHolder.StateMonitoringViewHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by 24363 on 2015/10/29.
 */
public class ConductorSwingWithWindRecyclerAdapter extends RecyclerView.Adapter<StateMonitoringViewHolder> {
    private final Context mContext;
    private List<ConductorSwingWithWind> mList;
    private LinkedHashSet<ConductorSwingWithWind> linkedHashSet = new LinkedHashSet<>();

    public ConductorSwingWithWindRecyclerAdapter(Context mContext, List<ConductorSwingWithWind> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public LinkedHashSet<ConductorSwingWithWind> getLinkedHashSet() {
        return linkedHashSet;
    }
    @Override
    public StateMonitoringViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_cm, parent, false);
        return new StateMonitoringViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StateMonitoringViewHolder holder, int position) {
        ConductorSwingWithWind conductorSwingWithWind = mList.get(position);

        holder.deviceName.setVisibility(View.GONE);
        holder.title4.setVisibility(View.VISIBLE);
        holder.title5.setVisibility(View.VISIBLE);

        holder.title4_data.setVisibility(View.VISIBLE);
        holder.title5_data.setVisibility(View.VISIBLE);

        holder.title1.setText("传感器编号");
        holder.title2.setText("数据收集时间");
        holder.title3.setText("风偏角(°)");
        holder.title4.setText("偏斜角(°)");
        holder.title5.setText("最小电气间隙(m)");

        holder.title1_data.setText(conductorSwingWithWind.getSensorName());
        holder.title2_data.setText(conductorSwingWithWind.getCollectDataTimeStr());
        holder.title3_data.setText(conductorSwingWithWind.getWindageYawAngle() + "");
        holder.title4_data.setText(conductorSwingWithWind.getDeflectionAngle() + "");
        holder.title5_data.setText(conductorSwingWithWind.getLeastClearance()+"");
    }

    @Override
    public int getItemCount() {
        return (this.mList != null) ? this.mList.size() : 0;

    }
    public void setCollection(Collection<ConductorSwingWithWind> conductorSwingWithWinds) {
        this.validateCollection(conductorSwingWithWinds);
        linkedHashSet.addAll(conductorSwingWithWinds);
        Iterator<ConductorSwingWithWind> titer = linkedHashSet.iterator();
        List<ConductorSwingWithWind> list1 = new ArrayList<>();
        while (titer.hasNext()) {
            list1.add(titer.next());
        }
        mList = list1;
        this.notifyDataSetChanged();
    }

    private void validateCollection(Collection<ConductorSwingWithWind> conductorSwingWithWinds) {
        if (conductorSwingWithWinds == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }
}
