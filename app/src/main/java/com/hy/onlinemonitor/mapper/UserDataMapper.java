/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hy.onlinemonitor.mapper;


import com.example.bean.DomainUser;
import com.hy.onlinemonitor.bean.User;

/**
 * Mapper class used to transform {@link User} (in the domain layer) to {@link UserModel} in the
 * presentation layer.
 */

public class UserDataMapper {

  public UserDataMapper() {}

  /**
   * Transform a {@link DomainUser} into an {@link User}.
   *
   * @param domainUser Object to be transformed.
   * @return {@link User}.
   */
  public User transform(DomainUser domainUser) {
    if (domainUser == null) {
      throw new IllegalArgumentException("Cannot transform a null value");
    }
    User user = new User();

    user.setSelectionType(domainUser.getSelectionType());
    user.setUserName(domainUser.getUserName());
    user.setOwnedEquipment(domainUser.getOwnedEquipment());

    return user;
  }

}
