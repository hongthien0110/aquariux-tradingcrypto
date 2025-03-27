package com.aquariux.tradingcrypto.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.aquariux.tradingcrypto.service.PriceAggregationService;
import com.aquariux.tradingcrypto.service.TradingService;
import com.aquariux.tradingcrypto.service.WalletService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TradingController.class)
public class TradingControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @MockitoBean
  private PriceAggregationService priceAggregationService;
  @MockitoBean
  private TradingService tradingService;

  @Test
  void shouldReturnBestPrice() throws Exception {
    mockMvc.perform(get("/trading/best-price")
            .param("symbol", "BTCUSDT")
            .param("tradingType", "LONG")
            .param("price", "1")
            .param("quantity", "1"))
        .andExpect(status().isOk());
  }
}
