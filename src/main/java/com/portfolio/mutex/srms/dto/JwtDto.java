package com.portfolio.mutex.srms.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtDto {

  private String token;

  private long expiration;
}
