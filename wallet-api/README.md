# Wallet System API

## 📦 Overview
A **RESTful API** built with **Spring Boot** that allows users to:
- Create wallets
- Fund wallets
- Debit wallets
- View wallet transactions

All responses are wrapped in a consistent `ApiResponse<T>` format for better API consistency.

---

## 🛠 Technology Stack
- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **H2 Database** (in-memory for development)
- **Swagger / Springdoc OpenAPI** (API documentation)
- **Lombok** (to reduce boilerplate code)
- **Jakarta Validation** (for request validation)

---

## 🚀 Steps to Build and Run the Project

1. **Clone the repository**
git clone <your-repo-url>
cd wallet-api

2. Build the project using Maven
mvn clean install

3. Run the application

mvn spring-boot:run

The API will start on http://localhost:8080 by default.


📄 Accessing Swagger UI

Swagger UI provides interactive API documentation for testing endpoints:

Open your browser and go to:

http://localhost:8080/swagger-ui/index.html



💡 Assumptions & Design Decisions

Each user can have one wallet identified by a unique userId.

Wallet operations (funding/debiting) return the updated wallet balance.

Transactions are logged and can be retrieved per wallet.

Validation is applied to all request bodies:

CreateWalletRequest.userId cannot be blank

WalletRequest.amount must be ≥ 0.01

H2 Database is used for development/testing. For production, replace with a persistent database (PostgreSQL, MySQL, etc.)

API responses are consistently wrapped in ApiResponse<T> for clarity and error handling.

Swagger UI is integrated using Springdoc OpenAPI for interactive API documentation.