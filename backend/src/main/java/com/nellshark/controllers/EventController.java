package com.nellshark.controllers;

import com.nellshark.models.Event;
import com.nellshark.models.Speaker;
import com.nellshark.services.EventService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/events")
public class EventController extends AbstractGenericController<Event, Long> {

  private final EventService eventService;
  public EventController(EventService eventService) {
    super(eventService);
    this.eventService = eventService;
  }

  @GetMapping("/{id}/speakers")
  public ResponseEntity<List<Speaker>> getSpeakersByEventId(@PathVariable("id") Long eventId) {
    List<Speaker> speakers = eventService.getSpeakersByEventId(eventId);
    return ResponseEntity.ok(speakers);
  }
}
