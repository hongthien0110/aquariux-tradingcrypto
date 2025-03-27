package com.aquariux.tradingcrypto.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.aquariux.tradingcrypto.entity.WalletEntity;
import com.aquariux.tradingcrypto.repository.WalletRepository;
import com.aquariux.tradingcrypto.service.impl.WalletServiceImpl;
import com.aquariux.tradingcrypto.utils.enums.Currency;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class WalletServiceImplTest {

  @Mock
  private WalletRepository walletRepository;
  @InjectMocks
  private WalletServiceImpl walletService;

  @Test
  void shouldReturnWallet() {
    when(walletRepository.findWalletByUserId("123", Currency.USDT)).thenReturn(new WalletEntity());
    var wallet = walletService.getWalletByUserId("123", Currency.USDT);
    assertNotNull(wallet);
  }
}
