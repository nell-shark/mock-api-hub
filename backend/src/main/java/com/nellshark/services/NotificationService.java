package com.nellshark.services;

import com.nellshark.exceptions.NotificationNotFoundException;
import com.nellshark.models.Notification;
import com.nellshark.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

  private final NotificationRepository notificationRepository;

  public NotificationService(NotificationRepository notificationRepository) {
    this.notificationRepository = notificationRepository;
  }

  public Notification getNotificationById(Long id) {
    return notificationRepository
        .findById(id)
        .orElseThrow(() ->
            new NotificationNotFoundException("Notification with id %s not found".formatted(id)));
  }
}
