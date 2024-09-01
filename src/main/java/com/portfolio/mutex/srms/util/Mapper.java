package com.portfolio.mutex.srms.util;

import com.portfolio.mutex.srms.dto.UserDto;
import com.portfolio.mutex.srms.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Mapper {

  /**
   * Maps a user entity to dto
   *
   * @param user the user entity
   * @return the user dto
   */
  public static UserDto map(User user) {
    return UserDto.builder()
        .username(user.getUsername())
        .email(user.getEmail())
        .displayName(AppHelper.getDisplayName(user))
        .active(user.getActive())
        .admin(user.getAdmin())
        .systemAdmin(user.getSystemAdmin())
        .build();
  }
}
