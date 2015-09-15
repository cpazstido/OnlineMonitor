package com.hy.data.entity.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hy.data.entity.SensorTypeEntity;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by 24363 on 2015/9/14.
 */
public class SensorTypeEntityJsonMapper {
    private final Gson gson;
    public SensorTypeEntityJsonMapper() {
        this.gson = new Gson();
    }

    public List<SensorTypeEntity> transformSensorTypeEntity(String JsonResponse) throws JsonSyntaxException {
        Type SensorEntityType = new TypeToken<List<SensorTypeEntity>>() {}.getType();

        return this.gson.fromJson(JsonResponse, SensorEntityType);
    }
}
