package com.hy.data.entity.mapper;

import com.example.bean.DomainRole;
import com.hy.data.entity.RoleEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 24363 on 2015/8/31.
 */
public class RoleEntityDataMapper {
    public RoleEntityDataMapper() {
    }

    public DomainRole transform (RoleEntity roleEntity){
        DomainRole domainRole = null;

        if (roleEntity != null) {
            domainRole = new DomainRole();
            domainRole.setSn(roleEntity.getSn());
            domainRole.setRoleName(roleEntity.getRoleName());
        }

        return domainRole;
    }

    public List<DomainRole> transform(Collection<RoleEntity> roleEntities) {
        List<DomainRole> domainRoles = new ArrayList<>();
        DomainRole domainRole;
        for (RoleEntity roleEntity : roleEntities) {
            domainRole = transform(roleEntity);
            if (domainRole != null) {
                domainRoles.add(domainRole);
            }
        }
        return domainRoles;
    }
}
