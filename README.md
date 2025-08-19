# Industrial Ops Suite — Cleaned & Runnable

## IntelliJ (Run locally)
1. Open IntelliJ → **Open** the root `pom.xml` (this is a Maven multi-module).
2. Ensure **JDK 21** is configured (Project SDK).
3. In the Maven tool window run `install` once (or from terminal: `mvn -DskipTests install`).
4. Start services:
   - `order-service`: run `com.industrial.orders.OrderApplication`
   - `api-gateway`: run `com.industrial.apigateway.Application`
   - (others are optional stub services with `/api/ping`)
5. Test:
   - `GET http://localhost:8081/api/orders` (empty list)
   - `POST http://localhost:8081/api/orders` (see sample below)
   - `GET http://localhost:8080/order/api/orders` (via gateway)

**Sample order JSON**
```json
{
  "customerId": "CUST-1",
  "items": [
    {"sku":"SKU-1","qty":2,"unitPrice": 10},
    {"sku":"SKU-2","qty":1,"unitPrice": 5}
  ],
  "total": 25
}
```

## Docker (one command)
```bash
docker compose up -d --build
```
- Gateway: `http://localhost:8080`
- Direct order-service: `http://localhost:8081`

## Notes
- Security is permissive under dev profile in gateway. Replace `SecurityConfig` with JWT rules when auth-server is ready.
- All services use in-memory state (no DB) to keep the demo runnable; swap with JPA/Redis later.
