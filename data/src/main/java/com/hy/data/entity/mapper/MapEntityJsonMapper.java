package com.hy.data.entity.mapper;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hy.data.entity.MapEntity;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by 24363 on 2015/8/19.
 */
public class MapEntityJsonMapper {

    private final Gson gson;
    public MapEntityJsonMapper() {
        this.gson = new Gson();
    }

    public List<MapEntity> transformMapEntity(String mapJsonResponse) throws JsonSyntaxException {
        Log.e("aaaaaa", mapJsonResponse);
        Type MapEntityType = new TypeToken<List<MapEntity>>() {}.getType();
        return this.gson.fromJson(mapJsonResponse, MapEntityType);
    }
}
