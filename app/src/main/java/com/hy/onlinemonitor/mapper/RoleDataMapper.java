package com.hy.onlinemonitor.mapper;

import com.example.bean.DomainRole;
import com.hy.onlinemonitor.bean.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RoleDataMapper {
    public RoleDataMapper() {}

    public static Role transform(DomainRole domainRole) {
        if (null == domainRole) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        Role role = new Role();
        role.setSn(domainRole.getSn());
        role.setRoleName(domainRole.getRoleName());
        return role;
    }

    public static List<Role> transform(Collection<DomainRole> domainRoles){
        List<Role> roles = new ArrayList<>();
        Role role;
        for (DomainRole domainRole : domainRoles) {
            role = transform(domainRole);
            if (role != null) {
                roles.add(role);
            }
        }

        return roles;
    }
}
