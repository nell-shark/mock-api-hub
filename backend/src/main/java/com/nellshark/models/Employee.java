package com.nellshark.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "employees")
@Entity
public class Employee {

  @Id
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  @Column(name = "firstName", nullable = false, updatable = false)
  private String firstName;

  @Column(name = "lastName", nullable = false, updatable = false)
  private String lastName;

  @Column(name = "salary", nullable = false, updatable = false)
  private Long salary;

  @Column(name = "email", nullable = false, updatable = false)
  private String email;

  @Column(name = "department", nullable = false, updatable = false)
  private String department;

  @Column(name = "position", nullable = false, updatable = false)
  private Long position;

  public Employee() {
  }

  public Employee(Long id,
      String firstName,
      String lastName,
      Long salary,
      String email,
      String department,
      Long position) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.salary = salary;
    this.email = email;
    this.department = department;
    this.position = position;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Long getSalary() {
    return salary;
  }

  public void setSalary(Long salary) {
    this.salary = salary;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public Long getPosition() {
    return position;
  }

  public void setPosition(Long position) {
    this.position = position;
  }

  @Override
  public String toString() {
    return "Employee{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", salary=" + salary +
        ", email='" + email + '\'' +
        ", department='" + department + '\'' +
        ", position=" + position +
        '}';
  }
}

