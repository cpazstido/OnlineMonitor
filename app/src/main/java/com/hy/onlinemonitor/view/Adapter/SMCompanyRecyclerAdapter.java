package com.hy.onlinemonitor.view.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.Company;
import com.hy.onlinemonitor.bean.OwnJurisdiction;
import com.hy.onlinemonitor.presenter.SMCompanyPresenter;
import com.hy.onlinemonitor.utile.ShowUtile;
import com.hy.onlinemonitor.view.ViewHolder.CompanyViewHolder;

import java.util.List;

/**
 * Created by wsw on 2015/7/21.
 */
public class SMCompanyRecyclerAdapter extends RecyclerSwipeAdapter<CompanyViewHolder> {
    private Context mContext;
    private List<Company> mDataset;
    private View positiveAction; //对话框的按钮
    private EditText newName, newAddress, newSuper;
    private SMCompanyPresenter smCompanyPresenter;

    public SMCompanyRecyclerAdapter(Context mContext, List<Company> mDataset) {
        this.mContext = mContext;
        this.mDataset = mDataset;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    @Override
    public CompanyViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sm_company, viewGroup, false);
        return new CompanyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CompanyViewHolder companyViewHolder, final int position) {
        companyViewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        companyViewHolder.swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {
                YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.item_delete));
            }
        });
        companyViewHolder.companyName.setText(mDataset.get(position).getCompanyName());
        companyViewHolder.companyAddress.setText(mDataset.get(position).getCompanyAddress());
        companyViewHolder.companySuperior.setText(mDataset.get(position).getCompanySuperior());

        companyViewHolder.ActionConfig.setOnClickListener(new View.OnClickListener() {  //设置
            @Override
            public void onClick(View v) {
                if (OwnJurisdiction.haveJurisdiction(28)) {//拥有的权限
                    MaterialDialog dialog = new MaterialDialog.Builder(mContext)
                            .title(R.string.company_config)
                            .customView(R.layout.dialog_company_change, true)
                            .positiveText(R.string.submit)
                            .negativeText(R.string.cancel)
                            .callback(new MaterialDialog.ButtonCallback() {

                                @Override
                                public void onPositive(MaterialDialog dialog) {
                                    String companyName = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_company_name)).getText().toString();
                                    String address = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_company_address)).getText().toString();
                                    int sn = mDataset.get(position).getSn();
                                    smCompanyPresenter.modifCompany(companyName, address, sn);
                                    super.onPositive(dialog);
                                }

                            })
                            .build();
                    positiveAction = dialog.getActionButton(DialogAction.POSITIVE);

                    newName = (EditText) dialog.getCustomView().findViewById(R.id.dialog_company_name);
                    newAddress = (EditText) dialog.getCustomView().findViewById(R.id.dialog_company_address);
                    Spinner companySpinner = (Spinner) dialog.getCustomView().findViewById(R.id.dialog_company_parent);
                    companySpinner.setVisibility(View.GONE);
                    newName.setText(companyViewHolder.companyName.getText());
                    newAddress.setText(companyViewHolder.companyAddress.getText());

                    dialog.show();
                } else {
                    ShowUtile.noJurisdictionToast(mContext);
                }
            }
        });
        companyViewHolder.ActionDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OwnJurisdiction.haveJurisdiction(27)) {//拥有的权限
                    new MaterialDialog.Builder(mContext)
                            .content(R.string.delete_information)
                            .positiveText(R.string.yes)
                            .negativeText(R.string.no)
                            .callback(new MaterialDialog.ButtonCallback() {
                                @Override
                                public void onPositive(MaterialDialog dialog) {
                                    //这里进行网络操作
                                    int sn = mDataset.get(position).getSn();
                                    smCompanyPresenter.deleteCompany(sn);
                                    mItemManger.removeShownLayouts(companyViewHolder.swipeLayout);
                                    mDataset.remove(position);
                                    notifyItemRemoved(position);
                                    notifyItemRangeChanged(position, mDataset.size());
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

        mItemManger.bindView(companyViewHolder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setPresenter(SMCompanyPresenter smCompanyPresenter) {
        this.smCompanyPresenter = smCompanyPresenter;
    }

    public void setCompanyList(List<Company> mList) {
        mDataset = mList;
        this.notifyDataSetChanged();
    }
}
