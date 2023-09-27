package com.nellshark.services;

import com.nellshark.exceptions.EventNotFoundException;
import com.nellshark.models.Event;
import com.nellshark.repositories.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class EventService {

  private final EventRepository eventRepository;

  public EventService(EventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }

  public Event getEventById(Long id) {
    return eventRepository
        .findById(id)
        .orElseThrow(() -> new EventNotFoundException("Event with id %s not found".formatted(id)));
  }
}
