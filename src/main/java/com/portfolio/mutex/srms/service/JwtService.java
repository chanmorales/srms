package com.portfolio.mutex.srms.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

  /**
   * Extracts username from the given token
   *
   * @param token the JWT
   * @return the username
   */
  String extractUsername(String token);

  /**
   * Generates JWT for the given user
   *
   * @param userDetails the user details
   * @return generated JWT
   */
  String generateToken(UserDetails userDetails);

  /**
   * Retrieve the expiration / duration of the JWT
   *
   * @return expiration time / duration of the JWT
   */
  long getExpirationTime();

  /**
   * Checks if the provided JWT is valid for the given user
   *
   * @param token       the JWT
   * @param userDetails the user details
   * @return <b>true</b> if the token is valid, otherwise, <b>false</b>
   */
  boolean isTokenValid(String token, UserDetails userDetails);
}
