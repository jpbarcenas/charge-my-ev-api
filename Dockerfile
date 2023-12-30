FROM eclipse-temurin:latest

LABEL mentainer="jpbarcenas.contractor@gmail.com"

WORKDIR /app

COPY target/charge-my-ev-api-0.0.1-SNAPSHOT.jar /app/charge-my-ev-api.jar

ENTRYPOINT ["java", "-jar", "charge-my-ev-api.jar"]