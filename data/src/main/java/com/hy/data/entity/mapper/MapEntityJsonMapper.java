package com.hy.data.entity.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hy.data.entity.MapEntity;
import com.hy.data.entity.UserEntity;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by 24363 on 2015/8/21.
 */
public class MapEntityJsonMapper {

    private final Gson gson;

    public MapEntityJsonMapper() {
        this.gson = new Gson();
    }

    /**
     * Transform from valid json string to {@link UserEntity}.
     *
     * @param mapJsonResponse A json representing a user profile.
     * @return {@link UserEntity}.
     */

    public List<MapEntity> transformMapEntity(String mapJsonResponse) throws JsonSyntaxException {
        Type mapEntityType = new TypeToken<List<MapEntity>>() {}.getType();
        return this.gson.fromJson(mapJsonResponse, mapEntityType);
    }

}
