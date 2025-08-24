package com.ios.order.infrastructure.messaging.kafka;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "outbox_events")
@Getter
@Setter
@NoArgsConstructor
public class OutboxEventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String topic;
    @Column(columnDefinition = "TEXT")
    private String payload;
    private String status; // PENDING, SENT, FAILED
    private Instant createdAt;
    private Instant lastTriedAt;

    public static OutboxEventEntity pending(String topic, String payload) {
        var e = new OutboxEventEntity();
        e.setTopic(topic);
        e.setPayload(payload);
        e.setStatus("PENDING");
        e.setCreatedAt(Instant.now());
        return e;
    }
}
