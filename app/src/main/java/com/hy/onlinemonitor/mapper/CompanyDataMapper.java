package com.hy.onlinemonitor.mapper;

import com.example.bean.DomainCompany;
import com.hy.onlinemonitor.bean.Company;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CompanyDataMapper {
    public CompanyDataMapper() {}

    public static Company transform(DomainCompany domainCompany) {
        if (null == domainCompany) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        Company company = new Company();
        company.setSn(domainCompany.getSn());
        company.setCompanyName(domainCompany.getCompanyName());
        if(domainCompany.getLineList()!=null){
            company.setLineList(LineDataMapper.transform(domainCompany.getLineList()));
        }
        return company;
    }

    public static List<Company> transform(Collection<DomainCompany> domainCompanies){
        List<Company> companies = new ArrayList<>();
        Company company;
        for (DomainCompany domainCompany : domainCompanies) {
            company = transform(domainCompany);
            if (company != null) {
                companies.add(company);
            }
        }

        return companies;
    }

}
