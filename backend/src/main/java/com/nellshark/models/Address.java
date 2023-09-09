package com.nellshark.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Table(name = "addresses")
@Entity
public class Address {

  @Id
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  @Column(name = "address", nullable = false, updatable = false)
  private String address;

  @Column(name = "city", nullable = false, updatable = false)
  private String city;

  @Column(name = "country", nullable = false, updatable = false)
  private String country;

  @Column(name = "zipcode", nullable = false, updatable = false)
  private Integer zipcode;

  public Address() {
  }

  public Address(Long id, String address, String city, String country, Integer zipcode) {
    this.id = id;
    this.address = address;
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

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
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
  public int hashCode() {
    return Objects.hash(id, address, city, country, zipcode);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Address address1 = (Address) o;
    return Objects.equals(id, address1.id)
        && Objects.equals(address, address1.address)
        && Objects.equals(city, address1.city)
        && Objects.equals(country, address1.country)
        && Objects.equals(zipcode, address1.zipcode);
  }

  @Override
  public String toString() {
    return "Address[" +
        "id=" + id + ", " +
        "address=" + address + ", " +
        "city=" + city + ", " +
        "country=" + country + ", " +
        "zipcode=" + zipcode + ']';
  }
}
