package com.aquariux.tradingcrypto.connection;

import com.aquariux.tradingcrypto.model.BinanceTickersResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "binance-crypto", url = "https://api.binance.com")
public interface BinanceConnection {

  @GetMapping(value = "/api/v3/ticker/bookTicker")
  BinanceTickersResponse getPriceAggregation();
}
