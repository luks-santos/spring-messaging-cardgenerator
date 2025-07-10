FROM maven:3.9.10-eclipse-temurin-24-alpine as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:24.0.1_9-jdk-alpine
WORKDIR .
COPY --from=build ./app/target/*.jar ./app.jar
ENV EUREKA_SERVER=http://admin-eureka:admin@credit-eureka:8761/eureka/
ENV KEYCLOAK_SERVER=credit-keycloak
ENV KEYCLOAK_PORT=8080

ENTRYPOINT java -jar app.jar 