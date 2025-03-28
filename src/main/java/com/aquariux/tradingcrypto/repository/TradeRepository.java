package com.aquariux.tradingcrypto.repository;

import com.aquariux.tradingcrypto.utils.enums.Symbol;
import com.aquariux.tradingcrypto.utils.enums.TradingType;
import com.aquariux.tradingcrypto.entity.TradeEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends JpaRepository<TradeEntity, String> {

  @Query("SELECT t FROM TradeEntity t WHERE t.userId = :userId " +
      "AND t.symbol = :symbol " +
      "AND t.timestamp >= :from AND t.timestamp <= :to " +
      "ORDER BY t.timestamp DESC " +
      "LIMIT :limit OFFSET :offset ")
  List<TradeEntity> getHistory(@Param("userId") String userId, @Param("symbol") Symbol symbol,
      @Param("tradingType") TradingType tradingType, @Param("from") LocalDateTime from,
      @Param("to") LocalDateTime to, @Param("limit") int limit, @Param("offset") int offset);
}
