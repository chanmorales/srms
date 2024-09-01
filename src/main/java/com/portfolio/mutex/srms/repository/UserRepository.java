package com.portfolio.mutex.srms.repository;

import com.portfolio.mutex.srms.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  /**
   * Finds user by username
   *
   * @param username the username
   * @return the {@link Optional} {@link User}
   */
  Optional<User> findByUsername(String username);

  /**
   * Finds user by username or email
   *
   * @param username the username
   * @param email    the email
   * @return the {@link Optional} {@link User}
   */
  Optional<User> findByUsernameOrEmail(String username, String email);
}
