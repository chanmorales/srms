package com.portfolio.mutex.srms.util;

import com.portfolio.mutex.srms.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppHelper {

  /**
   * Gets the display name of the user which is in `&lt;Firstname&gt; &lt;Lastname&gt;` format
   *
   * @param user the user whose display name to retrieve
   * @return the user's display name
   */
  public static String getDisplayName(User user) {
    return user.getFirstname().trim() + " " + user.getLastname().trim();
  }

  /**
   * Checks whether the given text is not empty including if it is not all whitespaces
   *
   * @param text the text to be checked
   * @return <b>true</b> if the text is not empty nor all whitespaces, otherwise, <b>false</b>
   */
  public static boolean isNotEmpty(String text) {
    return !isEmpty(text);
  }

  /**
   * Checks whether the given text is empty including if it is all whitespaces
   *
   * @param text the text to be checked
   * @return <b>true</b> if the text is empty or all whitespaces, otherwise, <b>false</b>
   */
  public static boolean isEmpty(String text) {
    return text == null || text.trim().isEmpty();
  }
}
