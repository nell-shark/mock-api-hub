package com.nellshark.repositories;

import com.nellshark.models.Notification;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationRepository extends AbstractGenericRepository<Notification> {

  public NotificationRepository() {
    super(List.of());
  }
}
