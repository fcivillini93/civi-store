## 🛒 Order Service

Order Service is a backend application developed in **Java 17** using **Spring Boot 3.4.3** and **Maven**.  
It is designed to manage user orders and product stock, with built-in support for safe concurrent updates using Redis-based locking.

## ⚙️ Build

To compile the project:

```bash
mvn clean compile
```
To build the Docker image:

```bash
mvn clean compile -Pdocker
```

## 🐳 Docker Compose

Inside the `support/` directory, two docker-compose files are provided:

```
support/
├── docker-compose.support.yaml     # Starts support services (MariaDB, Redis)
└── docker-compose.store.yaml       # Starts the OrderService container
```

### 1. Start dependencies (MariaDB + Redis)

```bash
docker-compose -f support/docker-compose.support.yaml up -d
```

Once running, connect to MariaDB and manually create the database:

```sql
CREATE DATABASE order_service;
```

### 2. Start the service

#### Option A: via Docker (recommended)

```bash
docker-compose -f support/docker-compose.store.yaml up -d
```

#### Option B: manually (without Docker)

or

```bash
java -jar /civi-store-boot/target/order-service-*.jar
```

## 🔍 API Documentation

Once the service is running, Swagger UI is available at:

👉 [http://localhost:9000/store/swagger-ui/index.html](http://localhost:9000/store/swagger-ui/index.html)

Use it to explore and test the API endpoints directly.