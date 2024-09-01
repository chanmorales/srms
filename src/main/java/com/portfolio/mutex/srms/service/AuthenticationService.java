package com.portfolio.mutex.srms.service;

import com.portfolio.mutex.srms.dto.JwtDto;
import com.portfolio.mutex.srms.dto.UserLoginDto;

public interface AuthenticationService {

  /**
   * Authenticates user login
   *
   * @param userLoginDetails the user login details
   * @return the JWT if the user login is valid, otherwise, throws an exception
   */
  JwtDto authenticateUserLogin(UserLoginDto userLoginDetails);
}
