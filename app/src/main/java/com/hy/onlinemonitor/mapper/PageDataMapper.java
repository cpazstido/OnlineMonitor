package com.hy.onlinemonitor.mapper;

import com.example.bean.DoaminEquipmentPage;
import com.example.bean.DomainAdministratorPage;
import com.example.bean.DomainAlarmPage;
import com.example.bean.DomainRolePage;
import com.hy.onlinemonitor.bean.AdministratorPage;
import com.hy.onlinemonitor.bean.AlarmPage;
import com.hy.onlinemonitor.bean.EquipmentPage;
import com.hy.onlinemonitor.bean.RolePage;


public class PageDataMapper {

    public PageDataMapper() {
    }

    public EquipmentPage transform(DoaminEquipmentPage doaminEquipmentPage) {
        if (null == doaminEquipmentPage) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        EquipmentInforDataMapper equipmentInforDataMapper = new EquipmentInforDataMapper();

        EquipmentPage equipmentPage = new EquipmentPage();
        equipmentPage.setRowCount(doaminEquipmentPage.getRowCount());
        equipmentPage.setPageNum(doaminEquipmentPage.getPageNum());
        equipmentPage.setPageSize(doaminEquipmentPage.getPageSize());
        equipmentPage.setTotalPage(doaminEquipmentPage.getTotalPage());
        equipmentPage.setList(equipmentInforDataMapper.transform(doaminEquipmentPage.getList()));
        return equipmentPage;
    }

    public AlarmPage transform(DomainAlarmPage domainAlarmPage) {
        if (null == domainAlarmPage) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        AlarmDataMapper alarmDataMapper = new AlarmDataMapper();

        AlarmPage alarmPage = new AlarmPage();
        alarmPage.setRowCount(domainAlarmPage.getRowCount());
        alarmPage.setPageNum(domainAlarmPage.getPageNum());
        alarmPage.setPageSize(domainAlarmPage.getPageSize());
        alarmPage.setTotalPage(domainAlarmPage.getTotalPage());
        alarmPage.setList(alarmDataMapper.transform(domainAlarmPage.getList()));
        return alarmPage;
    }

    public AdministratorPage transform(DomainAdministratorPage domainAdministratorPage) {
        if (null == domainAdministratorPage) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        AdministratorDataMapper administratorDataMapper = new AdministratorDataMapper();

        AdministratorPage administratorPage = new AdministratorPage();
        administratorPage.setRowCount(domainAdministratorPage.getRowCount());
        administratorPage.setPageNum(domainAdministratorPage.getPageNum());
        administratorPage.setPageSize(domainAdministratorPage.getPageSize());
        administratorPage.setTotalPage(domainAdministratorPage.getTotalPage());
        administratorPage.setList(administratorDataMapper.transform(domainAdministratorPage.getList()));

        return administratorPage;
    }

    public RolePage transform(DomainRolePage domainRolePage) {
        if (null == domainRolePage) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }

        RolePage rolePage = new RolePage();
        rolePage.setRowCount(domainRolePage.getRowCount());
        rolePage.setPageNum(domainRolePage.getPageNum());
        rolePage.setPageSize(domainRolePage.getPageSize());
        rolePage.setTotalPage(domainRolePage.getTotalPage());
        rolePage.setList(RoleDataMapper.transform(domainRolePage.getList()));

        return rolePage;
    }
}
