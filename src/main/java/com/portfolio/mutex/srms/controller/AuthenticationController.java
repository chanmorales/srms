package com.portfolio.mutex.srms.controller;

import static com.portfolio.mutex.srms.common.UriConstants.AUTHENTICATION_BASE_URI;
import static com.portfolio.mutex.srms.common.UriConstants.LOGIN_URI;

import com.portfolio.mutex.srms.dto.JwtDto;
import com.portfolio.mutex.srms.dto.UserLoginDto;
import com.portfolio.mutex.srms.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTHENTICATION_BASE_URI)
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  /**
   * Authenticates a user login
   *
   * @param userLoginDetails the details of the user logging in
   * @return an authenticated JWT of the user
   */
  @PostMapping(value = LOGIN_URI,
      produces = {MediaType.APPLICATION_JSON_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  @Hidden
  public ResponseEntity<JwtDto> authenticateLogin(@RequestBody UserLoginDto userLoginDetails) {
    return ResponseEntity.ok(authenticationService.authenticateUserLogin(userLoginDetails));
  }
}
