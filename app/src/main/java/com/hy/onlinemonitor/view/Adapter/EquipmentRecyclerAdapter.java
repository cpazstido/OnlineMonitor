package com.hy.onlinemonitor.view.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.EquipmentInformation;
import com.hy.onlinemonitor.view.ViewHolder.EquipmentListViewHolder;
import com.lid.lib.LabelView;

import java.util.Collection;
import java.util.List;

/**
 * Created by wsw on 2015/7/15.
 */
public class EquipmentRecyclerAdapter extends RecyclerView.Adapter<EquipmentListViewHolder> {
    private int selectionType;
    private Context mContext;
    private List<EquipmentInformation> mList;
    private LabelView label;
    public EquipmentRecyclerAdapter(int selectionType, Context context, List<EquipmentInformation> list) {
        this.selectionType = selectionType;
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public EquipmentListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cardview_equipment, parent,
                false);

        EquipmentListViewHolder holder = new EquipmentListViewHolder(view, selectionType);
        return holder;
    }

    @Override
    public void onBindViewHolder(final EquipmentListViewHolder holder, int position) {
        final EquipmentInformation equipmentInformation = mList.get(position);
        holder.stateShow.setText(equipmentInformation.getEquipmnetState());
        holder.equipmentName.setText(equipmentInformation.getEquipmnetName());
        if(equipmentInformation.getNewBreakAlarm()!=0 || equipmentInformation.getNewFireAlarm()!=0|| equipmentInformation.getNewSensorAlarm()!=0){
            holder.newAlarmImageView.setVisibility(View.VISIBLE);
        }
        if(equipmentInformation.getNewSensorAlarm() ==0){
            //newSensorAlarm,newFireAlarm,newBreakAlarm
            //equipmentAlarmNull
            holder.newSensorAlarm.setVisibility(View.GONE);
        }
        if(equipmentInformation.getNewFireAlarm() ==0){
            holder.newFireAlarm.setVisibility(View.GONE);
        }
        if(equipmentInformation.getNewBreakAlarm() ==0){
            holder.newBreakAlarm.setVisibility(View.GONE);
        }
        if(equipmentInformation.getNewSensorAlarm() ==0 && equipmentInformation.getNewFireAlarm() ==0&& equipmentInformation.getNewBreakAlarm() ==0)        {
            holder.newSensorAlarm.setVisibility(View.GONE);
            holder.newFireAlarm.setVisibility(View.GONE);
            holder.newBreakAlarm.setVisibility(View.GONE);
            holder.equipmentAlarmNull.setVisibility(View.VISIBLE);
        }

        holder.realVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(holder.itemView.getContext(), VideoActivity.class);

//                holder.itemView.getContext().startActivity(intent);
            }
        });
        holder.historyBreakAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(holder.itemView.getContext(), AlarmInformationActivity.class);
//                intent.putExtra("titleName","外破历史");
//                holder.itemView.getContext().startActivity(intent);
            }
        });
        holder.historyFireAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(holder.itemView.getContext(), AlarmInformationActivity.class);
//                intent.putExtra("titleName","山火历史");
//                holder.itemView.getContext().startActivity(intent);
            }
        });

        holder.historySensorAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(holder.itemView.getContext(), AlarmInformationActivity.class);                intent.putExtra("titleName","外破历史");
//                intent.putExtra("titleName","传感器历史");
//                holder.itemView.getContext().startActivity(intent);
            }
        });

        holder.newBreakAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(holder.itemView.getContext(), AlarmInformationActivity.class);
//                intent.putExtra("titleName","新外破");
//                holder.itemView.getContext().startActivity(intent);
            }
        });

        holder.newFireAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(holder.itemView.getContext(), AlarmInformationActivity.class);
//                intent.putExtra("titleName","新山火");
//                holder.itemView.getContext().startActivity(intent);
            }
        });

        holder.newSensorAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(holder.itemView.getContext(), AlarmInformationActivity.class);
//                intent.putExtra("titleName","新传感器");
//                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (this.mList != null) ? this.mList.size() : 0;
    }

    public void setEquipmentCollection(Collection<EquipmentInformation> equipmentInformationCollection){
        this.validateEquipmentCollection(equipmentInformationCollection);
        this.mList = (List<EquipmentInformation>) equipmentInformationCollection;
        this.notifyDataSetChanged();
    }

    private void validateEquipmentCollection(Collection<EquipmentInformation> equipmentInformationCollection) {
        if (equipmentInformationCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }
}
