package com.hy.data.repository;

import com.example.bean.DomainPrivilege;
import com.example.bean.DomainUser;
import com.example.repository.UserRepository;
import com.hy.data.entity.mapper.PrivilegeEntityDataMapper;
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
  private String curProject;
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

  public UserDataRepository(UserDataStoreFactory userDataStoreFactory,String curProject) {
    this.userDataStoreFactory = userDataStoreFactory;
    this.curProject = curProject;
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
  public Observable<String> upDataUser(int choiceType) {
    final UserDataStore userDataStore = this.userDataStoreFactory.createLocalDataStore();
    return userDataStore.upDataUser(choiceType);
  }

  @Override
  public Observable<DomainUser> getUserInfor() {
    final UserDataStore userDataStore = this.userDataStoreFactory.createLocalDataStore();//这里只是为了创建不同的CloudDataStore
    return userDataStore.getUserInfor().map(this.userEntityDataMapper::transform);
  }

  @Override
  public Observable<String> setCurrentPorject() {
    final UserDataStore userDataStore = this.userDataStoreFactory.createCloudDataStore(curProject);//这里只是为了创建不同的CloudDataStore
    return userDataStore.setCurrentPorject(curProject);
  }

  @Override
  public Observable<List<DomainPrivilege>> getJurisdiction(int userId, int roleSn) {
    final UserDataStore userDataStore = this.userDataStoreFactory.createCloudDataStore(userId,roleSn);//这里只是为了创建不同的CloudDataStore
    PrivilegeEntityDataMapper privilegeEntityDataMapper = new PrivilegeEntityDataMapper();
    return userDataStore.getJurisdiction(userId,roleSn).map(privilegeEntityDataMapper::transform);
  }
}
