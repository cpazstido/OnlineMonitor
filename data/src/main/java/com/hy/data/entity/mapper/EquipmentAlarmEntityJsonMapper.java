package com.hy.data.entity.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hy.data.entity.EquipmentAlarmEntity;

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

    public List<EquipmentAlarmEntity> transformEquipmentAlarmEntity(String equipmentAlarmJsonResponse) throws JsonSyntaxException {
        try {
            Type equipmentAlarmEntityType = new TypeToken<List<EquipmentAlarmEntity>>() {}.getType();
            List<EquipmentAlarmEntity> equipmentAlarmEntitys = this.gson.fromJson(equipmentAlarmJsonResponse, equipmentAlarmEntityType);

            return equipmentAlarmEntitys;
        } catch (JsonSyntaxException jsonException) {
            throw jsonException;
        }
    }
}
