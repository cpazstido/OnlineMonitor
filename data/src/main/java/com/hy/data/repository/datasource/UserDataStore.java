package com.hy.data.repository.datasource;


import com.hy.data.entity.UserEntity;

import java.util.List;

import rx.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface UserDataStore {

  /**
   * Get an {@link Observable} which will emit a {@link UserEntity} by its id.
   *
   *
   * @param loginAccount 用户账号
   * @param loginPwd 用户密码
   */
  Observable<UserEntity> userEntity(final String loginAccount, final String loginPwd);


  Observable<List<String>> equipmentList();

//  List<String> equipmentList();

  void upDataUser(int choiceType);
}
