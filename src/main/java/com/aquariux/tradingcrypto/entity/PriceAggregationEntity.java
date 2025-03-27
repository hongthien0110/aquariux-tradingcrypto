package com.aquariux.tradingcrypto.entity;

import com.aquariux.tradingcrypto.utils.enums.CryptoCurrency;
import com.aquariux.tradingcrypto.utils.enums.Symbol;
import com.aquariux.tradingcrypto.entity.PriceAggregationEntity.PriceAggregationKey;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "price_aggregation")
@IdClass(PriceAggregationKey.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class PriceAggregationEntity {

  @Id
  @Enumerated(EnumType.STRING)
  private Symbol symbol;

  @Id
  @Enumerated(EnumType.STRING)
  private CryptoCurrency cryptoCurrencyName;

  private BigDecimal bidPrice;

  private BigDecimal bidQty;

  private BigDecimal askPrice;

  private BigDecimal askQty;

  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  public static class PriceAggregationKey implements Serializable {

    private Symbol symbol;
    private CryptoCurrency cryptoCurrencyName;
  }
}
