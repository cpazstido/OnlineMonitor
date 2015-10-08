package com.hy.onlinemonitor.view.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.presenter.UserPresenter;
import com.hy.onlinemonitor.view.ViewHolder.TypeSelectViewHolder;

import java.util.List;

/**
 * Created by 24363 on 2015/10/8.
 */
public class TypeSelectAdapter extends RecyclerView.Adapter<TypeSelectViewHolder> {
    private final Context mContext;
    private List<String> list;
    private UserPresenter userPresenter;

    public TypeSelectAdapter(Context mContext, List<String> ownedEquipmentList, UserPresenter userPresenter) {
        this.mContext = mContext;
        this.list = ownedEquipmentList;
        this.userPresenter = userPresenter;
    }

    @Override
    public TypeSelectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.cardview_selection, parent, false);
        return new TypeSelectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TypeSelectViewHolder holder, final int position) {
        int choiceType = -1;
        switch (list.get(position)) {
            case "fire":
                choiceType = 0;
                holder.cardImage.setImageDrawable(mContext.getResources().getDrawable(R.drawable.fire));
                holder.cardTitle.setText("山火");
                break;
            case "break":
                choiceType = 1;
                holder.cardImage.setImageDrawable(mContext.getResources().getDrawable(R.drawable.break_picture));
                holder.cardTitle.setText("外破");
                break;
            case "video":
                choiceType = 2;
                holder.cardImage.setImageDrawable(mContext.getResources().getDrawable(R.drawable.normal));
                holder.cardTitle.setText("普通视频");
                break;
            case "uav":
                choiceType = 3;
                holder.cardImage.setImageDrawable(mContext.getResources().getDrawable(R.drawable.autoplane));
                holder.cardTitle.setText("无人机");
                break;
        }

        final int finalChoiceType = choiceType;
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPresenter.setCurrentProject(finalChoiceType, mContext, list.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return (this.list != null) ? this.list.size() : 0;
    }

}
