package com.hy.onlinemonitor.view.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.daimajia.swipe.SwipeLayout;
import com.hy.onlinemonitor.R;

public class SMBaseViewHolder extends RecyclerView.ViewHolder{

    public SwipeLayout swipeLayout;
    public ImageView ActionDelete, ActionConfig;

    public SMBaseViewHolder(View itemView) {
        super(itemView);
        ActionDelete = (ImageView) itemView.findViewById(R.id.item_delete);
        ActionConfig = (ImageView) itemView.findViewById(R.id.item_config);
        swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);
    }

}
