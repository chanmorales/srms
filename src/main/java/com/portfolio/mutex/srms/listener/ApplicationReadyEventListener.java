package com.portfolio.mutex.srms.listener;

import com.portfolio.mutex.srms.entity.User;
import com.portfolio.mutex.srms.repository.UserRepository;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Value("${app.default-credentials.username}")
  private String defaultUsername;

  @Value("${app.default-credentials.email}")
  private String defaultEmail;

  @Value("${app.default-credentials.password}")
  private String defaultPassword;

  /**
   * {@inheritDoc}
   */
  @Override
  public void onApplicationEvent(@Nonnull ApplicationReadyEvent event) {
    /*
     * Persist the default user on initial start up
     */
    if (userRepository.count() == 0) {
      User defaultUser = new User();
      defaultUser.setUsername(defaultUsername);
      defaultUser.setEmail(defaultEmail);
      defaultUser.setPassword(passwordEncoder.encode(defaultPassword));
      defaultUser.setFirstname("System");
      defaultUser.setLastname("Administrator");
      defaultUser.setActive(true);
      defaultUser.setAdmin(true);
      defaultUser.setSystemAdmin(true);

      userRepository.save(defaultUser);
      log.info("Default user successfully created.");
    }
  }
}
