package com.aquariux.tradingcrypto.service;

public interface IdempotencyService {

  boolean isIdempotent(String key);

  void setIdempotent(String key);
}
