package com.hy.onlinemonitor.view;

import com.hy.onlinemonitor.bean.Map;

import java.util.Collection;

/**
 * Created by 24363 on 2015/8/19.
 */
public interface MapListView extends LoadDataView{

    void renderMapList(Collection<Map> maps);

}
