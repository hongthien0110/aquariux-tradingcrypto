package com.aquariux.tradingcrypto.entity;

import com.aquariux.tradingcrypto.utils.enums.Currency;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "wallet")
@IdClass(WalletEntity.WalletEntityKey.class)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WalletEntity {

  @Id
  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity userEntity;

  @Id
  @Enumerated(EnumType.STRING)
  private Currency currency;

  private BigDecimal balance;

  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @Getter
  public static class WalletEntityKey implements Serializable {

    private String userEntity;
    private Currency currency;
  }
}
