package com.aquariux.tradingcrypto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BinanceTicker {

  @JsonProperty("symbol")
  private String symbol;

  @JsonProperty("bidPrice")
  private BigDecimal bidPrice;

  @JsonProperty("bidQty")
  private BigDecimal bidQty;

  @JsonProperty("askPrice")
  private BigDecimal askPrice;

  @JsonProperty("askQty")
  private BigDecimal askQty;
}
