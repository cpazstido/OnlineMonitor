package com.hy.onlinemonitor.view.ViewHolder;


import android.view.View;
import android.widget.TextView;

import com.hy.onlinemonitor.R;

/**
 * Created by wsw on 2015/7/21.
 */
public class AdministratorViewHolder extends SMBaseViewHolder{

    public TextView loginName,realName,phoneNumber,isReceiveMessage,towerManagement;

    public AdministratorViewHolder(View itemView) {
        super(itemView);
        realName = (TextView) itemView.findViewById(R.id.real_name);
        loginName = (TextView) itemView.findViewById(R.id.login_name);
        phoneNumber = (TextView) itemView.findViewById(R.id.phone_number);
        isReceiveMessage = (TextView) itemView.findViewById(R.id.is_receive_messages);
        towerManagement= (TextView) itemView.findViewById(R.id.tower_management);
    }
}
