package com.aquariux.tradingcrypto.service;

import com.aquariux.tradingcrypto.utils.enums.Currency;
import com.aquariux.tradingcrypto.service.dto.Wallet;

public interface WalletService {

  Wallet findWalletByUserIdAndCurrency(String userId, Currency currency);
}
