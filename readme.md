# Aquariux Exam

# Overview

1. Retrieve the latest price from Binance, Huobi: using Spring Cloud OpenFeign
2. Database: using In-memory H2
3. CI/CD: using GitHub Actions
4. Handle duplicated requests: using the Idempotent key (not implemented)
5. Handle distributed locks: using Redis (not implemented)

# Run in Docker container

1. `./gradlew clean build` for build project
2. `docker build -t tradingcrypto .` for build docker image
3. `docker run -p 8080:8080 tradingcrypto` for run docker image

# Main features

1. Retrieve the latest price from Binance, Huobi after every 10s
   ![image](https://github.com/user-attachments/assets/1b1d5ff5-8574-4cf3-bafd-8c2207bf73e6)
2. Retrieve the latest best aggregated price
   ![image](https://github.com/user-attachments/assets/7bfb0a6d-c397-4958-8c7c-039de197c317)
3. Users can trade based on the latest best aggregated price
   ![image](https://github.com/user-attachments/assets/55ccd8f8-dd4c-4262-97b4-f2632445b726)
4. Retrieve the user’s crypto currencies wallet balance
   ![image](https://github.com/user-attachments/assets/2a1c873c-4b1f-4656-9d9f-cfca58d29b94)
5. Retrieve the user's trading history
   ![image](https://github.com/user-attachments/assets/e08659a7-0c20-459e-88db-2a7b76bb0af1)
