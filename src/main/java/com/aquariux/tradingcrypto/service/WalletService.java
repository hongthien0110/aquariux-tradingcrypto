package com.aquariux.tradingcrypto.service;

import com.aquariux.tradingcrypto.utils.enums.Currency;
import com.aquariux.tradingcrypto.service.dto.Wallet;
import java.util.List;

public interface WalletService {

  List<Wallet> findWalletByUserIdAndCurrency(String userId, Currency currency);
}
