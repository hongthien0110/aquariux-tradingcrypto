package com.aquariux.tradingcrypto.utils.enums;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public enum Symbol {
  BTCUSDT(Currency.BTC,
      Currency.USDT),
  ETHUSDT(Currency.ETH,
      Currency.USDT);
  private final Currency shortCurrency;
  private final Currency longCurrency;

  private static final Map<String, Boolean> SUPPORTED_SYMBOL_MAP;

  static {
    var symbolMap = new HashMap<String, Boolean>();
    for (var symbol : Symbol.values()) {
      symbolMap.put(symbol.name(), true);
    }
    SUPPORTED_SYMBOL_MAP = Collections.unmodifiableMap(symbolMap);
  }

  public static boolean isSupportedSymbol(String symbol) {
    return SUPPORTED_SYMBOL_MAP.containsKey(symbol.toUpperCase());
  }
}
