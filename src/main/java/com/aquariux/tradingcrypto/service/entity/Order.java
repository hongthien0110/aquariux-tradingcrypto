package com.aquariux.tradingcrypto.service.entity;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Builder
@Data
@Getter
@ToString
public class Order {
    private String orderId;
    private BigDecimal balance;
    private BigDecimal newBalance;
    private String status;
    private long timestamp;
}
