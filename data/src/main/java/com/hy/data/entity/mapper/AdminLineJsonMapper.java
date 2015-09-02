package com.hy.data.entity.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hy.data.entity.AdminLineEntity;

import java.util.List;

/**
 * Created by 24363 on 2015/9/2.
 */
public class AdminLineJsonMapper {
    private final Gson gson;
    public AdminLineJsonMapper() {
        this.gson = new Gson();
    }

    public List<AdminLineEntity> transformAdminLineEntity(String AdminLineEntityJsonResponse) throws JsonSyntaxException {
        return this.gson.fromJson(AdminLineEntityJsonResponse, new TypeToken<List<AdminLineEntity>>() {}.getType());
    }
}
