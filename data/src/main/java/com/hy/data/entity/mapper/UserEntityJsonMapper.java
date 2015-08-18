package com.hy.data.entity.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hy.data.entity.UserEntity;

import java.lang.reflect.Type;

/**
 * Class used to transform from Strings representing json to valid objects.
 */
public class UserEntityJsonMapper {

  private final Gson gson;

  public UserEntityJsonMapper() {
    this.gson = new Gson();
  }

  /**
   * Transform from valid json string to {@link UserEntity}.
   *
   * @param userJsonResponse A json representing a user profile.
   * @return {@link UserEntity}.
   */

  public UserEntity transformUserEntity(String userJsonResponse) throws JsonSyntaxException {
    Type userEntityType = new TypeToken<UserEntity>() {}.getType();
    return this.gson.fromJson(userJsonResponse, userEntityType);
  }

}
