package com.aquariux.tradingcrypto.service.impl;

import com.aquariux.tradingcrypto.utils.enums.Currency;
import com.aquariux.tradingcrypto.exception.NotFoundException;
import com.aquariux.tradingcrypto.repository.WalletRepository;
import com.aquariux.tradingcrypto.service.WalletService;
import com.aquariux.tradingcrypto.service.dto.Wallet;
import com.aquariux.tradingcrypto.utils.MapperUtils;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class WalletServiceImpl implements WalletService {

  private WalletRepository walletRepository;

  @Override
  public Wallet findWalletByUserIdAndCurrency(String userId, Currency currency) {
    var entity = this.walletRepository.findWalletByUserIdAndCurrency(userId, currency);
    if (entity == null) {
      throw new NotFoundException("Wallet not found");
    }
    return MapperUtils.INSTANCE.toWallet(entity);
  }
}
