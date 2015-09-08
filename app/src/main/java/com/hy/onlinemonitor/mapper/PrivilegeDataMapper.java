package com.hy.onlinemonitor.mapper;

import com.example.bean.DomainPrivilege;
import com.hy.onlinemonitor.bean.Privilege;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 24363 on 2015/9/8.
 */
public class PrivilegeDataMapper {
    public PrivilegeDataMapper() {}

    public Privilege transform(DomainPrivilege domainPrivilege) {
        if (null == domainPrivilege) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        Privilege privilege = new Privilege();
        privilege.setSn(domainPrivilege.getSn());
        privilege.setPrivilegeName(domainPrivilege.getPrivilegeName());
        return privilege;
    }

    public List<Privilege> transform(Collection<DomainPrivilege> domainPrivileges){
        List<Privilege> privileges = new ArrayList<>();
        Privilege privilege;
        for (DomainPrivilege domainPrivilege : domainPrivileges) {
            privilege = transform(domainPrivilege);
            if (privilege != null) {
                privileges.add(privilege);
            }
        }

        return privileges;
    }

}
