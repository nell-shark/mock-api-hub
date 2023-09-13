package com.nellshark.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Table(name = "courses")
@Entity
public class Course {

  @Id
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  @Column(name = "name", nullable = false, updatable = false)
  private String name;

  @Column(name = "description", nullable = false, updatable = false)
  private String description;

  @Column(name = "price", nullable = false, updatable = false)
  private Double price;

  @Column(name = "instructor", nullable = false, updatable = false)
  private String instructor;

  @Column(name = "startDate", nullable = false, updatable = false)
  private LocalDate startDate;

  @Column(name = "endDate", nullable = false, updatable = false)
  private LocalDate endDate;

  public Course() {
  }

  public Course(Long id,
      String name,
      String description,
      Double price,
      String instructor,
      LocalDate startDate,
      LocalDate endDate) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.instructor = instructor;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getInstructor() {
    return instructor;
  }

  public void setInstructor(String instructor) {
    this.instructor = instructor;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  @Override
  public String toString() {
    return "Course{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", price=" + price +
        ", instructor='" + instructor + '\'' +
        ", startDate=" + startDate +
        ", endDate=" + endDate +
        '}';
  }
}
