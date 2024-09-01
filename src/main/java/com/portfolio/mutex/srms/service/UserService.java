package com.portfolio.mutex.srms.service;

import com.portfolio.mutex.srms.dto.UserDto;

public interface UserService {

  /**
   * Retrieves the currently logged-in user
   *
   * @param username the username of the currently logged-in user
   * @return the currently logged-ini user
   */
  UserDto getCurrentUser(String username);
}
