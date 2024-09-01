package com.portfolio.mutex.srms.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

  private String username;

  private String email;

  private String displayName;

  private boolean active;

  private boolean admin;

  private boolean systemAdmin;
}
