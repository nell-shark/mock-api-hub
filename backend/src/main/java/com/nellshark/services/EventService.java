package com.nellshark.services;

import com.nellshark.repositories.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class EventService {

  private final EventRepository eventRepository;

  public EventService(EventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }
}
