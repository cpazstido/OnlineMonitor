package com.hy.onlinemonitor.view;

import com.hy.onlinemonitor.bean.AlarmInformation;

import java.util.Collection;

/**
 * Created by 24363 on 2015/8/17.
 */
public interface AlarmListView extends LoadDataView{

    void renderAlarmList(Collection<AlarmInformation> alarmInformationCollection);

    /**
     * View a {@link AlarmInformation} profile/details.
     *
     * @param alarmInformation The user that will be shown.
     */
    void viewAlarm(AlarmInformation alarmInformation);


}
