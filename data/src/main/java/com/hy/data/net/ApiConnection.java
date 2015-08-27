package com.hy.data.net;

import java.net.URL;

/**
 * Api Connection class used to retrieve data from the cloud.
 * Implements {@link java.util.concurrent.Callable} so when executed asynchronously can
 * return a value.
 */
public class ApiConnection{

  private URL url;
  private String response;

}
