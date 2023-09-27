package com.nellshark.services;

import com.nellshark.models.Message;
import com.nellshark.repositories.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService extends AbstractGenericService<Message, Long> {


  public MessageService(MessageRepository messageRepository) {
    super(messageRepository);
  }
}
