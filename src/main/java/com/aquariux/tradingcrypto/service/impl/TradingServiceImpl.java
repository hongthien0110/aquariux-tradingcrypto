package com.aquariux.tradingcrypto.service.impl;

import com.aquariux.tradingcrypto.utils.enums.Currency;
import com.aquariux.tradingcrypto.utils.enums.Symbol;
import com.aquariux.tradingcrypto.utils.enums.TradingType;
import com.aquariux.tradingcrypto.entity.TradeEntity;
import com.aquariux.tradingcrypto.entity.WalletEntity;
import com.aquariux.tradingcrypto.exception.CryptoException;
import com.aquariux.tradingcrypto.model.OrderHistory;
import com.aquariux.tradingcrypto.model.OrderHistoryResponse;
import com.aquariux.tradingcrypto.repository.TradeRepository;
import com.aquariux.tradingcrypto.repository.WalletRepository;
import com.aquariux.tradingcrypto.service.TradingService;
import com.aquariux.tradingcrypto.service.entity.Order;
import com.aquariux.tradingcrypto.utils.MapperUtils;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import javax.naming.InsufficientResourcesException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Log4j2
public class TradingServiceImpl implements TradingService {

  private WalletRepository walletRepository;
  private TradeRepository tradeRepository;

  @Override
  @Transactional
  public Order placeOrder(String userId, Symbol symbol, TradingType tradingType,
      BigDecimal price, BigDecimal quantity) {

    try {
      var wallet = getAndValidateWallet(userId,
          tradingType.equals(TradingType.LONG) ? symbol.getLongCurrency()
              : symbol.getShortCurrency());

      var minusAmount = price.multiply(quantity);
      if (!isSufficientBalance(wallet.getBalance(), minusAmount)) {
        throw new InsufficientResourcesException("insufficient balance");
      }

      wallet.setBalance(wallet.getBalance().subtract(minusAmount));
      TradeEntity trade = TradeEntity.builder().userId(userId)
          .symbol(symbol)
          .tradingType(tradingType)
          .price(price)
          .quantity(quantity)
          .status("Success")
          .timestamp(LocalDateTime.now())
          .build();

      wallet = walletRepository.saveAndFlush(wallet);
      var tradeEntity = tradeRepository.saveAndFlush(trade);
      log.info("Trade {} has been placed", tradeEntity.getId());

      return Order.builder().orderId(tradeEntity.getId())
          .balance(wallet.getBalance())
          .newBalance(wallet.getBalance())
          .timestamp(tradeEntity.getTimestamp().toEpochSecond(ZoneOffset.UTC))
          .status(tradeEntity.getStatus())
          .build();
    } catch (InsufficientResourcesException e) {
      throw new CryptoException(e.getMessage());
    }

  }

  @Override
  public List<OrderHistoryResponse> getHistory(OrderHistory history) {
    var entities = tradeRepository.getHistory(history.getUserId(), history.getSymbol(),
        history.getTradingType(), history.getFrom(), history.getTo(), history.getLimit(),
        history.getOffset());
    return entities.stream().map(MapperUtils.INSTANCE::toOrderHistory).toList();

  }

  private WalletEntity getAndValidateWallet(String userId, Currency currency) {
    var wallet = walletRepository.findByUserIdAndCurrency(userId, currency);
    if (wallet == null) {
      throw new CryptoException("Wallet not found");
    }
    return wallet;
  }

  private boolean isSufficientBalance(BigDecimal balance, BigDecimal minusAmount) {
    return balance.compareTo(minusAmount) >= 0;
  }
}
