package com.aquariux.tradingcrypto.model;

import java.util.ArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Getter
@ToString
public class BinanceTickersResponse extends ArrayList<BinanceTicker> {

}
