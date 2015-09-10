package com.hy.data.entity.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hy.data.entity.AdministratorPageEntity;
import com.hy.data.entity.AlarmPageEntity;
import com.hy.data.entity.EquipmentInforPageEntity;
import com.hy.data.entity.LinePageEntity;
import com.hy.data.entity.PolePageEntity;
import com.hy.data.entity.RolePageEntity;

import java.lang.reflect.Type;

public class PageEntityJsonMapper {

    private final Gson gson;
    public PageEntityJsonMapper() {
        this.gson = new Gson();
    }

    public AlarmPageEntity transformAlarmPageEntity(String alarmPageJsonResponse) throws JsonSyntaxException {
        Type AlarmPageEntityType = new TypeToken<AlarmPageEntity>() {}.getType();
        return this.gson.fromJson(alarmPageJsonResponse, AlarmPageEntityType);

    }

    public EquipmentInforPageEntity transformEquipmentPageEntity(String equipmentPageJsonResponse) throws JsonSyntaxException {
        Type EquipmentPageEntityType = new TypeToken<EquipmentInforPageEntity>() {}.getType();

        return this.gson.fromJson(equipmentPageJsonResponse, EquipmentPageEntityType);
    }

    public AdministratorPageEntity transformAdministratorPageEntity(String administratorPageJsonResponse) throws JsonSyntaxException {
        Type AdministratorPageEntityType = new TypeToken<AdministratorPageEntity>() {}.getType();

        return this.gson.fromJson(administratorPageJsonResponse, AdministratorPageEntityType);
    }

    public RolePageEntity transformRolePageEntity(String JsonResponse) throws JsonSyntaxException {
        Type RolePageEntityType = new TypeToken<RolePageEntity>() {}.getType();

        return this.gson.fromJson(JsonResponse, RolePageEntityType);
    }

    public LinePageEntity transformLinePageEntity(String JsonResponse) throws JsonSyntaxException {
        Type LinePageEntityType = new TypeToken<LinePageEntity>() {}.getType();

        return this.gson.fromJson(JsonResponse, LinePageEntityType);
    }

    public PolePageEntity transformPolePageEntity(String JsonResponse) throws JsonSyntaxException {
        Type PolePageEntityType = new TypeToken<PolePageEntity>() {}.getType();

        return this.gson.fromJson(JsonResponse, PolePageEntityType);
    }
}
