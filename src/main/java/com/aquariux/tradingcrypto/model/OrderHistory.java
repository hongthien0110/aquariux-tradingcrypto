package com.aquariux.tradingcrypto.model;

import com.aquariux.tradingcrypto.utils.enums.Symbol;
import com.aquariux.tradingcrypto.utils.enums.TradingType;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderHistory {

  private String userId;
  private Symbol symbol;
  private TradingType tradingType;
  private LocalDateTime from;
  private LocalDateTime to;
  private int limit;
  private int offset;
}
