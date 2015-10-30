package com.hy.data.entity.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hy.data.entity.AdministratorPageEntity;
import com.hy.data.entity.AeolianVibrationPageEntity;
import com.hy.data.entity.AlarmPageEntity;
import com.hy.data.entity.ConductorSagPageEntity;
import com.hy.data.entity.ConductorSwingWithWindPageEntity;
import com.hy.data.entity.EquipmentInforPageEntity;
import com.hy.data.entity.EquipmentPageEntity;
import com.hy.data.entity.IceCoatingPageEntity;
import com.hy.data.entity.LinePageEntity;
import com.hy.data.entity.MicroclimatePageEntity;
import com.hy.data.entity.OnlineDeviceStatePageEntity;
import com.hy.data.entity.PolePageEntity;
import com.hy.data.entity.PoleStatusPageEntity;
import com.hy.data.entity.RolePageEntity;

import java.lang.reflect.Type;

public class PageEntityJsonMapper {

    private final Gson gson;

    public PageEntityJsonMapper() {
        this.gson = new Gson();
    }

    public AlarmPageEntity transformAlarmPageEntity(String alarmPageJsonResponse) throws JsonSyntaxException {
        Type AlarmPageEntityType = new TypeToken<AlarmPageEntity>() {
        }.getType();
        return this.gson.fromJson(alarmPageJsonResponse, AlarmPageEntityType);

    }

    public EquipmentInforPageEntity transformEquipmentInforPageEntity(String equipmentPageJsonResponse) throws JsonSyntaxException {
        Type EquipmentPageEntityType = new TypeToken<EquipmentInforPageEntity>() {
        }.getType();

        return this.gson.fromJson(equipmentPageJsonResponse, EquipmentPageEntityType);
    }

    public AdministratorPageEntity transformAdministratorPageEntity(String administratorPageJsonResponse) throws JsonSyntaxException {
        Type AdministratorPageEntityType = new TypeToken<AdministratorPageEntity>() {
        }.getType();

        return this.gson.fromJson(administratorPageJsonResponse, AdministratorPageEntityType);
    }

    public RolePageEntity transformRolePageEntity(String JsonResponse) throws JsonSyntaxException {
        Type RolePageEntityType = new TypeToken<RolePageEntity>() {
        }.getType();

        return this.gson.fromJson(JsonResponse, RolePageEntityType);
    }

    public LinePageEntity transformLinePageEntity(String JsonResponse) throws JsonSyntaxException {
        Type LinePageEntityType = new TypeToken<LinePageEntity>() {
        }.getType();

        return this.gson.fromJson(JsonResponse, LinePageEntityType);
    }

    public PolePageEntity transformPolePageEntity(String JsonResponse) throws JsonSyntaxException {
        Type PolePageEntityType = new TypeToken<PolePageEntity>() {
        }.getType();

        return this.gson.fromJson(JsonResponse, PolePageEntityType);
    }

    public EquipmentPageEntity transformEquipmentPageEntity(String JsonResponse) throws JsonSyntaxException {
        Type EquipmentPageEntityType = new TypeToken<EquipmentPageEntity>() {
        }.getType();

        return this.gson.fromJson(JsonResponse, EquipmentPageEntityType);
    }

    public OnlineDeviceStatePageEntity transformOnlineDeviceStatePageEntity(String JsonResponse) throws JsonSyntaxException {
        Type OnlineDeviceStatePageEntityType = new TypeToken<OnlineDeviceStatePageEntity>() {
        }.getType();

        return this.gson.fromJson(JsonResponse, OnlineDeviceStatePageEntityType);
    }

    public AeolianVibrationPageEntity transformAeolianVibrationPageEntity(String JsonResponse) throws JsonSyntaxException {
        Type AeolianVibrationPageEntityType = new TypeToken<AeolianVibrationPageEntity>() {
        }.getType();

        return this.gson.fromJson(JsonResponse, AeolianVibrationPageEntityType);
    }

    public ConductorSagPageEntity transformConductorSagPageEntity(String JsonResponse) throws JsonSyntaxException {
        Type ConductorSagPageEntityType = new TypeToken<ConductorSagPageEntity>() {
        }.getType();

        return this.gson.fromJson(JsonResponse, ConductorSagPageEntityType);
    }

    public ConductorSwingWithWindPageEntity transformConductorSwingWithWindPageEntity(String JsonResponse) throws JsonSyntaxException {
        Type ConductorSwingWithWindPageEntityType = new TypeToken<ConductorSwingWithWindPageEntity>() {
        }.getType();

        return this.gson.fromJson(JsonResponse, ConductorSwingWithWindPageEntityType);
    }

    public IceCoatingPageEntity transformIceCoatingPageEntity(String JsonResponse) throws JsonSyntaxException {
        Type IceCoatingPageEntityType = new TypeToken<IceCoatingPageEntity>() {
        }.getType();

        return this.gson.fromJson(JsonResponse, IceCoatingPageEntityType);
    }

    public MicroclimatePageEntity transformMicroclimatePageEntity(String JsonResponse) throws JsonSyntaxException {
        Type MicroclimatePageEntityType = new TypeToken<MicroclimatePageEntity>() {
        }.getType();

        return this.gson.fromJson(JsonResponse, MicroclimatePageEntityType);
    }

    public PoleStatusPageEntity transformPoleStatusPageEntity(String JsonResponse) throws JsonSyntaxException {
        Type PoleStatusPageEntityType = new TypeToken<PoleStatusPageEntity>() {
        }.getType();

        return this.gson.fromJson(JsonResponse, PoleStatusPageEntityType);
    }
}
