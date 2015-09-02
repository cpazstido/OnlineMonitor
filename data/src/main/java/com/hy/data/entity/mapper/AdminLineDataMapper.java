package com.hy.data.entity.mapper;

import com.example.bean.DomainAdminLine;
import com.hy.data.entity.AdminLineEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 24363 on 2015/9/2.
 */
public class AdminLineDataMapper {
    public AdminLineDataMapper() {
    }

    public DomainAdminLine transform(AdminLineEntity adminLineEntity) {
        DomainAdminLine domainAdminLine = null;

        if (adminLineEntity != null) {
            domainAdminLine = new DomainAdminLine();
            domainAdminLine.setName(adminLineEntity.getName());
            domainAdminLine.setTowers(AdminTowerDataMapper.transform(adminLineEntity.getPoleSet()));
        }
        return domainAdminLine;
    }

    public List<DomainAdminLine> transform(Collection<AdminLineEntity> adminLineEntities) {
        List<DomainAdminLine> domainAdminLines = new ArrayList<>();
        DomainAdminLine domainAdminLine;
        for (AdminLineEntity adminLineEntity : adminLineEntities) {
            domainAdminLine = transform(adminLineEntity);
            if (domainAdminLine != null) {
                domainAdminLines.add(domainAdminLine);
            }
        }
        return domainAdminLines;
    }

}
