package com.hy.data.repository;

import com.example.bean.DomainUser;
import com.example.repository.UserRepository;
import com.hy.data.entity.mapper.UserEntityDataMapper;
import com.hy.data.repository.datasource.UserDataStore;
import com.hy.data.repository.datasource.UserDataStoreFactory;

import java.util.List;

import rx.Observable;

/**
 * {@link UserRepository} for retrieving user data.
 */

public class UserDataRepository implements UserRepository {

  private final UserDataStoreFactory userDataStoreFactory;
  private  UserEntityDataMapper userEntityDataMapper;

  /**
   * Constructs a {@link UserRepository}.
   *
   * @param dataStoreFactory A factory to construct different data source implementations.
   * @param userEntityDataMapper {@link UserEntityDataMapper}.
   */

  public UserDataRepository(UserDataStoreFactory dataStoreFactory,
                            UserEntityDataMapper userEntityDataMapper) {
    this.userDataStoreFactory = dataStoreFactory;
    this.userEntityDataMapper = userEntityDataMapper;
  }

  public UserDataRepository(UserDataStoreFactory userDataStoreFactory) {
    this.userDataStoreFactory = userDataStoreFactory;
  }

  @Override
  public Observable<DomainUser> user(String loginAccount, String loginPwd) {
    final UserDataStore userDataStore = this.userDataStoreFactory.createCloudDataStore(loginAccount, loginPwd);
//    return userDataStore.userEntity(loginAccount,loginPwd).map(userEntity -> this.userEntityDataMapper.transform(userEntity));
    return userDataStore.userEntity(loginAccount, loginPwd).map(this.userEntityDataMapper::transform);
  }

  @Override
  public Observable<List<String>> equipmentList() {
     UserDataStore userDataStore = this.userDataStoreFactory.createLocalDataStore();
    return userDataStore.equipmentList();
  }

  @Override
  public void upDataUser(int choiceType) {
    final UserDataStore userDataStore = this.userDataStoreFactory.createLocalDataStore();
    userDataStore.upDataUser(choiceType);
  }

}
