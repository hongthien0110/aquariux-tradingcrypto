package com.aquariux.tradingcrypto.service;

import com.aquariux.tradingcrypto.service.dto.PriceAggregation;
import com.aquariux.tradingcrypto.utils.enums.Symbol;
import com.aquariux.tradingcrypto.utils.enums.TradingType;

public interface PriceAggregationService {

  PriceAggregation getBestPrice(Symbol symbol, TradingType tradingType);
}
