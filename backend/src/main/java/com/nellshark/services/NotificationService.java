package com.nellshark.services;

import com.nellshark.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

  private final NotificationRepository notificationRepository;

  public NotificationService(NotificationRepository notificationRepository) {
    this.notificationRepository = notificationRepository;
  }
}
