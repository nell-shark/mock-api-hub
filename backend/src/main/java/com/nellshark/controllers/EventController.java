package com.nellshark.controllers;

import com.nellshark.models.Event;
import com.nellshark.services.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

  private final EventService eventService;

  public EventController(EventService eventService) {
    this.eventService = eventService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Event> getEventById(@PathVariable("id") Long id) {
    Event event = eventService.getEventById(id);
    return ResponseEntity.ok(event);
  }
}
