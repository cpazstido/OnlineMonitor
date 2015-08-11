/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.hy.onlinemonitor.view;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of {@link com.hy.test.onlinesystem.Bean.User}.
 */
public interface UserListView extends LoadDataView {
  /**
   * Render a user list in the UI.
   *
   * @param userCollection The collection of {@link com.hy.test.onlinesystem.Bean.User} that will be shown.
   */
  //void renderUserList(Collection<User> userCollection);

}
