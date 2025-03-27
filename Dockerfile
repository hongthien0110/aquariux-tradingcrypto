# Pull JDK image
FROM openjdk:23

# Expose port 8080 to this container
EXPOSE 8080

# Copy JAR file to docker container
COPY build/libs/TradingCrypto-1.0.jar tradingcrypto-docker.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/tradingcrypto-docker.jar"]