package com.portfolio.mutex.srms.config;

import static com.portfolio.mutex.srms.common.UriConstants.AUTHENTICATION_BASE_URI;
import static com.portfolio.mutex.srms.common.UriConstants.LOGOUT_URI;

import com.portfolio.mutex.srms.filter.JwtAuthenticationFilter;
import com.portfolio.mutex.srms.handler.CustomLogoutSuccessHandler;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final AuthenticationProvider authenticationProvider;
  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  /**
   * Whitelisted resource endpoints that doesn't need the user to be authenticated
   */
  private static final String[] WHITELISTED_ENDPOINTS = {
      "/authentication/**", "/swagger-ui/**", "/v3/api-docs",
  };

  /**
   * Provides an additional security filter chain that filter requests
   *
   * @param http the current http security configuration
   * @return the http security configuration with additional filters
   * @throws Exception the exception that occurs
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(CsrfConfigurer::disable)
        .logout(
            logout -> logout.logoutUrl(AUTHENTICATION_BASE_URI + LOGOUT_URI)
                .logoutSuccessHandler(new CustomLogoutSuccessHandler()))
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .authorizeHttpRequests(
            httpRequests -> httpRequests.requestMatchers(WHITELISTED_ENDPOINTS).permitAll()
                .anyRequest().authenticated())
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  /**
   * Provides a custom configuration of {@link CorsConfigurationSource}
   *
   * @return a custom configuration of {@link CorsConfigurationSource}
   */
  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:8080"));
    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
    configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    return source;
  }
}
