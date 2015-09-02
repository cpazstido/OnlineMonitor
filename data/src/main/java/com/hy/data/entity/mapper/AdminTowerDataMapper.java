package com.hy.data.entity.mapper;

import com.example.bean.DomainAdminTower;
import com.hy.data.entity.AdminTowerEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 24363 on 2015/9/2.
 */
public class AdminTowerDataMapper {
    public AdminTowerDataMapper() {
    }

    public static DomainAdminTower transform(AdminTowerEntity adminTowerEntity) {
        DomainAdminTower domainAdminTower = null;

        if (adminTowerEntity != null) {
            domainAdminTower = new DomainAdminTower();
            domainAdminTower.setSn(adminTowerEntity.getSn());
            domainAdminTower.setTowerName(adminTowerEntity.getName());
        }
        return domainAdminTower;
    }

    public static List<DomainAdminTower> transform(Collection<AdminTowerEntity> adminTowerEntities) {
        List<DomainAdminTower> domainAdminTowers = new ArrayList<>();
        DomainAdminTower domainAdminTower;
        for (AdminTowerEntity adminTowerEntity : adminTowerEntities) {
            domainAdminTower = transform(adminTowerEntity);
            if (domainAdminTower != null) {
                domainAdminTowers.add(domainAdminTower);
            }
        }
        return domainAdminTowers;
    }
}
