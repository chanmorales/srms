package com.portfolio.mutex.srms.config;

import com.portfolio.mutex.srms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class JwtConfiguration {

  private final UserRepository userRepository;

  /**
   * Provides a custom implementation of {@link UserDetailsService#loadUserByUsername}
   *
   * @return custom implementation of {@link UserDetailsService#loadUserByUsername}
   */
  @Bean
  UserDetailsService userDetailsService() {
    return username -> userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found."));
  }

  /**
   * Provides an implementation of
   * {@link org.springframework.security.crypto.password.PasswordEncoder}
   *
   * @return an implementation of
   * {@link org.springframework.security.crypto.password.PasswordEncoder}
   */
  @Bean
  BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * Provides an implementation of {@link AuthenticationManager}
   *
   * @param configuration the authentication configuration
   * @return an implementation of {@link AuthenticationManager}
   * @throws Exception the exception that occurs
   */
  @Bean
  AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
      throws Exception {
    return configuration.getAuthenticationManager();
  }

  /**
   * Provides an implementation of {@link AuthenticationProvider}
   *
   * @return an implementation of {@link AuthenticationProvider}
   */
  @Bean
  AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService());
    authenticationProvider.setPasswordEncoder(passwordEncoder());

    return authenticationProvider;
  }
}
