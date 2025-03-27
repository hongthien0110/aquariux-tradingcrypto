package com.aquariux.tradingcrypto.repository;

import com.aquariux.tradingcrypto.entity.PriceAggregationEntity;
import com.aquariux.tradingcrypto.entity.PriceAggregationEntity.PriceAggregationKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceAggregationRepository extends
    JpaRepository<PriceAggregationEntity, PriceAggregationKey> {

}
