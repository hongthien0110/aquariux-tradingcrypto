package com.aquariux.tradingcrypto.controller;

import com.aquariux.tradingcrypto.utils.enums.Symbol;
import com.aquariux.tradingcrypto.utils.enums.TradingType;
import com.aquariux.tradingcrypto.exception.CryptoException;
import com.aquariux.tradingcrypto.model.OrderHistory;
import com.aquariux.tradingcrypto.model.OrderHistoryResponse;
import com.aquariux.tradingcrypto.model.OrderRequest;
import com.aquariux.tradingcrypto.service.PriceAggregationService;
import com.aquariux.tradingcrypto.service.TradingService;
import com.aquariux.tradingcrypto.service.entity.Order;
import com.aquariux.tradingcrypto.service.entity.PriceAggregation;
import com.aquariux.tradingcrypto.utils.constants.Constant;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trading")
@AllArgsConstructor
public class TradingController {

  private TradingService tradingService;
  private PriceAggregationService aggregatedPriceService;

  @GetMapping(value = "/best-price", produces = "application/json")
  PriceAggregation getBestPrice(
      @RequestParam("symbol") Symbol symbol,
      @RequestParam("tradingType") TradingType tradingType,
      HttpServletRequest servletRequest) throws CryptoException {
    return aggregatedPriceService.getBestPrice(symbol, tradingType);
  }

  @PostMapping(value = "/place-order", produces = "application/json")
  Order placeOrder(
      @RequestBody OrderRequest orderRequest,
      HttpServletRequest servletRequest) throws Exception {
    var userId = servletRequest.getHeader(Constant.USER_HEADER_KEY);
    checkUserPermission(userId);
    return tradingService.placeOrder(userId, orderRequest.getSymbol(),
        orderRequest.getTradingType(), orderRequest.getPrice(), orderRequest.getQuantity());
  }

  @GetMapping(value = "/history", produces = "application/json")
  List<OrderHistoryResponse> getHistory(
      @RequestParam("symbol") Symbol symbol,
      @RequestParam("tradingType") TradingType tradingType,
      @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
      @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
      @RequestParam(value = "limit", defaultValue = "50") int limit,
      @RequestParam(value = "offset", defaultValue = "0") int offset,
      HttpServletRequest servletRequest) throws Exception {
    var userId = servletRequest.getHeader(Constant.USER_HEADER_KEY);
    checkUserPermission(userId);
    var historyRequest = OrderHistory.builder().userId(userId)
        .symbol(symbol).tradingType(tradingType)
        .from(LocalDateTime.from(from.atStartOfDay()))
        .to(LocalDateTime.from(to.atStartOfDay()))
        .limit(limit).offset(offset)
        .build();
    return tradingService.getHistory(historyRequest);
  }

  private void checkUserPermission(String userId) {
    if (userId.isBlank()) {
      throw new IllegalArgumentException("Missing user id");
    }
  }
}
