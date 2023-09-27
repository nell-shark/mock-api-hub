package com.nellshark.controllers;

import com.nellshark.models.Message;
import com.nellshark.services.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

  private final MessageService messageService;

  public MessageController(MessageService messageService) {
    this.messageService = messageService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Message> getMessageById(@PathVariable("id") Long id) {
    Message message = messageService.getMessageById(id);
    return ResponseEntity.ok(message);
  }
}
