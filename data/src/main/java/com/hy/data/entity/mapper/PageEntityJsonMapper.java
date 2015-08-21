package com.hy.data.entity.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hy.data.entity.AlarmPageEntity;
import com.hy.data.entity.EquipmentPageEntity;

import java.lang.reflect.Type;

/**
 * Created by 24363 on 2015/8/21.
 */
public class PageEntityJsonMapper {

    private final Gson gson;
    public PageEntityJsonMapper() {
        this.gson = new Gson();
    }

    public AlarmPageEntity transformAlarmPageEntity(String alarmPageJsonResponse) throws JsonSyntaxException {
        Type AlarmPageEntityType = new TypeToken<AlarmPageEntity>() {}.getType();
        return this.gson.fromJson(alarmPageJsonResponse, AlarmPageEntityType);
    }

    public EquipmentPageEntity transformEquipmentPageEntity(String equipmentPageJsonResponse) throws JsonSyntaxException {
        Type EquipmentPageEntityType = new TypeToken<EquipmentPageEntity>() {}.getType();
        return this.gson.fromJson(equipmentPageJsonResponse, EquipmentPageEntityType);
    }

}
