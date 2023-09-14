package com.nellshark.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "comments")
@Entity
public class Comment {

  @Id
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  @Column(name = "text", nullable = false, updatable = false)
  private String text;

  @Column(name = "postId", nullable = false, updatable = false)
  private Long postId;

  @Column(name = "userId", nullable = false, updatable = false)
  private Long userId;

  public Comment() {
  }

  public Comment(Long id, String text, Long postId, Long userId) {
    this.id = id;
    this.text = text;
    this.postId = postId;
    this.userId = userId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Long getPostId() {
    return postId;
  }

  public void setPostId(Long postId) {
    this.postId = postId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  @Override
  public String toString() {
    return "Comment{" +
        "id=" + id +
        ", text='" + text + '\'' +
        ", postId=" + postId +
        ", userId=" + userId +
        '}';
  }
}
