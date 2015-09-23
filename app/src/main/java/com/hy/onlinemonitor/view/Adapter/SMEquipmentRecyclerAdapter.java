package com.hy.onlinemonitor.view.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.afollestad.materialdialogs.MaterialDialog;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.Equipment;
import com.hy.onlinemonitor.bean.OwnJurisdiction;
import com.hy.onlinemonitor.presenter.SMEquipmentPresenter;
import com.hy.onlinemonitor.utile.ShowUtile;
import com.hy.onlinemonitor.view.Activity.SystemManagement.EquipmentMoreInformationActivity;
import com.hy.onlinemonitor.view.ViewHolder.EquipmentViewHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SMEquipmentRecyclerAdapter extends RecyclerSwipeAdapter<EquipmentViewHolder> {

    private Context mContext;
    private List<Equipment> equipmentList;
    private SMEquipmentPresenter smEquipmentPresenter;
    private List<String> deviceTypeList;
    private SMEquipmentRecyclerAdapter.SensorManage sensorManage;

    public void setSensorManage(SensorManage sensorManage) {
        this.sensorManage = sensorManage;
    }

    public interface SensorManage {
        void ToSensorActivity(Equipment equipment);
    }

    public SMEquipmentRecyclerAdapter(Context mContext, List<Equipment> equipmentList) {
        this.mContext = mContext;
        this.equipmentList = equipmentList;
    }

    @Override
    public EquipmentViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sm_equipment, viewGroup, false);
        return new EquipmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final EquipmentViewHolder equipmentViewHolder, final int i) {
        final Equipment equipment = equipmentList.get(i);
        equipmentViewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        equipmentViewHolder.swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {
                YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.item_delete));
            }
        });

        if (equipmentList.get(i).getSendMmsState() == 0) {
            equipmentViewHolder.alarmInformationSend.setText("否");
        } else {
            equipmentViewHolder.alarmInformationSend.setText("是");
        }
        equipmentViewHolder.equipmentDvrid.setText(equipmentList.get(i).getDvrID());
        equipmentViewHolder.equipmentIdentifier.setText(equipmentList.get(i).getDeviceID());
        equipmentViewHolder.equipmentType.setText(equipmentList.get(i).getDvrType());

        equipmentViewHolder.moreInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moreInformationIntent = new Intent(mContext, EquipmentMoreInformationActivity.class);
                moreInformationIntent.putExtra("equipmentInformation", equipmentList.get(i));
                mContext.startActivity(moreInformationIntent);
            }
        });

        equipmentViewHolder.equipmentRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OwnJurisdiction.haveJurisdiction(19)) {//拥有的权限
                    smEquipmentPresenter.equipmentReset(equipment.getSn());
                } else {
                    ShowUtile.noJurisdictionToast(mContext);
                }
            }
        });

        equipmentViewHolder.ActionConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OwnJurisdiction.haveJurisdiction(25)) {//拥有的权限
                    deviceTypeList = new ArrayList<>();
                    deviceTypeList.add("fire");
                    deviceTypeList.add("break");
                    deviceTypeList.add("video");
                    deviceTypeList.add("uav");

                    MaterialDialog dialog = new MaterialDialog.Builder(mContext)
                            .title(R.string.equipment_change)
                            .customView(R.layout.dialog_equipment_change, true)
                            .positiveText(R.string.submit)
                            .negativeText(R.string.cancel)
                            .callback(new MaterialDialog.ButtonCallback() {
                                @Override
                                public void onAny(MaterialDialog dialog) {
                                    super.onAny(dialog);
                                }

                                @Override
                                public void onPositive(MaterialDialog dialog) {
                                    String deviceType = deviceTypeList.get(((Spinner) dialog.getCustomView().findViewById(R.id.dialog_equipment_type)).getSelectedItemPosition());
                                    int isSendMessage = ((Spinner) dialog.getCustomView().findViewById(R.id.dialog_equipment_type)).getSelectedItemPosition();
                                    if (isSendMessage == 0) {
                                        isSendMessage = 1;
                                    } else {
                                        isSendMessage = 0;
                                    }
                                    String deviceID = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_equipment_identifier)).getText().toString();
                                    String dvrId = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_equipment_dvrid)).getText().toString();
                                    String angleRelativeToNorthPole = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_equipment_preset)).getText().toString();
                                    String cma_ID = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_equipment_cmaid)).getText().toString();
                                    String sensor_ID = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_equipment_sensorid)).getText().toString();
                                    String equipment_ID = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_equipment_eqmenid)).getText().toString();
                                    smEquipmentPresenter.changeEquipment(deviceID, dvrId, angleRelativeToNorthPole, deviceType, isSendMessage, cma_ID, sensor_ID, equipment_ID, equipmentList.get(i).getSn());

                                    super.onPositive(dialog);
                                }

                                @Override
                                public void onNegative(MaterialDialog dialog) {
                                    super.onNegative(dialog);
                                }

                                @Override
                                public void onNeutral(MaterialDialog dialog) {
                                    super.onNeutral(dialog);
                                }
                            })
                            .build();
                    dialog.getCustomView().findViewById(R.id.dialog_tower_choice_tv).setVisibility(View.GONE);
                    dialog.getCustomView().findViewById(R.id.dialog_equipment_tower_choice).setVisibility(View.GONE);

                    Spinner equipmentTypeSpinner = (Spinner) dialog.getCustomView().findViewById(R.id.dialog_equipment_type);
                    Spinner informationSendSpinner = (Spinner) dialog.getCustomView().findViewById(R.id.dialog_alarm_information_send);

                    EditText equipmentIdentifierET = (EditText) dialog.getCustomView().findViewById(R.id.dialog_equipment_identifier);
                    EditText equipmentDvridET = (EditText) dialog.getCustomView().findViewById(R.id.dialog_equipment_dvrid);
                    EditText equipmentPresetET = (EditText) dialog.getCustomView().findViewById(R.id.dialog_equipment_preset);
                    EditText equipmentCmaidET = (EditText) dialog.getCustomView().findViewById(R.id.dialog_equipment_cmaid);
                    EditText equipmentSensoridET = (EditText) dialog.getCustomView().findViewById(R.id.dialog_equipment_sensorid);
                    EditText equipmentEeqmenidET = (EditText) dialog.getCustomView().findViewById(R.id.dialog_equipment_eqmenid);


                    String[] deviceType = {"山火", "外破", "普通视频", "无人机"};
                    equipmentTypeSpinner.setAdapter(new ArrayAdapter<>(dialog.getContext(), android.R.layout.simple_list_item_1, deviceType));
                    String deviceTypes = equipmentList.get(i).getDeviceType();
                    String[] isSendMessage = {"是", "否"};
                    informationSendSpinner.setAdapter(new ArrayAdapter<>(dialog.getContext(), android.R.layout.simple_list_item_1, isSendMessage));
                    int messageSend = -1;
                    if (equipmentList.get(i).getSendMmsState() == 0) {
                        messageSend = 1;
                    } else {
                        messageSend = 0;
                    }

                    informationSendSpinner.setSelection(messageSend, true);  //设置spinner的初始值.需要根据选中的来选择

                    int typeSet = -1;
                    switch (deviceTypes) {
                        case "fire":
                            typeSet = 0;
                            break;
                        case "break":
                            typeSet = 1;
                            break;
                        case "video":
                            typeSet = 2;
                            break;
                        case "uav":
                            typeSet = 3;
                            break;
                    }
                    equipmentTypeSpinner.setSelection(typeSet, true);  //设置spinner的初始值.需要根据选中的来选择

                    equipmentIdentifierET.setText(equipmentList.get(i).getDeviceID());
                    equipmentDvridET.setText(equipmentList.get(i).getDvrID());
                    equipmentPresetET.setText("" + equipmentList.get(i).getAngleRelativeToNorthPole());
                    equipmentCmaidET.setText(equipmentList.get(i).getCma_ID());
                    equipmentSensoridET.setText(equipmentList.get(i).getSensor_ID());
                    equipmentEeqmenidET.setText(equipmentList.get(i).getEquipment_ID());
                    dialog.show();
                } else {
                    ShowUtile.noJurisdictionToast(mContext);
                }
            }
        });

        equipmentViewHolder.ActionDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OwnJurisdiction.haveJurisdiction(36)) {//拥有的权限
                    new MaterialDialog.Builder(mContext)
                            .content(R.string.delete_information)
                            .positiveText(R.string.yes)
                            .negativeText(R.string.no)
                            .callback(new MaterialDialog.ButtonCallback() {
                                @Override
                                public void onPositive(MaterialDialog dialog) {
                                    //这里进行网络操作
                                    mItemManger.removeShownLayouts(equipmentViewHolder.swipeLayout);
                                    smEquipmentPresenter.deleteEquipment(equipmentList.get(i).getSn());
                                    equipmentList.remove(i);
                                    notifyItemRemoved(i);
                                    notifyItemRangeChanged(i, equipmentList.size());
                                    mItemManger.closeAllItems();
                                    super.onPositive(dialog);
                                }
                            })
                            .show();
                } else {
                    ShowUtile.noJurisdictionToast(mContext);
                }
            }
        });
        equipmentViewHolder.sensorManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SMEquipmentRecyclerAdapter.this.sensorManage != null) {
                    SMEquipmentRecyclerAdapter.this.sensorManage.ToSensorActivity(equipment);
                }

            }
        });
        mItemManger.bindView(equipmentViewHolder.itemView, i);
    }

    @Override
    public int getItemCount() {
        return equipmentList.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int i) {
        return R.id.swipe;
    }

    public void setEquipmentPage(List<Equipment> list) {
        this.validateEquipmentCollection(list);
        this.equipmentList = list;
        this.notifyDataSetChanged();
    }

    private void validateEquipmentCollection(Collection<Equipment> equipments) {
        if (equipments == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    public void setPresenter(SMEquipmentPresenter smEquipmentPresenter) {
        this.smEquipmentPresenter = smEquipmentPresenter;
    }

}
