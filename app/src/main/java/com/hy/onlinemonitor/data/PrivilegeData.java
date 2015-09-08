package com.hy.onlinemonitor.data;

import com.hy.onlinemonitor.bean.Privilege;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 24363 on 2015/9/8.
 */
public class PrivilegeData {
    public static CharSequence[] getAllPrivilege(List<Privilege> privileges){
        List<String> strings = new ArrayList<>();
        for(Privilege privilege :privileges){
            strings.add(privilege.getPrivilegeName());
        }
        return strings.toArray(new CharSequence[strings.size()]);
    }

    public static Integer[] getOwnPrivilege(List<Privilege> privileges, List<Privilege> ownPrivileges) {
        List<Integer> list = new ArrayList<>();

        for(Privilege privilege :privileges){
            for(Privilege privilege1 :ownPrivileges){
                if(privilege1.getSn() == privilege.getSn()){
                    list.add(privileges.indexOf(privilege));
                }
            }
        }
        return list.toArray(new Integer[list.size()]);
    }
}
