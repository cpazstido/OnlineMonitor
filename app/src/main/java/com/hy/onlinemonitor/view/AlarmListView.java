package com.hy.onlinemonitor.view;

import com.hy.onlinemonitor.bean.AlarmInformation;
import com.hy.onlinemonitor.bean.AlarmPage;

/**
 * Created by 24363 on 2015/8/17.
 */
public interface AlarmListView extends LoadDataView{

    void renderAlarmList(AlarmPage alarmPage);

    /**
     * View a {@link AlarmInformation} profile/details.
     *
     * @param alarmInformation The user that will be shown.
     */
    void viewAlarm(AlarmInformation alarmInformation);

}
