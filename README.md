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
- Java 24+
- Maven 3.6+
- Docker (optional)

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

## Configurations

- **Development**: Default profile with H2 in-memory database
- **Production**: `production` profile with Docker
- **Logs**: DEBUG (dev) / INFO (prod)
