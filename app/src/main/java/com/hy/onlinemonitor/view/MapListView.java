package com.hy.onlinemonitor.view;

import com.hy.onlinemonitor.bean.Map;

import java.util.Collection;

public interface MapListView extends LoadDataView{

    void renderMapList(Collection<Map> maps);

}
