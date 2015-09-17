package com.hy.data.entity.mapper;

import com.example.bean.DomainCompany;
import com.hy.data.entity.CompanyEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CompanyEntityDataMapper {
    public CompanyEntityDataMapper() {
    }

    public DomainCompany transform (CompanyEntity companyEntity){
        DomainCompany domainCompany = null;

        if (companyEntity != null) {
            domainCompany = new DomainCompany();
            domainCompany.setCompanyName(companyEntity.getCompanyName());
            domainCompany.setSn(companyEntity.getSn());
            if(companyEntity.getLineList() !=null){
                LineEntityDataMapper lineEntityDataMapper = new LineEntityDataMapper();
                domainCompany.setLineList(lineEntityDataMapper.transform(companyEntity.getLineList()));
            }
            if(companyEntity.getCompanyAddress()!= null){
                domainCompany.setCompanyAddress(companyEntity.getCompanyAddress());
                domainCompany.setParentCompanyName(companyEntity.getParentCompanyName());
            }
        }
        return domainCompany;
    }

    public List<DomainCompany> transform(Collection<CompanyEntity> companyEntities) {
        List<DomainCompany> domainCompanies = new ArrayList<>();
        DomainCompany domainCompany;
        for (CompanyEntity companyEntity : companyEntities) {
            domainCompany = transform(companyEntity);
            if (domainCompany != null) {
                domainCompanies.add(domainCompany);
            }
        }
        return domainCompanies;
    }
}
