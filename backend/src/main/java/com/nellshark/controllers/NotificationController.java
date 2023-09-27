package com.nellshark.controllers;

import com.nellshark.models.Notification;
import com.nellshark.services.NotificationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController extends AbstractGenericController<Notification, Long> {

  public NotificationController(NotificationService notificationService) {
    super(notificationService);
  }
}
