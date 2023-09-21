package com.nellshark.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Table(name = "posts")
@Entity
public class Post {

  @Id
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  @Column(name = "title", nullable = false, updatable = false)
  private String title;

  @Column(name = "content", nullable = false, updatable = false)
  private String content;

  @Column(name = "authorId", nullable = false, updatable = false)
  private Long authorId;

  @Column(name = "timestamp", nullable = false, updatable = false)
  private LocalDateTime timestamp;

  @Column(name = "likes", nullable = false, updatable = false)
  private Long likes;

  public Post() {
  }

  public Post(Long id,
      String title,
      String content,
      Long authorId,
      LocalDateTime timestamp,
      Long likes) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.authorId = authorId;
    this.timestamp = timestamp;
    this.likes = likes;
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

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Long getAuthorId() {
    return authorId;
  }

  public void setAuthorId(Long authorId) {
    this.authorId = authorId;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public Long getLikes() {
    return likes;
  }

  public void setLikes(Long likes) {
    this.likes = likes;
  }

  @Override
  public String toString() {
    return "Post{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", content=" + content +
        ", authorId=" + authorId +
        ", timestamp=" + timestamp +
        ", likes=" + likes +
        '}';
  }
}
