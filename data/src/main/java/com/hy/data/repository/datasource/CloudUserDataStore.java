package com.hy.data.repository.datasource;


import com.hy.data.cache.UserCache;
import com.hy.data.entity.UserEntity;
import com.hy.data.net.RestApi;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * {@link UserDataStore} implementation based on connections to the api (Cloud).
 */
public class CloudUserDataStore implements UserDataStore {

  private final RestApi restApi;
  private final UserCache userCache;

  private final Action1<UserEntity> saveToCacheAction =
      userEntity -> {
        if (userEntity != null) {
          CloudUserDataStore.this.userCache.put(userEntity);
        }
      };

  /**
   * Construct a {@link UserDataStore} based on connections to the api (Cloud).
   *
   * @param restApi The {@link RestApi} implementation to use.
   */
  public CloudUserDataStore(RestApi restApi,UserCache userCache) {
    this.restApi = restApi;
    this.userCache = userCache;
  }

  @Override
  public Observable<UserEntity> userEntity(final String loginAccount,final String loginPwd) {
    return this.restApi.userEntity(loginAccount,loginPwd)
            .doOnNext(saveToCacheAction);
  }


  @Override
  public Observable<List<String>> equipmentList() {
    return null;
  }

  @Override
  public Observable upDataUser(int choiceType) {
    return null;
  }

  @Override
  public Observable<UserEntity> getUserInfor() {
    return null;
  }

  @Override
  public Observable<String> setCurrentPorject(String curProject) {
    return this.restApi.setCurrentPorject(curProject);
  }

}
