# Microservices System - Card Generator

Microservices system for credit card generation.

## Project Structure

```
/
├── eureka/                    # Discovery server
├── gateway/                   # API Gateway
├── client/                    # Client service
├── card/                      # Card service
└── CreditEvaluator/          # Credit evaluation service
```

## How to Run

### Prerequisites
- Java 17+
- Maven 3.6+
- Docker (optional, for production)

### Local Development

1. **Build the project**:
```bash
mvn clean install
```

2. **Run services** (in separate terminals):

```bash
# Eureka Server
mvn spring-boot:run -pl eureka

# Gateway
mvn spring-boot:run -pl gateway

# Client Service
mvn spring-boot:run -pl client

# Card Service
mvn spring-boot:run -pl card

# Credit Evaluator
mvn spring-boot:run -pl CreditEvaluator
```

### Production with Docker

```bash
# Run all services
docker-compose up --build

# Run in background
docker-compose up -d

# Stop services
docker-compose down
```

## Service Ports

- **Eureka Server**: 8761
- **Gateway**: 8080
- **Client Service**: 8082
- **Card Service**: 8081
- **Credit Evaluator**: 8083

## Access URLs

- **Eureka Dashboard**: http://localhost:8761
- **Gateway**: http://localhost:8080
- **H2 Console (Client)**: http://localhost:8082/h2-console
- **H2 Console (Card)**: http://localhost:8081/h2-console
- **Swagger UI**: http://localhost:[port]/swagger-ui.html

## Configurations

- **Development**: Default profile with H2 in-memory database
- **Production**: `production` profile with Docker
- **Logs**: DEBUG (dev) / INFO (prod)
