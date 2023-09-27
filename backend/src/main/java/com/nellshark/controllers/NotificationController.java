package com.nellshark.controllers;

import com.nellshark.models.Notification;
import com.nellshark.services.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

  private final NotificationService notificationService;

  public NotificationController(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Notification> getNotificationById(@PathVariable("id") Long id) {
    Notification notification = notificationService.getNotificationById(id);
    return ResponseEntity.ok(notification);
  }
}
