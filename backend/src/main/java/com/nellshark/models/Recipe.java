package com.nellshark.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "recipes")
@Entity
public class Recipe {

  @Id
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  @Column(name = "name", nullable = false, updatable = false)
  private String name;

  @Column(name = "prepTime", nullable = false, updatable = false)
  private Long prepTime;

  @Column(name = "totalTime", nullable = false, updatable = false)
  private Long totalTime;

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

  public Long getPrepTime() {
    return prepTime;
  }

  public void setPrepTime(Long prepTime) {
    this.prepTime = prepTime;
  }

  public Long getTotalTime() {
    return totalTime;
  }

  public void setTotalTime(Long totalTime) {
    this.totalTime = totalTime;
  }

  @Override
  public String toString() {
    return "Recipe{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", prepTime=" + prepTime +
        ", totalTime=" + totalTime +
        '}';
  }
}
