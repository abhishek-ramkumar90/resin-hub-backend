# CoatingBazaar API - Spring Boot Backend

## Prerequisites
- Java 17+
- Maven 3.8+

## Run
```bash
mvn spring-boot:run
```

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/categories` | All categories |
| GET | `/api/categories/{id}` | Single category |
| GET | `/api/products` | All products (flat list) |
| GET | `/api/products/by-category` | Products grouped by category |
| GET | `/api/products/category/{id}` | Products for a category |
| GET | `/api/prices` | All categories with their products |

## Example
```
GET http://localhost:8081/api/prices
GET http://localhost:8081/api/categories
GET http://localhost:8081/api/products/category/epoxy-resins
```
