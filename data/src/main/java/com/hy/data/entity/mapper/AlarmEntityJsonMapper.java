package com.hy.data.entity.mapper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hy.data.entity.AlarmEntity;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by 24363 on 2015/8/18.
 */
public class AlarmEntityJsonMapper {
    private final Gson gson;

    public AlarmEntityJsonMapper() {
        this.gson = new Gson();
    }

    public List<AlarmEntity> transformEquipmentAlarmEntity(String responseAlarmEntities) {
        Type AlarmEntityType = new TypeToken<List<AlarmEntity>>() {}.getType();
        return this.gson.fromJson(responseAlarmEntities, AlarmEntityType);
    }
}
