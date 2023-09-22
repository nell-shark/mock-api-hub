package com.nellshark.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class Team {

  private String name;
  private String role;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public String toString() {
    return "Team{" +
        "name='" + name + '\'' +
        ", role='" + role + '\'' +
        '}';
  }
}
