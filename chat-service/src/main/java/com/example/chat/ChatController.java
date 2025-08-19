package com.example.chat;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import jakarta.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/chat")
@Validated
public class ChatController {

    private final KafkaTemplate<String, ChatMessage> kafkaTemplate;
    private final String topicName;

    public ChatController(KafkaTemplate<String, ChatMessage> kafkaTemplate,
                          @Value("${chat.topic:chat-messages}") String topicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    private final Map<String, Sinks.Many<ChatMessage>> roomSinks = new ConcurrentHashMap<>();

    private Sinks.Many<ChatMessage> sinkOf(String room) {
        return roomSinks.computeIfAbsent(room, k -> Sinks.many().multicast().onBackpressureBuffer());
    }

    @PostMapping("/{room}")
    public void post(@PathVariable("room") String room, @RequestBody @Validated ChatRequest req) {
        ChatMessage msg = new ChatMessage(room, req.getUser(), req.getMessage(), Instant.now().toString());
        kafkaTemplate.send(topicName, room, msg);
    }

    @GetMapping(path = "/{room}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChatMessage> stream(@PathVariable("room") String room) {
        return sinkOf(room).asFlux();
    }

    @KafkaListener(topics = "#{@kafkaConfig.chatTopic().name()}")
    public void onMessage(ChatMessage msg) {
        sinkOf(msg.getRoom()).tryEmitNext(msg);
    }

    @Data
    public static class ChatRequest {
        @NotBlank
        private String user;
        @NotBlank
        private String message;
    }
}
