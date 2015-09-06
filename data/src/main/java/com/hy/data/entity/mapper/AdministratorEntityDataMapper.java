package com.hy.data.entity.mapper;

import com.example.bean.DomainAdministrator;
import com.hy.data.entity.AdministratorEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 24363 on 2015/8/31.
 */
public class AdministratorEntityDataMapper {
    public AdministratorEntityDataMapper() {
    }

    public DomainAdministrator transform(AdministratorEntity administratorEntity) {
        DomainAdministrator domainAdministrator = null;
        RoleEntityDataMapper roleEntityDataMapper = new RoleEntityDataMapper();
        if (administratorEntity != null) {
            domainAdministrator = new DomainAdministrator();
            domainAdministrator.setIsReceiveMessages(administratorEntity.getReceiveMessage());
            domainAdministrator.setLoginName(administratorEntity.getLoginName());
            domainAdministrator.setPassword(administratorEntity.getPassword());
            domainAdministrator.setPhoneNumber(administratorEntity.getMobilePhone());
            domainAdministrator.setRealName(administratorEntity.getRealName());
            domainAdministrator.setCompanySn(administratorEntity.getCompany_SN());
            domainAdministrator.setDomainRole(roleEntityDataMapper.transform(administratorEntity.getRole()));
            domainAdministrator.setSn(administratorEntity.getSn());
            domainAdministrator.setAllPoleSeleceted(administratorEntity.getAllPoleSeleceted());
        }

        return domainAdministrator;
    }

    public List<DomainAdministrator> transform(Collection<AdministratorEntity> administratorEntities) {
        List<DomainAdministrator> domainAdministrators = new ArrayList<>();
        DomainAdministrator domainAdministrator;
        for (AdministratorEntity administratorEntity : administratorEntities) {
            domainAdministrator = transform(administratorEntity);
            if (domainAdministrator != null) {
                domainAdministrators.add(domainAdministrator);
            }
        }
        return domainAdministrators;
    }

}
