package com.nellshark.repositories;

import com.nellshark.models.Message;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class MessageRepository extends AbstractGenericRepository<Message> {

  public MessageRepository() {
    super(List.of(new Message(1L)));
  }
}
