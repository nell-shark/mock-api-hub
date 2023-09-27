package com.nellshark.services;

import com.nellshark.exceptions.MessageNotFoundException;
import com.nellshark.models.Message;
import com.nellshark.repositories.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

  private final MessageRepository messageRepository;

  public MessageService(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  public Message getMessageById(Long id) {
    return messageRepository
        .findById(id)
        .orElseThrow(
            () -> new MessageNotFoundException("Message with id %s not found".formatted(id)));
  }
}
