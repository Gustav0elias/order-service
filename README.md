# Order Microservice

A robust microservice built with Spring Boot for managing orders, products, and users in a distributed system architecture.

## 🚀 Features

- Order management and processing
- User management
- Product catalog
- Exception handling with detailed error responses
- Domain-Driven Design (DDD) architecture
- RESTful API endpoints

## 🛠 Technical Stack

- Java
- Spring Boot
- Spring Data JPA
- ModelMapper
- UUID for entity identification
- Logging with SLF4J
- RESTful architecture

## 📦 Project Structure

src/main/java/com/ms/ordermicroservice/
├── application/
│   └── mapper/
├── config/
├── domain/
│   └── model/
├── infrastructure/
│   ├── persistence/
│   │   └── entity/
│   └── web/
│       ├── controller/
│       └── exception/


## 🏗 Architecture

- **Domain Layer**: Contains business logic and domain models
- **Application Layer**: Houses application services and DTOs
- **Infrastructure Layer**: Manages external concerns like persistence and web
- **Configuration**: Global configurations and beans setup

## 🔍 Key Components

- **Order Management**: Complete order lifecycle handling
- **User Management**: User creation and management
- **Product Catalog**: Product information and inventory
- **Global Exception Handler**: Centralized error handling
- **Mappers**: DTO to Entity conversions using ModelMapper

## 💡 Design Patterns

- DTO Pattern
- Repository Pattern
- Mapper Pattern
- Factory Pattern
- Dependency Injection

## 🔒 Error Handling

Comprehensive error handling including:
- Resource Not Found
- Validation Errors
- Business Logic Exceptions
- Generic Exceptions

## 🚀 Getting Started

1. Clone the repository
2. Configure your database properties
3. Run the application using Maven:
bash
mvn spring-boot:run


## 📝 API Documentation

The API provides endpoints for:
- Order creation and management
- User operations
- Product management
- Status updates

## 🤝 Contributing

Feel free to contribute to this project by opening issues or submitting pull requests.

## 📄 License

This project is licensed under the MIT License.
