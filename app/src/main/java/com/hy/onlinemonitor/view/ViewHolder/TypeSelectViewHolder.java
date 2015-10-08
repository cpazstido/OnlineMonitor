package com.hy.onlinemonitor.view.ViewHolder;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hy.onlinemonitor.R;

/**
 * Created by 24363 on 2015/10/8.
 */
public class TypeSelectViewHolder extends SMBaseViewHolder{
    public CardView cardView;
    public ImageView cardImage;
    public TextView cardTitle;

    public TypeSelectViewHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.select_card_view);
        cardImage = (ImageView) itemView.findViewById(R.id.list_item_image);
        cardTitle = (TextView) itemView.findViewById(R.id.list_item_title);
    }
}
