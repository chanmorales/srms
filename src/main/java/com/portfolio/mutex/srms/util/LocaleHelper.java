package com.portfolio.mutex.srms.util;

import java.util.Locale;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LocaleHelper {

  private final MessageSource messageSource;

  /**
   * Get the message for the specified code
   *
   * @param code the code of the message to be retrieved
   * @return the localized message
   */
  public String toLocale(String code) {
    return toLocale(code, null);
  }

  /**
   * Get the message for the specified code with arguments
   *
   * @param code the code of the message to be retrieved
   * @param args the arguments
   * @return the localized message
   */
  public String toLocale(String code, Object[] args) {
    Locale locale = LocaleContextHolder.getLocale();
    return toLocale(code, args, locale);
  }

  /**
   * Get the message for the specified code with arguments for the specified locale
   *
   * @param code   the code of the message to be retrieved
   * @param args   the arguments
   * @param locale the current locale
   * @return the localized message
   */
  public String toLocale(String code, Object[] args, Locale locale) {
    try {
      return messageSource.getMessage(code, args, locale);
    } catch (Exception ex) {
      // If getting localized message fails, use the code instead
      log.warn("Failed to get message for code {} {}", code, ex.getMessage());
      return code;
    }
  }
}
