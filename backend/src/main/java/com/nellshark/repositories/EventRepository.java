package com.nellshark.repositories;

import com.nellshark.models.Event;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class EventRepository extends AbstractGenericRepository<Event> {

  public EventRepository() {
    super(List.of(new Event(1L)));
  }
}
