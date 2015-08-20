package com.hy.data.repository;

import android.content.Context;

import com.example.bean.DomainMap;
import com.example.repository.MapRepository;
import com.hy.data.entity.mapper.MapEntityDataMapper;
import com.hy.data.entity.mapper.MapEntityJsonMapper;
import com.hy.data.net.RestApiImpl;

import java.util.List;

import rx.Observable;

/**
 * Created by 24363 on 2015/8/19.
 */
public class MapDataRepository implements MapRepository {

    private Context mContext;
    private String userName;
    private int selectedType;
    private MapEntityDataMapper mapEntityDataMapper;

    public MapDataRepository(Context mContext, String userName, int selectedType) {
        this.mContext = mContext;
        this.userName = userName;
        this.selectedType = selectedType;
        this.mapEntityDataMapper = new MapEntityDataMapper();
    }

    @Override
    public Observable<List<DomainMap>> mapList() {
        RestApiImpl restApi = new RestApiImpl(mContext,new MapEntityJsonMapper());
        return restApi.mapEntity(userName, selectedType).map(this.mapEntityDataMapper::transform);

    }

}
