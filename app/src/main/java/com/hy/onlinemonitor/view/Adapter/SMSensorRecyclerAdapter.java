package com.hy.onlinemonitor.view.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.Sensor;
import com.hy.onlinemonitor.presenter.SMSensorPresenter;
import com.hy.onlinemonitor.view.ViewHolder.SensorViewHolder;

import java.util.Collection;
import java.util.List;

public class SMSensorRecyclerAdapter extends RecyclerSwipeAdapter<SensorViewHolder> {

    private Context mContext;


    private List<Sensor> sensors;
    private SMSensorPresenter smSensorPresenter;

    public SMSensorRecyclerAdapter(Context mContext, List<Sensor> sensors) {
        this.mContext = mContext;
        this.sensors = sensors;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }
    @Override
    public SensorViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sm_sensor, viewGroup, false);
        return new SensorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SensorViewHolder sensorViewHolder, final int i) {
        sensorViewHolder.sensorName.setText(sensors.get(i).getName());
        sensorViewHolder.sensorIdentifier.setText(sensors.get(i).getSensorInDeviceID());
        sensorViewHolder.ActionDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemManger.removeShownLayouts(sensorViewHolder.swipeLayout);
                sensors.remove(i);
                notifyItemRemoved(i);
                notifyItemRangeChanged(i, sensors.size());
                mItemManger.closeAllItems();
            }
        });

    }

    @Override
    public int getItemCount() {
        return sensors.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int i) {
        return R.id.swipe;
    }

    public void setPresenter(SMSensorPresenter smSensorPresenter) {
        this.smSensorPresenter = smSensorPresenter;
    }

    public void setSensorList(List<Sensor> mList) {
        this.validateSensorCollection(mList);
        this.sensors = mList;
        this.notifyDataSetChanged();
    }

    private void validateSensorCollection(Collection<Sensor> sensors) {
        if (sensors == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }
}
