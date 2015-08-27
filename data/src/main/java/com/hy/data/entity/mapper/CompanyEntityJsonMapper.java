package com.hy.data.entity.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hy.data.entity.CompanyEntity;

import java.util.List;

/**
 * Created by 24363 on 2015/8/31.
 */
public class CompanyEntityJsonMapper {
    private final Gson gson;
    public CompanyEntityJsonMapper() {
        this.gson = new Gson();
    }

    public List<CompanyEntity> transformCompanyEntity(String CompanyEntityJsonResponse) throws JsonSyntaxException {
        return this.gson.fromJson(CompanyEntityJsonResponse, new TypeToken<List<CompanyEntity>>() {}.getType());
    }
}
