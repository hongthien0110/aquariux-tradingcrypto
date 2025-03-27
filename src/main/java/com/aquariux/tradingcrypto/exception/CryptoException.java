package com.aquariux.tradingcrypto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CryptoException extends RuntimeException {

  public CryptoException(String message) {
    super(message);
  }
}
