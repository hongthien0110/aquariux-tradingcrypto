package com.aquariux.tradingcrypto.repository;

import com.aquariux.tradingcrypto.utils.enums.Currency;
import com.aquariux.tradingcrypto.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<WalletEntity, String> {

  @Query("SELECT w FROM WalletEntity w WHERE w.userEntity.id = :userId AND w.currency = :currency")
  WalletEntity findWalletByUserId(@Param("userId") String userId,
      @Param("currency") Currency currency);

  @Query("SELECT w FROM WalletEntity w WHERE w.userEntity.id = :userId AND w.currency = :currency")
  WalletEntity findByUserIdAndCurrency(String userId, Currency currency);
}
