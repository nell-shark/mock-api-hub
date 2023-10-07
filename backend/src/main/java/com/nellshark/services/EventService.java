package com.nellshark.services;

import com.nellshark.models.Event;
import com.nellshark.models.Speaker;
import com.nellshark.repositories.EventRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EventService extends AbstractGenericService<Event, Long> {

  private static final Logger logger = LoggerFactory.getLogger(EventService.class);


  public EventService(EventRepository eventRepository) {
    super(eventRepository);
  }

  public List<Speaker> getSpeakersByEventId(Long eventId) {
    logger.info("Getting speakers by event id: {}", eventId);
    return getEntityById(eventId).getSpeakers();
  }
}
