package com.nellshark.repositories;

import com.nellshark.models.Event;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CustomGenericRepository<Event, Long> {

}
