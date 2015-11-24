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
import com.hy.onlinemonitor.utile.TransformationUtils;
import com.hy.onlinemonitor.view.Activity.Function.SingleAlarmInformationActivity;
import com.hy.onlinemonitor.view.Activity.Function.VideoActivity;
import com.hy.onlinemonitor.view.ViewHolder.EquipmentListViewHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by wsw on 2015/7/15.
 */
public class EquipmentRecyclerAdapter extends RecyclerView.Adapter<EquipmentListViewHolder> {
    private int selectionType;
    private Context mContext;
    private List<EquipmentInformation> mList;
    private int userId;
    String choiceStr = null;

    TreeMap<String, EquipmentInformation> equipmentInformatics = new TreeMap<>();

    public TreeMap<String, EquipmentInformation> getEquipmentInformatics() {
        return equipmentInformatics;
    }

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

        final EquipmentInformation equipmentInformation = mList.get(position);
        String realShow = equipmentInformation.getLineName() + "--" + equipmentInformation.getPoleName() + "--";
        realShow += TransformationUtils.getDeviceNameLastSix(equipmentInformation.getEquipmnetName());
        holder.stateShow.setText(equipmentInformation.getEquipmnetState());
        holder.equipmentName.setText(realShow);

        holder.newSensorAlarm.setText(TransformationUtils.getNumberButton("传感器",equipmentInformation.getNewSensorAlarm()));
        holder.newFireAlarm.setText(TransformationUtils.getNumberButton("山火",equipmentInformation.getNewFireAlarm()));
        holder.newBreakAlarm.setText(TransformationUtils.getNumberButton("外破",equipmentInformation.getNewBreakAlarm()));

        holder.historySensorAlarm.setText(TransformationUtils.getNumberButton("传感器",equipmentInformation.getHistorySensorAlarm()));
        holder.historyFireAlarm.setText(TransformationUtils.getNumberButton("山火",equipmentInformation.getHistoryFireAlarm()));
        holder.historyBreakAlarm.setText(TransformationUtils.getNumberButton("外破",equipmentInformation.getHistoryBreakAlarm()));

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
                    intent.putExtra("dvrType", 2);//2代表外破
                    intent.putExtra("dvrId", equipmentInformation.getDvrId());
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
                    intent.putExtra("dvrType", 1);//1代表山火
                    intent.putExtra("dvrId", equipmentInformation.getDvrId());
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
                    intent.putExtra("curProject", choiceStr);
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
                    intent.putExtra("dvrType", 2);//2代表外破
                    intent.putExtra("dvrId", equipmentInformation.getDvrId());
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
                    intent.putExtra("dvrId", equipmentInformation.getDvrId());
                    intent.putExtra("dvrType", 1);//1代表山火
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
                    intent.putExtra("curProject", choiceStr);
                    intent.putExtra("status", 0);//1是历史 0是新报警
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

        for (EquipmentInformation equipmentInformation : equipmentInformationCollection) {
            equipmentInformatics.put(equipmentInformation.getEquipmnetName(), equipmentInformation);
        }

        Iterator titer = equipmentInformatics.entrySet().iterator();
        List<EquipmentInformation> list1 = new ArrayList<>();
        while (titer.hasNext()) {
            Map.Entry ent = (Map.Entry) titer.next();
            list1.add((EquipmentInformation) ent.getValue());
        }
        mList = list1;
        this.notifyDataSetChanged();
    }

    private void validateEquipmentCollection(Collection<EquipmentInformation> equipmentInformationCollection) {
        if (equipmentInformationCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }
}
