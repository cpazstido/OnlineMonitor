package com.hy.onlinemonitor.mapper;

import java.util.List;

public class ConditionMonitoringDataMapper {

    private static final String TAG = "aaaa";

    public static List transform(List list, int transformType) {
        if (list != null) {
            switch (transformType){
                case 1:
                    return AeolianVibrationDataMapper.transform(list);
                case 2:
                    return IceCoatingDataMapper.transform(list);
                case 3:
                    return ConductorSagDataMapper.transform(list);
                case 4:
                    return ConductorSwingWithWindDataMapper.transform(list);
                case 5:
                    return PoleStatusDataMapper.transform(list);
                case 6:
                    return MicroclimateDataMapper.transform(list);
            }
        }
        return null;
    }
}
