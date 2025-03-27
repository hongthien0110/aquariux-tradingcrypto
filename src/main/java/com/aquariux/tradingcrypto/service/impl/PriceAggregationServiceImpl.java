package com.aquariux.tradingcrypto.service.impl;

import com.aquariux.tradingcrypto.utils.enums.CryptoCurrency;
import com.aquariux.tradingcrypto.utils.enums.Symbol;
import com.aquariux.tradingcrypto.utils.enums.TradingType;
import com.aquariux.tradingcrypto.connection.BinanceConnection;
import com.aquariux.tradingcrypto.connection.HuobiConnection;
import com.aquariux.tradingcrypto.entity.PriceAggregationEntity;
import com.aquariux.tradingcrypto.entity.PriceAggregationEntity.PriceAggregationKey;
import com.aquariux.tradingcrypto.exception.CryptoException;
import com.aquariux.tradingcrypto.repository.PriceAggregationRepository;
import com.aquariux.tradingcrypto.service.PriceAggregationService;
import com.aquariux.tradingcrypto.service.entity.PriceAggregation;
import com.aquariux.tradingcrypto.utils.MapperUtils;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@AllArgsConstructor
public class PriceAggregationServiceImpl implements PriceAggregationService {

  private final BinanceConnection binanceConnection;
  private final HuobiConnection huobiConnection;
  private final PriceAggregationRepository priceAggregationRepository;

  @Scheduled(fixedRate = 10, timeUnit = TimeUnit.SECONDS)
  private void getPrice() {
    log.debug("Begin getting crypto price");
    var binancePricesEntities = binanceConnection
        .getPriceAggregation()
        .stream()
        .filter(binanceTicker -> Symbol.isSupportedSymbol(binanceTicker.getSymbol())).map(
            binanceTicker -> PriceAggregationEntity.builder()
                .cryptoCurrencyName(CryptoCurrency.BINANCE)
                .symbol(Symbol.valueOf(binanceTicker.getSymbol().toUpperCase()))
                .bidPrice(binanceTicker.getBidPrice()).bidQty(binanceTicker.getBidQty())
                .askPrice(binanceTicker.getAskPrice()).askQty(binanceTicker.getAskQty()).build())
        .toList();
    var huobiPricesEntities = huobiConnection.getPriceAggregation().getData().stream()
        .filter(huobiTicker -> Symbol.isSupportedSymbol(huobiTicker.getSymbol())).map(
            huobiTicker -> PriceAggregationEntity.builder().cryptoCurrencyName(CryptoCurrency.HUOBI)
                .symbol(Symbol.valueOf(huobiTicker.getSymbol().toUpperCase()))
                .bidPrice(huobiTicker.getBid()).bidQty(huobiTicker.getBidSize())
                .askPrice(huobiTicker.getAsk()).askQty(huobiTicker.getAskSize()).build()).toList();

    var combinedPriceEntities = Stream.concat(binancePricesEntities.stream(),
        huobiPricesEntities.stream()).toList();
    priceAggregationRepository.saveAllAndFlush(combinedPriceEntities);

    log.debug("End getting crypto price");
  }

  @Override
  public PriceAggregation getBestPrice(Symbol symbol, TradingType tradingType) {
    var entities = priceAggregationRepository.findAllById(Stream.of(
        PriceAggregationKey.builder().symbol(symbol).cryptoCurrencyName(CryptoCurrency.BINANCE)
            .build(),
        PriceAggregationKey.builder().symbol(symbol).cryptoCurrencyName(CryptoCurrency.HUOBI)
            .build()).toList());
    if (entities.isEmpty()) {
      throw new CryptoException("Symbol not found");
    }
    var maxPrice = Collections.max(entities, Comparator.comparing(entity -> {
      if (TradingType.LONG.equals(tradingType)) {
        return entity.getBidPrice();
      } else {
        return entity.getAskPrice();
      }
    }));
    log.info("The max price: {}", maxPrice.toString());
    return MapperUtils.INSTANCE.toPriceAggregation(maxPrice);
  }
}
