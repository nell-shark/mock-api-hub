package com.nellshark.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class Dimension {

  private Double width;
  private Double height;
  private Double length;

  public Double getWidth() {
    return width;
  }

  public void setWidth(Double width) {
    this.width = width;
  }

  public Double getHeight() {
    return height;
  }

  public void setHeight(Double height) {
    this.height = height;
  }

  public Double getLength() {
    return length;
  }

  public void setLength(Double length) {
    this.length = length;
  }

  @Override
  public String toString() {
    return "Dimension{" +
        "weight=" + width +
        ", height=" + height +
        ", depth=" + length +
        '}';
  }
}
