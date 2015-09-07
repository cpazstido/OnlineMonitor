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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SMJurisdictionRecyclerAdapter extends RecyclerSwipeAdapter<JurisdictionViewHolder> {

    private Context mContext;
    private List<Role> roleList;
    private CharSequence[] mDatase;
    private SMJurisdictionPresenter smJurisdictionPresenter;
    private int userId;
    public SMJurisdictionRecyclerAdapter(Context mContext, List<Role> roleList,int userId) {
        this.mContext = mContext;
        this.roleList = roleList;
        this.userId = userId;
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

                //这里进行网络操作取得数据
               mDatase = getformInternet();

                new MaterialDialog.Builder(mContext)
                        .title(R.string.jurisdiction_config)
                        .items(mDatase)
                        .itemsCallbackMultiChoice(new Integer[]{1, 3}, new MaterialDialog.ListCallbackMultiChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                                StringBuilder str = new StringBuilder();
                                for (int i = 0; i < which.length; i++) {
                                    if (i > 0) str.append('\n');
                                    str.append(which[i]);
                                    str.append(": ");
                                    str.append(text[i]);
                                }
                                return true; // allow selection
                            }
                        })
                        .callback(new MaterialDialog.ButtonCallback() {
                            @Override
                            public void onNeutral(MaterialDialog dialog) {
                                if(dialog.getSelectedIndices().length == mDatase.length) {
                                    dialog.clearSelectedIndices();
                                }else {
                                    int[] a = new int[mDatase.length];
                                    for (int i = 0; i < mDatase.length; i++) {
                                        a[i] = i;
                                    }
//                                    Integer[] integes = TransformationUtils.getIntegerFromInt(a);
//
//                                    dialog.setSelectedIndices(integes);
                                }
                            }

                            @Override
                            public void onPositive(MaterialDialog dialog) {
                                super.onPositive(dialog);
                            }

                            @Override
                            public void onNegative(MaterialDialog dialog) {
                                dialog.cancel();
                                super.onNegative(dialog);
                            }
                        })
                        .alwaysCallMultiChoiceCallback()
                        .positiveText(R.string.submit)
                        .negativeText(R.string.cancel)
                        .neutralText(R.string.check_all)
                        .autoDismiss(false)
                        .show();
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
                                smJurisdictionPresenter.deleteRole(userId, roleList.get(i).getSn());
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

    private CharSequence[] getformInternet() {
        List<String> mlists = new ArrayList<>();
        mlists.add("山火");
        mlists.add("外破");
        mlists.add("普通视频");
        mlists.add("呵呵");
        mlists.add("哈哈");
        mlists.add("嘿嘿");
        mlists.add("山火");
        mlists.add("外破");
        mlists.add("普通视频");
        mlists.add("呵呵");
        mlists.add("哈哈");
        mlists.add("嘿嘿");
        mlists.add("山火");
        mlists.add("外破");
        mlists.add("普通视频");
        mlists.add("呵呵");
        mlists.add("哈哈");
        mlists.add("嘿嘿");
        mlists.add("山火");
        mlists.add("外破");
        mlists.add("普通视频");
        mlists.add("呵呵");
        mlists.add("哈哈");
        mlists.add("嘿嘿");
        mlists.add("山火");
        mlists.add("外破");
        mlists.add("普通视频");
        mlists.add("呵呵");
        mlists.add("哈哈");
        mlists.add("嘿嘿");
        mlists.add("山火");
        mlists.add("外破");
        mlists.add("普通视频");
        mlists.add("呵呵");
        mlists.add("哈哈");
        mlists.add("嘿嘿");

        return mlists.toArray(new CharSequence[mlists.size()]);
    }

    @Override
    public int getItemCount() {
        return roleList.size();
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
