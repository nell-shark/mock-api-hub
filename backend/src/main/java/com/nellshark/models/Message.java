package com.nellshark.models;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Table(name = "messages")
@Entity
public class Message {

  @Id
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  @Column(name = "sender", nullable = false, updatable = false)
  private String sender;

  @Column(name = "receiver", nullable = false, updatable = false)
  private String receiver;

  @Column(name = "timestamp", nullable = false, updatable = false)
  @JsonFormat(shape = STRING)
  private LocalDateTime timestamp;

  @Column(name = "content", nullable = false, updatable = false)
  private String content;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public String getReceiver() {
    return receiver;
  }

  public void setReceiver(String receiver) {
    this.receiver = receiver;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Override
  public String toString() {
    return "Message{" +
        "id=" + id +
        ", sender='" + sender + '\'' +
        ", receiver='" + receiver + '\'' +
        ", timestamp=" + timestamp +
        ", content='" + content + '\'' +
        '}';
  }
}
