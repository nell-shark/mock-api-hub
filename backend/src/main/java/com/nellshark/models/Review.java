package com.nellshark.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Table(name = "reviews")
@Entity
public class Review {

  @Id
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  @Column(name = "productId", nullable = false, updatable = false)
  private Long productId;

  @Column(name = "userId", nullable = false, updatable = false)
  private Long userId;

  @Column(name = "rating", nullable = false, updatable = false)
  private Long rating;

  @Column(name = "date", nullable = false, updatable = false)
  private LocalDate date;

  @Column(name = "body", nullable = false, updatable = false)
  private String body;

  @Column(name = "helpfulVotes", nullable = false, updatable = false)
  private Long helpfulVotes;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getRating() {
    return rating;
  }

  public void setRating(Long rating) {
    this.rating = rating;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public Long getHelpfulVotes() {
    return helpfulVotes;
  }

  public void setHelpfulVotes(Long helpfulVotes) {
    this.helpfulVotes = helpfulVotes;
  }

  @Override
  public String toString() {
    return "Review{" +
        "id=" + id +
        ", productId=" + productId +
        ", userId=" + userId +
        ", rating=" + rating +
        ", date=" + date +
        ", body='" + body + '\'' +
        ", helpfulVotes=" + helpfulVotes +
        '}';
  }
}
