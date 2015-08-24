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
package com.hy.data.entity.mapper;


import com.example.bean.DomainUser;
import com.hy.data.entity.UserEntity;

/**
 * Mapper class used to transform {@link UserEntity} (in the data layer) to {@link DomainUser} in the
 * domain layer.
 */

public class UserEntityDataMapper {

  public UserEntityDataMapper() {}

  /**
   * Transform a {@link UserEntity} into an {@link DomainUser}.
   *
   * @param userEntity Object to be transformed.
   * @return {@link DomainUser} if valid {@link UserEntity} otherwise null.
   */
  public DomainUser transform(UserEntity userEntity) {
    DomainUser domainUser = null;

    if (userEntity != null) {
      domainUser = new DomainUser();
      domainUser.setCompanyName(userEntity.getCompanyName());
      domainUser.setUserId(userEntity.getUserId());
      domainUser.setOwnedEquipment(userEntity.getOwnedEquipment());
      domainUser.setSelectionType(userEntity.getSelectionType());
    }

    return domainUser;
  }

}
