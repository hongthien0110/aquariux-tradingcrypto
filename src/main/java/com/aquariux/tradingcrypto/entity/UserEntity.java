package com.aquariux.tradingcrypto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserEntity {

  @Id
  private String id;

  @Column(name = "USER_NAME", length = 99, nullable = false, unique = true)
  private String userName;

  @Column(name = "EMAIL", length = 99, nullable = false, unique = true)
  private String email;
}
