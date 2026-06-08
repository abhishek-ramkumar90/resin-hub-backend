# CoatingBazaar API - Spring Boot Backend

## Prerequisites
- Java 21+
- Maven 3.8+

## Run locally (Maven)
```bash
mvn spring-boot:run
```

## Run with Docker Compose (recommended)

Starts both the API and MailHog in one command:

```bash
docker compose up --build
```

| Service | URL |
|---------|-----|
| API | `http://localhost:8081/coatingbazar` |
| MailHog Web UI | `http://localhost:8025` |

Stop everything:

```bash
docker compose down
```

### Build & run the API image standalone

```bash
# Build
docker build -t coating-bazaar-api .

# Run (point to an external MailHog or SMTP host via env vars)
docker run -p 8081:8081 \
  coating-bazaar-api
```

### Cloud deployment notes
All configuration can be overridden via environment variables (Spring's relaxed binding maps `SPRING_MAIL_HOST` → `spring.mail.host` etc.).  
Set the following in your cloud platform's environment config:

| Variable | Description | Default |
|----------|-------------|---------|
| `SPRING_MAIL_HOST` | SMTP host | From `application.properties` |
| `SPRING_MAIL_PORT` | SMTP port | From `application.properties` |
| `APP_MAIL_FROM` | Sender address | `no-reply@coatingbazaar.com` |
| `SERVER_PORT` | HTTP port | `8081` |

For cloud compose profile, you can also set:

| Variable | Description | Default |
|----------|-------------|---------|
| `IMAGE_NAME` | Output image name/tag in compose | `coating-bazaar-api:latest` |
| `APP_PORT` | Host port mapped to container `8081` | `8081` |

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
| POST | `/api/email/send` | Send order email through configured SMTP |

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
