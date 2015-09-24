package com.hy.onlinemonitor.view.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.EquipmentInforPage;
import com.hy.onlinemonitor.bean.EquipmentInformation;
import com.hy.onlinemonitor.bean.OwnJurisdiction;
import com.hy.onlinemonitor.utile.ShowUtile;
import com.hy.onlinemonitor.view.Activity.Function.SingleAlarmInformationActivity;
import com.hy.onlinemonitor.view.Activity.Function.VideoActivity;
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
    private int userId;

    public EquipmentRecyclerAdapter(int selectionType, Context context, EquipmentInforPage equipmentInforPage, int userId) {
        this.selectionType = selectionType;
        this.mContext = context;
        this.mList = equipmentInforPage.getList();
        this.userId = userId;
    }

    @Override
    public EquipmentListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cardview_equipment, parent,
                false);

        return new EquipmentListViewHolder(view, selectionType);
    }

    @Override
    public void onBindViewHolder(final EquipmentListViewHolder holder, int position) {
        final EquipmentInformation equipmentInformation = mList.get(position);
        holder.stateShow.setText(equipmentInformation.getEquipmnetState());
        holder.equipmentName.setText(equipmentInformation.getEquipmnetName());
        if ((equipmentInformation.getNewBreakAlarm() != 0 && 1 == selectionType) || (equipmentInformation.getNewFireAlarm() != 0 && 0 == selectionType) || equipmentInformation.getNewSensorAlarm() != 0) {
            holder.newAlarmImageView.setVisibility(View.VISIBLE);
        } else {
            holder.newAlarmImageView.setVisibility(View.GONE);
        }

        if (equipmentInformation.getNewSensorAlarm() == 0) {
            holder.newSensorAlarm.setVisibility(View.GONE);
        } else {
            holder.newSensorAlarm.setVisibility(View.VISIBLE);
        }

        if (equipmentInformation.getNewFireAlarm() == 0) {
            holder.newFireAlarm.setVisibility(View.GONE);
        } else {
            holder.newFireAlarm.setVisibility(View.VISIBLE);
        }

        if (equipmentInformation.getNewBreakAlarm() == 0) {
            holder.newBreakAlarm.setVisibility(View.GONE);
        } else {
            holder.newBreakAlarm.setVisibility(View.VISIBLE);
        }

        if (equipmentInformation.getNewSensorAlarm() == 0 && equipmentInformation.getNewFireAlarm() == 0 && equipmentInformation.getNewBreakAlarm() == 0) {
            holder.newSensorAlarm.setVisibility(View.GONE);
            holder.newFireAlarm.setVisibility(View.GONE);
            holder.newBreakAlarm.setVisibility(View.GONE);
            holder.equipmentAlarmNull.setVisibility(View.VISIBLE);
        } else {
            holder.equipmentAlarmNull.setVisibility(View.GONE);
        }

        holder.realVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), VideoActivity.class);
                intent.putExtra("type", "real");
                String choiceStr = null;
                switch (selectionType) {
                    case 0:
                        choiceStr = "fire";
                        break;
                    case 1:
                        choiceStr = "break";
                        break;
                    case 2:
                        choiceStr = "video";
                        break;
                    case 3:
                        choiceStr = "auv";
                        break;
                }
                intent.putExtra("choiceType", choiceStr);
                intent.putExtra("EquipmentInformation", equipmentInformation);
                holder.itemView.getContext().startActivity(intent);
            }
        });
        holder.historyBreakAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OwnJurisdiction.haveJurisdiction(3)) {
                    Intent intent = new Intent(holder.itemView.getContext(), SingleAlarmInformationActivity.class);
                    intent.putExtra("queryAlarmType", "break");
                    intent.putExtra("status", 1);
                    intent.putExtra("title", "外破历史报警");
                    intent.putExtra("equipmentName", equipmentInformation.getEquipmnetName());
                    intent.putExtra("userId", userId);
                    intent.putExtra("showType", 1);
                    holder.itemView.getContext().startActivity(intent);
                } else {
                    ShowUtile.noJurisdictionToast(mContext);
                }
            }
        });

        holder.historyFireAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OwnJurisdiction.haveJurisdiction(3)) {
                    Intent intent = new Intent(holder.itemView.getContext(), SingleAlarmInformationActivity.class);
                    intent.putExtra("queryAlarmType", "fire");
                    intent.putExtra("status", 1);//1是历史 0是新报警
                    intent.putExtra("title", "山火历史报警");
                    intent.putExtra("equipmentName", equipmentInformation.getEquipmnetName());
                    intent.putExtra("userId", userId);
                    intent.putExtra("showType", 1);
                    holder.itemView.getContext().startActivity(intent);
                } else {
                    ShowUtile.noJurisdictionToast(mContext);
                }
            }
        });

        holder.historySensorAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OwnJurisdiction.haveJurisdiction(3)) {
                    Intent intent = new Intent(holder.itemView.getContext(), SingleAlarmInformationActivity.class);
                    intent.putExtra("titleName", "外破历史");
                    intent.putExtra("queryAlarmType", "sensor");
                    intent.putExtra("status", 1);//1是历史 0是新报警
                    intent.putExtra("title", "传感器历史报警");
                    intent.putExtra("equipmentName", equipmentInformation.getEquipmnetName());
                    intent.putExtra("userId", userId);
                    intent.putExtra("showType", 0);
                    holder.itemView.getContext().startActivity(intent);
                } else {
                    ShowUtile.noJurisdictionToast(mContext);
                }
            }
        });

        holder.newBreakAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OwnJurisdiction.haveJurisdiction(3)) {
                    Intent intent = new Intent(holder.itemView.getContext(), SingleAlarmInformationActivity.class);
                    intent.putExtra("queryAlarmType", "break");
                    intent.putExtra("status", 0);//1是历史 0是新报警
                    intent.putExtra("title", "外破新报警");
                    intent.putExtra("equipmentName", equipmentInformation.getEquipmnetName());
                    intent.putExtra("userId", userId);
                    intent.putExtra("showType", 1);
                    holder.itemView.getContext().startActivity(intent);
                } else {
                    ShowUtile.noJurisdictionToast(mContext);
                }
            }
        });

        holder.newFireAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OwnJurisdiction.haveJurisdiction(3)) {
                    Intent intent = new Intent(holder.itemView.getContext(), SingleAlarmInformationActivity.class);
                    intent.putExtra("queryAlarmType", "fire");
                    intent.putExtra("title", "山火新报警");
                    intent.putExtra("status", 0);//1是历史 0是新报警
                    intent.putExtra("equipmentName", equipmentInformation.getEquipmnetName());
                    intent.putExtra("userId", userId);
                    intent.putExtra("showType", 1);
                    holder.itemView.getContext().startActivity(intent);
                } else {
                    ShowUtile.noJurisdictionToast(mContext);
                }
            }
        });

        holder.newSensorAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OwnJurisdiction.haveJurisdiction(3)) {
                    Intent intent = new Intent(holder.itemView.getContext(), SingleAlarmInformationActivity.class);
                    intent.putExtra("queryAlarmType", "sensor");
                    intent.putExtra("title", "传感器新报警");
                    intent.putExtra("status", 1);//1是历史 0是新报警
                    intent.putExtra("equipmentName", equipmentInformation.getEquipmnetName());
                    intent.putExtra("userId", userId);
                    intent.putExtra("showType", 0);
                    holder.itemView.getContext().startActivity(intent);
                } else {
                    ShowUtile.noJurisdictionToast(mContext);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (this.mList != null) ? this.mList.size() : 0;
    }

    public void setEquipmentCollection(Collection<EquipmentInformation> equipmentInformationCollection) {
        this.validateEquipmentCollection(equipmentInformationCollection);

        if (mList.size() == 0) {
            mList = (List<EquipmentInformation>) equipmentInformationCollection;
        } else {
            List<EquipmentInformation> newArrayList = mList;
            for (EquipmentInformation equipmentInformation : equipmentInformationCollection) {
                for (EquipmentInformation equipmentInformation1 : mList) {
                    if (equipmentInformation.getSn() == equipmentInformation1.getSn()) {
                        newArrayList.remove(equipmentInformation1);
                    }
                }
            }
            for (EquipmentInformation equipmentInformation : equipmentInformationCollection) {
                newArrayList.add(equipmentInformation);
            }
            mList = newArrayList;
        }
        this.notifyDataSetChanged();
    }

    private void validateEquipmentCollection(Collection<EquipmentInformation> equipmentInformationCollection) {
        if (equipmentInformationCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }
}
