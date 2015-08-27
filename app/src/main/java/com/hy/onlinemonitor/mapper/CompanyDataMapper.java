package com.hy.onlinemonitor.mapper;

import com.example.bean.DomainCompany;
import com.hy.onlinemonitor.bean.CompanyInformation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CompanyDataMapper {
    public CompanyDataMapper() {}

    public static CompanyInformation transform(DomainCompany domainCompany) {
        if (null == domainCompany) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        CompanyInformation companyInformation = new CompanyInformation();

        companyInformation.setSn(domainCompany.getSn());
        companyInformation.setCompanyName(domainCompany.getCompanyName());
        return companyInformation;
    }

    public static List<CompanyInformation> transform(Collection<DomainCompany> domainCompanies){
        List<CompanyInformation> companyInformations = new ArrayList<>();
        CompanyInformation companyInformation;
        for (DomainCompany domainCompany : domainCompanies) {
            companyInformation = transform(domainCompany);
            if (companyInformation != null) {
                companyInformations.add(companyInformation);
            }
        }

        return companyInformations;
    }

}
