package com.hy.data.entity.mapper;

import com.example.bean.DoaminEquipmentPage;
import com.example.bean.DomainAlarmPage;
import com.hy.data.entity.AlarmPageEntity;
import com.hy.data.entity.EquipmentPageEntity;

/**
 * Created by 24363 on 2015/8/21.
 */
public class PageEntityDataMapper {
    public PageEntityDataMapper() {
    }

    public DoaminEquipmentPage transform(EquipmentPageEntity equipmentPageEntity) {
        EquipmentAlarmEntityDataMapper equipmentAlarmEntityDataMapper = new EquipmentAlarmEntityDataMapper();
        DoaminEquipmentPage doaminEquipmentPage = null;
        if (null != equipmentPageEntity) {
            doaminEquipmentPage = new DoaminEquipmentPage();
            doaminEquipmentPage.setTotalPage(equipmentPageEntity.getTotalPage());
            doaminEquipmentPage.setPageSize(equipmentPageEntity.getPageSize());
            doaminEquipmentPage.setPageNum(equipmentPageEntity.getPageNum());
            doaminEquipmentPage.setList(equipmentAlarmEntityDataMapper.transform(equipmentPageEntity.getList()));
            doaminEquipmentPage.setRowCount(equipmentPageEntity.getRowCount());
        }
        return doaminEquipmentPage;
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
}
