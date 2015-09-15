package com.hy.onlinemonitor.view.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.Pole;
import com.hy.onlinemonitor.presenter.SMPolePresenter;
import com.hy.onlinemonitor.view.ViewHolder.TowerViewHolder;

import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2015/7/27.
 */
public class SMTowerRecyclerAdapter extends RecyclerSwipeAdapter<TowerViewHolder> {
    private Context mContext;
    private List<Pole> poles;
    private SMPolePresenter smPolePresenter;
    public SMTowerRecyclerAdapter(Context mContext, List<Pole> poles) {
        this.mContext = mContext;
        this.poles = poles;
    }

    @Override
    public TowerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sm_tower, viewGroup, false);
        return new TowerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TowerViewHolder towerViewHolder, final int i) {
        towerViewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        towerViewHolder.swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {
                YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.item_delete));
            }
        });

        towerViewHolder.towerName.setText(poles.get(i).getPoleName());
        towerViewHolder.longitude.setText(poles.get(i).getLongitude());
        towerViewHolder.latitude.setText(poles.get(i).getLatitude());
        towerViewHolder.altitude.setText(poles.get(i).getAltitude());
        towerViewHolder.ActionDelete.setOnClickListener(new View.OnClickListener() {
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
                                mItemManger.removeShownLayouts(towerViewHolder.swipeLayout);
                                smPolePresenter.deletePole(poles.get(i).getPoleSn());
                                poles.remove(i);
                                notifyItemRemoved(i);
                                notifyItemRangeChanged(i, poles.size());
                                mItemManger.closeAllItems();
                                super.onPositive(dialog);
                            }
                        })
                        .show();
            }
        });
        towerViewHolder.ActionConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDialog dialog = new MaterialDialog.Builder(mContext)
                        .title(R.string.tower_management)
                        .customView(R.layout.dialog_tower_change, true)
                        .positiveText(R.string.submit)
                        .negativeText(R.string.cancel)
                        .callback(new MaterialDialog.ButtonCallback() {
                            @Override
                            public void onPositive(MaterialDialog dialog) {
                                String name = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_tower_name)).getText().toString();
                                String longitude = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_longitude)).getText().toString();
                                String latitude = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_latitude)).getText().toString();
                                String altitude = ((EditText) dialog.getCustomView().findViewById(R.id.dialog_altitude)).getText().toString();
                                int poleSn = poles.get(i).getPoleSn();
                                smPolePresenter.changePole(name,longitude,latitude,altitude,poleSn);
                                super.onPositive(dialog);
                            }
                        })
                        .build();

                EditText dialogTowerName = (EditText) dialog.getCustomView().findViewById(R.id.dialog_tower_name);
                EditText dialogLongitude = (EditText) dialog.getCustomView().findViewById(R.id.dialog_longitude);
                EditText dialogLatitude = (EditText) dialog.getCustomView().findViewById(R.id.dialog_latitude);
                EditText dialogAltitude = (EditText) dialog.getCustomView().findViewById(R.id.dialog_altitude);

                dialogTowerName.setText(towerViewHolder.towerName.getText());
                dialogLongitude.setText(towerViewHolder.longitude.getText());
                dialogLatitude.setText(towerViewHolder.latitude.getText());
                dialogAltitude.setText(towerViewHolder.altitude.getText());

                dialog.show();
            }
        });
        mItemManger.bindView(towerViewHolder.itemView, i);
    }

    @Override
    public int getItemCount() {
        return poles.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int i) {
        return R.id.swipe;
    }

    public void setPoleCollection(Collection<Pole> poleCollection){
        this.validateEquipmentCollection(poleCollection);
        if(poles.size()==0){
            poles = (List<Pole>) poleCollection;
        }else{
            for(Pole pole :poleCollection){
                if(!poles.contains(pole)){
                    poles.add(pole);
                }
            }
        }
        this.notifyDataSetChanged();
    }

    private void validateEquipmentCollection(Collection<Pole> poles) {
        if (poles == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    public void setPresenter(SMPolePresenter smPolePresenter) {
        this.smPolePresenter = smPolePresenter;
    }

    public void cleanList() {
        poles.clear();
    }
}
