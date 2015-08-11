/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.hy.onlinemonitor.view;

import android.content.Context;

/**
 * Interface representing a View that will use to load data.
 */
public interface LoadDataView {
  /**
   * 显示加载框
   */
  void showLoading();

  /**
   * 隐藏加载框
   */
  void hideLoading();

  /**
   * 显示错误信息
   *
   * @param message A string representing an error.
   */
  void showError(String message);

  /**
   * Get a {@link Context}.
   */
  Context getContext();
}
