# Release 1 — Order Workflow Service (Merge Guide)

This package contains the new `order-workflow-service` module and a dev `docker-compose` for Postgres + Kafka.

## How to run locally
1) `docker compose -f docker-compose.dev.yml up -d`
2) `cd order-workflow-service`
3) `./mvnw spring-boot:run` (or `mvn spring-boot:run`)

## API
- POST `/api/orders`
- PATCH `/api/orders/{id}/status`

## Notes
- Outbox pattern is enabled via `OutboxPublisherScheduler`.
- Security is permissive (permitAll) for now; will be replaced with OAuth2 Resource Server later in Release 1.
