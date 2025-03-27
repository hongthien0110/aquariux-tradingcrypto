package com.aquariux.tradingcrypto.utils;


import com.aquariux.tradingcrypto.entity.PriceAggregationEntity;
import com.aquariux.tradingcrypto.entity.TradeEntity;
import com.aquariux.tradingcrypto.entity.WalletEntity;
import com.aquariux.tradingcrypto.model.OrderHistoryResponse;
import com.aquariux.tradingcrypto.service.dto.PriceAggregation;
import com.aquariux.tradingcrypto.service.dto.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MapperUtils {

  MapperUtils INSTANCE = Mappers.getMapper(MapperUtils.class);

  PriceAggregation toPriceAggregation(PriceAggregationEntity entity);

  @Mapping(source = "walletEntity.userEntity.id", target = "userId")
  Wallet toWallet(WalletEntity walletEntity);

  @Mapping(target = "timestamp", source = "timestamp")
  @Mapping(target = "orderId", source = "id")
  OrderHistoryResponse toOrderHistory(TradeEntity tradeEntity);
}
