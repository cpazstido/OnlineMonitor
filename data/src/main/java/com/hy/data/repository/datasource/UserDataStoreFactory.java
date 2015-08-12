package com.hy.data.repository.datasource;

import android.content.Context;

import com.hy.data.cache.UserCache;
import com.hy.data.cache.UserCacheImp;
import com.hy.data.entity.mapper.UserEntityJsonMapper;
import com.hy.data.net.LoginRestApi;
import com.hy.data.net.RestApi;


/**
 * Factory that creates different implementations of {@link UserDataStore}.
 */

public class UserDataStoreFactory {

  private final Context context;
  private final UserCache userCache;


  public UserDataStoreFactory(Context context) {
    if (context == null) {
      throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
    }
    this.userCache = new UserCacheImp(context);
    this.context = context;
  }


  /**
   * Create {@link UserDataStore} to retrieve data from the Cloud.
   */
  public UserDataStore createCloudDataStore(String loginAccount, String loginPwd) {
    UserEntityJsonMapper userEntityJsonMapper = new UserEntityJsonMapper();
    RestApi restApi = new LoginRestApi(this.context, userEntityJsonMapper);

    return new CloudUserDataStore(restApi,this.userCache);
  }

  public UserDataStore createLocalDataStore(){
    return new LocalUserDataStore(context,new UserCacheImp(context));
  }
}
