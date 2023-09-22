package com.nellshark.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class Speaker {

  private String name;
  private String topic;
  private String bio;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  @Override
  public String toString() {
    return "Speaker{" +
        "name='" + name + '\'' +
        ", topic='" + topic + '\'' +
        ", bio='" + bio + '\'' +
        '}';
  }
}
