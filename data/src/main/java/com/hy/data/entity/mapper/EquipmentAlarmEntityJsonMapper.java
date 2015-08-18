package com.hy.data.entity.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hy.data.entity.EquipmentEntity;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by 24363 on 2015/8/13.
 */
public class EquipmentAlarmEntityJsonMapper {
    private final Gson gson;

    public EquipmentAlarmEntityJsonMapper() {
        this.gson = new Gson();
    }

    public List<EquipmentEntity> transformEquipmentAlarmEntity(String equipmentJsonResponse) throws JsonSyntaxException {
        Type equipmentEntityType = new TypeToken<List<EquipmentEntity>>() {}.getType();
        return this.gson.fromJson(equipmentJsonResponse, equipmentEntityType);
    }
}
