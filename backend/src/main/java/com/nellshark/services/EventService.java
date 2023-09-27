package com.nellshark.services;

import com.nellshark.models.Event;
import com.nellshark.repositories.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class EventService extends AbstractGenericService<Event, Long> {

  public EventService(EventRepository eventRepository) {
    super(eventRepository);
  }
}
