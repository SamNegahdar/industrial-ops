package com.ios.order.infrastructure.messaging.kafka;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Component
public class OutboxPublisherScheduler {

    private final OutboxRepository repo;
    private final OrderEventPublisher publisher;

    public OutboxPublisherScheduler(OutboxRepository repo, OrderEventPublisher publisher) {
        this.repo = repo;
        this.publisher = publisher;
    }

    /**
     * هر ۲ ثانیه یک‌بار، تا ۵۰ رویداد با وضعیت PENDING را منتشر می‌کند.
     * بعد از انتشار موفق → SENT
     * در صورت خطا → FAILED (برای ریتری بعدی)
     */
    @Scheduled(fixedDelayString = "${outbox.publisher.fixed-delay-ms:2000}")
    @Transactional
    public void publishPendingBatch() {
        List<OutboxEventEntity> batch = repo.findTop50ByStatusOrderByCreatedAtAsc("PENDING");
        if (batch.isEmpty()) return;

        for (OutboxEventEntity e : batch) {
            try {
                publisher.publish(e.getTopic(), e.getEventId(), e.getPayload());
                e.setStatus("SENT");
                e.setLastTriedAt(Instant.now());
                repo.save(e);
            } catch (Exception ex) {
                e.setStatus("FAILED");
                e.setLastTriedAt(Instant.now());
                repo.save(e);
//      log.warn("Outbox publish failed for id={}", e.getId(), ex);
            }
        }
    }
}
