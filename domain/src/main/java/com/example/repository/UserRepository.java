package com.example.repository;


import com.example.bean.DomainUser;

import java.util.List;

import rx.Observable;

/**
 * Interface that represents a Repository for getting {@link DomainUser} related data.
 */
public interface UserRepository {

  /**
   * Get an {@link Observable} which will emit a {@link DomainUser}.
   *
   */
  Observable<DomainUser> user(final String loginAccount, final String loginPwd);

  Observable<List<String>> equipmentList();

  Observable<String> upDataUser(int choiceType);

  Observable<DomainUser> getUserInfor();

  Observable<String> setCurrentPorject();

}
