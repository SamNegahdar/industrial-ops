package com.industrial.notificationservice.service;

import com.industrial.common.BusinessException;
import com.industrial.common.ErrorCode;
import com.industrial.notificationservice.domain.Domain;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ServiceLogic {
  private final List<Domain.Notification> outbox = new ArrayList<>();
  private static final Pattern EMAIL = Pattern.compile("^[^@\s]+@[^@\s]+\.[^@\s]+$");

  public Domain.Notification send(@Valid Domain.Notification n){
    if (n.getChannel()== Domain.Channel.EMAIL && !EMAIL.matcher(n.getTo()).matches())
      throw new BusinessException(ErrorCode.VALIDATION_ERROR, "Invalid email address");
    // simulate enqueue
    outbox.add(n);
    return n;
  }

  public List<Domain.Notification> queued(){ return outbox; }
}
