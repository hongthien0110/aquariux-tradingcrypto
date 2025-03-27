package com.aquariux.tradingcrypto.service.entity;

import com.aquariux.tradingcrypto.utils.enums.Currency;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Wallet {

  private String userId;
  private BigDecimal balance;
  private Currency currency;
}
