package com.portfolio.mutex.srms.service;

import com.portfolio.mutex.srms.dto.JwtDto;
import com.portfolio.mutex.srms.dto.UserLoginDto;
import com.portfolio.mutex.srms.entity.User;
import com.portfolio.mutex.srms.exception.AppServiceException;
import com.portfolio.mutex.srms.repository.UserRepository;
import com.portfolio.mutex.srms.util.LocaleHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;
  private final JwtService jwtService;
  private final LocaleHelper localeHelper;

  /**
   * {@inheritDoc}
   */
  @Override
  public JwtDto authenticateUserLogin(UserLoginDto userLoginDetails) {
    // Authenticate user
    User authenticatedUser = authenticate(userLoginDetails);

    return JwtDto.builder()
        .token(jwtService.generateToken(authenticatedUser))
        .expiration(jwtService.getExpirationTime())
        .build();
  }

  /**
   * Authenticates the user login details
   *
   * @param userLoginDetails the user login details to be authenticated
   * @return {@link User} if the login is valid
   */
  private User authenticate(UserLoginDto userLoginDetails) {
    // Retrieve user then authenticate
    User user = userRepository.findByUsernameOrEmail(userLoginDetails.getUsername(),
            userLoginDetails.getUsername())
        .orElseThrow(() -> new AppServiceException(localeHelper.toLocale("invalid_credentials"),
            HttpStatus.UNAUTHORIZED));
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(user.getUsername(),
            userLoginDetails.getPassword()));

    return user;
  }
}
