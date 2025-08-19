package com.industrial.notificationservice.domain;

import jakarta.validation.constraints.NotBlank;

public class Domain {
  public enum Channel { EMAIL, SMS }
  public static class Notification {
    @NotBlank private String to;
    @NotBlank private String subject;
    @NotBlank private String body;
    private Channel channel = Channel.EMAIL;

    public String getTo(){ return to; }
    public String getSubject(){ return subject; }
    public String getBody(){ return body; }
    public Channel getChannel(){ return channel; }
    public void setTo(String to){ this.to = to; }
    public void setSubject(String subject){ this.subject = subject; }
    public void setBody(String body){ this.body = body; }
    public void setChannel(Channel channel){ this.channel = channel; }
  }
}
