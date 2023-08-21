package com.nellshark.services;

import com.nellshark.models.Notification;
import com.nellshark.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationService extends AbstractGenericService<Notification> {

  public NotificationService(NotificationRepository notificationRepository) {
    super(notificationRepository);
  }
}
