package com.aquariux.tradingcrypto.model;

import com.aquariux.tradingcrypto.utils.enums.Symbol;
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
public class OrderHistoryResponse {

  private String orderId;
  private Symbol symbol;
  private String tradingType;
  private String quantity;
  private String price;
  private String timestamp;
  private String status;
}
