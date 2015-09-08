package com.hy.data.entity.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hy.data.entity.PrivilegeEntity;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by 24363 on 2015/9/8.
 */
public class PrivilegeEntityJsonMapper {
    private final Gson gson;
    public PrivilegeEntityJsonMapper() {
        this.gson = new Gson();
    }

    public List<PrivilegeEntity> transformPrivilegeEntity(String privilegeEntityJsonResponse) throws JsonSyntaxException {
        Type PrivilegeEntityType = new TypeToken<List<PrivilegeEntity>>() {}.getType();

        return this.gson.fromJson(privilegeEntityJsonResponse, PrivilegeEntityType);
    }
}
