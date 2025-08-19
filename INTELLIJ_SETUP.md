# IntelliJ Local Run Guide

## Prerequisites
- JDK 21 (Temurin)
- Maven 3.9+
- Docker & Docker Compose

## Steps
1. Open the root `pom.xml` as a project in IntelliJ.
2. Set Project SDK to JDK 21 (File > Project Structure > Project SDK).
3. For each service module, use the `Application` main class (search by `@SpringBootApplication`).
4. Profiles: use `local` by default. For Docker, use `docker` profile (see `application-docker.yml` if present).
5. To run infra locally, use Docker:
   ```bash
   docker compose up -d zookeeper kafka postgres redis
   ```
6. Then run services from IntelliJ (Shift+Ctrl+F10).

## Build
```bash
./mvnw clean package -DskipTests
```

## Docker (all services)
```bash
docker compose up --build
```
