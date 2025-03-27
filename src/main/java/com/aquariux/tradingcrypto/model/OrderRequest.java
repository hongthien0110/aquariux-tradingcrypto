package com.aquariux.tradingcrypto.model;

import com.aquariux.tradingcrypto.utils.enums.Symbol;
import com.aquariux.tradingcrypto.utils.enums.TradingType;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderRequest {

  private Symbol symbol;
  private TradingType tradingType;
  private BigDecimal price;
  private BigDecimal quantity;
}
