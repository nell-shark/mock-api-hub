package com.nellshark.models;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Table(name = "events")
@Entity
public class Event {

  @Id
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  @Column(name = "title", nullable = false, updatable = false)
  private String title;

  @Column(name = "date", nullable = false, updatable = false)
  @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd")
  private LocalDate date;

  @Column(name = "location", nullable = false, updatable = false)
  private String location;

  @Column(name = "description", nullable = false, updatable = false)
  private String description;

  @ElementCollection
  @CollectionTable(name = "event_speakers", joinColumns = @JoinColumn(name = "event_id"))
  private List<Speaker> speakers;

  public Event() {
  }

  public Event(Long id,
      String title,
      LocalDate date,
      String location,
      String description,
      List<Speaker> speakers) {
    this.id = id;
    this.title = title;
    this.date = date;
    this.location = location;
    this.description = description;
    this.speakers = speakers;
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

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Speaker> getSpeakers() {
    return speakers;
  }

  public void setSpeakers(List<Speaker> speakers) {
    this.speakers = speakers;
  }

  @Override
  public String toString() {
    return "Event{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", date=" + date +
        ", location='" + location + '\'' +
        ", description='" + description + '\'' +
        ", speakers=" + speakers +
        '}';
  }
}
