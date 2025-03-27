package com.aquariux.tradingcrypto.service.impl;

import com.aquariux.tradingcrypto.exception.NotFoundException;
import com.aquariux.tradingcrypto.repository.WalletRepository;
import com.aquariux.tradingcrypto.service.WalletService;
import com.aquariux.tradingcrypto.service.dto.Wallet;
import com.aquariux.tradingcrypto.utils.MapperUtils;
import com.aquariux.tradingcrypto.utils.enums.Currency;
import java.util.List;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class WalletServiceImpl implements WalletService {

  private WalletRepository walletRepository;

  @Override
  public List<Wallet> findWalletByUserIdAndCurrency(String userId, Currency currency) {
    var entities =
        currency != null ? Stream.of(
            walletRepository.findWalletByUserIdAndCurrency(userId, currency)).toList() :
            walletRepository.findWalletByUserId(userId);
    if (entities == null) {
      throw new NotFoundException("Wallet not found");
    }
    return entities.stream().map(MapperUtils.INSTANCE::toWallet).toList();
  }
}
