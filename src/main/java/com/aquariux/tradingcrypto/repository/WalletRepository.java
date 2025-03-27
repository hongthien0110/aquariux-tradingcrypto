package com.aquariux.tradingcrypto.repository;

import com.aquariux.tradingcrypto.entity.WalletEntity;
import com.aquariux.tradingcrypto.utils.enums.Currency;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<WalletEntity, String> {

  @Query("SELECT w FROM WalletEntity w WHERE w.userEntity.id = :userId")
  List<WalletEntity> findWalletByUserId(String userId);

  @Query("SELECT w FROM WalletEntity w WHERE w.userEntity.id = :userId AND w.currency = :currency")
  WalletEntity findWalletByUserIdAndCurrency(String userId, Currency currency);
}
