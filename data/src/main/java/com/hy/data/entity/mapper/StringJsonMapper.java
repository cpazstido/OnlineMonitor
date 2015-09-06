package com.hy.data.entity.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

/**
 * Created by 24363 on 2015/9/5.
 */
public class StringJsonMapper {
    private final Gson gson;
    public StringJsonMapper() {
        this.gson = new Gson();
    }

    public String transformString(String JsonResponse) throws JsonSyntaxException {
        return this.gson.fromJson(JsonResponse, new TypeToken<String>() {}.getType());
    }
}
