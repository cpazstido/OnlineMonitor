package com.hy.data.entity.mapper;

import com.example.bean.DomainPrivilege;
import com.hy.data.entity.PrivilegeEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 24363 on 2015/9/8.
 */
public class PrivilegeEntityDataMapper {
    public PrivilegeEntityDataMapper() {
    }

    public DomainPrivilege transform(PrivilegeEntity privilegeEntity) {
        DomainPrivilege domainPrivilege = null;

        if (privilegeEntity != null) {
            domainPrivilege = new DomainPrivilege();
            domainPrivilege.setPrivilegeName(privilegeEntity.getPrivilegeName());
            domainPrivilege.setSn(privilegeEntity.getSn());
        }
        return domainPrivilege;
    }

    public List<DomainPrivilege> transform(Collection<PrivilegeEntity> privilegeEntities) {
        List<DomainPrivilege> domainPrivileges = new ArrayList<>();
        DomainPrivilege domainPrivilege;
        for (PrivilegeEntity privilegeEntity : privilegeEntities) {
            domainPrivilege = transform(privilegeEntity);
            if (domainPrivilege != null) {
                domainPrivileges.add(domainPrivilege);
            }
        }
        return domainPrivileges;
    }

}
