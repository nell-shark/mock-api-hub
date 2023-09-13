package com.nellshark.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "addresses")
@Entity
public class Address {

  @Id
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  @Column(name = "street", nullable = false, updatable = false)
  private String street;

  @Column(name = "city", nullable = false, updatable = false)
  private String city;

  @Column(name = "country", nullable = false, updatable = false)
  private String country;

  @Column(name = "zipcode", nullable = false, updatable = false)
  private Integer zipcode;

  public Address() {
  }

  public Address(Long id, String street, String city, String country, Integer zipcode) {
    this.id = id;
    this.street = street;
    this.city = city;
    this.country = country;
    this.zipcode = zipcode;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Integer getZipcode() {
    return zipcode;
  }

  public void setZipcode(Integer zipcode) {
    this.zipcode = zipcode;
  }

  @Override
  public String toString() {
    return "Address[" +
        "id=" + id + ", " +
        "address=" + street + ", " +
        "city=" + city + ", " +
        "country=" + country + ", " +
        "zipcode=" + zipcode + ']';
  }
}
