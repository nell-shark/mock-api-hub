package com.nellshark.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Table(name = "books")
@Entity
public class Book {

  @Id
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  @Column(name = "title", nullable = false, updatable = false)
  private String title;

  @Column(name = "author", nullable = false, updatable = false)
  private String author;

  @Column(name = "published", nullable = false, updatable = false)
  private LocalDate published;

  @Column(name = "language", nullable = false, updatable = false)
  private String language;

  @Column(name = "description", nullable = false, updatable = false)
  private String description;

  public Book() {
  }

  public Book(Long id, String title, String author, LocalDate year, String language,
      String description) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.published = published;
    this.language = language;
    this.description = description;
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

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public LocalDate getYear() {
    return published;
  }

  public void setYear(LocalDate published) {
    this.published = published;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "Book{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", author=" + author +
        ", published=" + published +
        ", language='" + language + '\'' +
        ", description='" + description + '\'' +
        '}';
  }
}
