package com.hy.onlinemonitor.view;


import com.hy.onlinemonitor.bean.EquipmentInformation;

import java.util.Collection;

public interface EquipmentList extends LoadDataView {

    void renderEquipmentList(Collection<EquipmentInformation> EquipmentInformationCollection);

}
