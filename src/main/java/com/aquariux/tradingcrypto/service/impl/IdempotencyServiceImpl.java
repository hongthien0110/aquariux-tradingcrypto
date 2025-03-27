package com.aquariux.tradingcrypto.service.impl;

import com.aquariux.tradingcrypto.service.IdempotencyService;
import org.springframework.stereotype.Service;

@Service
public class IdempotencyServiceImpl implements IdempotencyService {

  @Override
  public boolean isIdempotent(String key) {
    return false;
  }

  @Override
  public void setIdempotent(String key) {

  }

}
