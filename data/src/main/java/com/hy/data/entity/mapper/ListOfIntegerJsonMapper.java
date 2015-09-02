package com.hy.data.entity.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by 24363 on 2015/9/2.
 */
public class ListOfIntegerJsonMapper {
    private final Gson gson;
    public ListOfIntegerJsonMapper() {
        this.gson = new Gson();
    }

    public List<Integer> transformIntegerEntity(String JsonResponse) throws JsonSyntaxException {
        return this.gson.fromJson(JsonResponse, new TypeToken<List<Integer>>() {}.getType());
    }
}
