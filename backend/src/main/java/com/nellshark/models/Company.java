package com.nellshark.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "companies")
@Entity
public class Company {

  @Id
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  @Column(name = "name", nullable = false, updatable = false)
  private String name;

  @Column(name = "industry", nullable = false, updatable = false)
  private String industry;

  @Column(name = "foundedYear", nullable = false, updatable = false)
  private Long foundedYear;

  @Column(name = "location", nullable = false, updatable = false)
  private String location;

  @Column(name = "website", nullable = false, updatable = false)
  private String website;

  @Column(name = "numberOfEmployees", nullable = false, updatable = false)
  private Long numberOfEmployees;

  @Column(name = "description", nullable = false, updatable = false)
  private String description;

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

  public String getIndustry() {
    return industry;
  }

  public void setIndustry(String industry) {
    this.industry = industry;
  }

  public Long getFoundedYear() {
    return foundedYear;
  }

  public void setFoundedYear(Long foundedYear) {
    this.foundedYear = foundedYear;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public Long getNumberOfEmployees() {
    return numberOfEmployees;
  }

  public void setNumberOfEmployees(Long numberOfEmployees) {
    this.numberOfEmployees = numberOfEmployees;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "Company{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", industry='" + industry + '\'' +
        ", foundedYear=" + foundedYear +
        ", location='" + location + '\'' +
        ", website='" + website + '\'' +
        ", numberOfEmployees=" + numberOfEmployees +
        ", description='" + description + '\'' +
        '}';
  }
}
