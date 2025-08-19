package com.industrial.chatservice.domain;

import jakarta.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.UUID;

public class Domain {
  public static class Message {
    private UUID id;
    @NotBlank private String channel;
    @NotBlank private String author;
    @NotBlank private String text;
    private Instant createdAt = Instant.now();

    public UUID getId(){ return id; }
    public String getChannel(){ return channel; }
    public String getAuthor(){ return author; }
    public String getText(){ return text; }
    public Instant getCreatedAt(){ return createdAt; }
    public void setId(UUID id){ this.id = id; }
    public void setChannel(String channel){ this.channel = channel; }
    public void setAuthor(String author){ this.author = author; }
    public void setText(String text){ this.text = text; }
    public void setCreatedAt(Instant createdAt){ this.createdAt = createdAt; }
  }
}
