FROM maven:3.9.10-eclipse-temurin-24-alpine as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:24.0.1_9-jdk-alpine
WORKDIR .
COPY --from=build ./app/target/*.jar ./app.jar
ENV RABBITMQ_SERVER=credit-rabbitmq
ENV EUREKA_SERVER=http://admin-eureka:admin@credit-eureka:8761/eureka/
ENTRYPOINT java -jar app.jar

