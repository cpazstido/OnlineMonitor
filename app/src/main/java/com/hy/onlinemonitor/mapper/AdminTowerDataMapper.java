package com.hy.onlinemonitor.mapper;

import com.example.bean.DomainAdminTower;
import com.hy.onlinemonitor.bean.AdminTower;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 24363 on 2015/9/2.
 */
public class AdminTowerDataMapper {

    public static AdminTower transform(DomainAdminTower domainAdminTower) {
        if (null == domainAdminTower) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        AdminTower adminTower = new AdminTower();
        adminTower.setTowerName(domainAdminTower.getTowerName());
        adminTower.setSn(domainAdminTower.getSn());
        return adminTower;
    }

    public static List<AdminTower> transform(Collection<DomainAdminTower> domainAdminTowers){
        List<AdminTower> adminTowers = new ArrayList<>();
        AdminTower adminTower;
        for (DomainAdminTower domainAdminTower : domainAdminTowers) {
            adminTower = transform(domainAdminTower);
            if (adminTower != null) {
                adminTowers.add(adminTower);
            }
        }

        return adminTowers;
    }
}
