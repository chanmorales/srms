package com.portfolio.mutex.srms.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UriConstants {

  /*
   * Authentication Resource URIs
   */
  public static final String AUTHENTICATION_BASE_URI = "/authentication";
  public static final String LOGIN_URI = "/login";
  public static final String LOGOUT_URI = "/logout";

  /*
   * User Resource URIs
   */
  public static final String USERS_BASE_URI = "/users";
  public static final String CURRENT_USER_URI = "/current";
}
