package com.aquariux.tradingcrypto.entity;

import com.aquariux.tradingcrypto.utils.enums.Symbol;
import com.aquariux.tradingcrypto.utils.enums.TradingType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "trade")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TradeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String userId;

  @Enumerated(EnumType.STRING)
  private Symbol symbol;

  @Enumerated(EnumType.STRING)
  private TradingType tradingType;

  private String status;

  private BigDecimal quantity;

  private BigDecimal price;

  private LocalDateTime timestamp;
}
