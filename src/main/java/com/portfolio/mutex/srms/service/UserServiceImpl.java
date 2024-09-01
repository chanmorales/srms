package com.portfolio.mutex.srms.service;

import com.portfolio.mutex.srms.dto.UserDto;
import com.portfolio.mutex.srms.entity.User;
import com.portfolio.mutex.srms.exception.AppServiceException;
import com.portfolio.mutex.srms.repository.UserRepository;
import com.portfolio.mutex.srms.util.LocaleHelper;
import com.portfolio.mutex.srms.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final LocaleHelper localeHelper;

  /**
   * {@inheritDoc}
   */
  @Override
  public UserDto getCurrentUser(String username) {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new AppServiceException(localeHelper.toLocale("not_found"),
            HttpStatus.NOT_FOUND));

    return Mapper.map(user);
  }
}
