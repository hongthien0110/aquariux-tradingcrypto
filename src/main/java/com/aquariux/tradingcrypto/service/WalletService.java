package com.aquariux.tradingcrypto.service;

import com.aquariux.tradingcrypto.utils.enums.Currency;
import com.aquariux.tradingcrypto.service.entity.Wallet;

public interface WalletService {

  Wallet getWalletByUserId(String userId, Currency currency);
}
