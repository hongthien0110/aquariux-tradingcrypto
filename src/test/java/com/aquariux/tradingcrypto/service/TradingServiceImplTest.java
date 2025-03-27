package com.aquariux.tradingcrypto.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.aquariux.tradingcrypto.entity.TradeEntity;
import com.aquariux.tradingcrypto.entity.UserEntity;
import com.aquariux.tradingcrypto.entity.WalletEntity;
import com.aquariux.tradingcrypto.repository.TradeRepository;
import com.aquariux.tradingcrypto.repository.WalletRepository;
import com.aquariux.tradingcrypto.service.impl.TradingServiceImpl;
import com.aquariux.tradingcrypto.utils.enums.Currency;
import com.aquariux.tradingcrypto.utils.enums.Symbol;
import com.aquariux.tradingcrypto.utils.enums.TradingType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TradingServiceImplTest {

  @Mock
  private WalletRepository walletRepository;
  @Mock
  private TradeRepository tradeRepository;
  @InjectMocks
  private TradingServiceImpl tradingService;

  @Test
  void shouldPlaceOrderLongSuccessfully() throws Exception {
    WalletEntity walletEntity = WalletEntity.builder()
        .currency(Currency.BTC)
        .balance(BigDecimal.TEN)
        .userEntity(UserEntity.builder().id("123").build())
        .build();
    TradeEntity tradeEntity = TradeEntity.builder()
        .status("Success")
        .timestamp(LocalDateTime.now())
        .id("123")
        .tradingType(TradingType.LONG)
        .build();
    when(walletRepository.findWalletByUserIdAndCurrency(anyString(),
        any(Currency.class))).thenReturn(
        walletEntity);
    when(walletRepository.saveAndFlush(any(WalletEntity.class))).thenReturn(walletEntity);
    when(tradeRepository.saveAndFlush(any(TradeEntity.class))).thenReturn(tradeEntity);

    var order = tradingService.placeOrder("123", Symbol.BTCUSDT, TradingType.LONG,
        BigDecimal.ONE, BigDecimal.ONE);

    assertEquals("Success", order.getStatus());
  }

  @Test
  void shouldPlaceOrderShortSuccessfully() throws Exception {
    //given
    WalletEntity walletEntity = WalletEntity.builder()
        .currency(Currency.USDT)
        .balance(BigDecimal.TEN)
        .userEntity(UserEntity.builder().id("123").build())
        .build();
    TradeEntity tradeEntity = TradeEntity.builder()
        .status("Success")
        .timestamp(LocalDateTime.now())
        .id("123")
        .tradingType(TradingType.SHORT)
        .build();
    when(walletRepository.findWalletByUserIdAndCurrency(anyString(),
        any(Currency.class))).thenReturn(
        walletEntity);
    when(walletRepository.saveAndFlush(any(WalletEntity.class))).thenReturn(walletEntity);
    when(tradeRepository.saveAndFlush(any(TradeEntity.class))).thenReturn(tradeEntity);

    var order = tradingService.placeOrder("123", Symbol.BTCUSDT, TradingType.SHORT,
        BigDecimal.ONE, BigDecimal.ONE);

    assertEquals("Success", order.getStatus());
  }
}
