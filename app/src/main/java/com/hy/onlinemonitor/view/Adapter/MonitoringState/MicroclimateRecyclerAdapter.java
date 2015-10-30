package com.hy.onlinemonitor.view.Adapter.MonitoringState;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.Microclimate;
import com.hy.onlinemonitor.view.ViewHolder.StateMonitoringViewHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by 24363 on 2015/10/29.
 */
public class MicroclimateRecyclerAdapter extends RecyclerView.Adapter<StateMonitoringViewHolder> {
    private final Context mContext;
    private List<Microclimate> mList;
    private LinkedHashSet<Microclimate> linkedHashSet = new LinkedHashSet<>();

    public MicroclimateRecyclerAdapter(Context mContext, List<Microclimate> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }
    public LinkedHashSet getLinkedHashSet() {
        return linkedHashSet;
    }

    @Override
    public StateMonitoringViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_cm, parent, false);
        return new StateMonitoringViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StateMonitoringViewHolder holder, int position) {
        Microclimate microclimate = mList.get(position);
        holder.deviceName.setVisibility(View.GONE);
        holder.title4.setVisibility(View.VISIBLE);
        holder.title5.setVisibility(View.VISIBLE);
        holder.title6.setVisibility(View.VISIBLE);
        holder.title7.setVisibility(View.VISIBLE);
        holder.title8.setVisibility(View.VISIBLE);
        holder.title9.setVisibility(View.VISIBLE);
        holder.title10.setVisibility(View.VISIBLE);
        holder.title11.setVisibility(View.VISIBLE);
        holder.title12.setVisibility(View.VISIBLE);
        holder.title13.setVisibility(View.VISIBLE);

        holder.title4_data.setVisibility(View.VISIBLE);
        holder.title5_data.setVisibility(View.VISIBLE);
        holder.title6_data.setVisibility(View.VISIBLE);
        holder.title7_data.setVisibility(View.VISIBLE);
        holder.title8_data.setVisibility(View.VISIBLE);
        holder.title9_data.setVisibility(View.VISIBLE);
        holder.title10_data.setVisibility(View.VISIBLE);
        holder.title11_data.setVisibility(View.VISIBLE);
        holder.title12_data.setVisibility(View.VISIBLE);
        holder.title13_data.setVisibility(View.VISIBLE);

        holder.title1.setText("传感器编号");
        holder.title2.setText("数据收集时间");
        holder.title3.setText("平均风速(m/s)");
        holder.title4.setText("风向");
        holder.title5.setText("最大风速(m/s)");
        holder.title6.setText("极大风速(m/s)");
        holder.title7.setText("标准风速(m/s)");
        holder.title8.setText("气温(℃)");
        holder.title9.setText("空气湿度(%)");
        holder.title10.setText("气压(hPa)");
        holder.title11.setText("降雨量(mm)");
        holder.title12.setText("降水强度(mm/min)");
        holder.title13.setText("辐射强度(W/㎡)");

        holder.title1_data.setText(microclimate.getSensorName());
        holder.title2_data.setText(microclimate.getCollectDataTimeStr());
        holder.title3_data.setText(microclimate.getAverageWindSpeed10Min() + "");
        holder.title4_data.setText(microclimate.getAverageWindDirection10Min() + "");
        holder.title5_data.setText(microclimate.getMaxWindSpeed() + "");
        holder.title6_data.setText(microclimate.getExtremeWindSpeed() + "");
        holder.title7_data.setText(microclimate.getStandardWindSpeed() + "");
        holder.title8_data.setText(microclimate.getAirTemperature() + "");
        holder.title9_data.setText(microclimate.getHumidity() + "");
        holder.title10_data.setText(microclimate.getAirPressure() + "");
        holder.title11_data.setText(microclimate.getPrecipitation() + "");
        holder.title12_data.setText(microclimate.getPrecipitationIntensity() + "");
        holder.title13_data.setText(microclimate.getRadiationIntensity() + "");
    }

    @Override
    public int getItemCount() {
        return (this.mList != null) ? this.mList.size() : 0;
    }

    public void setCollection(Collection<Microclimate> microclimates) {
        this.validateCollection(microclimates);
        linkedHashSet.addAll(microclimates);
        Iterator<Microclimate> titer = linkedHashSet.iterator();
        List<Microclimate> list1 = new ArrayList<>();
        while (titer.hasNext()) {
            list1.add(titer.next());
        }
        mList = list1;
        this.notifyDataSetChanged();
    }

    private void validateCollection(Collection<Microclimate> microclimates) {
        if (microclimates == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }
}
