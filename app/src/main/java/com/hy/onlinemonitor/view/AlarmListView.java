package com.hy.onlinemonitor.view;

import com.hy.onlinemonitor.bean.AlarmPage;

public interface AlarmListView extends LoadDataView{

    void renderAlarmList(AlarmPage alarmPage);

}
