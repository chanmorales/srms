package com.portfolio.mutex.srms.entity;

import static com.portfolio.mutex.srms.common.DbConstants.COLUMN_CREATED_AT;
import static com.portfolio.mutex.srms.common.DbConstants.COLUMN_UPDATED_AT;
import static com.portfolio.mutex.srms.common.DbConstants.TABLE_USERS;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * User Entity
 */
@Table(name = TABLE_USERS)
@Entity
@Getter
@Setter
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false)
  private Long id;

  @Column(nullable = false, unique = true, length = 50)
  private String username;

  @Column(nullable = false, unique = true, length = 100)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String lastname;

  @Column(nullable = false)
  private String firstname;

  private String middlename;

  private Boolean active;

  private Boolean admin;

  private Boolean systemAdmin;

  @CreationTimestamp
  @Column(updatable = false, name = COLUMN_CREATED_AT)
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = COLUMN_UPDATED_AT)
  private Date updatedAt;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.emptyList();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return this.active;
  }
}
