package com.hy.onlinemonitor.mapper;

import com.example.bean.DomainEquipmentInforPage;
import com.example.bean.DomainAdministratorPage;
import com.example.bean.DomainAlarmPage;
import com.example.bean.DomainEquipmentPage;
import com.example.bean.DomainLinePage;
import com.example.bean.DomainOnlineDeviceStatePage;
import com.example.bean.DomainPolePage;
import com.example.bean.DomainRolePage;
import com.hy.onlinemonitor.bean.AdministratorPage;
import com.hy.onlinemonitor.bean.AlarmPage;
import com.hy.onlinemonitor.bean.EquipmentInforPage;
import com.hy.onlinemonitor.bean.EquipmentPage;
import com.hy.onlinemonitor.bean.LinePage;
import com.hy.onlinemonitor.bean.OnlineDeviceStatePage;
import com.hy.onlinemonitor.bean.PolePage;
import com.hy.onlinemonitor.bean.RolePage;

/**
 * page数据转换,用于将domain对象转换成当前bean对象
 */
public class PageDataMapper {

    public PageDataMapper() {
    }

    public EquipmentInforPage transform(DomainEquipmentInforPage domainEquipmentInforPage) {
        if (null == domainEquipmentInforPage) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        EquipmentInforDataMapper equipmentInforDataMapper = new EquipmentInforDataMapper();

        EquipmentInforPage equipmentInforPage = new EquipmentInforPage();
        equipmentInforPage.setRowCount(domainEquipmentInforPage.getRowCount());
        equipmentInforPage.setPageNum(domainEquipmentInforPage.getPageNum());
        equipmentInforPage.setPageSize(domainEquipmentInforPage.getPageSize());
        equipmentInforPage.setTotalPage(domainEquipmentInforPage.getTotalPage());
        equipmentInforPage.setList(equipmentInforDataMapper.transform(domainEquipmentInforPage.getList()));
        return equipmentInforPage;
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

    public LinePage transform(DomainLinePage domainLine) {
        if (null == domainLine) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }

        LinePage linePage = new LinePage();
        linePage.setRowCount(domainLine.getRowCount());
        linePage.setPageNum(domainLine.getPageNum());
        linePage.setPageSize(domainLine.getPageSize());
        linePage.setTotalPage(domainLine.getTotalPage());
        linePage.setList(LineDataMapper.transform(domainLine.getList()));

        return linePage;
    }

    public PolePage transform(DomainPolePage domainPolePage) {
        if (null == domainPolePage) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }

        PolePage polePage = new PolePage();
        polePage.setRowCount(domainPolePage.getRowCount());
        polePage.setPageNum(domainPolePage.getPageNum());
        polePage.setPageSize(domainPolePage.getPageSize());
        polePage.setTotalPage(domainPolePage.getTotalPage());
        polePage.setList(PoleDataMapper.transform(domainPolePage.getList()));

        return polePage;
    }

    public EquipmentPage transform(DomainEquipmentPage domainEquipmentPage) {
        if (null == domainEquipmentPage) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }

        EquipmentPage equipmentPage = new EquipmentPage();
        equipmentPage.setRowCount(domainEquipmentPage.getRowCount());
        equipmentPage.setPageNum(domainEquipmentPage.getPageNum());
        equipmentPage.setPageSize(domainEquipmentPage.getPageSize());
        equipmentPage.setTotalPage(domainEquipmentPage.getTotalPage());
        equipmentPage.setList(EquipmentDataMapper.transform(domainEquipmentPage.getList()));

        return equipmentPage;
    }

    public OnlineDeviceStatePage transform(DomainOnlineDeviceStatePage domainOnlineDeviceStatePage) {
        if (null == domainOnlineDeviceStatePage) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }

        OnlineDeviceStatePage onlineDeviceStatePage = new OnlineDeviceStatePage();
        onlineDeviceStatePage.setRowCount(domainOnlineDeviceStatePage.getRowCount());
        onlineDeviceStatePage.setPageNum(domainOnlineDeviceStatePage.getPageNum());
        onlineDeviceStatePage.setPageSize(domainOnlineDeviceStatePage.getPageSize());
        onlineDeviceStatePage.setTotalPage(domainOnlineDeviceStatePage.getTotalPage());
        onlineDeviceStatePage.setList(OnlineDeviceStateDataMapper.transform(domainOnlineDeviceStatePage.getList()));

        return onlineDeviceStatePage;
    }
}
