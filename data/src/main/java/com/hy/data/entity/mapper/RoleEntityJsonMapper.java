package com.hy.data.entity.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hy.data.entity.RoleEntity;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by 24363 on 2015/8/31.
 */
public class RoleEntityJsonMapper {
    private final Gson gson;
    public RoleEntityJsonMapper() {
        this.gson = new Gson();
    }

    public List<RoleEntity> transformRoleEntity(String roleEntityJsonResponse) throws JsonSyntaxException {
        Type RoleEntityType = new TypeToken<List<RoleEntity>>() {}.getType();

        return this.gson.fromJson(roleEntityJsonResponse, RoleEntityType);
    }
}
