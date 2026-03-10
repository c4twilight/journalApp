# Journal App

Spring Boot backend for a journal application with JWT authentication, MongoDB persistence, Redis caching, email notifications, and optional Kafka-based sentiment messaging.

## Tech Stack

- Java 17
- Spring Boot 2.7
- MongoDB
- Redis
- Spring Security + JWT
- Spring Mail
- Spring Kafka
- Maven

## Quick Start

1. Clone the repo.
2. Copy `src/main/resources/application-example.properties` to `src/main/resources/application.properties`.
3. Update values for your environment.
4. Run:

```bash
./mvnw spring-boot:run
```

On Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

## Configuration

Set these properties in `application.properties`:

- `spring.data.mongodb.uri`
- `spring.data.mongodb.auto-index-creation`
- `spring.redis.host`
- `spring.redis.port`
- `spring.mail.host`
- `spring.mail.port`
- `spring.mail.username`
- `spring.mail.password`
- `spring.mail.properties.mail.smtp.auth`
- `spring.mail.properties.mail.smtp.starttls.enable`
- `weather.api.key`
- `jwt.secret`
- `spring.kafka.bootstrap-servers`
- `spring.kafka.consumer.group-id`

## API Overview

Public:
- `GET /public/health-check`
- `POST /public/signup`
- `POST /public/login`

User (JWT required):
- `GET /user`
- `PUT /user`
- `DELETE /user`

Journal (JWT required):
- `GET /journal`
- `POST /journal`
- `GET /journal/id/{id}`
- `PUT /journal/id/{id}`
- `DELETE /journal/id/{id}`

Admin (ADMIN role):
- `GET /admin/all-users`
- `POST /admin/create-admin-user`
- `GET /admin/clear-app-cache`

## Testing

```bash
./mvnw test
```

The test profile uses short Mongo timeouts and disables scheduled tasks to avoid external infra dependency for basic CI checks.

## CI

GitHub Actions pipeline includes:
- Build + tests (`build` job)
- Optional SonarCloud analysis (`sonar` job), only when these are configured:
  - Repository secret: `SONAR_TOKEN`
  - Repository variable: `SONAR_PROJECT_KEY`
  - Repository variable: `SONAR_ORGANIZATION` (optional if you set it in `pom.xml`)

## Production Notes

- Use strong secrets for `jwt.secret` and mail credentials.
- Run MongoDB/Redis/Kafka as managed services or production-grade clusters.
- Enable HTTPS and secure reverse proxy rules.
- Configure centralized logs and monitoring.
