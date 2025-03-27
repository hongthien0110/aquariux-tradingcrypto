package com.aquariux.tradingcrypto.service;

import com.aquariux.tradingcrypto.utils.enums.Symbol;
import com.aquariux.tradingcrypto.utils.enums.TradingType;
import com.aquariux.tradingcrypto.service.entity.PriceAggregation;

public interface PriceAggregationService {

  PriceAggregation getBestPrice(Symbol symbol, TradingType tradingType);
}
