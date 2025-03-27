package com.aquariux.tradingcrypto.service.dto;

import com.aquariux.tradingcrypto.utils.enums.CryptoCurrency;
import com.aquariux.tradingcrypto.utils.enums.Symbol;
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
public class PriceAggregation {

  private Symbol symbol;
  private CryptoCurrency cryptoCurrencyName;
  private BigDecimal bidPrice;
  private BigDecimal askPrice;
}
