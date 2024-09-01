package com.portfolio.mutex.srms.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DbConstants {

  /*
   * Table names
   */
  public static final String TABLE_USERS = "users";

  /*
   * Column names
   */
  public static final String COLUMN_CREATED_AT = "created_at";
  public static final String COLUMN_UPDATED_AT = "updated_at";
}
