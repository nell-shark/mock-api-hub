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
  private Long location;

  @Column(name = "website", nullable = false, updatable = false)
  private Long website;

  @Column(name = "description", nullable = false, updatable = false)
  private Long description;

  @Column(name = "numberOfEmployees", nullable = false, updatable = false)
  private Long numberOfEmployees;

  public Company() {
  }

  public Company(Long id,
      String name,
      String industry,
      Long foundedYear,
      Long location,
      Long website,
      Long description,
      Long numberOfEmployees) {
    this.id = id;
    this.name = name;
    this.industry = industry;
    this.foundedYear = foundedYear;
    this.location = location;
    this.website = website;
    this.description = description;
    this.numberOfEmployees = numberOfEmployees;
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

  public Long getLocation() {
    return location;
  }

  public void setLocation(Long location) {
    this.location = location;
  }

  public Long getWebsite() {
    return website;
  }

  public void setWebsite(Long website) {
    this.website = website;
  }

  public Long getDescription() {
    return description;
  }

  public void setDescription(Long description) {
    this.description = description;
  }

  public Long getNumberOfEmployees() {
    return numberOfEmployees;
  }

  public void setNumberOfEmployees(Long numberOfEmployees) {
    this.numberOfEmployees = numberOfEmployees;
  }

  @Override
  public String toString() {
    return "Company{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", industry='" + industry + '\'' +
        ", foundedYear=" + foundedYear +
        ", location=" + location +
        ", website=" + website +
        ", description=" + description +
        ", numberOfEmployees=" + numberOfEmployees +
        '}';
  }
}
