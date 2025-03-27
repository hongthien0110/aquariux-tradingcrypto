package com.aquariux.tradingcrypto.service.entity;

import com.aquariux.tradingcrypto.utils.enums.CryptoCurrency;
import com.aquariux.tradingcrypto.utils.enums.Symbol;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class PriceAggregation {

  private Symbol symbol;
  private CryptoCurrency cryptoCurrencyName;
  private BigDecimal bidPrice;
  private BigDecimal bidQty;
  private BigDecimal askPrice;
  private BigDecimal askQty;
}
