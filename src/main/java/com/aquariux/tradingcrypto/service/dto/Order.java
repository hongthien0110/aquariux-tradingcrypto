package com.aquariux.tradingcrypto.service.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Order {

  private String orderId;
  private BigDecimal balance;
  private BigDecimal newBalance;
  private String status;
  private long timestamp;
}
