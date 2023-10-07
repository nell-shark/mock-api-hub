package com.nellshark.repositories;

import com.nellshark.models.Notification;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends CustomGenericRepository<Notification, Long> {

}
