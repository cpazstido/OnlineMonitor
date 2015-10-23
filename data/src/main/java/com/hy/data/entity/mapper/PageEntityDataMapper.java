package com.hy.data.entity.mapper;

import com.example.bean.DomainEquipmentInforPage;
import com.example.bean.DomainAdministratorPage;
import com.example.bean.DomainAlarmPage;
import com.example.bean.DomainEquipmentPage;
import com.example.bean.DomainLinePage;
import com.example.bean.DomainOnlineDeviceStatePage;
import com.example.bean.DomainPolePage;
import com.example.bean.DomainRolePage;
import com.hy.data.entity.AdministratorPageEntity;
import com.hy.data.entity.AlarmPageEntity;
import com.hy.data.entity.EquipmentInforPageEntity;
import com.hy.data.entity.EquipmentPageEntity;
import com.hy.data.entity.LinePageEntity;
import com.hy.data.entity.OnlineDeviceStatePageEntity;
import com.hy.data.entity.PolePageEntity;
import com.hy.data.entity.RolePageEntity;

/**
 * Created by 24363 on 2015/8/21.
 */
public class PageEntityDataMapper {
    public PageEntityDataMapper() {
    }

    public DomainEquipmentInforPage transform(EquipmentInforPageEntity equipmentInforPageEntity) {
        EquipmentAlarmEntityDataMapper equipmentAlarmEntityDataMapper = new EquipmentAlarmEntityDataMapper();
        DomainEquipmentInforPage domainEquipmentInforPage = null;
        if (null != equipmentInforPageEntity) {
            domainEquipmentInforPage = new DomainEquipmentInforPage();
            domainEquipmentInforPage.setTotalPage(equipmentInforPageEntity.getTotalPage());
            domainEquipmentInforPage.setPageSize(equipmentInforPageEntity.getPageSize());
            domainEquipmentInforPage.setPageNum(equipmentInforPageEntity.getPageNum());
            domainEquipmentInforPage.setList(equipmentAlarmEntityDataMapper.transform(equipmentInforPageEntity.getList()));
            domainEquipmentInforPage.setRowCount(equipmentInforPageEntity.getRowCount());
        }
        return domainEquipmentInforPage;
    }

    public DomainAlarmPage transform(AlarmPageEntity alarmPageEntity) {
        DomainAlarmPage domainAlarmPage = null;
        AlarmEntityDataMapper alarmEntityDataMapper = new AlarmEntityDataMapper();
        if (null != alarmPageEntity) {
            domainAlarmPage = new DomainAlarmPage();
            domainAlarmPage.setTotalPage(alarmPageEntity.getTotalPage());
            domainAlarmPage.setPageSize(alarmPageEntity.getPageSize());
            domainAlarmPage.setPageNum(alarmPageEntity.getPageNum());
            domainAlarmPage.setList(alarmEntityDataMapper.transform(alarmPageEntity.getList()));
            domainAlarmPage.setRowCount(alarmPageEntity.getRowCount());
        }
        return domainAlarmPage;
    }

    public DomainAdministratorPage transform(AdministratorPageEntity administratorPageEntity) {
        DomainAdministratorPage domainAdministratorPage = null;
        AdministratorEntityDataMapper administratorEntityDataMapper = new AdministratorEntityDataMapper();
        if (null != administratorPageEntity) {
            domainAdministratorPage = new DomainAdministratorPage();
            domainAdministratorPage.setTotalPage(administratorPageEntity.getTotalPage());
            domainAdministratorPage.setPageSize(administratorPageEntity.getPageSize());
            domainAdministratorPage.setPageNum(administratorPageEntity.getPageNum());
            domainAdministratorPage.setList(administratorEntityDataMapper.transform(administratorPageEntity.getList()));
            domainAdministratorPage.setRowCount(administratorPageEntity.getRowCount());
        }
        return domainAdministratorPage;
    }

    public DomainRolePage transform(RolePageEntity rolePageEntity) {
        DomainRolePage domainRolePage = null;
        RoleEntityDataMapper roleEntityDataMapper = new RoleEntityDataMapper();
        if (null != rolePageEntity) {
            domainRolePage = new DomainRolePage();
            domainRolePage.setTotalPage(rolePageEntity.getTotalPage());
            domainRolePage.setPageSize(rolePageEntity.getPageSize());
            domainRolePage.setPageNum(rolePageEntity.getPageNum());
            domainRolePage.setList(roleEntityDataMapper.transform(rolePageEntity.getList()));
            domainRolePage.setRowCount(rolePageEntity.getRowCount());
        }
        return domainRolePage;
    }

    public DomainLinePage transform(LinePageEntity linePageEntity) {
        DomainLinePage domainLinePage = null;
        LineEntityDataMapper lineEntityDataMapper = new LineEntityDataMapper();
        if (null != linePageEntity) {
            domainLinePage = new DomainLinePage();
            domainLinePage.setTotalPage(linePageEntity.getTotalPage());
            domainLinePage.setPageSize(linePageEntity.getPageSize());
            domainLinePage.setPageNum(linePageEntity.getPageNum());
            domainLinePage.setList(lineEntityDataMapper.transform(linePageEntity.getList()));
            domainLinePage.setRowCount(linePageEntity.getRowCount());
        }
        return domainLinePage;
    }

    public DomainPolePage transform(PolePageEntity polePageEntity) {
        DomainPolePage domainPolePage = null;
        if (null != polePageEntity) {
            domainPolePage = new DomainPolePage();
            domainPolePage.setTotalPage(polePageEntity.getTotalPage());
            domainPolePage.setPageSize(polePageEntity.getPageSize());
            domainPolePage.setPageNum(polePageEntity.getPageNum());
            domainPolePage.setList(PoleEntityDataMapper.transform(polePageEntity.getList()));
            domainPolePage.setRowCount(polePageEntity.getRowCount());
        }
        return domainPolePage;
    }

    public DomainEquipmentPage transform(EquipmentPageEntity equipmentPageEntity) {
        DomainEquipmentPage domainEquipmentPage = null;
        if (null != equipmentPageEntity) {
            domainEquipmentPage = new DomainEquipmentPage();
            domainEquipmentPage.setTotalPage(equipmentPageEntity.getTotalPage());
            domainEquipmentPage.setPageSize(equipmentPageEntity.getPageSize());
            domainEquipmentPage.setPageNum(equipmentPageEntity.getPageNum());
            domainEquipmentPage.setList(EquipmentEntityDataMapper.transform(equipmentPageEntity.getList()));
            domainEquipmentPage.setRowCount(equipmentPageEntity.getRowCount());
        }
        return domainEquipmentPage;
    }

    public DomainOnlineDeviceStatePage transform(OnlineDeviceStatePageEntity onlineDeviceStatePageEntity) {
        DomainOnlineDeviceStatePage domainOnlineDeviceStatePage = null;
        if (null != onlineDeviceStatePageEntity) {
            domainOnlineDeviceStatePage = new DomainOnlineDeviceStatePage();
            domainOnlineDeviceStatePage.setTotalPage(onlineDeviceStatePageEntity.getTotalPage());
            domainOnlineDeviceStatePage.setPageSize(onlineDeviceStatePageEntity.getPageSize());
            domainOnlineDeviceStatePage.setPageNum(onlineDeviceStatePageEntity.getPageNum());
            domainOnlineDeviceStatePage.setList(OnlineDeviceStateEntityDataMapper.transform(onlineDeviceStatePageEntity.getList()));
            domainOnlineDeviceStatePage.setRowCount(onlineDeviceStatePageEntity.getRowCount());
        }
        return domainOnlineDeviceStatePage;
    }
}
