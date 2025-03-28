package com.aquariux.tradingcrypto.service.impl;

import com.aquariux.tradingcrypto.entity.TradeEntity;
import com.aquariux.tradingcrypto.entity.WalletEntity;
import com.aquariux.tradingcrypto.exception.InsufficientBalanceException;
import com.aquariux.tradingcrypto.exception.NotFoundException;
import com.aquariux.tradingcrypto.model.OrderHistory;
import com.aquariux.tradingcrypto.model.OrderHistoryResponse;
import com.aquariux.tradingcrypto.repository.TradeRepository;
import com.aquariux.tradingcrypto.repository.WalletRepository;
import com.aquariux.tradingcrypto.service.TradingService;
import com.aquariux.tradingcrypto.service.dto.Order;
import com.aquariux.tradingcrypto.utils.MapperUtils;
import com.aquariux.tradingcrypto.utils.enums.Currency;
import com.aquariux.tradingcrypto.utils.enums.Symbol;
import com.aquariux.tradingcrypto.utils.enums.TradingType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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
      BigDecimal bidPrice, BigDecimal askPrice, BigDecimal quantity)
      throws Exception {

    var longWallet = getAndValidateWallet(userId, symbol.getLongCurrency());
    var shortWallet = getAndValidateWallet(userId, symbol.getShortCurrency());
    WalletEntity wallet;
    BigDecimal price;
    BigDecimal previousBalance;

    if (tradingType.equals(TradingType.LONG)) {
      price = askPrice;
      var minusAmount = price.multiply(quantity);
      if (isSufficientBalance(longWallet.getBalance(), minusAmount)) {
        throw new InsufficientBalanceException("Insufficient balance");
      }

      previousBalance = longWallet.getBalance();

      longWallet.setBalance(longWallet.getBalance().subtract(minusAmount));
      longWallet = walletRepository.saveAndFlush(longWallet);
      wallet = longWallet;

      shortWallet.setBalance(shortWallet.getBalance().add(quantity));
      walletRepository.saveAndFlush(shortWallet);
    } else {
      price = bidPrice;
      if (isSufficientBalance(shortWallet.getBalance(), quantity)) {
        throw new InsufficientResourcesException("Insufficient balance");
      }

      previousBalance = shortWallet.getBalance();

      shortWallet.setBalance(shortWallet.getBalance().subtract(quantity));
      shortWallet = walletRepository.saveAndFlush(shortWallet);
      wallet = shortWallet;

      var minusAmount = price.multiply(quantity);
      longWallet.setBalance(longWallet.getBalance().add(minusAmount));
      walletRepository.saveAndFlush(longWallet);
    }

    TradeEntity trade = TradeEntity.builder().userId(userId)
        .symbol(symbol)
        .tradingType(tradingType)
        .price(price)
        .quantity(quantity)
        .status("Success")
        .timestamp(LocalDateTime.now())
        .build();
    var tradeEntity = tradeRepository.saveAndFlush(trade);
    log.info("Your trade {} has been placed order", tradeEntity.getId());

    return Order.builder().orderId(tradeEntity.getId())
        .balance(previousBalance)
        .newBalance(wallet.getBalance())
        .timestamp(tradeEntity.getTimestamp().format(DateTimeFormatter.ISO_DATE_TIME))
        .status(tradeEntity.getStatus())
        .build();
  }

  @Override
  public List<OrderHistoryResponse> getHistory(OrderHistory history) throws Exception {
    var entities = tradeRepository.getHistory(history.getUserId(), history.getSymbol(),
        history.getFrom(), history.getTo(), history.getLimit(), history.getOffset());
    return entities.stream().map(MapperUtils.INSTANCE::toOrderHistory).toList();

  }

  private WalletEntity getAndValidateWallet(String userId, Currency currency) {
    var wallet = walletRepository.findWalletByUserIdAndCurrency(userId, currency);
    if (wallet == null) {
      throw new NotFoundException("Wallet not found");
    }
    return wallet;
  }

  private boolean isSufficientBalance(BigDecimal balance, BigDecimal minusAmount) {
    return balance.compareTo(minusAmount) < 0;
  }
}
