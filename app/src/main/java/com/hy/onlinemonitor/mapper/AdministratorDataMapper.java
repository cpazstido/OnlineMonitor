package com.hy.onlinemonitor.mapper;

import com.example.bean.DomainAdministrator;
import com.hy.onlinemonitor.bean.AdministratorInformation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AdministratorDataMapper {
    public AdministratorDataMapper() {}

    public AdministratorInformation transform(DomainAdministrator domainAdministrator) {
        if (null == domainAdministrator) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        AdministratorInformation administratorInformation = new AdministratorInformation();

        administratorInformation.setRealName(domainAdministrator.getRealName());
        administratorInformation.setPhoneNumber(domainAdministrator.getPhoneNumber());
        administratorInformation.setPassword(domainAdministrator.getPassword());
        administratorInformation.setOwnTowerList(domainAdministrator.getOwnTowerList());
        administratorInformation.setAllTowerList(domainAdministrator.getAllTowerList());
        administratorInformation.setIsReceiveMessages(domainAdministrator.getIsReceiveMessages());
        administratorInformation.setLoginName(domainAdministrator.getLoginName());
        administratorInformation.setCompanySn(domainAdministrator.getCompanySn());
        administratorInformation.setRole(RoleDataMapper.transform(domainAdministrator.getDomainRole()));
        administratorInformation.setSn(domainAdministrator.getSn());
        return administratorInformation;
    }

    public List<AdministratorInformation> transform(Collection<DomainAdministrator> domainAdministrators){
        List<AdministratorInformation> administratorInformations = new ArrayList<>();
        AdministratorInformation administratorInformation;
        for (DomainAdministrator domainAdministrator : domainAdministrators) {
            administratorInformation = transform(domainAdministrator);
            if (administratorInformation != null) {
                administratorInformations.add(administratorInformation);
            }
        }

        return administratorInformations;
    }
}
