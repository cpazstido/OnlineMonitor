package com.hy.onlinemonitor.view.Adapter.MonitoringState;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.IceCoatings;
import com.hy.onlinemonitor.view.ViewHolder.StateMonitoringViewHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by 24363 on 2015/10/29.
 */
public class IceCoatingRecyclerAdapter extends RecyclerView.Adapter<StateMonitoringViewHolder> {
    private final Context mContext;
    private List<IceCoatings> mList;
    private LinkedHashSet<IceCoatings> linkedHashSet = new LinkedHashSet<>();

    public IceCoatingRecyclerAdapter(Context mContext, List<IceCoatings> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }
    public LinkedHashSet<IceCoatings> getLinkedHashSet() {
        return linkedHashSet;
    }
    @Override
    public StateMonitoringViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_cm, parent, false);
        return new StateMonitoringViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StateMonitoringViewHolder holder, int position) {
        IceCoatings iceCoatings = mList.get(position);

        holder.deviceName.setVisibility(View.GONE);
        holder.title4.setVisibility(View.VISIBLE);
        holder.title5.setVisibility(View.VISIBLE);
        holder.title6.setVisibility(View.VISIBLE);
        holder.title7.setVisibility(View.VISIBLE);

        holder.title4_data.setVisibility(View.VISIBLE);
        holder.title5_data.setVisibility(View.VISIBLE);
        holder.title6_data.setVisibility(View.VISIBLE);
        holder.title7_data.setVisibility(View.VISIBLE);

        holder.title1.setText("传感器编号");
        holder.title2.setText("数据收集时间");
        holder.title3.setText("等值覆冰厚度(mm)");
        holder.title4.setText("综合悬挂载荷(N)");
        holder.title5.setText("不均衡张力差(N)");
        holder.title6.setText("绝缘子串风偏角(°)");
        holder.title7.setText("绝缘子串偏斜角(°)");

        holder.title1_data.setText(iceCoatings.getSensorName());
        holder.title2_data.setText(iceCoatings.getCollectDataTimeStr());
        holder.title3_data.setText(iceCoatings.getEqualIceThickness() + "");
        holder.title4_data.setText(iceCoatings.getTension() + "");
        holder.title5_data.setText(iceCoatings.getTensionDifference()+"");
        holder.title6_data.setText(iceCoatings.getWindageYawAngle()+"");
        holder.title7_data.setText(iceCoatings.getDeflectionAngle()+"");
    }

    @Override
    public int getItemCount() {
        return (this.mList != null) ? this.mList.size() : 0;

    }
    public void setCollection(Collection<IceCoatings> iceCoatingses) {
        this.validateCollection(iceCoatingses);
        linkedHashSet.addAll(iceCoatingses);
        Iterator<IceCoatings> titer = linkedHashSet.iterator();
        List<IceCoatings> list1 = new ArrayList<>();
        while (titer.hasNext()) {
            list1.add(titer.next());
        }
        mList = list1;
        this.notifyDataSetChanged();
    }

    private void validateCollection(Collection<IceCoatings> iceCoatingses) {
        if (iceCoatingses == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }
}
