package com.aquariux.tradingcrypto.service;

import com.aquariux.tradingcrypto.utils.enums.Symbol;
import com.aquariux.tradingcrypto.utils.enums.TradingType;
import com.aquariux.tradingcrypto.model.OrderHistory;
import com.aquariux.tradingcrypto.model.OrderHistoryResponse;
import com.aquariux.tradingcrypto.service.dto.Order;
import java.math.BigDecimal;
import java.util.List;

public interface TradingService {

  Order placeOrder(String userId, Symbol symbol, TradingType tradingType,
      BigDecimal price, BigDecimal quantity) throws Exception;

  List<OrderHistoryResponse> getHistory(OrderHistory history) throws Exception;
}
