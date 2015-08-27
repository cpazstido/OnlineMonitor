package com.hy.onlinemonitor.view.Adapter;

import android.content.Context;
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
import com.hy.onlinemonitor.bean.AdministratorInformation;
import com.hy.onlinemonitor.bean.CompanyInformation;
import com.hy.onlinemonitor.bean.Role;
import com.hy.onlinemonitor.presenter.SMAdministratorPresenter;
import com.hy.onlinemonitor.view.ViewHolder.AdministratorViewHolder;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.Collection;
import java.util.List;

public class SMAdministratorRecyclerAdapter extends RecyclerSwipeAdapter<AdministratorViewHolder> {

    private Context mContext;
    private List<AdministratorInformation> mDatas;
    private AndroidTreeView tView;
    private TreeNode root;

    private List<String> roleNameList;
    private List<String> companyNameList;
    private List<Role> roleList;
    private List<CompanyInformation> companyInformations;
    private SMAdministratorPresenter smAdministratorPresenter;

    public SMAdministratorRecyclerAdapter(Context mContext, List<AdministratorInformation> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    public void setCompanyInformations(List<CompanyInformation> companyInformations) {
        this.companyInformations = companyInformations;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public void setCompanyNameList(List<String> companyNameList) {
        this.companyNameList = companyNameList;
    }

    public void setRoleNameList(List<String> roleNameList) {
        this.roleNameList = roleNameList;
    }

    @Override
    public int getItemCount() {
        return (this.mDatas != null) ? this.mDatas.size() : 0;
    }

    @Override
    public int getSwipeLayoutResourceId(int i) {
        return R.id.swipe;
    }

    @Override
    public AdministratorViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sm_administrator, viewGroup, false);
        return new AdministratorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdministratorViewHolder administratorViewHolder, final int position) {

        administratorViewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        administratorViewHolder.swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {
                YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.item_delete));
            }
        });

        administratorViewHolder.loginName.setText(mDatas.get(position).getLoginName());
        administratorViewHolder.realName.setText(mDatas.get(position).getRealName());
        administratorViewHolder.phoneNumber.setText(mDatas.get(position).getPhoneNumber());
        administratorViewHolder.isReceiveMessage.setText(mDatas.get(position).getIsReceiveMessages());

        administratorViewHolder.ActionConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDialog dialog = new MaterialDialog.Builder(mContext)
                        .title(R.string.administrator_config)
                        .customView(R.layout.dialog_administrator_change, true)
                        .positiveText(R.string.submit)
                        .negativeText(R.string.cancel)
                        .callback(new MaterialDialog.ButtonCallback() {

                            @Override
                            public void onPositive(MaterialDialog dialog) {
                                String loginName = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_administrator_loginName)).getText().toString();
                                String realName = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_administrator_realName)).getText().toString();
                                String password = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_administrator_password)).getText().toString();
                                String phoneNumber = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_administrator_number)).getText().toString();
                                String isMessage = ((Spinner) dialog.getCustomView().findViewById(R.id.dialog_administrator_message)).getSelectedItem().toString();
                                int companySn = companyInformations.get(((Spinner) dialog.getCustomView().findViewById(R.id.dialog_administrator_company)).getSelectedItemPosition()).getSn();
                                int roleSn = roleList.get(((Spinner) dialog.getCustomView().findViewById(R.id.dialog_administrator_role)).getSelectedItemPosition()).getSn();
                                int sn = mDatas.get(position).getSn();
                                smAdministratorPresenter.changeAdministrator(sn, roleSn, companySn, loginName, realName, password, phoneNumber, isMessage);

                                super.onPositive(dialog);
                            }

                            @Override
                            public void onNegative(MaterialDialog dialog) {
                                super.onNegative(dialog);
                            }
                        })
                        .build();
                EditText loginName = (EditText) dialog.getCustomView().findViewById(R.id.dialog_administrator_loginName);
                EditText realName = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_administrator_realName));
                EditText password = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_administrator_password));
                EditText number = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_administrator_number));

                loginName.setText(administratorViewHolder.loginName.getText());
                realName.setText(administratorViewHolder.realName.getText());
                password.setText(mDatas.get(position).getPassword());
                number.setText(administratorViewHolder.phoneNumber.getText());
                Spinner companySpinner = (Spinner) dialog.getCustomView().findViewById(R.id.dialog_administrator_company);
                companySpinner.setAdapter(new ArrayAdapter<>(dialog.getContext(), android.R.layout.simple_list_item_1, companyNameList));
                Spinner roleSpinner = (Spinner) dialog.getCustomView().findViewById(R.id.dialog_administrator_role);
                roleSpinner.setAdapter(new ArrayAdapter<>(dialog.getContext(), android.R.layout.simple_list_item_1, roleNameList));

                companySpinner.setSelection(companyNameList.lastIndexOf(mDatas.get(position).getRole().getRoleName()), true);
                roleSpinner.setSelection(roleNameList.lastIndexOf(mDatas.get(position).getRole().getRoleName()), true);

                Spinner messageSpinner = (Spinner) dialog.getCustomView().findViewById(R.id.dialog_administrator_message);
                messageSpinner.setAdapter(new ArrayAdapter<>(dialog.getContext(), android.R.layout.simple_list_item_1, new String[]{"是", "否"}));

                if ("是".equals(mDatas.get(position).getIsReceiveMessages())) {
                    messageSpinner.setSelection(0, true);
                } else {
                    messageSpinner.setSelection(1, true);
                }

                dialog.show();
            }
        });

        administratorViewHolder.ActionDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(mContext)
                        .content(R.string.delete_information)
                        .positiveText(R.string.yes)
                        .negativeText(R.string.no)
                        .callback(new MaterialDialog.ButtonCallback() {
                            @Override
                            public void onPositive(MaterialDialog dialog) {
                                //这里进行网络操作
                                mItemManger.removeShownLayouts(administratorViewHolder.swipeLayout);
                                smAdministratorPresenter.deleteAdministrator(mDatas.get(position).getSn());
                                mDatas.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, mDatas.size());
                                mItemManger.closeAllItems();
                                super.onPositive(dialog);
                            }
                        })
                        .show();
            }
        });
        //.neutralText(R.string.more_info)

        /*
         *杆塔管理
        administratorViewHolder.towerManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDialog dialog = new MaterialDialog.Builder(mContext)
                        .title(R.string.tower_management)
                        .customView(R.layout.dialog_tree_choice, true)
                        .positiveText(R.string.submit)
                        .negativeText(R.string.cancel)
                        .callback(new MaterialDialog.ButtonCallback() {
                            @Override
                            public void onAny(MaterialDialog dialog) {
                                super.onAny(dialog);
                            }

                            @Override
                            public void onPositive(MaterialDialog dialog) {

                                List<TreeNode> treeNode = tView.getSelected();
                                for (TreeNode treeNode1 : treeNode) {
                                    if (treeNode1.getParent() != root) {
                                        int baseId = treeNode1.getParent().getId();//这取到的是某条线路的位置
                                        String LineName = mTowers.get(baseId).getLineName();//得到线路名

                                        int towerId = treeNode1.getId();
                                        String towerName = mTowers.get(baseId).getTowers().get(towerId).getTowerName();//得到杆塔名
                                        Log.e("id", LineName + ":" + towerName);//这取到的是某个具体杆塔在list内的位置
                                    }
                                }

                                super.onPositive(dialog);
                            }

                            @Override
                            public void onNegative(MaterialDialog dialog) {
                                super.onNegative(dialog);
                            }
                        })
                        .build();
                ViewGroup containerView = (ViewGroup) dialog.getCustomView().findViewById(R.id.dialog_tree_show);

                RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.item_check, null);

                CheckBox checkAll = (CheckBox) relativeLayout.findViewById(R.id.check_box);

                checkAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            tView.selectAll(true);
                        } else {
                            tView.deselectAll();
                        }
                    }
                });
                root = TreeNode.root();

                //得到数据

                for (AdministratorTower administratorTower : mTowers) {
                    TreeNode lineTree = new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_earth, administratorTower.getLineName())).setViewHolder(new SelectableHeaderHolder(mContext));
                    for (TowerInformation towerInformation : administratorTower.getTowers()) {
                        TreeNode towerTree = new TreeNode(towerInformation.getTowerName()).setViewHolder(new SelectableItemHolder(mContext));
                        lineTree.addChild(towerTree);
                    }
                    root.addChild(lineTree);
                }
                tView = new AndroidTreeView(mContext, root);
                tView.setDefaultAnimation(true);
                tView.setSelectionModeEnabled(true);
                containerView.addView(relativeLayout);
                containerView.addView(tView.getView());
                dialog.show();
            }
        });
            */
        mItemManger.bindView(administratorViewHolder.itemView, position);
    }

    public void setAdministratorCollection(Collection<AdministratorInformation> administratorInformations) {
        this.validateAdministratorCollection(administratorInformations);

//        for(AdministratorInformation administratorInformation :(List<AdministratorInformation>)administratorInformations){
//            mDatas.add(administratorInformation);
//        }
        mDatas = (List<AdministratorInformation>) administratorInformations;
//        TransformationUtils.removeDuplicateWithOrder(mDatas);

        this.notifyDataSetChanged();
    }

    public void validateAdministratorCollection(Collection<AdministratorInformation> administratorInformations) {
        if (administratorInformations == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    public void setPresenter(SMAdministratorPresenter smAdministratorPresenter) {
        this.smAdministratorPresenter = smAdministratorPresenter;
    }
}
