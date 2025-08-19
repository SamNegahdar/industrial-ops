package com.industrial.chatservice.service;

import com.industrial.common.BusinessException;
import com.industrial.common.ErrorCode;
import com.industrial.chatservice.domain.Domain;
import jakarta.validation.Valid;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceLogic {
  private final Map<String, List<Domain.Message>> channels = new ConcurrentHashMap<>();
  private static final Set<String> banned = Set.of("badword"); // naive filter

  public Domain.Message post(@Valid Domain.Message m){
    for (String b : banned) {
      if (m.getText().toLowerCase().contains(b)) {
        throw new BusinessException(ErrorCode.BUSINESS_RULE_VIOLATION, "Profanity not allowed");
      }
    }
    m.setId(UUID.randomUUID());
    channels.computeIfAbsent(m.getChannel(), k -> Collections.synchronizedList(new ArrayList<>())).add(m);
    return m;
  }

  public List<Domain.Message> list(String channel){
    return channels.getOrDefault(channel, List.of());
  }
}
