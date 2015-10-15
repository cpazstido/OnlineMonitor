package com.hy.onlinemonitor.view.Activity.SystemManagement;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.daimajia.swipe.util.Attributes;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.Equipment;
import com.hy.onlinemonitor.bean.EquipmentPage;
import com.hy.onlinemonitor.bean.Line;
import com.hy.onlinemonitor.bean.OwnJurisdiction;
import com.hy.onlinemonitor.bean.Pole;
import com.hy.onlinemonitor.presenter.SMEquipmentPresenter;
import com.hy.onlinemonitor.utile.ShowUtile;
import com.hy.onlinemonitor.view.Adapter.SMEquipmentRecyclerAdapter;
import com.hy.onlinemonitor.view.ViewHolder.IconTreeItemHolder;
import com.hy.onlinemonitor.view.ViewHolder.SelectableHeaderHolder;
import com.hy.onlinemonitor.view.ViewHolder.SelectableItemHolder;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 24363 on 2015/9/11.
 */
public class EquipmentManageActivity extends SMBaseActivity {

    private SMEquipmentRecyclerAdapter mAdapter;
    private AndroidTreeView tView;
    private TreeNode root;
    private List<Line> lines;
    private SMEquipmentPresenter smEquipmentPresenter;
    private EquipmentPage equipmentPage;
    private List<String> deviceTypeList;
    private List<Pole> poles;
    private int poleSn;

    public void setLines(List<Line> lines) {
        this.lines = lines;
        poles = new ArrayList<>();
        for (Line line : lines) {
            for (Pole pole : line.getPoleList()) {
                poles.add(pole);
            }
        }
    }

    @Override
    protected void initTitle() {
        toolbar.setTitle(R.string.system_management);
        toolbar.setSubtitle(R.string.equipment_manage);
    }

    @Override
    protected void initViewDisplay() {
        choiceBtn.setVisibility(View.VISIBLE);
        choiceBtn.setText(R.string.tower_choice);

        choiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//获取数据,并创建树形菜单对话框
                Log.e("choiceBtn", "调用了");

                final MaterialDialog dialog = new MaterialDialog.Builder(EquipmentManageActivity.this)
                        .title(R.string.line_choice)
                        .customView(R.layout.dialog_tree_choice, true)
                        .negativeText(R.string.cancel)
                        .build();

                LinearLayout ll = (LinearLayout) dialog.getCustomView().findViewById(R.id.dialog_tree_show);
                root = TreeNode.root();

                for (final Line line : lines) {
                    TreeNode lineTree = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_earth, line.getLineName())).setViewHolder(new SelectableHeaderHolder(EquipmentManageActivity.this));
                    if (line.getPoleList().size() != 0) {
                        for (final Pole pole : line.getPoleList()) {
                            TreeNode poleTree = new TreeNode(pole.getPoleName()).setViewHolder(new SelectableItemHolder(EquipmentManageActivity.this));
                            poleTree.setClickListener(new TreeNode.TreeNodeClickListener() {
                                @Override
                                public void onClick(TreeNode treeNode, Object o) {
                                    dialog.cancel();
                                    poleSn = pole.getPoleSn();
                                    smEquipmentPresenter.getEquipmentPage(poleSn);
                                }
                            });
                            lineTree.addChild(poleTree);
                        }
                    } else {
                        TreeNode poleTree = new TreeNode("无").setViewHolder(new SelectableItemHolder(EquipmentManageActivity.this));
                        lineTree.addChild(poleTree);
                    }
                    lineTree.setSelectable(false);
                    root.addChild(lineTree);
                }
                tView = new AndroidTreeView(EquipmentManageActivity.this, root);

                tView.setDefaultAnimation(true);
                tView.setSelectionModeEnabled(false);
                ll.addView(tView.getView());
                dialog.show();
            }
        });
    }

    @Override
    protected void initRvAdapter() {
        mAdapter = new SMEquipmentRecyclerAdapter(EquipmentManageActivity.this, new ArrayList<Equipment>());
        mAdapter.setMode(Attributes.Mode.Single);
        smRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initRvData() {
        smEquipmentPresenter = new SMEquipmentPresenter(EquipmentManageActivity.this);
        smEquipmentPresenter.setEquipmentManageActivity(this);
        smEquipmentPresenter.loadAllPole(getUser().getUserId());
    }

    @Override
    protected void menuDataLoad() {
        if (OwnJurisdiction.haveJurisdiction(35)) {//拥有的权限
            MaterialDialog dialog = new MaterialDialog.Builder(EquipmentManageActivity.this)
                    .title(R.string.equipment_add)
                    .customView(R.layout.dialog_equipment_change, true)
                    .positiveText(R.string.submit)
                    .negativeText(R.string.cancel)
                    .callback(new MaterialDialog.ButtonCallback() {
                        @Override
                        public void onPositive(MaterialDialog dialog) {
                            String deviceType = deviceTypeList.get(((Spinner) dialog.getCustomView().findViewById(R.id.dialog_equipment_type)).getSelectedItemPosition());
                            int isSendMessage = ((Spinner) dialog.getCustomView().findViewById(R.id.dialog_equipment_type)).getSelectedItemPosition();
                            int poleSn = poles.get(((Spinner) dialog.getCustomView().findViewById(R.id.dialog_equipment_type)).getSelectedItemPosition()).getPoleSn();
                            if (isSendMessage == 0) {
                                isSendMessage = 1;
                            } else {
                                isSendMessage = 0;
                            }
                            String deviceID = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_equipment_identifier)).getText().toString();
                            String dvrid = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_equipment_dvrid)).getText().toString();
                            String angleRelativeToNorthPole = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_equipment_preset)).getText().toString();
                            String cma_ID = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_equipment_cmaid)).getText().toString();
                            String sensor_ID = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_equipment_sensorid)).getText().toString();
                            String equipment_ID = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_equipment_eqmenid)).getText().toString();

                            smEquipmentPresenter.addEquipment(poleSn, deviceID, dvrid, angleRelativeToNorthPole, deviceType, isSendMessage, cma_ID, sensor_ID, equipment_ID);
                            super.onPositive(dialog);
                        }

                        @Override
                        public void onNegative(MaterialDialog dialog) {
                            super.onNegative(dialog);
                        }

                    })
                    .build();

            Spinner equipmentTypeSpinner = (Spinner) dialog.getCustomView().findViewById(R.id.dialog_equipment_type);
            Spinner informationSendSpinner = (Spinner) dialog.getCustomView().findViewById(R.id.dialog_alarm_information_send);
            Spinner poleChoiceSpinner = (Spinner) dialog.getCustomView().findViewById(R.id.dialog_equipment_tower_choice);

            String[] deviceType = {"山火", "外破", "普通视频", "无人机"};
            deviceTypeList = new ArrayList<>();
            deviceTypeList.add("fire");
            deviceTypeList.add("break");
            deviceTypeList.add("video");
            deviceTypeList.add("uav");
            equipmentTypeSpinner.setAdapter(new ArrayAdapter<>(dialog.getContext(), android.R.layout.simple_list_item_1, deviceType));

            String[] isSendMessage = {"是", "否"};
            informationSendSpinner.setAdapter(new ArrayAdapter<>(dialog.getContext(), android.R.layout.simple_list_item_1, isSendMessage));

            List<String> poleName = new ArrayList<>();
            for (Pole pole : poles) {
                poleName.add(pole.getPoleName());
            }
            poleChoiceSpinner.setAdapter(new ArrayAdapter<>(dialog.getContext(), android.R.layout.simple_list_item_1, poleName));

            dialog.show();
        } else {
            ShowUtile.noJurisdictionToast(EquipmentManageActivity.this);
        }
    }

    @Override
    protected void doRefresh() {
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smEquipmentPresenter.loadAllPole(getUser().getUserId());
            }
        });
    }

    @Override
    public void showLoading() {
        alertDialog.show();
    }

    @Override
    public void hideLoading() {
        alertDialog.cancel();
    }

    @Override
    public void showError(String message) {
        ErrorThing(message);
    }

    @Override
    public Context getContext() {
        return EquipmentManageActivity.this;
    }

    public void renderEquipmentList(EquipmentPage equipmentPage) {
        if (equipmentPage != null) {
            errorMessageLl.setVisibility(View.GONE);
            smRecyclerView.setVisibility(View.VISIBLE);
            this.equipmentPage = equipmentPage;
            mAdapter.setSensorManage(sensorManage);
            mAdapter.setEquipmentPage(equipmentPage.getList());
            mAdapter.setPresenter(smEquipmentPresenter);
        }else{
            showError(getResources().getString(R.string.not_data));
        }
    }

    private SMEquipmentRecyclerAdapter.SensorManage sensorManage =
            new SMEquipmentRecyclerAdapter.SensorManage() {
                @Override
                public void ToSensorActivity(Equipment equipment) {
                    Intent sensorIntent = new Intent(EquipmentManageActivity.this, SensorManageActivity.class);
                    sensorIntent.putExtra("equipment", equipment);
                    sensorIntent.putExtra("userId", getUser().getUserId());
                    Log.e("tag", "startActivityForResult");
                    startActivityForResult(sensorIntent, 88);
                }
            };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("onActivityResult", "requestCode" + requestCode + "resultCode" + resultCode);
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是888
            case 888:
                Log.e("onActivityResult", "onActivityResult");
                smEquipmentPresenter.getEquipmentPage(poleSn);
                break;
            default:
                break;
        }
    }

    public void restEquipment(String result) {
        if (result.equals("true")) {
            Toast.makeText(EquipmentManageActivity.this, "复位成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(EquipmentManageActivity.this, "复位失败，请重试", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        smEquipmentPresenter.destroy();
    }
}
