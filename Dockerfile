FROM openjdk:21-ea-24-oracle

WORKDIR /app
COPY target/users-0.0.1-SNAPSHOT.jar app.jar
COPY Wallet_GK39II3MDYKIVX8D /app/oracle_wallet
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]