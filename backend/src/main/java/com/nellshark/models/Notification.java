package com.nellshark.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Table(name = "notifications")
@Entity
public class Notification {

  @Id
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  @Column(name = "title", nullable = false, updatable = false)
  private String title;

  @Column(name = "message", nullable = false, updatable = false)
  private String message;

  @Column(name = "timestamp", nullable = false, updatable = false)
  private LocalDateTime timestamp;

  @Column(name = "read", nullable = false, updatable = false)
  private Boolean read;

  public Notification() {
  }

  public Notification(Long id,
      String title,
      String message,
      LocalDateTime timestamp,
      Boolean read) {
    this.id = id;
    this.title = title;
    this.message = message;
    this.timestamp = timestamp;
    this.read = read;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public Boolean getRead() {
    return read;
  }

  public void setRead(Boolean read) {
    this.read = read;
  }

  @Override
  public String toString() {
    return "Notification{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", message='" + message + '\'' +
        ", timestamp=" + timestamp +
        ", read=" + read +
        '}';
  }
}
