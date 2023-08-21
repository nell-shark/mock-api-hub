package com.nellshark.controllers;

import com.nellshark.models.Event;
import com.nellshark.services.EventService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/events")
public class EventController extends AbstractGenericController<Event> {

  public EventController(EventService service) {
    super(service);
  }
}
