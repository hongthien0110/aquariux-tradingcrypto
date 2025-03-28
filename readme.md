# Aquariux Exam

# Overview

1. Retrieve the pricing from trading crypto (Binance, Huobi): using Spring Cloud OpenFeign
2. Database: using In-memory H2
3. CI/CD: using GitHub Actions
4. Handle duplicated requests: using Idempotency key ( not implemented )

# Run in Docker container

1. `./gradlew clean build` for building the project
2. `docker build -t tradingcrypto .` for building a Docker image
3. `docker run -p 8080:8080 tradingcrypto` to run Docker image

# Main features
+ Postman Collection:
https://www.postman.com/api-team-6723/thienlai/collection/a1kcq7t/aquariux-apis?action=share&creator=4946196

1. Retrieve the latest price from trading crypto (Binance, Huobi) after every 10s
   
   ![image](https://github.com/user-attachments/assets/072e4a77-692a-423c-8ae9-a217d8b91303)

2. Retrieve the latest best aggregated price
   
   ![image](https://github.com/user-attachments/assets/5c9dcf60-897a-415f-9a62-3841e7029b7a)
   
3. Users can trade based on the latest best aggregated price
   
   ![image](https://github.com/user-attachments/assets/829e30c7-0b75-4fbd-9cce-cb625fc5fb89)

4. Retrieve the user’s crypto currencies wallet balance
    
   ![image](https://github.com/user-attachments/assets/93e3677a-4366-47c1-80b6-f0da85b17edd)
   
5. Retrieve the user's trading history
    
   ![image](https://github.com/user-attachments/assets/1ae20902-c856-4823-99be-c1cb3587455f)

