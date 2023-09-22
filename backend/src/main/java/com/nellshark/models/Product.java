package com.nellshark.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "products")
@Entity
public class Product {

  @Id
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  @Column(name = "name", nullable = false, updatable = false)
  private String name;

  @Column(name = "description", nullable = false, updatable = false)
  private String description;

  @Column(name = "price", nullable = false, updatable = false)
  private Double price;

  @Column(name = "currency", nullable = false, updatable = false)
  private String currency;

  @Column(name = "manufacturer", nullable = false, updatable = false)
  private String manufacturer;

  @Column(name = "category", nullable = false, updatable = false)
  private String category;

  @Column(name = "availability", nullable = false, updatable = false)
  private Boolean availability;

  @Embedded
  private Dimension dimensions;

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

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public Boolean getAvailability() {
    return availability;
  }

  public void setAvailability(Boolean availability) {
    this.availability = availability;
  }

  public Dimension getDimensions() {
    return dimensions;
  }

  public void setDimensions(Dimension dimensions) {
    this.dimensions = dimensions;
  }

  @Override
  public String toString() {
    return "Product{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", price=" + price +
        ", currency='" + currency + '\'' +
        ", manufacturer='" + manufacturer + '\'' +
        ", category='" + category + '\'' +
        ", availability=" + availability +
        ", dimensions=" + dimensions +
        '}';
  }
}
