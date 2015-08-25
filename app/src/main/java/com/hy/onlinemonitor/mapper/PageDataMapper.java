package com.hy.onlinemonitor.mapper;

import com.example.bean.DoaminEquipmentPage;
import com.example.bean.DomainAlarmPage;
import com.hy.onlinemonitor.bean.AlarmPage;
import com.hy.onlinemonitor.bean.EquipmentPage;

/**
 * Created by 24363 on 2015/8/21.
 */
public class PageDataMapper {

    public PageDataMapper() {
    }

    public EquipmentPage transform(DoaminEquipmentPage doaminEquipmentPage) {
        if (null == doaminEquipmentPage) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        EquipmentDataMapper equipmentDataMapper = new EquipmentDataMapper();

        EquipmentPage equipmentPage = new EquipmentPage();
        equipmentPage.setRowCount(doaminEquipmentPage.getRowCount());
        equipmentPage.setPageNum(doaminEquipmentPage.getPageNum());
        equipmentPage.setPageSize(doaminEquipmentPage.getPageSize());
        equipmentPage.setTotalPage(doaminEquipmentPage.getTotalPage());
        equipmentPage.setList(equipmentDataMapper.transform(doaminEquipmentPage.getList()));
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

}
