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

  @Column(name = "countryCode", nullable = false, updatable = false)
  private String countryCode;

  @Column(name = "country", nullable = false, updatable = false)
  private String country;

  @Column(name = "postCode", nullable = false, updatable = false)
  private String postCode;

  public Address() {
  }

  public Address(Long id,
      String street,
      String city,
      String countryCode,
      String country,
      String postCode) {
    this.id = id;
    this.street = street;
    this.city = city;
    this.countryCode = countryCode;
    this.country = country;
    this.postCode = postCode;
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


  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getPostCode() {
    return postCode;
  }

  public void setPostCode(String postCode) {
    this.postCode = postCode;
  }

  @Override
  public String toString() {
    return "Address{" +
        "id=" + id +
        ", street='" + street + '\'' +
        ", city='" + city + '\'' +
        ", countryCode='" + countryCode + '\'' +
        ", country='" + country + '\'' +
        ", postCode='" + postCode + '\'' +
        '}';
  }
}
