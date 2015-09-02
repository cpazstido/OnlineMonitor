package com.hy.onlinemonitor.mapper;

import com.example.bean.DomainAdminLine;
import com.hy.onlinemonitor.bean.AdminLine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AdminLineDataMapper {
    public AdminLineDataMapper() {}

    public static AdminLine transform(DomainAdminLine domainAdminLine) {
        if (null == domainAdminLine) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        AdminLine adminLine = new AdminLine();
        adminLine.setTowers(AdminTowerDataMapper.transform(domainAdminLine.getTowers()));
        adminLine.setName(domainAdminLine.getName());
        return adminLine;
    }

    public static List<AdminLine> transform(Collection<DomainAdminLine> domainAdminLines){
        List<AdminLine> adminLines = new ArrayList<>();
        AdminLine adminLine;
        for (DomainAdminLine domainAdminLine : domainAdminLines) {
            adminLine = transform(domainAdminLine);
            if (adminLine != null) {
                adminLines.add(adminLine);
            }
        }

        return adminLines;
    }
}
