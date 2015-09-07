package com.hy.data.entity.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hy.data.entity.LineEntity;

import java.util.List;

/**
 * Created by 24363 on 2015/9/2.
 */
public class LineJsonMapper {
    private final Gson gson;
    public LineJsonMapper() {
        this.gson = new Gson();
    }

    public List<LineEntity> transformAdminLineEntity(String AdminLineEntityJsonResponse) throws JsonSyntaxException {
        return this.gson.fromJson(AdminLineEntityJsonResponse, new TypeToken<List<LineEntity>>() {}.getType());
    }
}
