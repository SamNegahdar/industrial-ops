# Performance Tuning Notes (2025-08-12T18:53:41.850010Z)

## JVM
- Use ZGC, MaxRAMPercentage=75% (already applied in Dockerfiles).
- Enable virtual threads for reactive/blocking mix when applicable.

## Spring Boot
- server.compression.enabled=true
- server.http2.enabled=true
- For WebFlux services (chat-service), prefer SSE/WebSocket for push.

## Kafka Consumers (if used)
- batch.size / fetch.min.bytes tuned for throughput; enable concurrency at container level.
- Dead Letter Topic for poison messages.

## Database
- Proper indexes on search fields.
- Use read-optimized projections; avoid N+1.
- Cache hot reads with Redis (TTL-based).

## Gateway
- Configure rate limiting and circuit breakers per route.
- Propagate correlation-id for tracing.