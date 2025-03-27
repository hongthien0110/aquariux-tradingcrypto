package com.aquariux.tradingcrypto.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@Getter
@ToString
public class HuobiTickersResponse {

  @JsonProperty("data")
  private List<HuobiTicker> data;
}
