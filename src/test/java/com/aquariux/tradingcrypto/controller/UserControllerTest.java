package com.aquariux.tradingcrypto.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.aquariux.tradingcrypto.service.WalletService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
public class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @MockitoBean
  private WalletService walletService;

  @Test
  void shouldReturnWallet() throws Exception {
    mockMvc.perform(get("/user/wallet")
            .header("user-id", "123")
            .param("currency", "USDT"))
        .andExpect(status().isOk());
  }
}
