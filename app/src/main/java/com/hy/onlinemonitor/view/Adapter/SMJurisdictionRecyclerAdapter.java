package com.hy.onlinemonitor.view.Adapter;


import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.Role;
import com.hy.onlinemonitor.presenter.SMJurisdictionPresenter;
import com.hy.onlinemonitor.view.ViewHolder.JurisdictionViewHolder;

import java.util.Collection;
import java.util.List;

public class SMJurisdictionRecyclerAdapter extends RecyclerSwipeAdapter<JurisdictionViewHolder> {

    private Context mContext;
    private List<Role> roleList;
    private SMJurisdictionPresenter smJurisdictionPresenter;
    private SMJurisdictionRecyclerAdapter.changeJurisdictionClickListener changeJurisdictionClickListener;

    public void setChangeJurisdictionClickListener(SMJurisdictionRecyclerAdapter.changeJurisdictionClickListener changeJurisdictionClickListener) {
        this.changeJurisdictionClickListener = changeJurisdictionClickListener;
    }

    public interface changeJurisdictionClickListener{
        void onChangeJurisdictionClick(Role role);
    }

    public SMJurisdictionRecyclerAdapter(Context mContext, List<Role> roleList) {
        this.mContext = mContext;
        this.roleList = roleList;
    }

    @Override
    public JurisdictionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sm_jurisdiction, viewGroup, false);
        return new JurisdictionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final JurisdictionViewHolder jurisdictionViewHolder,final int i) {
        jurisdictionViewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        jurisdictionViewHolder.swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {
                YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.item_delete));
            }
        });

        jurisdictionViewHolder.administratorName.setText(roleList.get(i).getRoleName());

        jurisdictionViewHolder.administratorName.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new MaterialDialog.Builder(mContext)
                        .title(R.string.role_name_change)
                        .inputType(InputType.TYPE_CLASS_TEXT |
                                InputType.TYPE_TEXT_VARIATION_PERSON_NAME |
                                InputType.TYPE_TEXT_FLAG_CAP_WORDS)
                        .content(roleList.get(i).getRoleName())
                        .positiveText(R.string.submit)
                        .negativeText(R.string.cancel)
                        .alwaysCallInputCallback() // this forces the callback to be invoked with every input change
                        .input(R.string.role_add_hint, 0, false, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                if (input.toString().equalsIgnoreCase("")) {
                                    dialog.getActionButton(DialogAction.POSITIVE).setEnabled(false);
                                } else {
                                    dialog.getActionButton(DialogAction.POSITIVE).setEnabled(true);
                                }
                            }
                        })
                        .callback(new MaterialDialog.ButtonCallback() {
                            @Override
                            public void onAny(MaterialDialog dialog) {
                                super.onAny(dialog);
                            }

                            @Override
                            public void onPositive(MaterialDialog dialog) {
                                smJurisdictionPresenter.changeRole(roleList.get(i).getSn(),dialog.getInputEditText().getText().toString());
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
                        .show();
                return true;
            }
        });

        jurisdictionViewHolder.ActionConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SMJurisdictionRecyclerAdapter.this.changeJurisdictionClickListener != null) {
                    SMJurisdictionRecyclerAdapter.this.changeJurisdictionClickListener.onChangeJurisdictionClick(roleList.get(i));
                }
            }
        });

        jurisdictionViewHolder.ActionDelete.setOnClickListener(new View.OnClickListener() {
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
                                mItemManger.removeShownLayouts(jurisdictionViewHolder.swipeLayout);
                                smJurisdictionPresenter.deleteRole(roleList.get(i).getSn());
                                roleList.remove(i);
                                notifyItemRemoved(i);
                                notifyItemRangeChanged(i, roleList.size());
                                mItemManger.closeAllItems();
                                super.onPositive(dialog);
                            }
                        })
                        .show();
            }
        });

        mItemManger.bindView(jurisdictionViewHolder.itemView, i);

    }

    @Override
    public int getItemCount() {
        return (this.roleList != null) ? this.roleList.size() : 0;
    }

    @Override
    public int getSwipeLayoutResourceId(int i) {
        return R.id.swipe;
    }

    public void setRolePage(Collection<Role> roleCollection) {
        this.validateRoleCollection(roleCollection);
        roleList = (List<Role>) roleCollection;
        this.notifyDataSetChanged();
    }

    private void validateRoleCollection(Collection<Role> roleCollection) {
        if (roleCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    public void setPresenter(SMJurisdictionPresenter smJurisdictionPresenter) {
        this.smJurisdictionPresenter = smJurisdictionPresenter;
    }
}
