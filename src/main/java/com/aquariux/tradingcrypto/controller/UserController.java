package com.aquariux.tradingcrypto.controller;

import com.aquariux.tradingcrypto.utils.enums.Currency;
import com.aquariux.tradingcrypto.service.WalletService;
import com.aquariux.tradingcrypto.service.entity.Wallet;
import com.aquariux.tradingcrypto.utils.constants.Constant;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

  private WalletService walletService;

  @GetMapping("/wallet")
  Wallet getWallet(
      @RequestParam("currency") Currency currency,
      HttpServletRequest request) {
    var userId = request.getHeader(Constant.USER_HEADER_KEY);
    checkUserPermission(userId);
    return walletService.getWalletByUserId(userId, currency);
  }

  private void checkUserPermission(String userId) {
    if (userId.isBlank()) {
      throw new IllegalArgumentException("Missing user id");
    }
  }
}
