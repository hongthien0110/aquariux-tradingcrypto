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
public class HuobiTicker {

  @JsonProperty("symbol")
  private String symbol;

  @JsonProperty("open")
  private BigDecimal open;

  @JsonProperty("high")
  private BigDecimal high;

  @JsonProperty("low")
  private BigDecimal low;

  @JsonProperty("close")
  private BigDecimal close;

  @JsonProperty("amount")
  private BigDecimal amount;

  @JsonProperty("vol")
  private BigDecimal vol;

  @JsonProperty("count")
  private BigDecimal count;

  @JsonProperty("bid")
  private BigDecimal bid;

  @JsonProperty("bidSize")
  private BigDecimal bidSize;

  @JsonProperty("ask")
  private BigDecimal ask;

  @JsonProperty("askSize")
  private BigDecimal askSize;
}
