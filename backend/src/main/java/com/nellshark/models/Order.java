package com.nellshark.models;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "orders")
@Entity
public class Order {

  @Id
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  @Column(name = "timestamp", nullable = false, updatable = false)
  @JsonFormat(shape = STRING)
  private LocalDateTime timestamp;

  @Column(name = "status", nullable = false, updatable = false)
  private String status;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
  private List<Item> items;

  public Order() {
  }

  public Order(Long id, LocalDateTime timestamp, String status, List<Item> items) {
    this.id = id;
    this.timestamp = timestamp;
    this.status = status;
    this.items = items;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }

  @Override
  public String toString() {
    return "Order{" +
        "id=" + id +
        ", timestamp=" + timestamp +
        ", status='" + status + '\'' +
        ", items=" + items +
        '}';
  }
}
