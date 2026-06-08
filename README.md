# CoatingBazaar API - Spring Boot Backend

## Prerequisites
- Java 21+
- Maven 3.8+

## Run
```bash
mvn spring-boot:run
```

## API Endpoints

Base URL: `http://localhost:8081/coatingbazar`

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/categories` | All categories |
| GET | `/api/categories/{id}` | Single category |
| GET | `/api/products` | All products (flat list) |
| GET | `/api/products/by-category` | Products grouped by category |
| GET | `/api/products/category/{id}` | Products for a category |
| GET | `/api/prices` | All categories with their products |
| POST | `/api/email/send` | Send test email through MailHog SMTP |

## MailHog
- SMTP: `localhost:1025`
- Web UI: `http://localhost:8025`
- App uses these defaults from `application.properties`.

## Example
```
GET http://localhost:8081/coatingbazar/api/prices
GET http://localhost:8081/coatingbazar/api/categories
GET http://localhost:8081/coatingbazar/api/products/category/epoxy-resins

POST http://localhost:8081/coatingbazar/api/email/send
Content-Type: application/json

{
  "to": "abhishek.ramkumar@gmail.com",
  "subject": "order request",
  "quantity": "15Mt",
  "companyname": "Gsharp corporation",
  "pincode": "400080",
  "contactnumber": "9833648779",
  "category": "Polyester Resin",
  "product": "TGIC Polyester Resin",
  "industry": "Automotive",
  "colour": "3AM in Shibuya#225577",
  "chemistry": "Polyester",
  "finish": "Smooth",
  "gloss": "80%"
}
```
