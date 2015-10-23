package com.hy.onlinemonitor.view.Adapter.StateMonitoring;

import android.support.v7.widget.RecyclerView;

import com.hy.onlinemonitor.bean.OnlineDeviceState;
import com.hy.onlinemonitor.view.ViewHolder.StateMonitoringViewHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by 24363 on 2015/10/22.
 */
public abstract class CMBaseAdapter<VH> extends RecyclerView.Adapter {

    protected List<OnlineDeviceState> mList;
    protected TreeMap<String, OnlineDeviceState> equipmentInformatics = new TreeMap<>();
    protected StateMonitoringViewHolder viewHolder;

    public void setCollection(Collection<OnlineDeviceState> OnlineDeviceStates) {
        this.validateEquipmentCollection(OnlineDeviceStates);

        for (OnlineDeviceState OnlineDeviceState : OnlineDeviceStates) {
            equipmentInformatics.put(OnlineDeviceState.getDeviceId(), OnlineDeviceState);
        }

        Iterator titer = equipmentInformatics.entrySet().iterator();
        List<OnlineDeviceState> list1 = new ArrayList<>();
        while (titer.hasNext()) {
            Map.Entry ent = (Map.Entry) titer.next();
            list1.add((OnlineDeviceState) ent.getValue());
        }
        mList = list1;
        this.notifyDataSetChanged();
    }

    private void validateEquipmentCollection(Collection<OnlineDeviceState> OnlineDeviceStates) {
        if (OnlineDeviceStates == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    public TreeMap<String, OnlineDeviceState> getEquipmentInformatics() {
        return equipmentInformatics;
    }

}
