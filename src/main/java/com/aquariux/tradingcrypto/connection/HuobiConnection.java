package com.aquariux.tradingcrypto.connection;

import com.aquariux.tradingcrypto.model.HuobiTickersResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "huobi-crypto", url = "https://api.huobi.pro")
public interface HuobiConnection {

  @GetMapping(value = "/market/tickers")
  HuobiTickersResponse getHuobiPriceAggregation();
}
