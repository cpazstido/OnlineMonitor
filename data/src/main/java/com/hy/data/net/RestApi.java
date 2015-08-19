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
package com.hy.data.net;


import com.hy.data.entity.AlarmEntity;
import com.hy.data.entity.EquipmentEntity;
import com.hy.data.entity.MapEntity;
import com.hy.data.entity.UserEntity;

import java.util.List;

import rx.Observable;

/**
 * RestApi for retrieving data from the network.
 */
public interface RestApi {
  static final String API_BASE_URL = "http://www.android10.org/myapi/";

  /**
   * 取得用户对象
   */

  Observable<UserEntity> userEntity(String loginAccount, String loginPwd);

  /**
   * 取得设备列表
   */
  Observable<List<EquipmentEntity>> equipmentEntity(String userName, int choiceType);

  /**
   * 取得报警列表
   */
  Observable<List<AlarmEntity>> alarmEntity(String userName,String title);

  /**
   * 取得地图列表
   */
  Observable<List<MapEntity>> mapEntity(String userName,int choiceType);

}
