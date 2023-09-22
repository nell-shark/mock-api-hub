package com.nellshark.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Table(name = "todos")
@Entity
public class Todo {

  @Id
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  @Column(name = "todo", nullable = false, updatable = false)
  private String todo;

  @Column(name = "completed", nullable = false, updatable = false)
  private Boolean completed;

  @Column(name = "timestamp", nullable = false, updatable = false)
  private LocalDateTime timestamp;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTodo() {
    return todo;
  }

  public void setTodo(String todo) {
    this.todo = todo;
  }

  public Boolean getCompleted() {
    return completed;
  }

  public void setCompleted(Boolean completed) {
    this.completed = completed;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public String toString() {
    return "Todo{" +
        "id=" + id +
        ", todo='" + todo + '\'' +
        ", completed=" + completed +
        ", timestamp=" + timestamp +
        '}';
  }
}
