package com.nellshark.controllers;

import com.nellshark.models.Message;
import com.nellshark.services.MessageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController extends AbstractGenericController<Message, Long> {

  public MessageController(MessageService messageService) {
    super(messageService);
  }
}
