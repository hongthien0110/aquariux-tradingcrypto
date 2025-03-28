package com.aquariux.tradingcrypto.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.aquariux.tradingcrypto.entity.PriceAggregationEntity;
import com.aquariux.tradingcrypto.repository.PriceAggregationRepository;
import com.aquariux.tradingcrypto.service.impl.PriceAggregationServiceImpl;
import com.aquariux.tradingcrypto.utils.enums.CryptoCurrency;
import com.aquariux.tradingcrypto.utils.enums.Symbol;
import com.aquariux.tradingcrypto.utils.enums.TradingType;
import java.math.BigDecimal;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PriceAggregationServiceImplTest {

  @Mock
  private PriceAggregationRepository repository;

  @InjectMocks
  private PriceAggregationServiceImpl priceAggregationService;

  @Test
  public void shouldReturnBestPrice() {
    var entity = PriceAggregationEntity.builder()
        .cryptoCurrencyName(CryptoCurrency.BINANCE)
        .symbol(Symbol.BTCUSDT)
        .bidPrice(new BigDecimal(10000))
        .bidQuantity(new BigDecimal(10))
        .askPrice(new BigDecimal(10000))
        .askQuantity(new BigDecimal(10))
        .build();
    when(repository.findAllById(any())).thenReturn(Collections.singletonList(entity));
    var priceAggregation = this.priceAggregationService.getBestPrice(Symbol.BTCUSDT,
        TradingType.LONG);

    assertEquals(new BigDecimal(10000), priceAggregation.getBidPrice());
    assertEquals(CryptoCurrency.BINANCE, priceAggregation.getCryptoCurrencyName());
  }

}
