package com.hy.onlinemonitor.view.ViewHolder;

import android.view.View;
import android.widget.TextView;

import com.hy.onlinemonitor.R;


/**
 * Created by wsw on 2015/7/21.
 */

public class CompanyViewHolder extends SMBaseViewHolder{

    public TextView companyName,companyAddress,companySuperior;
    public CompanyViewHolder(View itemView) {
        super(itemView);
        companyName = (TextView) itemView.findViewById(R.id.company_name);
        companyAddress = (TextView) itemView.findViewById(R.id.company_address);
        companySuperior = (TextView) itemView.findViewById(R.id.company_superior);
    }
}
