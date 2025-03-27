# Aquariux Exam

# Overview

1. Retrieve the latest price from Binance, Huobi: using Spring Cloud OpenFeign
2. Database: using In-memory H2
3. CI/CD: using Github Actions
4. Handle duplicated requests: using idempotent key (not implemented)
5. Handle distributed locks: using Redis (not implemented)

# Run in docker container

1. `./gradlew clean build` for build project
2. `docker build -t tradingcrypto .` for build docker image
3. `docker run -p 8080:8080 tradingcrypto` for run docker image

# Features

1. Every 10s, we will get latest price from Binance, Huobi
   ![Price Ticker](.github/features/get_price_ticker.png)
2. We can get best price by
   ![Best Price](.github/features/get_best_price.png)
3. We can get wallet balance
   ![Wallet Balance](.github/features/wallet_balance.png)
4. We can place order
   ![Place Order](.github/features/place_order.png)
5. We can get order history
   ![Order History](.github/features/history.png)
