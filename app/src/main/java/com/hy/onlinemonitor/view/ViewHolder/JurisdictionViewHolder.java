package com.hy.onlinemonitor.view.ViewHolder;

import android.view.View;
import android.widget.TextView;

import com.hy.onlinemonitor.R;


/**
 * Created by Administrator on 2015/7/23.
 */
public class JurisdictionViewHolder extends SMBaseViewHolder{
    public TextView administratorName;

    public JurisdictionViewHolder(final View itemView) {
        super(itemView);
        administratorName = (TextView) itemView.findViewById(R.id.administrator_name);
    }
}
