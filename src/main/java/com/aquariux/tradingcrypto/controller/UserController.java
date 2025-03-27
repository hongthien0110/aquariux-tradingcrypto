package com.aquariux.tradingcrypto.controller;

import com.aquariux.tradingcrypto.utils.enums.Currency;
import com.aquariux.tradingcrypto.service.WalletService;
import com.aquariux.tradingcrypto.service.dto.Wallet;
import com.aquariux.tradingcrypto.utils.constants.Constant;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
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

  @GetMapping(value = "/wallet", produces = "application/json")
  List<Wallet> getWallet(
      @RequestParam(value = "currency", required = false) Currency currency,
      HttpServletRequest request) {
    var userId = request.getHeader(Constant.USER_HEADER_KEY);
    checkUserPermission(userId);
    return walletService.findWalletByUserIdAndCurrency(userId, currency);
  }

  private void checkUserPermission(String userId) {
    if (userId.isEmpty()) {
      throw new IllegalArgumentException("Missing user id");
    }
  }
}
