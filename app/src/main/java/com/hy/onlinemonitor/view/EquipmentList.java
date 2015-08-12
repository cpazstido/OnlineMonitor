package com.hy.onlinemonitor.view;


import com.hy.onlinemonitor.bean.EquipmentAlarmInformation;

import java.util.Collection;

public interface EquipmentList extends LoadDataView {

    void renderEquipmentList(Collection<EquipmentAlarmInformation> EquipmentInformationCollection);

}
